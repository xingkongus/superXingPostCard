package us.xingkong.xingpostcard.update;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Update extends Thread {

    public String result;
    public String version;
    public String up_url;
    private String url = "http://api.fir.im/apps/latest/56212c8cf2fc4229c6000001?api_token=";

    public Update() {

    }

    public void run() {
        try {
            URL httpUrl = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) httpUrl
                    .openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(3000);

            if (conn.getResponseCode() == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                StringBuffer sb = new StringBuffer();
                String str;

                while ((str = reader.readLine()) != null) {
                    sb.append(str);
                }
                result = new String(sb.toString().getBytes(), "utf-8");
                JSONObject object = new JSONObject(result);
                version = object.getString("versionShort");
                up_url = object.getString("installUrl");
                System.out.println("4sssssssssssss"+version+up_url);
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
