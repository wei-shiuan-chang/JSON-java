import org.json.JSONObject;
import org.json.JSONPointer;
import org.json.XML;


import java.io.*;

public class Main {




    public static void main(String[] args) throws Exception {

        if(args[0].equals("1"))
            test1(args);
        else if(args[0].equals("2"))
            test2(args);
        else{
            System.out.println("Please provide 1 or 2 as the first argument.");
            return;
        }


    }

    public static void test1(String[] args)throws Exception{
        File b = new File(args[1]);
        JSONPointer pointer = new JSONPointer(args[2]);
        Reader br = new BufferedReader(new FileReader(b.getAbsolutePath()));

        XML xml = new XML();
        xml.toJSONObject(br,pointer);
    }

    public static void test2(String[] args)throws Exception{
        args[1] = "xml/records.xml";

        File b = new File(args[1]);
        JSONPointer pointer = new JSONPointer(args[2]);
        Reader br = new BufferedReader(new FileReader(b.getAbsolutePath()));
        JSONObject jo = new JSONObject();
        jo.put("name", "jon doe");
        jo.put("age", "22");
        jo.put("city", "chicago");

        XML xml = new XML();
        xml.toJSONObject(br,pointer,jo);

        br.close();
        return;
    }


}
