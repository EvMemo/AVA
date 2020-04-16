package com.example.ava;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ImageRecycData {
    Context context;
    ArrayList<ImageAVDData>imageAVDData;
    public ImageRecycData(Context context){
        this.context=context;
        imageAVDData=new ArrayList<>();
        imageAVDData.add(new ImageAVDData(0));
    }
    public class ImageAVDData{
        public String name;
        public String[] urlName;
        public boolean boolState;
        public int intMax,intMin;
        public ImageAVDData(int intNum){
            urlName=new String[3];
            boolState=false;
            intMax=-1;
            intMin=-1;
            initStringName(intNum);
            initData();
            cacDataMax();
        }
        void initStringName(int intAA){
            switch(intAA){
                case 0:
                    name="shirouto";
                    urlName[0]="shirouto";
                    urlName[1]="siro";
                    break;
                default:
                    break;
            }

        }
        void initData(){
            //获得SharedPreferences的实例
            SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
//通过key值获取到相应的data，如果没取到，则返回后面的默认值
            intMax = sp.getInt("intMax", -1);
            intMin=sp.getInt("intMin", -1);

        }
        void cacDataMax(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolState=false;
                    long long_A=System.currentTimeMillis();
                    if(true)
                        {
                        boolean bool_C1=true;
                        if(intMin==-1)
                            {
                                intMin=100;
                            }

                        do {
                            if(getHttpBool(getUrlString(intMin)))
                                {
                                    bool_C1=false;
                                }else {
                                intMin/=2;
                                if(intMin<0)
                                    {
                                        Log.e(this.toString(), "run: !!!" );
                                        return;
                                    }
                            }
                        }while (bool_C1);


                            intMax=intMin;
                            intMax+=100;
                            bool_C1=true;
                        do {
                            if(getHttpBool(getUrlString(intMax)))
                            {
                                Log.w(this.toString(), "cacDataMax02: "+"="+name +"="+intMax +"="+intMin );
                                intMin=intMax;
                                intMax+=100;
                            }else {
                                bool_C1=false;
                            }
                        }while (bool_C1);
                            Log.w(this.toString(), "cacDataMax03: "+"="+name +"="+intMax +"="+intMin );

//                            int int_C2=intMax-50;
//                        if(getHttpBool(getUrlString(int_C2)))
//                            {
//                                intMin=int_C2;
//                            }else {
//                            intMax=int_C2;
//                        }
                            for(int i=0;i<0;i++)
                                {

                                }
                        for(int i=intMax;i>=intMin;i--)
                            {
                                Log.w(this.toString(), "cacDataMax04: "+"="+i);
                            if(getHttpBool(getUrlString(i)))
                                {
                                    intMax=i;
                                    break;
                                }
                            }

                        }
                    Log.w(this.toString(), "cacDataMax01: "+"="+name +"="+intMax +"="+intMin +"="+getUrlString(300) );
                    boolState=true;

                }
            }).start();

        }
        String getUrlString(int intAA){

            String string_A=null;
            if(intAA<100)
            {
                string_A=("0"+intAA);
            }else {
                string_A=String.valueOf(intAA);
            }
            String string_rt="https://image.mgstage.com/images/"+urlName[0]+"/"+urlName[1]+"/"+string_A +"/pf_o1_"+urlName[1]+"-"+string_A+".jpg";
            return string_rt;

        }
        boolean getHttpBool(String urlStringAA){
            try{
                URL url = new URL(urlStringAA);
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
                    return false;
                  //  Log.w(this.toString(), "initData3A01: "+"="+false);
                }

                int code = conn.getResponseCode();
                if ((code >= 100) && (code < 400)){
                    return true;
                  //  Log.w(this.toString(), "initData3A02: "+"="+true);
                }
                return false;
                //Log.w(this.toString(), "initData3A03: "+"="+false +"="+(System.currentTimeMillis()-long_A));
            }catch (Exception e){
                e.printStackTrace();
                return false;
               // Log.w(this.toString(), "initData3A04: "+"="+false);
            }
        }
        void sevaData(){
            SharedPreferences sp = context.getSharedPreferences("name", Context.MODE_PRIVATE);
//获得Editor 实例
            SharedPreferences.Editor editor = sp.edit();
//以key-value形式保存数据
            editor.putInt("intMax", intMax);
            editor.putInt("intMin", intMin);
//apply()是异步写入数据
            editor.apply();
//commit()是同步写入数据
//editor.commit();
        }

    }
}
