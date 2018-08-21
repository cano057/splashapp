package com.example.manu.splashapp.client;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.ecommerce.model.Product;
import com.example.manu.ecommerce.R;
import com.example.manu.splashapp.mainLayout.AdapterProduct;
import com.example.manu.splashapp.mainLayout.MainActivity;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ListView listViewProducts;

    private Button continueShoppingButton;
    private Button orderButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewProducts = (ListView) findViewById(R.id.products_list);

        continueShoppingButton = (Button) findViewById(R.id.continue_button);
        continueShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CartActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        orderButton = (Button) findViewById(R.id.order_button);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void inflateListProducts() {
        // Construct the data source
        ArrayList<Product> arrayofProducts = new ArrayList<Product>();
// Create the adapter to convert the array to views
        AdapterProduct adapter = new AdapterProduct(this, arrayofProducts, R.layout.content_cart, R.id.product_name_text, R.id.product_image_view, R.layout.content_products);
// Attach the adapter to a ListView
        listViewProducts.setAdapter(adapter);
    }

}
