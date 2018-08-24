package com.example.manu.splashapp.client;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.Connection.ClientClass;
import com.example.manu.ecommerce.R;
import com.example.manu.splashapp.mainLayout.MainActivity;
import com.example.manu.splashapp.mainLayout.SliderCategoriesAdapter;
import com.example.manu.splashapp.use.GetCategoriesAsyntask;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView.Adapter mAdapter;
    private ArrayList<Integer> imagesCategories = new ArrayList<>();
    private GetCategoriesAsyntask mAuthTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initialiteImages();
        mAuthTask = new GetCategoriesAsyntask("http://192.168.100.16:8080/api/f/categorys", 0, imagesCategories, CategoryActivity.this, findViewById(R.id.list_categories_layout), mAdapter);
        mAuthTask.execute((Void) null);
    }

    public void setRecycles(int id, ArrayList... params){
        RecyclerView recyclerView = (RecyclerView) findViewById(id);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CategoryActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new SliderCategoriesAdapter(CategoryActivity.this, params[0], params[1]);
        recyclerView.setAdapter(mAdapter);
    }

    public void initialiteImages() {

        //categories images
        imagesCategories.add(R.drawable.fresh_vegetables);
        imagesCategories.add(R.drawable.seasonable_fruits);
        imagesCategories.add(R.drawable.meat_fish_egg);
        imagesCategories.add(R.drawable.snacks);
        imagesCategories.add(R.drawable.dairy);//bakery
        imagesCategories.add(R.drawable.groceries);
        imagesCategories.add(R.drawable.household_items);
        imagesCategories.add(R.drawable.beverages);
        imagesCategories.add(R.drawable.breakfast_and_cereals);
        imagesCategories.add(R.drawable.breakfast_and_cereals);//chocolate and candies
        imagesCategories.add(R.drawable.spices);
        imagesCategories.add(R.drawable.international_food);
    }

}
