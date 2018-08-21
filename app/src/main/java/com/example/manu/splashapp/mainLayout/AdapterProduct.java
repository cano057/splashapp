package com.example.manu.splashapp.mainLayout;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.manu.ecommerce.R;
import com.ecommerce.model.Product;
import java.util.ArrayList;

public class AdapterProduct extends ArrayAdapter<Product> {
    private int layoutId;
    private int imageId;
    private int nameId;
    private int contentId;
    private static class ViewHolder {
        TextView name;
        ImageView image;
    }

    public AdapterProduct(Context context, ArrayList<Product> users, int layoutId, int nameId, int imageId, int contentId) {
        super(context, layoutId, users);
        this.layoutId = layoutId;
        this.imageId = imageId;
        this.nameId = nameId;
        this.contentId = contentId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Product product = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(contentId, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(nameId);
            viewHolder.image = (ImageView) convertView.findViewById(imageId);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.

        viewHolder.name.setText(product.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}