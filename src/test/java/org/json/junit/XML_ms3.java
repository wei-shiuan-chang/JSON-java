package org.json.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.json.*;
import org.junit.Test;
import org.mockito.internal.matchers.StartsWith;


public class XML_ms3 {

    

    public static class Transformer1 implements Function<String, String> {

        @Override
        public String apply(String input) {
            return "swe262_" + input;
        }
    }

    @Test
    public void Test1() throws IOException {
        String link = "src/main/java/xml/records.xml";
        File file = new File(link);
        Reader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
        JSONObject jo = new JSONObject();

        
        Function<String, String> transformer = new Transformer1();
        
        jo = XML.toJSONObject(br,transformer);
        Pattern p = Pattern.compile("^swe262_*$");
        for(Map.Entry<String, Object> entry: jo.toMap().entrySet()){
            assertTrue(entry.getKey().startsWith("swe262_"));
        }
        
    }

    public static class Transformer2 implements Function<String, String> {

        @Override
        public String apply(String input) {
            return input + "_swe262";
        }
    }

    @Test
    public void Test2() throws IOException {
        String link = "src/main/java/xml/records.xml";
        File file = new File(link);
        Reader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
        JSONObject jo = new JSONObject();

        Function<String, String> transformer = new Transformer2();
        
        jo = XML.toJSONObject(br,transformer);
        for(Map.Entry<String, Object> entry: jo.toMap().entrySet()){
            assertTrue(entry.getKey().endsWith("_swe262"));
        }
    }


    public static class Transformer3 implements Function<String, String> {

        @Override
        public String apply(String input) {
            
            StringBuilder builder = new StringBuilder();
            for(char c:input.toCharArray()){
                builder.append((int)c);
            }
            return builder.toString();

        }
    }

    @Test
    public void Test3() throws IOException {
        String link = "src/main/java/xml/records.xml";
        File file = new File(link);
        Reader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
        JSONObject jo = new JSONObject();

        
        Function<String, String> transformer = new Transformer3();
        
        jo = XML.toJSONObject(br,transformer);
        Pattern p = Pattern.compile("^[0-9]+$");
        for(Map.Entry<String, Object> entry: jo.toMap().entrySet()){
            Matcher m = p.matcher(entry.getKey());
            assertTrue(m.matches());
        }
    }



}


