package com.example.ava;

import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.ava.Tool.CollectMGSTool;
import com.example.ava.Tool.DataPool;
import com.example.ava.Tool.GetHttpTool;
import com.example.ava.Tool.MGSSurfaceView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CollectMGSTool collectMGSTool;
    DataPool dataPool;
    MGSSurfaceView mgsSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);
        GetHttpTool getHttpTool=new GetHttpTool();
        dataPool=new DataPool();
        mgsSurfaceView=findViewById(R.id.MGSSurfaceView);
        collectMGSTool=new CollectMGSTool(this,dataPool.mgsData,getHttpTool);
        if(true)
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    collectMGSTool.reqCollectMGSData();
                    Log.w(this.toString(), "MainActivity.onCreate: E0"+"="+ dataPool.mgsData.mgsDataClassesMain.size());
                    if(dataPool.mgsData.mgsDataClassesMain!=null&&dataPool.mgsData.mgsDataClassesMain.size()!=0)
                        {

                            mgsSurfaceView.setMGSDataData(dataPool.mgsData.mgsDataClassesMain);
                        }
                }
            }).start();
        }
//        WebView webView_A=findViewById(R.id.web_001);
//        webView_A.getSettings().setJavaScriptEnabled(true);
//        webView_A.loadUrl("https://www.qimai.cn/");
//        webView_A.setWebViewClient(new WebViewClient(){
//            @Override
//            public void onPageFinished(WebView view, String url)
//            {
//                Log.w(this.toString(), "onPageFinished: "+"="+"OK" );//设定加载结束的操作
//                 }   });
//        webView_A.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error)
//            {
//                Log.w(this.toString(), "onPageFinished: "+"="+"onReceivedSslError" );//设定加载结束的操作
//                handler.proceed();    //表示等待证书响应
//
//               // handler.cancel();      //表示挂起连接，为默认方式
//                // handler.handleMessage(null);    //可做其他处理
//                }         });

       // ImageRecycData imageAVDData=new ImageRecycData(this);
       // initData_002();
        //WebView webView_A=findViewById(R.id.webview_mainlay_01);
       // webView_A.loadUrl("https://www.mgstage.com/product/product_detail/345SIMM-257/");
//        webView_A.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
        //initData_002();
        //initView();
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);
    }
    void initData_003(){
        new Thread(new Runnable() {
            @Override
            public void run() {


        boolean bool_A=true;
        long long_A=System.currentTimeMillis();
        try{
            URL url = new URL("https://image.mgstage.com/images/shirouto/siro/3914/cap_e_1_siro-3914.jpg");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setConnectTimeout(50);
            conn.setReadTimeout(1000);
            //HTTP connect
            try {
                conn.connect();
            } catch(Exception e) {
                e.printStackTrace();
                Log.w(this.toString(), "initData3A01: "+"="+false);
            }

            int code = conn.getResponseCode();
            if ((code >= 100) && (code < 400)){
                Log.w(this.toString(), "initData3A02: "+"="+true);
            }

            Log.w(this.toString(), "initData3A03: "+"="+false +"="+(System.currentTimeMillis()-long_A));
        }catch (Exception e){
            e.printStackTrace();
            Log.w(this.toString(), "initData3A04: "+"="+false);
        }
            }
        }).start();
    }
    RecyclerView recyclerView;
    void initData_002(){
        recyclerView=null;
        MyRecycTool myRecycTool=new MyRecycTool(this,recyclerView);
    }
    SimpleDraweeView simpleDraweeView;
    private void initView() {
        //创建SimpleDraweeView对象
        simpleDraweeView = (SimpleDraweeView) findViewById(R.id.main_sdv);
        //创建将要下载的图片的URI
        Uri imageUri = Uri.parse("https://img-blog.csdn.net?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvRlhfU0tZ/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast");
        Uri imageUri_B=Uri.parse("https://image.mgstage.com/images/shirouto/siro/4914/cap_e_1_siro-3914.jpg");
        //开始下载
        simpleDraweeView.setImageURI(imageUri_B);
        //创建DraweeController
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                //加载的图片URI地址
                .setUri(imageUri_B)
                //设置点击重试是否开启
                .setTapToRetryEnabled(true)
                //设置旧的Controller
                .setOldController(simpleDraweeView.getController())
                //构建
                .build();

        //设置DraweeController
        simpleDraweeView.setController(controller);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
