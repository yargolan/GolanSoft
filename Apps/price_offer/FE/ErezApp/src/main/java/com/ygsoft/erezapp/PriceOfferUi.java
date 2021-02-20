package com.ygsoft.erezapp;


import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.ygsoft.erezapp.data.*;


public class PriceOfferUi {

    // Helper classes
    Lines lines = new Lines();
    ShoppingCart shoppingCart = new ShoppingCart();


    // Global variables
    int priceOfferNumber = 123456;
    final Items items = new Items();
    List<Item> itemsList;
    List<String> categoriesList;


    // UI parts
    JTextField tfItemPrice;
    JFrame fPriceOfferNew = new JFrame();
    final static int textFieldWidth = 180;
    final static int dropDownWidth  = 200;
    JComboBox<String> ddItemMake, ddItemModel, ddItemAmount, ddItemCategory;

    JTextField tfOfferId       = new JTextField();
    JTextField tfCustomerName  = new JTextField();
    JTextField tfCustomerEmail = new JTextField();
    JTextField tfCustomerPhone = new JTextField();

    JButton btnReset          = new JButton("Reset form");
    JButton btnAddToCart      = new JButton("Add to cart");
    JButton btnSelectMake     = new JButton("Select");
    JButton btnSelectModel    = new JButton("Select");
    JButton btnGenerateOffer  = new JButton("Generate offer");
    JButton btnSelectCategory = new JButton("Select");




    public PriceOfferUi() {


        // +-----------------------------------
        // | Set lists values
        // +-----------------------------------
        String line = lines.generateLine(80);
        initValues();


        // +-----------------------------------
        // | Set the frame's generic part
        // +-----------------------------------
        fPriceOfferNew.setLayout(null);
        fPriceOfferNew.setTitle("New price offer");
        fPriceOfferNew.setBounds(500, 200, 700, 550);
        fPriceOfferNew.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);



        // +-----------------------------------
        // | Set the customer's part
        // +-----------------------------------
        JLabel lSeparator1    = new JLabel(line);
        JLabel lSeparator2    = new JLabel(line);
        JLabel lPriceOfferId  = new JLabel("Offer ID:");
        JLabel lCustomerName  = new JLabel("Customer name:");
        JLabel lCustomerEmail = new JLabel("Customer email:");
        JLabel lCustomerPhone = new JLabel("Customer phone:");

        tfOfferId.setEditable(false);
        tfOfferId.setText(String.valueOf(priceOfferNumber));


        // Place them on the frame
        lPriceOfferId.setBounds (20,  20, 100, 20);
        lCustomerName.setBounds (20,  50, 120, 20);
        lCustomerEmail.setBounds(20,  80, 120, 20);
        lCustomerPhone.setBounds(350, 80, 120, 20);
        lSeparator1.setBounds   (10,  130,650, 20);


        tfOfferId.setBounds      (140, 20, 80,  20);
        tfCustomerName.setBounds (140, 50, textFieldWidth, 20);
        tfCustomerEmail.setBounds(140, 80, textFieldWidth, 20);
        tfCustomerPhone.setBounds(470, 80, textFieldWidth, 20);



        // +-----------------------------------
        // | Set the purchase part
        // +-----------------------------------
        JLabel lItemMake      = new JLabel("Make name");
        JLabel lItemModel     = new JLabel("Model name");
        JLabel lItemPrice     = new JLabel("Item price");
        JLabel lItemAmount    = new JLabel("Item amount");
        JLabel lItemCategory  = new JLabel("Category");

        tfItemPrice    = new JTextField();
        ddItemMake     = new JComboBox<>();
        ddItemModel    = new JComboBox<>();
        ddItemAmount   = new JComboBox<>();
        ddItemCategory = new JComboBox<>();

        // Disable editing the price field
        tfItemPrice.setEnabled(false);


        // Place them on the frame
        lItemCategory.setBounds(20, 170, 100, 20);
        lItemMake.setBounds    (20, 210, 100, 20);
        lItemModel.setBounds   (20, 250, 100, 20);
        lItemPrice.setBounds   (220,290, 100, 20);
        lItemAmount.setBounds  (20, 290, 100, 20);

        ddItemMake.setBounds     (110, 210, dropDownWidth, 20);
        ddItemModel.setBounds    (110, 250, dropDownWidth, 20);
        ddItemAmount.setBounds   (110, 290, 80, 20);
        ddItemCategory.setBounds (110, 170, dropDownWidth, 20);

        tfItemPrice.setBounds (320, 290, 70, 20);

