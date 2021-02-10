<<<<<<< HEAD
import org.junit.Test;
import org.json.JSONObject;
import org.json.JSONPointer;
import org.json.XML;
import static org.junit.Assert.*;
=======
import org.json.JSONObject;
import org.json.JSONPointer;
import org.json.XML;

>>>>>>> 6b79b946d566396810972da293b87c6d29bf076d

import java.io.*;

public class Main {
<<<<<<< HEAD
    //extract test1
    //extract test on regular xml
    @Test
    public void TestExtract1() throws IOException {
        String link = "src/main/java/xml/records.xml";
        File file = new File(link);
        Reader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
        JSONPointer pointer = new JSONPointer("/CATALOG/TestKey");
        XML xml = new XML();
        //reset static variables
        xml.res = new JSONObject();
        xml.count = 0;
        Object jo = xml.extractJSONObject(br, pointer);
        //Use the result of milestone to verify
        assertEquals(milestone1.task2(link, "/CATALOG/TestKey").toString(), jo.toString());
        System.out.println(((JSONObject) jo).toString(4));
    }
    //extract test2
    //Change the position of testKey to see if the parsing stop right after reading the target's value.
    @Test
    public void TestExtract2() throws IOException {
        String link = "src/main/java/xml/records2.xml";
        File file = new File(link);
        Reader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
        JSONPointer pointer = new JSONPointer("/CATALOG/TestKey");
        XML xml = new XML();
        //reset static variables
        xml.res = new JSONObject();
        xml.count = 0;
        Object jo = xml.extractJSONObject(br, pointer);
        //Use the result of milestone to verify
        assertEquals(milestone1.task2(link, "/CATALOG/TestKey").toString(), jo.toString());
        System.out.println(((JSONObject) jo).toString(4));
    }
    //extract test3
    //extract test on nested file
    @Test
    public void TestExtract3() throws IOException {
        String link = "src/main/java/xml/nested.xml";
        File file = new File(link);
        Reader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
        JSONPointer pointer = new JSONPointer("/items/CD");
        XML xml = new XML();
        //reset static variables
        xml.res = new JSONObject();
        xml.count = 0;
        JSONObject jo = xml.extractJSONObject(br, pointer);
        //Use the result of milestone to verify
        assertEquals(milestone1.task2(link, "/items/CD").toString(), jo.toString());
        System.out.println(jo.toString(4));
    }
    //replace test1
    //replace test on regular test
    @Test
    public void TestReplace1() throws Exception {

        String link = "src/main/java/xml/records.xml";
        File file = new File(link);
        Reader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
        JSONObject jo = new JSONObject();
        JSONPointer pointer = new JSONPointer("/CATALOG/TestKey");
        jo.put("name", "jon doe");
        jo.put("age", "22");
        jo.put("city", "chicago");
        XML xm = new XML();
        xm.OutputCount = 0;
        JSONObject res =  xm.replaceJSONObject(br, pointer, jo);
        System.out.println(res.toString(4));
        //Use the result of milestone to verify
        assertEquals(milestone1.task5(link,"/CATALOG/TestKey", jo).toString(), res.toString());
        br.close();
        return;
    }
    //replace test2
    //replace tset on nested file
    @Test
    public void TestReplace2() throws Exception {
        String link = "src/main/java/xml/nested.xml";
        File file = new File(link);
        Reader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
        JSONObject jo = new JSONObject();
        JSONPointer pointer = new JSONPointer("/items/CD");
        jo.put("name", "jon doe");
        jo.put("age", "22");
        jo.put("city", "chicago");
        XML xm = new XML();
        JSONObject res =  xm.replaceJSONObject(br, pointer, jo);
        //Use the result of milestone to verify
        assertEquals(milestone1.task5(link, "/items/CD", jo).toString(), res.toString());
        System.out.println(res.toString(4));
        br.close();
        return;
    }
}

=======




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
>>>>>>> 6b79b946d566396810972da293b87c6d29bf076d
