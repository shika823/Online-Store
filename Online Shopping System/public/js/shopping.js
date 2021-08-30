"use strict";
class SaleItem {

    constructor(product, quantity) {
        // only set the fields if we have a valid product
        if (product) {
            this.product = product;
            this.quantityPurchased = quantity;
            this.salePrice = product.listPrice;

        }
    }

    getItemTotal() {
        return this.salePrice * this.quantityPurchased;
    }

}

class ShoppingCart {

    constructor() {
        this.items = new Array();
    }

    reconstruct(sessionData) {
        for (let item of sessionData.items) {
            this.addItem(Object.assign(new SaleItem(), item));
        }
    }

    getItems() {
        return this.items;
    }

    addItem(item) {
        this.items.push(item);
    }

    setCustomer(customer) {
        this.customer = customer;
    }

    getTotal() {
        let total = 0;
        for (let item of this.items) {
            total += item.getItemTotal();
        }
        return total;
    }

}

var module = angular.module('ShoppingApp', ['ngResource', 'ngStorage']);


module.factory('productAPI', function ($resource) {
    return $resource('/api/products/:id');
});




module.controller('ProductController', function (productAPI, categoryAPI) {
    this.products = productAPI.query();
    this.categories = categoryAPI.query();
    this.selectCategory = function (selectedCat) {
        this.products = categoryAPI.query({"category": selectedCat});
        this.getProducts = function (allCat) {
            this.products = productAPI.query("product");


        };
    };

});
module.factory('cart', function ($sessionStorage) {
    let cart = new ShoppingCart();

    // is the cart in the session storage?
    if ($sessionStorage.cart) {

        // reconstruct the cart from the session data
        cart.reconstruct($sessionStorage.cart);
    }

    return cart;
});

module.factory('categoryAPI', function ($resource) {
    return $resource('/api/categories/:category');
});


module.factory('registerAPI', function ($resource) {
    return $resource('/api/register');
});

module.factory('signInAPI', function ($resource) {
    return $resource('/api/customers/:username');
});

module.factory('saleAPI', function ($resource) {
    return $resource('/api/sales');
});


module.controller('CustomerController', function (registerAPI, $window, signInAPI, $sessionStorage) {
    this.signInMessage = "Please sign in to continue.";
    this.checkSignIn = function () {
        // has the customer been added to the session?
        if ($sessionStorage.customer) {
            this.signedIn = true;
            this.welcome = "Welcome " + $sessionStorage.customer.firstName;
        } else {
            this.signedIn = false;
        }
    };
    this.registerCustomer = function (customer) {
        registerAPI.save(null, customer,
                // success callback
                        function () {
                            $window.location = 'signin.html';
                        },
                        // error callback
                                function (error) {
                                    console.log(error);
                                }
                        );
                    };
            let ctrl = this;
            this.signIn = function (username, password) {

                // get customer from web service
                signInAPI.get({'username': username},
                        // success callback
                                function (customer) {
                                    // also store the retrieved customer
                                    $sessionStorage.customer = customer;
                                    // redirect to home
                                    $window.location = '.';
                                },
                                // fail callback
                                        function () {
                                            ctrl.signInMessage = 'Sign in failed. Please try again.';
                                        }
                                );
                            };

                    this.signOut = function () {
                        delete $sessionStorage.customer;
                        $window.location = 'index.html';
                    };
                });


        module.controller('ShoppingCartController', function (cart, $window, $sessionStorage, productAPI, saleAPI) {
            this.items = cart.getItems();
            this.total = cart.getTotal();
            this.selectedProduct = $sessionStorage.product;
            this.buyProduct = function (product) {
                productAPI.get({'product': product}),
                        $sessionStorage.product = product;
                $window.location = 'buy.html';
            };
            this.addCart = function (amount) {

                let product = $sessionStorage.product;
                let item = new SaleItem(product, amount);
                cart.addItem(item);
                $sessionStorage.cart = cart;
                delete $sessionStorage.product;
                $window.location = 'products.html';

            };
            this.checkout = function () {
                if ($sessionStorage.cart) {
                    cart.setCustomer($sessionStorage.customer);
                    saleAPI.save(cart);
                    //delete $sessionStorage.customer;
                    delete $sessionStorage.cart;
                    $window.location = 'confirmation.html';
                } else {
                    alert("No product in cart");
                }

            };
        });


