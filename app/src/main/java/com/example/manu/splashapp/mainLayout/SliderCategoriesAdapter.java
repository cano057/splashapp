package com.example.manu.splashapp.mainLayout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecommerce.model.Category;
import com.example.manu.ecommerce.R;
import com.example.manu.splashapp.client.CategoryActivity;

import java.util.ArrayList;

public class SliderCategoriesAdapter extends RecyclerView.Adapter<SliderCategoriesAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Category> categories;
    private ArrayList<Integer> imagesCategories;
    private LayoutInflater inflater;
    private LayoutInflater layoutInflater;
    public TextView mTextView;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImageView;
        public TextView mTextView;
        public LinearLayout layout;
        public MyViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.text_view_category);
            mImageView = (ImageView) v.findViewById(R.id.image_view_category);
            layout = (LinearLayout) v.findViewById(R.id.activity_categories_layout);
        }
    }

    public SliderCategoriesAdapter(Context context, ArrayList<Category> categories, ArrayList<Integer> imagesCategories) {
        this.context = context;
        this.categories = categories;
        inflater = LayoutInflater.from(context);
        this.imagesCategories = imagesCategories;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SliderCategoriesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_category_of_list, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.mTextView.setText(category.getCategory_name());
        holder.mImageView.setImageResource(imagesCategories.get(position));
        holder.mImageView.setMaxWidth(MainActivity.getWeithAndHeight(context)[0]/3);
        holder.mTextView.setMaxWidth(holder.mImageView.getWidth());

        //Change layout size params
        ViewGroup.LayoutParams params = holder.layout.getLayoutParams();
        params.height = holder.mImageView.getMaxHeight() + holder.mTextView.getHeight();
        params.width = holder.mTextView.getWidth();
        holder.layout.setLayoutParams(params);

        //category pressed
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CategoryActivity.class);
                i.putExtra("category name", holder.mTextView.getText().toString());
                i.putExtra("position", position);
                context.startActivity(i);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return categories.size();
    }
}
