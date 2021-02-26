package org.json.junit;

import org.junit.Assert;
import org.junit.Test;
//import org.json.JSONArray;
import org.json.JSONObject;
//import org.json.XML;

import java.io.*;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.json.*;


public class XML_ms4 {
    
    @Test
    public void Test1() throws Exception {

        String link = "src/main/java/xml/records.xml";
        File file = new File(link);

        String testKey = "BOTANICAL";
        FileReader fileReader = new FileReader(file);
        JSONObject jsonObject = XML.toJSONObject(fileReader);


        List<JSONObject> Objects = jsonObject.toStream().filter(o -> o.keys().next().equals(testKey)).collect(Collectors.toList());
        System.out.println(Objects);

        for(JSONObject obj:Objects){
           for(String key:obj.keySet()){
               Assert.assertEquals(key,testKey);
           }
        }
    }

    @Test
    public void Test2() throws Exception {

        String link = "src/main/java/xml/records.xml";
        File file = new File(link);
        FileReader fileReader = new FileReader(file);
        JSONObject jsonObject = XML.toJSONObject(fileReader);
        String header = "";

        jsonObject.toStream().forEach(o -> {
                for(String key:o.keySet()){
                    key = header + key;
                }
        });
        List<JSONObject> Objects = jsonObject.toStream().collect(Collectors.toList());
        System.out.println(Objects);

        for(JSONObject obj:Objects){
            for(String key:obj.keySet()){
                Assert.assertTrue(key.startsWith(header));
            }
        }
    }

    @Test
    public void Test3() throws Exception {

        String link = "src/main/java/xml/records.xml";
        File file = new File(link);
        FileReader fileReader = new FileReader(file);
        JSONObject jsonObject = XML.toJSONObject(fileReader);
        
        //Change map to public and test
        long leaf_num = jsonObject.toStream().map(o -> o.length()).collect(Collectors.summarizingInt(Integer::intValue)).getSum();
        Assert.assertEquals(leaf_num,55);

    }

}
