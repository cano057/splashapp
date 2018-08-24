package com.example.manu.splashapp.use;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.Connection.ClientClass;
import com.Connection.ReUsableClass;
import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.example.manu.ecommerce.R;
import com.example.manu.splashapp.mainLayout.MainActivity;
import com.example.manu.splashapp.mainLayout.SliderCategoriesAdapter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class GetCategoriesAsyntask extends AsyncTask<Void, Void, Boolean> {

    private boolean result = false;
    private String url;
    private int savePlace;
    private ArrayList<Integer> imagesCategories;
    private Context context;
    private View view;
    private RecyclerView.Adapter mAdapter;
    public GetCategoriesAsyntask(String url, int savePlace, ArrayList<Integer> imagesCategories, Context context, View view, RecyclerView.Adapter mAdapter) {
        this.url = url;
        this.savePlace = savePlace;
        this.imagesCategories = imagesCategories;
        this.context = context;
        this.view = view;
        this.mAdapter = mAdapter;
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
                case 0: setRecycles(R.id.list_categories, view, context, mAdapter, listAlphabeticly(ClientClass.getCategories()), imagesCategories);
                    break;
            }
        }  else {
            //Toast.makeText(MainActivity.this, "Could not find any category", Toast.LENGTH_SHORT).show();
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
    public void setRecycles(int id, View view, Context context, RecyclerView.Adapter mAdapter, ArrayList... params){
        RecyclerView recyclerView = (RecyclerView) view.findViewById(id);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new SliderCategoriesAdapter(context, params[0], params[1]);
        recyclerView.setAdapter(mAdapter);
    }
}
