import org.json.*;
import java.io.*;
import java.util.*;

public class milestone1 {
    public static void main(String[] args) throws IOException {
        String link = "xml/records.xml";
        //task2(link, "/CATALOG/PLANT");
        //task3(link, "/CATALOG/PLANT");
        //task4(link);
        JSONObject jo = new JSONObject();
        jo.put("name", "jon doe");
        jo.put("age", "22");
        jo.put("city", "chicago");
        task5(link,"/CATALOG/TestKey", jo);
    }

    private static void task1(String path) throws JSONException, IOException{
        File b = new File(path);
        String line="",str="";
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.json"));
        BufferedReader br = new BufferedReader(new FileReader(b.getAbsolutePath()));
        while ((line = br.readLine()) != null)
        {
            str+=line;
        }
        writer.write(XML.toJSONObject(str).toString(4));
        writer.close();
        br.close();
    }

    public static Object task2(String path, String subObject)throws JSONException, IOException{
        File b = new File(path);
        String line="",str="";
        BufferedWriter writer = new BufferedWriter(new FileWriter("output2.json"));
        BufferedReader br = new BufferedReader(new FileReader(b.getAbsolutePath()));
        while ((line = br.readLine()) != null)
        {
            str+=line;
        }
        JSONObject json = XML.toJSONObject(str);
        JSONPointer pointer = new JSONPointer(subObject);

        Object ob = pointer.queryFrom(json);

        if(ob instanceof JSONObject)
            writer.write(((JSONObject) ob).toString(4));
        else if(ob instanceof JSONArray)
            writer.write(((JSONArray) ob).toString(4));
        else
            writer.write(ob.toString());

        writer.close();
        br.close();
        return ob;
    }

    private static void task3(String path, String subObject)throws JSONException, IOException{
        File b = new File(path);
        String line="",str="";
        BufferedWriter writer = new BufferedWriter(new FileWriter("output3.json"));
        BufferedReader br = new BufferedReader(new FileReader(b.getAbsolutePath()));
        while ((line = br.readLine()) != null)
        {
            str+=line;
        }
        JSONObject json = XML.toJSONObject(str);
        JSONPointer pointer = new JSONPointer(subObject);
        Object ob = pointer.queryFrom(json);
        if(ob != null) {
            if (ob instanceof JSONObject)
                writer.write(((JSONObject) ob).toString(4));
            else if (ob instanceof JSONArray)
                writer.write(((JSONArray) ob).toString(4));
            else
                writer.write(ob.toString());
        }

        writer.close();
        br.close();
    }

    private static void task4(String path) throws IOException {
        File b = new File(path);
        String line="",str="";
        BufferedWriter writer = new BufferedWriter(new FileWriter("output4.json"));
        BufferedReader br = new BufferedReader(new FileReader(b.getAbsolutePath()));
        while ((line = br.readLine()) != null)
        {
            str+=line;
        }
        JSONObject json = XML.toJSONObject(str);
        helper(json);
        writer.write(json.toString(4));
        writer.close();
        br.close();
    }

    private static void helper(Object o){
        if(o instanceof JSONObject){
            Map<String, Object> map = new HashMap<>(((JSONObject) o).toMap());
            for(Map.Entry<String, Object> entry: map.entrySet()){
                String ori = entry.getKey();
                String newKey = "swe262_" + ori;
                helper(((JSONObject) o).opt(ori));
                ((JSONObject)o).put(newKey, ((JSONObject) o).opt(ori));
                ((JSONObject)o).remove(ori);
            }
        }
        else if(o instanceof JSONArray) {
            for (int i = 0; i < ((JSONArray) o).length(); i++) {
                helper(((JSONArray) o).get(i));
            }
        }
    }

    public static Object task5(String path, String subObject, Object substitute) throws IOException {
        File b = new File(path);
        String line="",str="";
        BufferedWriter writer = new BufferedWriter(new FileWriter("output5.json"));
        BufferedReader br = new BufferedReader(new FileReader(b.getAbsolutePath()));
        while ((line = br.readLine()) != null)
        {
            str+=line;
        }
        JSONObject json = XML.toJSONObject(str);
        int i = subObject.length() - 1;
        for(; i>=0; i--){
            if(subObject.charAt(i) == '/') break;
        }
        String key = subObject.substring(i+1, subObject.length());
        String parentkey = subObject.substring(0, i);
        Object ob = json.query(parentkey);
        ((JSONObject)ob).put(key, substitute);
        writer.write(((JSONObject)ob).toString(4));
        writer.close();
        br.close();
        return json;
    }
}

