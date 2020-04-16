package com.example.ava.Tool;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetHttpTool {
    public GetHttpTool(){
        if(false)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String string_A=getHttpPostTradingData(new String("https://www.mgstage.com/search/search.php?search_word=&sort=new&list_cnt=120&disp_type=thumb"));
                        Log.w(this.toString(), "GetHttpTool.run: E0"+"="+string_A.length() );
                        //]Log.w(TAG, "InternetToolF0 "+"="+string_A.length()+"="+ string_A.substring(string_A.length()-300,string_A.length()-1));
                        //sendGetRuquestWithOkHttp();
                    }
                }).start();
            }


    }

    String getHttpPostTradingData(String stringUrl_A){
        String string_rt=null;
        try {
            URL url = new URL(stringUrl_A);//"https://www.mgstage.com/search/search.php?search_word=&sort=new&list_cnt=120&disp_type=thumb");
            //final String originHostname = "www.wolfcstech.com";
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(2000);//设置连接超时,2000ms
            httpURLConnection.setReadTimeout(5000);//设置指定时间内服务器没有返回数据的超时，5000ms
            httpURLConnection.setDoOutput(true);//设置允许输出
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");//设置请求的方式
            //aaab053bc2390410303986c316d995f5; _ga=GA1.2.578304246.1575126534; __ulfpc=201911302308549106; PHPSESSID=nf2bcvaclg0revh3erm70gllb3; adc=1; _gid=GA1.2.1236849156.1586613883; _gat_UA-158726521-1=1; _gat_UA-58252858-1=1
            //aaab053bc2390410303986c316d995f5; _ga=GA1.2.578304246.1575126534; __ulfpc=201911302308549106; PHPSESSID=nf2bcvaclg0revh3erm70gllb3; adc=1; _gid=GA1.2.1236849156.1586613883; _gat_UA-158726521-1=1; _gat_UA-58252858-1=1
           // Host: www.mgstage.com
           // User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0
           // Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
            //Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
            //Accept-Encoding: gzip, deflate, br
            //Referer: https://www.mgstage.com/ppv/index.php
            //Connection: keep-alive
            //Cookie: uuid=aaab053bc2390410303986c316d995f5; _ga=GA1.2.578304246.1575126534; __ulfpc=201911302308549106; PHPSESSID=nf2bcvaclg0revh3erm70gllb3; adc=1; _gid=GA1.2.1236849156.1586613883; _gat_UA-158726521-1=1; _gat_UA-58252858-1=1
            //Upgrade-Insecure-Requests: 1
            //Cache-Control: max-age=0

            httpURLConnection.setRequestProperty("Host","www.mgstage.com");
            httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0");
            //httpURLConnection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httpURLConnection.setRequestProperty("Accept-Charset","UTF-8");
            httpURLConnection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
           // httpURLConnection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
            httpURLConnection.setRequestProperty("Referer","https://www.mgstage.com/ppv/index.php");
            httpURLConnection.setRequestProperty("Connection","keep-alive");
            httpURLConnection.setRequestProperty("Cookie","uuid=aaab053bc2390410303986c316d995f5; _ga=GA1.2.578304246.1575126534; __ulfpc=201911302308549106; PHPSESSID=nf2bcvaclg0revh3erm70gllb3; adc=1; _gid=GA1.2.1236849156.1586613883; _gat_UA-158726521-1=1; _gat_UA-58252858-1=1");
            httpURLConnection.setRequestProperty("Upgrade-Insecure-Requests","1");
           // httpURLConnection.setRequestProperty("Cache-Control","max-age=0");


//            httpURLConnection.setRequestProperty("Host", "www.mgstage.com");
//            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0");
//            httpURLConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//            httpURLConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
//            httpURLConnection.setRequestProperty("Accept-Encoding", " deflate");
//            httpURLConnection.setRequestProperty("Connection", "keep-alive");
//            httpURLConnection.setRequestProperty("Referer", "https://www.mgstage.com/search/search.php?search_word=&sort=popular&list_cnt=120&disp_type=thumb&search_range=latest&is_dvd_product=0");
//            httpURLConnection.setRequestProperty("Cookie", "uuid=aaab053bc2390410303986c316d995f5; _ga=GA1.2.578304246.1575126534; __ulfpc=201911302308549106; adc=1; _gid=GA1.2.1178591849.1575386319; PHPSESSID=i4v5gaabghtchatv8e261fle61; _gat_UA-58252858-1=1");
//            httpURLConnection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            //String string_Cookie="coc=1; uuid=aaab053bc2390410303986c316d995f5; _ga=GA1.2.578304246.1575126534; __ulfpc=201911302308549106; PHPSESSID=h1le6lp8d0bvnph0o5ekuau936; adc=1";

            //httpURLConnection.setRequestProperty("Cookie", string_Cookie);





//            CookieManager manager = new CookieManager();
//            CookieHandler.setDefault(manager);

//            PrintWriter out = new PrintWriter(httpURLConnection.getOutputStream() );
//            String string_G="search_word=\n" +
//                    "sort=new\n" +
//                    "list_cnt=120\n" +
//                    "disp_type=thumb\n" +
//                    "search_range=latest\n" +
//                    "is_dvd_product=0";
//            out.print(string_G);//写入输出流
//            out.flush();//立即刷新
//            out.close();

            int code = httpURLConnection.getResponseCode();
            Log.w(this.toString(), "getHttpPostTradingDataDD0: "+"="+httpURLConnection.getRequestMethod() +"="+code );
            if(code == 200){
                if(false) {
                    InputStream input = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input, "UTF-8");
                    //readStream(input);
                    StringBuffer sb1 = new StringBuffer();
//                    if(true)
//                        {
//                            byte[] b= new byte[1024];
//                            int count = 0;
//                            if(input.available()>0 == false){
//                                continue;
//                            }else{
//                                Thread.sleep(200);
//                            }
//                            count = input.read(b);
//                        }
                    int ss=0;
                    char[] buffer = new char[1024];
                    while (ss != -1) {
                        ss = reader.read(buffer);
                        //]Log.w(TAG, "getHttpPostTradingDataR0: "+"="+ss +"="+sb1.length() );
                        sb1.append(buffer);

                    }
//                    Thread.sleep(500);
//                    while ((ss = reader.read()) != -1) {
//                        sb1.append((char) ss);
//                    }
//                    Thread.sleep(500);
//                    while ((ss = reader.read()) != -1) {
//                        sb1.append((char) ss);
//                    }
                    string_rt=sb1.toString();
                    input.close();
                }else {

                    InputStream input = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input);
                    //  JSONObject jsonObject=new JSONObject(reader);
                    StringBuffer sb1 = new StringBuffer();
                    int ss;
                    while ((ss = reader.read()) != -1) {
                        sb1.append((char) ss);
                    }
                    string_rt=sb1.toString();
                    input.close();
                }

            }
        }catch (Exception e) {
            //]Log.w(TAG, "getHttpPostTradingDataF0: "+"="+e.toString() );
            e.printStackTrace();
        }

        return string_rt;
    }

    public static String getHttpPostTradingDataC(String stringUrl_A){
        String string_rt=null;
        try {
            URL url = new URL(stringUrl_A);//"https://www.mgstage.com/search/search.php?search_word=&sort=new&list_cnt=120&disp_type=thumb");
            //final String originHostname = "www.wolfcstech.com";
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(2000);//设置连接超时,2000ms
            httpURLConnection.setReadTimeout(5000);//设置指定时间内服务器没有返回数据的超时，5000ms
            httpURLConnection.setDoOutput(true);//设置允许输出
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("POST");//设置请求的方式
            //aaab053bc2390410303986c316d995f5; _ga=GA1.2.578304246.1575126534; __ulfpc=201911302308549106; PHPSESSID=nf2bcvaclg0revh3erm70gllb3; adc=1; _gid=GA1.2.1236849156.1586613883; _gat_UA-158726521-1=1; _gat_UA-58252858-1=1
            //aaab053bc2390410303986c316d995f5; _ga=GA1.2.578304246.1575126534; __ulfpc=201911302308549106; PHPSESSID=nf2bcvaclg0revh3erm70gllb3; adc=1; _gid=GA1.2.1236849156.1586613883; _gat_UA-158726521-1=1; _gat_UA-58252858-1=1
            // Host: www.mgstage.com
            // User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0
            // Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
            //Accept-Language: zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2
            //Accept-Encoding: gzip, deflate, br
            //Referer: https://www.mgstage.com/ppv/index.php
            //Connection: keep-alive
            //Cookie: uuid=aaab053bc2390410303986c316d995f5; _ga=GA1.2.578304246.1575126534; __ulfpc=201911302308549106; PHPSESSID=nf2bcvaclg0revh3erm70gllb3; adc=1; _gid=GA1.2.1236849156.1586613883; _gat_UA-158726521-1=1; _gat_UA-58252858-1=1
            //Upgrade-Insecure-Requests: 1
            //Cache-Control: max-age=0

            httpURLConnection.setRequestProperty("Host","www.mgstage.com");
            httpURLConnection.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:74.0) Gecko/20100101 Firefox/74.0");
            //httpURLConnection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httpURLConnection.setRequestProperty("Accept-Charset","UTF-8");
            httpURLConnection.setRequestProperty("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
            // httpURLConnection.setRequestProperty("Accept-Encoding","gzip, deflate, br");
            httpURLConnection.setRequestProperty("Referer","https://www.mgstage.com/ppv/index.php");
            httpURLConnection.setRequestProperty("Connection","keep-alive");
            httpURLConnection.setRequestProperty("Cookie","uuid=aaab053bc2390410303986c316d995f5; _ga=GA1.2.578304246.1575126534; __ulfpc=201911302308549106; PHPSESSID=nf2bcvaclg0revh3erm70gllb3; adc=1; _gid=GA1.2.1236849156.1586613883; _gat_UA-158726521-1=1; _gat_UA-58252858-1=1");
            httpURLConnection.setRequestProperty("Upgrade-Insecure-Requests","1");
            // httpURLConnection.setRequestProperty("Cache-Control","max-age=0");


//            httpURLConnection.setRequestProperty("Host", "www.mgstage.com");
//            httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:70.0) Gecko/20100101 Firefox/70.0");
//            httpURLConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
//            httpURLConnection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
//            httpURLConnection.setRequestProperty("Accept-Encoding", " deflate");
//            httpURLConnection.setRequestProperty("Connection", "keep-alive");
//            httpURLConnection.setRequestProperty("Referer", "https://www.mgstage.com/search/search.php?search_word=&sort=popular&list_cnt=120&disp_type=thumb&search_range=latest&is_dvd_product=0");
//            httpURLConnection.setRequestProperty("Cookie", "uuid=aaab053bc2390410303986c316d995f5; _ga=GA1.2.578304246.1575126534; __ulfpc=201911302308549106; adc=1; _gid=GA1.2.1178591849.1575386319; PHPSESSID=i4v5gaabghtchatv8e261fle61; _gat_UA-58252858-1=1");
//            httpURLConnection.setRequestProperty("Upgrade-Insecure-Requests", "1");
            //String string_Cookie="coc=1; uuid=aaab053bc2390410303986c316d995f5; _ga=GA1.2.578304246.1575126534; __ulfpc=201911302308549106; PHPSESSID=h1le6lp8d0bvnph0o5ekuau936; adc=1";

            //httpURLConnection.setRequestProperty("Cookie", string_Cookie);





//            CookieManager manager = new CookieManager();
//            CookieHandler.setDefault(manager);

//            PrintWriter out = new PrintWriter(httpURLConnection.getOutputStream() );
//            String string_G="search_word=\n" +
//                    "sort=new\n" +
//                    "list_cnt=120\n" +
//                    "disp_type=thumb\n" +
//                    "search_range=latest\n" +
//                    "is_dvd_product=0";
//            out.print(string_G);//写入输出流
//            out.flush();//立即刷新
//            out.close();

            int code = httpURLConnection.getResponseCode();
            //]Log.w(TAG, "getHttpPostTradingDataDD0: "+"="+httpURLConnection.getRequestMethod() +"="+code );
            if(code == 200){
                if(false) {
                    InputStream input = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input, "UTF-8");
                    //readStream(input);
                    StringBuffer sb1 = new StringBuffer();
//                    if(true)
//                        {
//                            byte[] b= new byte[1024];
//                            int count = 0;
//                            if(input.available()>0 == false){
//                                continue;
//                            }else{
//                                Thread.sleep(200);
//                            }
//                            count = input.read(b);
//                        }
                    int ss=0;
                    char[] buffer = new char[1024];
                    while (ss != -1) {
                        ss = reader.read(buffer);
                        //]Log.w(TAG, "getHttpPostTradingDataR0: "+"="+ss +"="+sb1.length() );
                        sb1.append(buffer);

                    }
//                    Thread.sleep(500);
//                    while ((ss = reader.read()) != -1) {
//                        sb1.append((char) ss);
//                    }
//                    Thread.sleep(500);
//                    while ((ss = reader.read()) != -1) {
//                        sb1.append((char) ss);
//                    }
                    string_rt=sb1.toString();
                    input.close();
                }else {

                    InputStream input = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(input);
                    //  JSONObject jsonObject=new JSONObject(reader);
                    StringBuffer sb1 = new StringBuffer();
                    int ss;
                    while ((ss = reader.read()) != -1) {
                        sb1.append((char) ss);
                    }
                    string_rt=sb1.toString();
                    input.close();
                }

            }
        }catch (Exception e) {
            //]Log.w(TAG, "getHttpPostTradingDataF0: "+"="+e.toString() );
            e.printStackTrace();
        }

        return string_rt;
    }
    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        //]Log.w(TAG, "readStreamA: "+"="+outSteam.toString() );
        return outSteam.toByteArray();
    }
}
