package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import domain.Product;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale.Category;
import static java.util.Locale.filter;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author ksts
 */
public class ProductCollectionsDAO implements ProductDAO {

    private static Collection<Product> products = new HashSet<>();
    private static Collection<String> categories = new HashSet<String>();
    private static Map<String, Product> ids = new HashMap<>();
    public static Multimap<String, Product> filterCategory = HashMultimap.create();

    @Override
    public void saveProduct(Product productToAdd) {
        products.add(productToAdd);
        //productToAdd.getCategory();
        categories.add(productToAdd.getCategory());
        ids.put(productToAdd.getProductID(), (Product) productToAdd);
        filterCategory.put(productToAdd.getCategory(), productToAdd);

    }

    @Override
    public void removeProduct(Product productToAdd) {
        products.remove(productToAdd);
        ids.remove(productToAdd.getProductID());
    }

    @Override
    public Collection<String> getCategories() {

        return categories;
    }

    @Override
    public Collection<Product> getProducts() {
        return products;

    }

    @Override
    public Product searchById(String productId) {
        boolean doesExist = ids.containsKey(productId);
        if (doesExist == true) {
            Product product = ids.get(productId);
            return product;
        } else {
            return null;
        }
    }

    @Override
    public Collection<Product> filterByCategory(String category) {

        //products = filter.get(category);   
        return filterCategory.get(category);

    }
}
