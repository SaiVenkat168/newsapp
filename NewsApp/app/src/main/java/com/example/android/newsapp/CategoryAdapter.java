package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
{
    private ArrayList<CategoryArrayModel> categoryAdapters;
    private Context context;
    private CategoryClickInterface categoryClickInterface;


    public CategoryAdapter(ArrayList<CategoryArrayModel> categoryAdapters, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryAdapters = categoryAdapters;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_rv_item,parent,false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position)
    {
        CategoryArrayModel categoryArrayModel=categoryAdapters.get(position);
        holder.textView.setText(categoryArrayModel.getCategory());
        Picasso.get().load(categoryArrayModel.getCategoryimageview()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryClickInterface.onCategoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryAdapters.size();
    }
    public interface CategoryClickInterface
    {
        void onCategoryClick(int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textView;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
          textView=itemView.findViewById(R.id.imageCategory);
          imageView=itemView.findViewById(R.id.txt);
        }
    }
}
