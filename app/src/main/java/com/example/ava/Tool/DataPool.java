package com.example.ava.Tool;

import java.util.ArrayList;

public class DataPool {
    public MGSData mgsData;
    public DataPool(){
        mgsData=new MGSData();
    }
    public class MGSData{
        public ArrayList<MGSDataClass> mgsDataClassesMain;
        public MGSData(){

        }
        public void addData(ArrayList<MGSDataClass> mgsDataClasses_A){
            if(mgsDataClassesMain==null)
                {
                    mgsDataClassesMain=new ArrayList<>();
                    mgsDataClassesMain.addAll(mgsDataClasses_A);
                }else {
                mgsDataClassesMain.addAll(0,mgsDataClasses_A);
            }


        }
    }
}
