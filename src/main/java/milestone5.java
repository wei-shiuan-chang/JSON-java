import org.json.*;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class milestone5 {

    public interface method {
        public void run(JSONObject jo) throws Exception;
    }
//Normal test case
    @Test
    public void task1() throws Exception {
        System.out.println("--------------task1 starts--------------");
        String link = "src/main/java/xml/records.xml";
        File file = new File(link);
        Reader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
        method m = jo -> {
            BufferedWriter writer = new BufferedWriter(new FileWriter("milestone5.json"));
            writer.write(jo.toString(4));
            writer.close();
        };
        asyncToJSONObject(br, m);
    }
    //Test if users provide invalid function to execute after getting the JSONObject.
    @Test
    public void task2() throws Exception {
        System.out.println("--------------task2 starts--------------");
        String link = "src/main/java/xml/records.xml";
        File file = new File(link);
        Reader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
        method m = jo -> {
            throw new Exception("Function provided contains error");
        };
        asyncToJSONObject(br, m);
    }

    //Test if the XML file is too large to finish before timeout.
    @Test
    public void task3() throws Exception {
        System.out.println("--------------task3 starts--------------");
        String link = "src/main/java/xml/large.xml";
        File file = new File(link);
        Reader br = new BufferedReader(new FileReader(file.getAbsolutePath()));
        method m = jo -> {
            BufferedWriter writer = new BufferedWriter(new FileWriter("milestone5.json"));
            writer.write(jo.toString(4));
            writer.close();
        };
        asyncToJSONObject(br, m);
    }

    public static void asyncToJSONObject(Reader reader, method fun) throws Exception {
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<JSONObject> future = executor.submit(() -> XML.toJSONObject(reader, XMLParserConfiguration.ORIGINAL));
        System.out.println("Not Blocking......");
        System.out.println("XML file processing......");
        JSONObject jo = null;
        try{
            //set timeout to 100 seconds
            jo = future.get(100, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.out.println("Conversion is not finished within 100 milliseconds");
        }
        //execute fun when the JSONObject becomes available
        if(jo != null){
            System.out.println("future get");
            try {
                fun.run(jo);
            }
            //The exception throw by fun is handled
            catch (Exception e){
                System.out.println(e.toString());
            }
        }
    }
}