        btnReset.setBounds         (470, 170, 100, 20);
        btnSelectMake.setBounds    (320, 210, 100, 20);
        btnSelectModel.setBounds   (320, 250, 100, 20);
        btnSelectCategory.setBounds(320, 170, 100, 20);

        lSeparator2.setBounds (10,  390,650, 20);
        btnAddToCart.setBounds(20,  340, 200, 40);

        btnGenerateOffer.setBounds(20,430, 200, 40);




        // +-----------------------------------
        // | Insert drop-down initial values
        // +-----------------------------------
        for (String item : categoriesList) {
            ddItemCategory.addItem(item);
        }

        for (int i = 1; i <= 6; i++) {
            ddItemAmount.addItem(Integer.toString(i));
        }


        // +-----------------------------------
        // | Disable UI components
        // +-----------------------------------
        resetForm();



        // +-----------------------------------
        // | Add the UI parts to the frame
        // +-----------------------------------
        fPriceOfferNew.add(lSeparator1);
        fPriceOfferNew.add(lSeparator2);

        fPriceOfferNew.add(lPriceOfferId);
        fPriceOfferNew.add(lCustomerName);
        fPriceOfferNew.add(lCustomerEmail);
        fPriceOfferNew.add(lCustomerPhone);

        fPriceOfferNew.add(tfOfferId);
        fPriceOfferNew.add(tfCustomerName);
        fPriceOfferNew.add(tfCustomerEmail);
        fPriceOfferNew.add(tfCustomerPhone);

        fPriceOfferNew.add(lItemMake);
        fPriceOfferNew.add(lItemModel);
        fPriceOfferNew.add(lItemPrice);
        fPriceOfferNew.add(lItemAmount);
        fPriceOfferNew.add(lItemCategory);

        fPriceOfferNew.add(ddItemMake);
        fPriceOfferNew.add(ddItemModel);
        fPriceOfferNew.add(ddItemAmount);
        fPriceOfferNew.add(ddItemCategory);

        fPriceOfferNew.add(tfItemPrice);

        fPriceOfferNew.add(btnReset);
        fPriceOfferNew.add(btnAddToCart);
        fPriceOfferNew.add(btnSelectMake);
        fPriceOfferNew.add(btnSelectModel);
        fPriceOfferNew.add(btnGenerateOffer);
        fPriceOfferNew.add(btnSelectCategory);



        // +-----------------------------------
        // | Add buttons action listeners
        // +-----------------------------------
        btnReset.addActionListener(e->resetForm());



        btnSelectCategory.addActionListener(e-> {

            // Disable the category drop-down and 'select' button
            ddItemCategory.setEnabled(false);
            btnSelectCategory.setEnabled(false);

            // Enable the make category drop-down and 'select' button
            ddItemMake.setEnabled(true);
            btnSelectMake.setEnabled(true);

            // Get the list of makes for that category.
            String category = (String)ddItemCategory.getSelectedItem();
            List<Make> makeList = items.getMakesFromCategory(category);

            // Insert the list to the drop-down
            for (Make make : makeList) {
                ddItemMake.addItem(make.getName());
            }

            // Enable the 'select make' button.
            btnSelectMake.setEnabled(true);
        });


        btnSelectMake.addActionListener(e->{

            // Disable the make drop-down and 'select' button
            ddItemMake.setEnabled(false);
            btnSelectMake.setEnabled(false);

            // Enable the model drop-down and 'select model' button.
            ddItemModel.setEnabled(true);
            btnSelectModel.setEnabled(true);

            // Get the list of models per that make.
            List<Model> models = items.getModelFromMake((String)ddItemMake.getSelectedItem());
            for (Model model : models) {
                ddItemModel.addItem(model.getName());
            }
        });



        btnSelectModel.addActionListener(e->{

            // Disable the model drop-down and 'select' button
            ddItemModel.setEnabled(false);
            btnSelectModel.setEnabled(false);

            String make  = (String)ddItemMake.getSelectedItem();
            String model = (String)ddItemModel.getSelectedItem();

            int price = items.getPrice(make, model);

            tfItemPrice.setText(String.valueOf(price));

            // Enable the amount drop-down
            ddItemAmount.setEnabled(true);

            // Enable the 'add to cart' button
            btnAddToCart.setEnabled(true);
        });


        btnAddToCart.addActionListener(e->{

            // Read the form.
            String itemAmount = (String)ddItemAmount.getSelectedItem();
            assert itemAmount != null;

            Item item = new Item(
                    null,
                    new Category((String)ddItemCategory.getSelectedItem()),
                    new Make((String)ddItemMake.getSelectedItem()),
                    new Model((String)ddItemModel.getSelectedItem()),
                    Integer.parseInt(tfItemPrice.getText()),
                    Integer.parseInt(itemAmount)
            );

            // get the ID
            String theId = items.getIdFromItemProperties(item);
            item.setId(theId);


            // Add the item to the shopping cart.
            shoppingCart.add(item);


            // reset the form
            resetForm();
        });


