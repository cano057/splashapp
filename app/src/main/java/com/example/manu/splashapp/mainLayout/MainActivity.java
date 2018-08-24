package com.example.manu.splashapp.mainLayout;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
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
import android.widget.Toast;

import com.Connection.ClientClass;
import com.Connection.ReUsableClass;
import com.ecommerce.model.Category;
import com.example.manu.ecommerce.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import com.ecommerce.model.Product;
import com.example.manu.splashapp.client.CategoryActivity;
import com.example.manu.splashapp.use.GetCategoriesAsyntask;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //imageSlider
    private static ViewPager mPager;
    //private static TabLayout indicator;
    private static int currentPage = 0;
    private Integer[] imagesArray = new Integer[6];
    private ArrayList<Integer> imagesArrayList = new ArrayList<Integer>();

    //listProducts
    private ListView listViewProducts;
    private ListView listViewAllProducts;
    private RecyclerView.Adapter mAdapter;

    //images categories
    private ArrayList<Integer> imagesCategories = new ArrayList<>();

    private GetCategoriesAsyntask mAuthTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Configuration of the differents sliders
       // mAuthTask = new GetObjectFromServer("http://192.168.100.16:8080/api/f/categorys", 0);
        mAuthTask = new GetCategoriesAsyntask("http://192.168.100.16:8080/api/f/categorys", 0, imagesCategories, MainActivity.this, findViewById(R.id.list_categories_layout), mAdapter);

        mAuthTask.execute((Void) null);
        //setRecycles(R.id.list_products, ClientClass.getProducts());
        initialitateSlider();
        //inflateListProducts();
        listViewAllProducts = (ListView) findViewById(R.id.content_list_products);
        //inflateViewProducts();
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
        if (id == R.id.menu_search) {
            //openSearch();
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

    /**
     *Inflates all the categories from ClientClass
     * @param id of the view to configure
     * @param params contents the Array with the Objects to put in the view
     */
    public void setRecycles(int id, ArrayList... params){
        RecyclerView recyclerView = (RecyclerView) findViewById(id);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new SliderCategoriesAdapter(MainActivity.this, params[0], params[1]);
        recyclerView.setAdapter(mAdapter);
    }
    /**
     * ImageSwitch initialitation
     */
    private void initialitateSlider() {
        initialitateImages();
        for(int i=0;i<imagesArray.length;i++)
            imagesArrayList.add(imagesArray[i]);

        mPager = (ViewPager) findViewById(R.id.pager);
        //indicator= (TabLayout) findViewById(R.id.indicator);

        SliderImagesAdapter viewPagerAdapter = new SliderImagesAdapter(MainActivity.this, imagesArrayList);
        mPager.setAdapter(viewPagerAdapter);
        mPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;
            }
        });
        //indicator.setupWithViewPager(mPager, true);

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
     * Inflate ListView with all the products View
     */
    private void inflateViewProducts() {
        ArrayList<View> arrayOfViews = new ArrayList<>();
        AdapterViewsProducts adapter = new AdapterViewsProducts(this, arrayOfViews, R.layout.content_main);
        listViewAllProducts.setAdapter(adapter);
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

    public static int[] getWeithAndHeight(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int[] meassures = {dm.widthPixels, dm.heightPixels};
        return meassures;
    }
    public class GetObjectFromServer extends AsyncTask<Void, Void, Boolean> {

        private boolean result = false;
        private String url;
        private int savePlace;
        public GetObjectFromServer(String url, int savePlace) {
            this.url = url;
            this.savePlace = savePlace;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            switch (savePlace) {
                case 0: ClientClass.setCategories(listAlphabeticly(getObject()));
                    if(!ClientClass.getCategories().isEmpty()){ result = true; }
                    break;
                case 1: ClientClass.setProducts(getObject());
                    if(!ClientClass.getProducts().isEmpty()){ result = true; }
                    break;
            }
            return result;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            Log.i("categories comunication", Boolean.toString(result));
            if (result) {
                //inflate slider
                switch (savePlace) {
                    case 0: setRecycles(R.id.list_categories, listAlphabeticly(ClientClass.getCategories()), imagesCategories);
                        break;
                }
            }  else {
                Toast.makeText(MainActivity.this, "Could not find any category", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onCancelled() {

        }

        private ArrayList getObject() {
            ObjectMapper mapper = new ObjectMapper();
            ArrayList arrayList = new ArrayList<>();
            String result = "";
            try {
                result = ReUsableClass.getMethod("GET", url);
                switch (savePlace){
                    case 0: arrayList = mapper.readValue(result, new TypeReference<List<Category>>(){});
                        break;
                    case 1: arrayList = mapper.readValue(result, new TypeReference<List<Product>>(){});
                        break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return arrayList;
        }
        private ArrayList<Category> listAlphabeticly(ArrayList<Category> arrayList) {
            for(int i=0;i<arrayList.size();i++){
                for(int j=0;i<arrayList.size() ;i++) {
                    if(arrayList.get(j).getCategory_name().compareTo(arrayList.get(j+1).getCategory_name()) > 0){
                        Category aux=arrayList.get(j+1);
                        arrayList.set(j+1,arrayList.get(j));
                        arrayList.set(j,aux);
                    }
                }
            }
            return arrayList;

        }
    }

}

