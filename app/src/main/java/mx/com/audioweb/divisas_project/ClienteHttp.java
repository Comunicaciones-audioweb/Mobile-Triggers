package mx.com.audioweb.divisas_project;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan Acosta on 8/28/2014.
 */
public class ClienteHttp {

    //public static final String URL = "http://206.225.83.102/mobile_trigger/getAlerts.php";
    public static final String URL = "http://66.226.72.48/mobile_trigger/getAlerts.php";
    private static final String DATEF = "yyyy-MM-dd HH:mm:ss";
    private static Gson gson = new GsonBuilder().setDateFormat(DATEF).create();

    public ArrayList<Contacto> contacto(String usr_id) throws JSONException {
        BufferedReader bufferedReader = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost request = new HttpPost(URL);
        List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("usr_id", usr_id));
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParameters);
            request.setEntity(entity);
            HttpResponse response = httpClient.execute(request);
            bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            bufferedReader.close();
            JSONArray jsonArray = new JSONArray(stringBuffer.toString());
            if (jsonArray != null) {
                ArrayList<Contacto> contactos = new ArrayList<Contacto>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    contactos.add(gson.fromJson(jsonArray.getJSONObject(i).toString(), Contacto.class));
                    Log.e("JSONOBJ", jsonArray.getJSONObject(i).toString());
                }
                return contactos;
            } else {
                return null;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
