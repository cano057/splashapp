package com.example.manu.splashapp.mainLayout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.manu.ecommerce.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import com.ecommerce.model.Product;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //imageSlider
    private static ViewPager mPager;
    private static TabLayout indicator;
    private static int currentPage = 0;
    private Integer[] imagesArray = new Integer[6];
    private ArrayList<Integer> imagesArrayList = new ArrayList<Integer>();

    //listProducts
    private ListView listViewProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listViewProducts = (ListView) findViewById(R.id.products_list);
        
        initialitateSlider();
        inflateListProducts();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cart) {

        } else if (id == R.id.nav_history) {

        } else if (id == R.id.nav_track_order) {

        } else if (id == R.id.nav_about_us) {

        } else if (id == R.id.nav_tutorial) {

        } else if (id == R.id.nav_log_out) {

        } else if (id == R.id.nav_pp) {


        } else if (id == R.id.nav_terms) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initialitateImages(){
        imagesArray[0] = R.drawable.sliderimage1;
        imagesArray[1] = R.drawable.sliderimage2;
        imagesArray[2] = R.drawable.sliderimage3;
        imagesArray[3] = R.drawable.sliderimage4;
        imagesArray[4] = R.drawable.sliderimage5;
        imagesArray[5] = R.drawable.sliderimage6;
    }

    /**
     * ImageSwitch initialitation
     */
    private void initialitateSlider() {
        initialitateImages();
        for(int i=0;i<imagesArray.length;i++)
            imagesArrayList.add(imagesArray[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        indicator= (TabLayout) findViewById(R.id.indicator);

        mPager.setAdapter(new com.example.manu.splashapp.mainLayout.SliderImagesAdapter(MainActivity.this, imagesArrayList));
        indicator.setupWithViewPager(mPager, true);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);
    }

    /**
     * Inflate ListView withe the products
     */
    private void inflateListProducts() {
        // Construct the data source
        ArrayList<Product> arrayofProducts = new ArrayList<Product>();
// Create the adapter to convert the array to views
        AdapterProduct adapter = new AdapterProduct(this, arrayofProducts, R.layout.content_main, R.id.product_name_text, R.id.product_image_view, R.layout.content_products);
// Attach the adapter to a ListView
        listViewProducts.setAdapter(adapter);
    }

    /**
     * Class SliderTimer which slide the picture in the arrange time
     */
    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (mPager.getCurrentItem() < imagesArrayList.size() - 1) {
                        mPager.setCurrentItem(mPager.getCurrentItem() + 1);
                    } else {
                        mPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
