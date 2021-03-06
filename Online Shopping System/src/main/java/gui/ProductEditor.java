/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DAOException;
import dao.ProductCollectionsDAO;
import dao.ProductDAO;
import dao.ProductJdbcDAO;
import domain.Product;
import helpers.SimpleListModel;
import java.awt.Window;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Locale.Category;
import javax.swing.JOptionPane;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.exception.ConstraintsViolatedException;

/**
 *
 * @author shika823
 */
public class ProductEditor extends javax.swing.JDialog {

    public static ProductDAO dao;
    SimpleListModel categoryModel = new SimpleListModel();

    private Product product = new Product();

    /**
     * Creates new form ProductEditor
     */
    public ProductEditor(Window parent, boolean modal, ProductDAO daoIn) {
        super(parent);
        super.setModal(modal);
        initComponents();

        categoryComboBox.setEditable(true);
        dao = daoIn;
        Collection<String> category = dao.getCategories();
        categoryModel.updateItems(category);
        categoryComboBox.setModel(categoryModel);
    }

    public ProductEditor(Window parent, boolean modal, Product productToEdit, ProductDAO dao) {
        this(parent, modal, dao);

        this.product = productToEdit;

        TxtId.setText(String.valueOf(productToEdit.getProductID()));
        TxtName.setText(productToEdit.getName());
        categoryComboBox.setSelectedItem(productToEdit.getCategory());

        TxtId.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      ProductIDLabel = new javax.swing.JLabel();
      TxtId = new javax.swing.JTextField();
      NameLabel = new javax.swing.JLabel();
      TxtName = new javax.swing.JTextField();
      DescriptionLabel = new javax.swing.JLabel();
      jScrollPane2 = new javax.swing.JScrollPane();
      TxtDescription = new javax.swing.JTextField();
      CategoryLabel = new javax.swing.JLabel();
      PriceLabel = new javax.swing.JLabel();
      TxtPrice = new javax.swing.JTextField();
      QuantityInStockLabel = new javax.swing.JLabel();
      TxtQuantityInStock = new javax.swing.JTextField();
      jButtonSave = new javax.swing.JButton();
      jButtonCancel = new javax.swing.JButton();
      categoryComboBox = new javax.swing.JComboBox<>();

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

      ProductIDLabel.setText("ProductID:");
      ProductIDLabel.setName("ProductIDLabel"); // NOI18N

      TxtId.setName("TxtId"); // NOI18N
      TxtId.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            TxtIdActionPerformed(evt);
         }
      });

      NameLabel.setText("Name:");
      NameLabel.setName("NameLabel"); // NOI18N

      TxtName.setName("TxtName"); // NOI18N

      DescriptionLabel.setText("Description:");
      DescriptionLabel.setName("DescriptionLabel"); // NOI18N

      jScrollPane2.setName("jScrollPane2"); // NOI18N

      TxtDescription.setName("TxtDescription"); // NOI18N
      TxtDescription.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            TxtDescriptionActionPerformed(evt);
         }
      });
      jScrollPane2.setViewportView(TxtDescription);

      CategoryLabel.setText("Category:");
      CategoryLabel.setName("CategoryLabel"); // NOI18N

      PriceLabel.setText("Price:");
      PriceLabel.setName("PriceLabel"); // NOI18N

      TxtPrice.setName("TxtPrice"); // NOI18N

      QuantityInStockLabel.setText("Quantity in Stock:");
      QuantityInStockLabel.setName("QuantityInStockLabel"); // NOI18N

      TxtQuantityInStock.setName("TxtQuantityInStock"); // NOI18N
      TxtQuantityInStock.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            TxtQuantityInStockActionPerformed(evt);
         }
      });

      jButtonSave.setText("Save");
      jButtonSave.setName("jButtonSave"); // NOI18N
      jButtonSave.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonSaveActionPerformed(evt);
         }
      });

      jButtonCancel.setText("Cancel");
      jButtonCancel.setName("jButtonCancel"); // NOI18N
      jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButtonCancelActionPerformed(evt);
         }
      });

      categoryComboBox.setEditable(true);
      categoryComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
      categoryComboBox.setName("categoryComboBox"); // NOI18N
      categoryComboBox.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            categoryComboBoxActionPerformed(evt);
         }
      });

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(NameLabel, javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(QuantityInStockLabel, javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(DescriptionLabel, javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(CategoryLabel, javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(PriceLabel, javax.swing.GroupLayout.Alignment.TRAILING)
               .addComponent(ProductIDLabel, javax.swing.GroupLayout.Alignment.TRAILING))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jScrollPane2)
               .addComponent(TxtName)
               .addComponent(TxtId)
               .addComponent(TxtPrice)
               .addComponent(TxtQuantityInStock)
               .addGroup(layout.createSequentialGroup()
                  .addGap(25, 25, 25)
                  .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGap(0, 98, Short.MAX_VALUE))
               .addComponent(categoryComboBox, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(TxtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(ProductIDLabel))
            .addGap(18, 18, 18)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(NameLabel)
               .addComponent(TxtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addGap(19, 19, 19)
                  .addComponent(DescriptionLabel))
               .addGroup(layout.createSequentialGroup()
                  .addGap(18, 18, 18)
                  .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
            .addGap(36, 36, 36)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(CategoryLabel)
               .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(35, 35, 35)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(PriceLabel)
               .addComponent(TxtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(31, 31, 31)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(QuantityInStockLabel)
               .addComponent(TxtQuantityInStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGap(14, 14, 14)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
               .addComponent(jButtonSave)
               .addComponent(jButtonCancel))
            .addGap(30, 30, 30))
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

   private void TxtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtIdActionPerformed
       // TODO add your handling code here:
   }//GEN-LAST:event_TxtIdActionPerformed

   private void TxtDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtDescriptionActionPerformed
       // TODO add your handling code here:
   }//GEN-LAST:event_TxtDescriptionActionPerformed

   private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
       try {
           String productID;
           productID = TxtId.getText();

           String name;
           name = TxtName.getText();

           String description;
           description = TxtDescription.getText();

           String category;
           category = (String) categoryComboBox.getSelectedItem();

           BigDecimal price;
           price = new BigDecimal(TxtPrice.getText());

           BigDecimal quantityInStock;
           quantityInStock = new BigDecimal(TxtQuantityInStock.getText());

           System.out.println(productID + " " + name + " " + description + " " + category + " " + price + " " + quantityInStock);

           Product product = new Product();

           product.setProductID(productID);
           product.setName(name);
           product.setDescription(description);
           product.setCategory(category);
           product.setListPrice(price);
           product.setQuantityInStock(quantityInStock);

           System.out.println(product);
           //dao.saveProduct(product);
           new Validator().assertValid(product);
           dao.saveProduct(product);

           dispose();
       } catch (NumberFormatException ex) {
           JOptionPane.showMessageDialog(this,
                   "You have entered a price or quantity that is not a valid number.",
                   "Input Error", JOptionPane.ERROR_MESSAGE);
       } catch (ConstraintsViolatedException ex) {

           ConstraintViolation[] violations = ex.getConstraintViolations();

           String msg = "Please fix the following input problems:";
           for (ConstraintViolation cv : violations) {
               msg += "\n  - " + cv.getMessage();
           }

           // display the message to the user
           JOptionPane.showMessageDialog(this, msg, "Input Error", JOptionPane.ERROR_MESSAGE);

       } catch (DAOException ex) {
           String msg = ex.getMessage();
           JOptionPane.showMessageDialog(this, msg, "Input Error", JOptionPane.ERROR_MESSAGE);
       }

   }//GEN-LAST:event_jButtonSaveActionPerformed

    private void TxtQuantityInStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtQuantityInStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtQuantityInStockActionPerformed

    private void categoryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryComboBoxActionPerformed

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
        dispose();
    }//GEN-LAST:event_jButtonCancelActionPerformed

    /**
     * @param args the command line arguments
     */
    /*
	public static void main(String args[]) {


		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				ProductEditor dialog = new ProductEditor(new javax.swing.JFrame(), true, dao);
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}
     */
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JLabel CategoryLabel;
   private javax.swing.JLabel DescriptionLabel;
   private javax.swing.JLabel NameLabel;
   private javax.swing.JLabel PriceLabel;
   private javax.swing.JLabel ProductIDLabel;
   private javax.swing.JLabel QuantityInStockLabel;
   private javax.swing.JTextField TxtDescription;
   private javax.swing.JTextField TxtId;
   private javax.swing.JTextField TxtName;
   private javax.swing.JTextField TxtPrice;
   private javax.swing.JTextField TxtQuantityInStock;
   private javax.swing.JComboBox<String> categoryComboBox;
   private javax.swing.JButton jButtonCancel;
   private javax.swing.JButton jButtonSave;
   private javax.swing.JScrollPane jScrollPane2;
   // End of variables declaration//GEN-END:variables
}
