package com.example.lab3databases;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView productId;
    EditText productName, productPrice;
    Button addBtn, findBtn, deleteBtn;
    ListView productListView;

    ArrayList<String> productList;
    ArrayAdapter adapter;
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = new ArrayList<>();

        // info layout
        productId = findViewById(R.id.productId);
        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);

        //buttons
        addBtn = findViewById(R.id.addBtn);
        findBtn = findViewById(R.id.findBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        // listview
        productListView = findViewById(R.id.productListView);

        // db handler
        dbHandler = new MyDBHandler(this);

        // button listeners
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = productName.getText().toString();
                double price = Double.parseDouble(productPrice.getText().toString());
                Product product = new Product(name, price);
                dbHandler.addProduct(product);
                productName.setText("");
                productPrice.setText("");
                viewProducts();
            }
        });

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameToFind = productName.getText().toString();
                String priceString = productPrice.getText().toString();
                double priceToFind = -1; // Default to an invalid price

                // Check if user entered anything at all
                if (nameToFind.trim().isEmpty() && priceString.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a name or price to find.", Toast.LENGTH_SHORT).show();
                    viewProducts(); // If search is cleared, show all products
                    return;
                }

                // Safely parse the price if it exists
                if (!priceString.trim().isEmpty()) {
                    try {
                        priceToFind = Double.parseDouble(priceString);
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Invalid price format.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                // Use the new findProduct method which returns a Cursor
                Cursor cursor = dbHandler.findProduct(nameToFind, priceToFind);

                // Update the ListView with the results from the cursor
                updateProductList(cursor);

                Toast.makeText(MainActivity.this, cursor.getCount() + " products found.", Toast.LENGTH_SHORT).show();

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameToDelete = productName.getText().toString();
                if (nameToDelete.trim().isEmpty()){
                    Toast.makeText(MainActivity.this, "Please enter a name to delete.", Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean wasDeleted = dbHandler.deleteProduct(nameToDelete);

                if (wasDeleted) {
                    // Clear fields and refresh the list
                    productId.setText("ID");
                    productName.setText("");
                    productPrice.setText("");
                    Toast.makeText(MainActivity.this, "Product Deleted.", Toast.LENGTH_SHORT).show();
                    viewProducts(); // Refresh the list to show the change
                } else {
                    // Product was not found to be deleted
                    Toast.makeText(MainActivity.this, "Product not found.", Toast.LENGTH_SHORT).show();
                }
            }
        });


        viewProducts();
    }

    private void viewProducts() {
        // This method now just gets all data and passes it to the new update method
        Cursor cursor = dbHandler.getData();
        updateProductList(cursor);
    }

    /**
     * Clears the product list and repopulates it with data from a given Cursor.
     * @param cursor The Cursor containing the product data to display.
     */
    private void updateProductList(Cursor cursor) {
        productList.clear(); // Always clear the list first

        if (cursor.getCount() == 0) {
            // No products found, list will be empty.
        } else {
            while (cursor.moveToNext()) {
                // Get data using the new public getter methods
                String name = cursor.getString(cursor.getColumnIndexOrThrow(dbHandler.getColumnProductName()));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(dbHandler.getColumnProductPrice()));
                productList.add(name + " ($" + price + ")");
            }
        }

        // Make sure the adapter is initialized before use
        if (adapter == null) {
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productList);
            productListView.setAdapter(adapter);
        } else {
            // If adapter already exists, just notify it that the data has changed
            adapter.notifyDataSetChanged();
        }

        cursor.close(); // IMPORTANT: Always close the cursor when you're done with it.
    }

//    public void newProduct(View view){
//        double price = Double.parseDouble(productPrice.getText().toString());
//        Product product = new Product(productName.getText().toString(), price);
//        dbHandler.addProduct(product);
//        productName.setText("");
//        productPrice.setText("");
//    }
}