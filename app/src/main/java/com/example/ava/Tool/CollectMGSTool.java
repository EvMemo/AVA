package com.example.ava.Tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class CollectMGSTool {
    public DataPool.MGSData mgsData;
    public GetHttpTool getHttpTool;
    Context context;
    public CollectMGSTool(Context context_A,DataPool.MGSData mgsData_A,GetHttpTool getHttpTool_A){
        context=context_A;
        mgsData=mgsData_A;
        getHttpTool=getHttpTool_A;
    }
    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }
    public void reqCollectMGSData(){
        SharedPreferences preferences=context.getSharedPreferences("MGS", Context.MODE_PRIVATE);
        String string_ID_A=preferences.getString("MGS_ID", null);
        ArrayList<MGSDataClass> mgsDataClasses_A=startCollectData(null);
        Log.w(this.toString(), "reqCollectMGSData: E0"+"="+ (mgsDataClasses_A!=null&&mgsDataClasses_A.size()!=0));
        if(mgsDataClasses_A!=null&&mgsDataClasses_A.size()!=0)
            {
                Log.w(this.toString(), "reqCollectMGSData: E1"+"="+ (mgsDataClasses_A.size()));
                string_ID_A=mgsDataClasses_A.get(0).stringID;

                SharedPreferences.Editor editor=preferences.edit();
                editor.putString("MGS_ID", string_ID_A);
                editor.commit();

            }
        mgsData.addData(mgsDataClasses_A);

    }
    public  ArrayList<MGSDataClass> startCollectData(String stringBool_A){
        String stringUrl_A="https://www.mgstage.com/search/search.php?search_word=&sort=new&list_cnt=120&disp_type=thumb";
        String string_Data=getHttpTool.getHttpPostTradingData(stringUrl_A);
        if(string_Data==null)
            {

            }
        Document document_A= null;
       document_A = Jsoup.parse(string_Data);
//
//        Log.w(this.toString(), "startCollectData: Y1"+"="+string_Data);
//        Log.w(this.toString(), "startCollectData: Y0"+"="+ getEncoding(string_Data) +"="+string_Data.length());
       // Log.w(this.toString(), "startCollectData: Y2"+"="+ document_A.toString());
//        try {
//            Log.w(this.toString(), "startCollectData: T2"+"="+ new String(string_Data.getBytes(), "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        try {
//            Log.w(this.toString(), "startCollectData: T2"+"="+ (new JSONObject(string_Data).toString()));
//        } catch (JSONException e) {
//            e.printStackTrace();
////        }
//        Log.w(this.toString(), "startCollectData: T1"+"="+ string_Data.length());
//        Log.w(this.toString(), "startCollectData: T0"+"="+ document_A.toString().length());
//
//        Log.w(this.toString(), "startCollectData: E0"+"="+elements_A.size() );
        Elements elements_A=document_A.select("div.rank_list");
        Elements elements_B=elements_A.get(0).select("li");
//        Log.w(this.toString(), "startCollectData: E1"+"="+elements_B.size() );
//        Log.w(this.toString(), "startCollectData: E2"+"="+ elements_B.get(0).html());
//        Log.w(this.toString(), "startCollectData: E3a"+"="+ elements_B.get(0).children().size());
//        Log.w(this.toString(), "startCollectData: E3b"+"="+ elements_B.get(0).child(0).html());
//        Log.w(this.toString(), "startCollectData: E3c"+"="+ elements_B.get(0).child(1).html());
//        Log.w(this.toString(), "startCollectData: E4"+"="+ elements_B.get(0).child(0).select("a").get(0).text());
//        Log.w(this.toString(), "startCollectData: E4"+"="+ elements_B.get(0).child(0).select("a").get(0).html());
//        Log.w(this.toString(), "startCollectData: E4"+"="+ elements_B.get(0).child(0).select("a").get(0).attr("href"));
//        Log.w(this.toString(), "startCollectData: E4a"+"="+ elements_B.get(0).child(0).select("img").get(0).attr("src"));
//        Log.w(this.toString(), "startCollectData: E4a"+"="+ elements_B.get(0).select("p.title.lineclamp").get(0).text());
        boolean bool_A=true;
        ArrayList<MGSDataClass> mgsDataClasses_A=new ArrayList<>();
        if(elements_B.size()!=0)
            {
                for(int i=0;i<elements_B.size();i++)
                {
                    String string_ID=elements_B.get(i).child(0).select("a").get(0).attr("href");
                    if(string_ID.equals(stringBool_A)&&(stringBool_A!=null))
                        {
                            bool_A=false;
                           // return mgsDataClasses_A;
                        }else {

                        String string_Img=elements_B.get(i).child(0).select("img").get(0).attr("src");
                        int int_A=string_Img.indexOf("pf_t1");
                        if(int_A!=-1)
                            {
                                StringBuilder stringBuilder_A=new StringBuilder(string_Img);
                                stringBuilder_A.replace(int_A,int_A+5,"pf_e");
                                //Log.w(this.toString(), "startCollectData: G0"+"="+ string_Img +"="+stringBuilder_A.toString());
                                string_Img=stringBuilder_A.toString();
                            }
                        String string_Name=elements_B.get(i).select("p.title.lineclamp").get(0).text();
                        mgsDataClasses_A.add(new MGSDataClass(string_ID,string_Name,string_Img));
                    }

                }
            }
        if(bool_A)
            {
                for(int y=2;y<3;y++)
                    {
                        ArrayList<MGSDataClass> mgsDataClasses_B=new ArrayList<>();
                        String string_N=stringUrl_A+"&page="+y;
                        string_Data=getHttpTool.getHttpPostTradingData(string_N);
                        if(string_Data==null)
                            {
                            for(int i=0;i<30;i++)
                                {
                                    string_Data=getHttpTool.getHttpPostTradingData(string_N);
                                    if(string_Data!=null)
                                        {
                                        break;
                                        }
                                }
                            }
                        document_A = Jsoup.parse(string_Data);
//
//        Log.w(this.toString(), "startCollectData: Y1"+"="+string_Data);
//        Log.w(this.toString(), "startCollectData: Y0"+"="+ getEncoding(string_Data) +"="+string_Data.length());
                        // Log.w(this.toString(), "startCollectData: Y2"+"="+ document_A.toString());
//        try {
//            Log.w(this.toString(), "startCollectData: T2"+"="+ new String(string_Data.getBytes(), "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        try {
//            Log.w(this.toString(), "startCollectData: T2"+"="+ (new JSONObject(string_Data).toString()));
//        } catch (JSONException e) {
//            e.printStackTrace();
////        }
//        Log.w(this.toString(), "startCollectData: T1"+"="+ string_Data.length());
//        Log.w(this.toString(), "startCollectData: T0"+"="+ document_A.toString().length());
//
//        Log.w(this.toString(), "startCollectData: E0"+"="+elements_A.size() );
                        elements_A=document_A.select("div.rank_list");
                        elements_B=elements_A.get(0).select("li");
                        if(elements_B.size()!=0)
                        {
                            for(int i=0;i<elements_B.size();i++)
                            {
                                String string_ID=elements_B.get(i).child(0).select("a").get(0).attr("href");
                                if(string_ID.equals(stringBool_A))
                                {
                                    bool_A=false;
                                   break;
                                }else {

                                    String string_Img=elements_B.get(i).child(0).select("img").get(0).attr("src");
                                    int int_A=string_Img.indexOf("pf_t1");
                                    if(int_A!=-1)
                                    {
                                        StringBuilder stringBuilder_A=new StringBuilder(string_Img);
                                        stringBuilder_A.replace(int_A,int_A+5,"pf_e");
                                       // Log.w(this.toString(), "startCollectData: G0"+"="+ string_Img +"="+stringBuilder_A.toString());
                                        string_Img=stringBuilder_A.toString();
                                    }
                                    String string_Name=elements_B.get(i).select("p.title.lineclamp").get(0).text();
                                    mgsDataClasses_B.add(new MGSDataClass(string_ID,string_Name,string_Img));
                                }

                            }
                        }
                        if(mgsDataClasses_B.size()!=0)
                            {
                            mgsDataClasses_A.addAll(mgsDataClasses_B);
                            }
                        if(!bool_A)
                            {
                            break;
                            }
                    }

            }

return mgsDataClasses_A;

    }

    public  ArrayList<MGSDataClass> startCollectDataB(String stringBool_A,String stringUrl_A){
        String string_Data=getHttpTool.getHttpPostTradingData(stringUrl_A);
        Document document_A= null;
        document_A = Jsoup.parse(string_Data);
//
//        Log.w(this.toString(), "startCollectData: Y1"+"="+string_Data);
//        Log.w(this.toString(), "startCollectData: Y0"+"="+ getEncoding(string_Data) +"="+string_Data.length());
        // Log.w(this.toString(), "startCollectData: Y2"+"="+ document_A.toString());
//        try {
//            Log.w(this.toString(), "startCollectData: T2"+"="+ new String(string_Data.getBytes(), "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        try {
//            Log.w(this.toString(), "startCollectData: T2"+"="+ (new JSONObject(string_Data).toString()));
//        } catch (JSONException e) {
//            e.printStackTrace();
////        }
//        Log.w(this.toString(), "startCollectData: T1"+"="+ string_Data.length());
//        Log.w(this.toString(), "startCollectData: T0"+"="+ document_A.toString().length());
//
//        Log.w(this.toString(), "startCollectData: E0"+"="+elements_A.size() );
        Elements elements_A=document_A.select("div.rank_list");
        Elements elements_B=elements_A.get(0).select("li");
//        Log.w(this.toString(), "startCollectData: E1"+"="+elements_B.size() );
//        Log.w(this.toString(), "startCollectData: E2"+"="+ elements_B.get(0).html());
//        Log.w(this.toString(), "startCollectData: E3a"+"="+ elements_B.get(0).children().size());
//        Log.w(this.toString(), "startCollectData: E3b"+"="+ elements_B.get(0).child(0).html());
//        Log.w(this.toString(), "startCollectData: E3c"+"="+ elements_B.get(0).child(1).html());
//        Log.w(this.toString(), "startCollectData: E4"+"="+ elements_B.get(0).child(0).select("a").get(0).text());
//        Log.w(this.toString(), "startCollectData: E4"+"="+ elements_B.get(0).child(0).select("a").get(0).html());
//        Log.w(this.toString(), "startCollectData: E4"+"="+ elements_B.get(0).child(0).select("a").get(0).attr("href"));
//        Log.w(this.toString(), "startCollectData: E4a"+"="+ elements_B.get(0).child(0).select("img").get(0).attr("src"));
//        Log.w(this.toString(), "startCollectData: E4a"+"="+ elements_B.get(0).select("p.title.lineclamp").get(0).text());
        ArrayList<MGSDataClass> mgsDataClasses_A=new ArrayList<>();
        if(elements_B.size()!=0)
        {
            for(int i=0;i<elements_B.size();i++)
            {
                String string_ID=elements_B.get(i).child(0).select("a").get(0).attr("href");
                if(string_ID.equals(stringBool_A))
                {

                    return mgsDataClasses_A;
                }else {

                    String string_Img=elements_B.get(i).child(0).select("img").get(0).attr("src");
                    String string_Name=elements_B.get(i).select("p.title.lineclamp").get(0).text();
                    mgsDataClasses_A.add(new MGSDataClass(string_ID,string_Name,string_Img));
                }

            }
        }

        return mgsDataClasses_A;

    }
}
