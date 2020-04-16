package com.example.ava;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class MyRecycTool {
    RecyclerView recyclerView;
    Context context;
    public MyRecycTool(Context contextAA,RecyclerView recyclerViewAA){
        context=contextAA;
        recyclerView=recyclerViewAA;
        initData();

    }
    void initData(){
        ArrayList<RecycAdapter.ImageData> imageData_A=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Connection connect = Jsoup.connect("https://www.sehuatang.net/forum-37-1.html");
// 伪装成浏览器抓取，具体有没用布吉岛。。
                    connect.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/20100101 Firefox/32.0");
                    final Document document =  connect.get();

                    String string_A=document.body().select("div.tl_bm_bmw").text();
                    Log.w(this.toString(), "initData00: "+"="+string_A);
                    Log.w(this.toString(), "initData01: "+"="+document.text());
                    Log.w(this.toString(), "initData03: "+"="+document.html());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        // 连接提供了一个方便的接口来从web获取内容，并将它们解析为文档

// 通过get()获取一个Document对象


    }

    void initView( ArrayList<RecycAdapter.ImageData> imageDataAA){
        RecycAdapter recycAdapter_A=new RecycAdapter(context,imageDataAA);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(recycAdapter_A);
    }

    public class RecycAdapter extends RecyclerView.Adapter<RecycAdapter.MyViewHolder>
    {
        public ArrayList<ImageData> imageData;
        Context context;
        public RecycAdapter(Context contextAA, ArrayList<ImageData> imageDataAA){
            imageData=imageDataAA;
            context=contextAA;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater inflater = LayoutInflater.from(context);
            View v =null;// inflater.inflate(R.layout.fresco_item, parent, false);
            SimpleDraweeView simpleDraweeView=null;
            return new MyViewHolder(simpleDraweeView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position)
        {
            holder.simpleDraweeView.setImageURI(imageData.get(position).uri);
        }

        @Override
        public int getItemCount()
        {
            return imageData.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder
        {
            SimpleDraweeView simpleDraweeView;


            public MyViewHolder(SimpleDraweeView simpleDraweeViewAA)
            {
                super(simpleDraweeViewAA);
                simpleDraweeView=simpleDraweeViewAA;
            }
        }
        public class ImageData{
            public String name;
            public String uri;
            public String[] nameData;
            public int intNum;
            public ImageData(String uriAA){
                uri=uriAA;

            }


    }
    }
}
