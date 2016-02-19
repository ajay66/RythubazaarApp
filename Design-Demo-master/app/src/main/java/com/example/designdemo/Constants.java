package com.example.designdemo;

import java.util.HashMap;

/**
 * Created by ajaybabu.p on 2/16/2016.
 */
public class Constants {

    public static  String[] vegnamesarray={"ash gourd/white pumpkin","beetroot","bottle gourd","bitter gourd","brinjal","cabbage","carrot","capsicum","cauliflower","cucumber","drumsticks","green chilli","ginger",
            "mushroom","lady finger","onion","potato","peas","pumpkin","radish","unripe raw banana","red chili","snake gourd","sweet potato","tomato","taro roots","gherkins","elephant yam"};
    public static String[] nameslist={"Mehadipatnam","Erragadda","JNTU","Kothapeta"};
    public static String[] price={"20","15","17","30","20","15","17","20","15","17","30","20","15","17","20","15","17","30","20","15","17","20","15","17","30","20","15","17"};
    public static String[] price1={"11","15","17","19","11","15","17","11","15","17","19","11","15","17","11","15","17","19","11","15","17","11","15","17","19","11","15","17"};
    public static String[] price2={"20","21","27","23","20","21","27","20","21","27","23","20","21","27","20","21","27","23","20","21","27","20","21","27","23","20","21","27"};
    public static String[] price3={"31","35","37","30","31","35","37","31","35","37","30","31","35","37","31","35","37","30","31","35","37","31","35","37","30","31","35","37"};
    public static String[] price4={"42","45","47","43","42","45","47","42","45","47","43","42","45","47","42","45","47","43","42","45","47","42","45","47","43","42","45","47"};

    public static HashMap<String,String> getHashMap(int position)
    {
        HashMap<String,String> hm=new HashMap<String,String>();
        hm.clear();
        if(position==0)
        {
            hm.clear();
            for(int i=0;i<vegnamesarray.length;i++)
            {
                hm.put(vegnamesarray[i].toString(),price[i]);
            }
        }
        else if(position==1)
        {
            hm.clear();
            for(int i=0;i<vegnamesarray.length;i++)
            {
                hm.put(vegnamesarray[i].toString(),price[i]);
            }
        }
        else if(position==2)
        {
            hm.clear();
            for(int i=0;i<vegnamesarray.length;i++)
            {
                hm.put(vegnamesarray[i].toString(),price[i]);
            }
        }
       else
        {
            hm.clear();
            for(int i=0;i<vegnamesarray.length;i++)
            {
                hm.put(vegnamesarray[i].toString(),price[i]);
            }
        }
        return hm;
    }
}
