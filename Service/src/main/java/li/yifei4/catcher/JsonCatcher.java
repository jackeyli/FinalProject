package li.yifei4.catcher;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.DataInputStream;
import java.io.IOException;

public class JsonCatcher {
    public JsonElement catchFromURL(String url){
        HttpClient client = HttpClientBuilder.create().build();
        HttpUriRequest request = new HttpGet(url);
        DataInputStream dis = null;
        try {

            dis = new DataInputStream(client.execute(request).getEntity().getContent());
            byte[] buffer = new byte[4000];
            byte[] result = new byte[0];
            int len = 0;
            while((len = dis.read(buffer)) > 0) {
                byte[] tmp = result;
                result = new byte[tmp.length + len];
                System.arraycopy(tmp,0,result,0,tmp.length);
                System.arraycopy(buffer,0,result,tmp.length,len);
            }
            return new JsonParser().parse(new String(result,"UTF-8"));
        } catch(IOException e) {
            throw new RuntimeException("catch data from " + url + "failed");
        }finally{
            try {
                dis.close();
            } catch (IOException e) {
                throw new RuntimeException("catch data from " + url + "failed");
            }
        }
    }
}
