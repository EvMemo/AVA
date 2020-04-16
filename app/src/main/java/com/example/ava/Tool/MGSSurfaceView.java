package com.example.ava.Tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MGSSurfaceView extends View {
    private int width;//设置高
    private int height;//设置高
    Paint paintBim;
    Paint paintUI;
    Paint paintUIB;
    Paint paintUIC;
    Paint paintText;
    public ArrayList<MGSDataClass> mgsDataClasses;
    public DrawData[] drawDataLL;
    public DrawData drawData;
    public int intDrawNum;
    public int intImNum;
    public boolean boolDraw;
    public boolean boolData;
    public boolean boolN;
    public MGSSurfaceView(Context context) {
        super(context);
    }

    public MGSSurfaceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paintUI=new Paint();
        paintUI.setColor(Color.WHITE);
        paintUIB=new Paint();
        paintUIB.setColor(Color.GRAY);
        paintUIC=new Paint();
        paintUIC.setColor(Color.BLUE);
        paintBim=new Paint();

        paintText = new Paint();
        paintText.setAntiAlias(true);//消除锯齿
        paintText.setColor(Color.BLACK);//设置画笔颜色
        paintText.setTextAlign(Paint.Align.CENTER);
        paintText.setTextSize(50);
        boolN=true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);

        setMeasuredDimension(width, height);//设置宽和高
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0,0,width,height,paintUI);
//        canvas.drawRect(0,height-100,width,height,paintUIB);
//        canvas.drawRect(0,height-350,width,height-100,paintUIC);
        if(boolDraw)
            {
                if(drawData.boolDraw)
                    {

                        if(boolN)
                            {
                               canvas.drawBitmap(drawData.bitmap,50,100,paintBim);
                            }else {
                           // Log.w(this.toString(), "onDraw:R0 "+"="+boolN +"="+(drawData.bitmapsImg!=null) +"="+(drawData.bitmapsImg[intImNum]!=null) );
                            if(drawData.bitmapsImg!=null&&drawData.bitmapsImg[intImNum]!=null)
                                {
                                    canvas.drawBitmap(drawData.bitmapsImg[intImNum],100,50,paintBim);
                                }

                        }


                    }else {
                    canvas.drawRect(width/2F-200,height/2F+200,width/2F-200,height/2F+200,paintUIC);
                }
                //canvas.drawText("No."+intDrawNum,width/2F,height/2F,paintText);

            }
    }
    public void setMGSDataData(ArrayList<MGSDataClass> mgsDataData_A){
        mgsDataClasses=mgsDataData_A;
        if(mgsDataClasses!=null)
            {
            boolData=true;

                drawDataLL=new DrawData[mgsDataData_A.size()];
                boolDraw=true;
                intDrawNum=0;
                for(int i=0;i<mgsDataClasses.size();i++)
                    {
                        drawDataLL[i]=new DrawData(mgsDataData_A.get(i));
                    }
                drawData=drawDataLL[0];
                drawData.startData();
            }

    }
    void nextMGSDraw(int int_A){
        Log.w(this.toString(), "nextMGSDraw: E0"+"="+boolData +"="+int_A );
        if(boolData)
            {
                if(boolN)
                    {
                        if(int_A>0)
                        {
                            if((intDrawNum+1)<mgsDataClasses.size())
                            {
                                intDrawNum+=1;
                                drawData=drawDataLL[intDrawNum];
                                drawData.startData();
                            }

                        }else {
                            if(intDrawNum!=0)
                            {
                                intDrawNum-=1;
                                drawData=drawDataLL[intDrawNum];
                                drawData.startData();
                            }

                        }
                    }else {
                    if(int_A>0)
                    {
                        if((intImNum+1)<drawData.intImgNum)
                        {
                            intImNum++;
                            drawData.nextImg(intImNum);
                        }

                    }else {
                        if(intImNum!=0)
                        {
                            intImNum--;
                            drawData.nextImg(intImNum);
                        }

                    }
                }

                invalidate();
            }


    }
    public void onTouch(float float_W,float float_H){
        if(boolN)
            {
                boolN=false;
                intImNum=0;
               // drawData.reqImgNData(intImNum);
            }else {
            boolN=true;
            intImNum=0;
            drawData.nextImg(intImNum);
        }
        invalidate();

    }
    boolean boolMove;
    float floatMove;
    void moveView(float floatMove_A) {
        if(true)
            {
                Log.w(this.toString(), "moveView: T0"+"="+floatMove +"="+floatMove_A );
                floatMove = floatMove + floatMove_A;
                if(floatMove>100)
                    {
                        boolMove=false;
                        floatMove=0;
                        nextMGSDraw(1);
                    }else if(-100>floatMove)
                        {
                            boolMove=false;
                            floatMove=0;
                            nextMGSDraw(-1);

                        }
            }

    }
    float floatDownX;
    float floatDownY;
    float floatDownTime;
    long longTouchTime;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                Log.w(this.toString(), "onTouchEvent: M0"+"="+boolMove );
                if(boolMove)
                    {
                        float float_A= floatDownX -event.getX();
                        float float_B=floatDownY-event.getY();
                        floatDownX =event.getX();
                        floatDownY=event.getY();
                        moveView(float_A);
                    }

                return true;
            case MotionEvent.ACTION_DOWN:
                boolMove=true;
                floatDownX = event.getX();
                floatDownY = event.getY();
                longTouchTime = System.currentTimeMillis();
                return true;
            case MotionEvent.ACTION_UP:
                if(boolMove)
                    {
                        float float_X = event.getX();
                        float float_Y = event.getY();
                        Log.w(this.toString(), "onTouchEvent: T0"+"="+ (System.currentTimeMillis()<longTouchTime+200&&(spacing(floatDownX,floatDownY,event.getX(),event.getY())<10)));
                        if(System.currentTimeMillis()<longTouchTime+200&&(spacing(floatDownX,floatDownY,event.getX(),event.getY())<10))
                        {
                            onTouch(float_X,float_Y);

                        }else {

                            //startScroller(floatTouchDownX,floatTouchDownY);
                        }
                       // onTouch(float_X, float_Y);
                    }


                return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        //Log.w(this.toString(), "DOKDonTouchEventD1: "+"="+event.getAction() );
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
        }

        return super.dispatchTouchEvent(event);
    }
    private float spacing(float float_X1,float float_Y1,float float_X2,float float_Y2) {
        float x = float_X1 - float_X2;
        float y = float_Y1 - float_Y2;
        return (float) Math.sqrt(x * x + y * y);
    }
    public class DrawData{
        public MGSDataClass mgsDataClass;
        public Bitmap bitmap;
        public boolean boolDraw;
        public boolean boolD;
        public boolean boolImg;
        public int intImgNum;
        public String[] stringsImgN;
        public Bitmap[] bitmapsImg;
        public DrawData(MGSDataClass mgsDataClass_A){
            mgsDataClass=mgsDataClass_A;
            boolDraw=false;
            boolD=false;

        }
        public void startData(){
            if(!boolD)
                {
                    boolD=true;
                    reqImg();
                    reqImgNum();
                }

        }
        public void nextImg(int int_A){
            if(boolImg)
                {
                    reqImgNData(int_A);
                }


        }
        void ccView(){
            Log.w(this.toString(), "DrawData.ccView: E0"+"="+ (bitmap!=null));
            if(bitmap!=null)
                {
                    boolDraw=true;

                    invalidate();
                }
        }
        void ccViewImg(){
            invalidate();
        }
        void reqImg(){
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(mgsDataClass.stringImg).tag(1)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                public void onFailure(Call call, IOException e) {
                    Log.w(this.toString(), "onFailure: E0"+"="+e.toString() );

                }
                public void onResponse(Call call, Response response) throws IOException {

                    Log.w(this.toString(), "onResponse: E0a");
                    InputStream inputStream = response.body().byteStream();//得到图片的流
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    ccBitmap();
                    post(new Runnable() {
                        @Override
                        public void run() {
                            ccView();
                        }
                    });
                }
            });
        }
        void ccBitmap(){
            int width_A = bitmap.getWidth();
            int height_A = bitmap.getHeight();
            // 设置想要的大小
            int newWidth = width-100;
            int newHeight = height-100;
            // 计算缩放比例
            float scaleWidth = ((float) newWidth) / width_A;
            float scaleHeight = ((float) newHeight) / height_A;
            float float_A=0;
            if(scaleWidth>scaleHeight)
                {
                    float_A=scaleHeight;
                }else {
                float_A=scaleWidth;
            }
            // 取得想要缩放的matrix参数
            Matrix matrix = new Matrix();
            matrix.postScale(float_A, float_A);
            // 得到新的图片
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width_A, height_A, matrix,
                    true);
        }
        Bitmap ccBitmap(Bitmap bitmap_A){
            int width_A = bitmap_A.getWidth();
            int height_A = bitmap_A.getHeight();
            // 设置想要的大小
            int newWidth = width-200;
            int newHeight = height-200;
            // 计算缩放比例
            float scaleWidth = ((float) newWidth) / width_A;
            float scaleHeight = ((float) newHeight) / height_A;
            float float_A=0;
            Matrix matrix = new Matrix();
            if(width_A>height_A)
            {
                float_A=1.8F;
                matrix.postRotate(90);
            }else {
                float_A=scaleWidth;
            }

            Log.w(this.toString(), "ccBitmap: R0"+"="+float_A );
            // 取得想要缩放的matrix参数


            matrix.postScale(float_A, float_A);
            // 得到新的图片
            return Bitmap.createBitmap(bitmap_A, 0, 0, width_A, height_A, matrix,
                    true);
        }
        void reqImgNum(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String string_A=GetHttpTool.getHttpPostTradingDataC("https://www.mgstage.com"+mgsDataClass.stringID);
                   // Log.w(this.toString(), "reqImgNum: E0"+"="+string_A);
                    Document document_A=  Jsoup.parse(string_A);
                    Elements elements_A=document_A.select("div.detail_left");
                    //Log.w(this.toString(), "reqImgNum: E1"+"="+elements_A.size()+"="+string_A.length() );
                    elements_A=elements_A.get(0).select("li");
                    //Log.w(this.toString(), "reqImgNum: E1b"+"="+elements_A.size() );
                    intImgNum=elements_A.size();
                    stringsImgN=new String[elements_A.size()];
                    bitmapsImg=new Bitmap[elements_A.size()];
                    StringBuilder stringBuilder_A;
                    int int_A;
                    for(int i=0;i<elements_A.size();i++) {
                        stringBuilder_A = new StringBuilder(elements_A.get(i).child(0).select("img").get(0).attr("src"));
                        int_A=stringBuilder_A.indexOf("cap_t1");
                        stringBuilder_A.replace(int_A,int_A+6,"cap_e");
                        stringsImgN[i]=stringBuilder_A.toString();
                       // Log.w(this.toString(), "reqImgNum: G0"+"="+stringsImgN[i] );
                    }
                    boolImg=true;
                    reqImgNData(0);
                    reqImgNData(1);
                    reqImgNData(2);
                    //Log.w(this.toString(), "reqImgNum: G1"+"="+stringsImgN.length );
                }
            }).start();
        }
       public void reqImgNData(int int_A){

            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(stringsImgN[int_A]).tag(int_A)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                public void onFailure(Call call, IOException e) {
                    Log.w(this.toString(), "onFailure: E0"+"="+e.toString() );

                }
                public void onResponse(Call call, Response response) throws IOException {


                    Log.w(this.toString(), "onResponse: E0"+"="+  ((int)call.request().tag()));
                    int int_Y=(int)call.request().tag();
                    InputStream inputStream = response.body().byteStream();//得到图片的流
                    Bitmap bitmap_A = BitmapFactory.decodeStream(inputStream);
                    bitmapsImg[int_Y]=ccBitmap(bitmap_A);
                   // ccBitmap();
                    post(new Runnable() {
                        @Override
                        public void run() {
                            ccViewImg();
                        }
                    });
                }
            });
        }
        class DrawImgData{
            public String stringU;
            public Bitmap bitmap;
            public boolean boolA;
            public DrawImgData(String string_A){
                stringU=string_A;
            }
        }
    }

}