        btnGenerateOffer.addActionListener(e->generate_price_offer(shoppingCart));
    }



    private void generate_price_offer(ShoppingCart shoppingCart) {

        String currentDate = DateAndTime.getCurrentTime(1);
        if (currentDate == null) {
            currentDate = "N/A";
        }

        if (shoppingCart.getItemsInCart().size() == 0) {
            String message = "There are no items in the shopping cart yet...";
            Messages.show_message(Messages.ERROR, message);
        }
        else {

            // Read the HTML template file.
            List<String> template = new ArrayList<>();
            File templateFile = new File("PriceOfferTemplate.html");

            try {
                BufferedReader br = new BufferedReader(new FileReader(templateFile));

                String currentLine;
                while ((currentLine = br.readLine()) != null) {
                    template.add(currentLine);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            // Insert the items into the offer.
            List<String> output = new ArrayList<>();
            for (String currentLine : template) {
                if (currentLine.equals("<!--__HERE__-->")) {

                    // For each item, add it to the list.
                    int total   = 0;
                    int counter = 0;

                    for (Item currentItem : shoppingCart.getItemsInCart()) {

                        counter++;

                        int    amount       = currentItem.getAmount();
                        String itemId       = currentItem.getId();
                        String itemMake     = currentItem.getMake().getName();
                        String itemModel    = currentItem.getModel().getName();
                        String itemCategory = currentItem.getCategory().getName();

                        int itemPricePerItem = currentItem.getPrice();
                        int itemPriceTotal   = itemPricePerItem * amount;
                        total += itemPriceTotal;

                        output.add("<tr>");
                        output.add(String.format("<td>%s</td>", counter));
                        output.add(String.format("<td>%s</td>", itemId));
                        output.add(String.format("<td>%s</td>", itemCategory));
                        output.add(String.format("<td>%s</td>", itemMake));
                        output.add(String.format("<td>%s</td>", itemModel));
                        output.add(String.format("<td>%s</td>", itemPricePerItem));
                        output.add(String.format("<td>%s</td>", amount));
                        output.add(String.format("<td>%s</td>", itemPriceTotal));
                        output.add("</tr>");
                    }

                    // Add the total sum.
                    output.add("<tr>");
                    output.add("<th colspan=\"7\">Total</th>");
                    output.add(String.format("<th>%s</th>", total));
                    output.add("</tr>");
                }
                else {
                    currentLine = currentLine.replace("__ID__",    tfOfferId.getText());
                    currentLine = currentLine.replace("__DATE__",  currentDate);
                    currentLine = currentLine.replace("__NAME__",  tfCustomerName.getText());
                    currentLine = currentLine.replace("__EMAIL__", tfCustomerEmail.getText());
                    currentLine = currentLine.replace("__PHONE__", tfCustomerPhone.getText());
                    output.add(currentLine);
                }
            }


            // Write it to a file
            try {
                String filename = String.format("price_off_%s.html", priceOfferNumber);

                FileWriter myWriter = new FileWriter(filename);

                for (String s : output) {
                    myWriter.write(s + "\n");
                }
                myWriter.close();
            }
            catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            // Show a confirmation message.
            Messages.show_message(Messages.INFO, "The price offer was successfully created.");
        }
    }



    private void resetForm() {

        ddItemMake.setEnabled(false);
        ddItemMake.removeAllItems();

        ddItemModel.setEnabled(false);
        ddItemModel.removeAllItems();

        ddItemCategory.setEnabled(true);
        ddItemAmount.setEnabled(false);

        btnAddToCart.setEnabled(false);
        btnSelectMake.setEnabled(false);
        btnSelectModel.setEnabled(false);
        btnSelectCategory.setEnabled(true);

        tfItemPrice.setText("");

    }



    private List<String> get_categories() {

        List<String> list = new ArrayList<>();

        for (Item item : itemsList) {
            String c = item.getCategory().getName();
            if ( ! list.contains(c)) {
                list.add(c);
            }
        }

        Collections.sort(list);

        return list;
    }



    private void initValues() {

        // Get the items for sale.
        itemsList = items.getItemsList();


        // Get the categories list.
        categoriesList = get_categories();
    }



    public static void main(String[] args) {
        PriceOfferUi priceOfferUi = new PriceOfferUi();
        priceOfferUi.show();

    }



    public void show() {
        fPriceOfferNew.setVisible(true);
    }
}
