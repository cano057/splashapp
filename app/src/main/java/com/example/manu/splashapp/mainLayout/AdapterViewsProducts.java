package com.example.manu.splashapp.mainLayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ecommerce.model.Product;
import com.example.manu.ecommerce.R;

import java.util.ArrayList;

public class AdapterViewsProducts extends ArrayAdapter<View> {
    private int nameId;
    private int contentId;
    private static class ViewHolder {
        View products;
    }

    public AdapterViewsProducts(Context context, ArrayList<View> productsView, int layoutId) {
        super(context, layoutId, productsView);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        View view = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        AdapterViewsProducts.ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new AdapterViewsProducts.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.content_view_products, parent, false);
            viewHolder.products = (View) convertView.findViewById(R.id.content_list_products);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (AdapterViewsProducts.ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        // Return the completed view to render on screen
        return convertView;
    }
}
