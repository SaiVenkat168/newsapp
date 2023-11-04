package com.example.android.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.CategoryClickInterface{
    private RecyclerView newsRV,categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryArrayModel> categoryArrayModelArrayList;
    private CategoryAdapter categoryAdapter;
    private NewsAdapter newsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRV=findViewById(R.id.vert);
        categoryRV=findViewById(R.id.categories);
        loadingPB=findViewById(R.id.prog);
        articlesArrayList=new ArrayList<>();
        categoryArrayModelArrayList=new ArrayList<>();
        newsAdapter=new NewsAdapter(articlesArrayList,this);
        categoryAdapter=new CategoryAdapter(categoryArrayModelArrayList,this,this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsAdapter);
        categoryRV.setAdapter(categoryAdapter);
        getNews("All");
        newsAdapter.notifyDataSetChanged();
    }
    private void getCategories()
    {
       categoryArrayModelArrayList.add(new CategoryArrayModel("All","https://images.unsplash.com/photo-1629906712956-630c42e50420?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1534&q=80"));
        categoryArrayModelArrayList.add(new CategoryArrayModel("Technology","https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8dGVjaG5vbG9neXxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryArrayModelArrayList.add(new CategoryArrayModel("Science","https://images.unsplash.com/photo-1507668077129-56e32842fceb?ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8c2NpZW5jZXxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryArrayModelArrayList.add(new CategoryArrayModel("Sport","https://media.istockphoto.com/photos/closeup-of-red-cricket-ball-and-bat-sitting-on-grass-picture-id177427917?s=612x612"));
        categoryArrayModelArrayList.add(new CategoryArrayModel("General","https://images.unsplash.com/photo-1489533119213-66a5cd877091?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8OHx8Z2VuZXJhbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60"));
        categoryArrayModelArrayList.add(new CategoryArrayModel("Bussiness","https://images.unsplash.com/photo-1612550761236-e813928f7271?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=750&q=80"));
        categoryArrayModelArrayList.add(new CategoryArrayModel("Entertainment","https://images.unsplash.com/photo-1603739903239-8b6e64c3b185?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=751&q=80"));
        categoryArrayModelArrayList.add(new CategoryArrayModel("Health","https://images.unsplash.com/photo-1498837167922-ddd27525d352?ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8aGVhbHRofGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryAdapter.notifyDataSetChanged();
    }
    private void getNews(String category)
    {
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String apiKey="YOUR-API-KEY";
        String categoryUrl="http://newsapi.org/v2/top-headlines?country=in&category="+category+"&apiKey="+apiKwy;
        String url="http://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey="+apiKey;
        String BASE_URL="https://newsapi.org/";
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI=retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;
        if(category.equals("All"))
        {
            call=retrofitAPI.getAllNews(url);
        }
        else
        {
            call=retrofitAPI.getNewsByCategory(categoryUrl);
        }
        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response)
            {
                NewsModal newsModal=response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articles=newsModal.getArticles();
                for (int i=0;i<articles.size();i++)
                {
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrltoimage(),articles.get(i).getUrl(),articles.get(i).getContent()));
                    newsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t)
            {
                Toast.makeText(MainActivity.this,"Failed to get news",Toast.LENGTH_LONG).show();
            }
        });

    }
    @Override
    public void onCategoryClick(int position)
    {
        String category=categoryArrayModelArrayList.get(position).getCategory();
        getNews(category);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture)
    {

    }
}
