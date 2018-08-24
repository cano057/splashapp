package com.example.manu.splashapp.mainLayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecommerce.model.Category;
import com.ecommerce.model.Product;
import com.example.manu.ecommerce.R;

import java.util.ArrayList;

public class SliderProductsAdapter extends RecyclerView.Adapter<SliderProductsAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Product> products;
    private LayoutInflater inflater;
    private LayoutInflater layoutInflater;
    public TextView mTextView;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImageView;
        public TextView mTextView;

        public MyViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.product_name_text);
            mImageView = (ImageView) v.findViewById(R.id.product_image_view);
        }
    }
    public SliderProductsAdapter(Context context, ArrayList<Product> products) {
            this.context = context;
            this.products = products;
            inflater = LayoutInflater.from(context);
            }

    // Create new views (invoked by the layout manager)
    @Override
    public SliderProductsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                 int viewType) {
            // create a new view
            View v = (View) LayoutInflater.from(parent.getContext())
            .inflate(R.layout.content_products, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
            }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            Product product = products.get(position);
            holder.mTextView.setText(product.getName());
            //holder.mImageView.setImageDrawable();

            }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
            return products.size();
            }
}