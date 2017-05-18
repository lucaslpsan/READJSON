package main.java.ReadJSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;

        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONArray readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            //String str;
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);

            //jsonText = jsonText.substring(1, jsonText.length() - 1);
            //str = jsonText.replaceAll("\"", "\\\"");
            //str = jsonText.replaceAll("},", "}");

            //System.out.println(str);

            JSONArray json = new JSONArray(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static String transformaJson(JSONArray jsonArray){
        StringBuilder array = new StringBuilder("[[Data, Valor],");
        String string;
        JSONObject json;

        for(int i = 0; i < jsonArray.length(); i++){
            array.append("[");

            json = jsonArray.getJSONObject(i);

            array.append(json.get("data") + ",");
            array.append(json.get("valor") + "],");
        }

        string = array.substring(0, array.length() - 1) + "]";

        return string;
    }

    public static void main(String[] args) throws IOException, JSONException {
        JSONArray jsonArray = readJsonFromUrl("http://api.bcb.gov.br/dados/serie/bcdata.sgs.24961/dados?formato=json");
        /*JSONObject json = new JSONObject(jsonArray.get(0).toString());
        System.out.println(jsonArray.toString());
        System.out.println(jsonArray.get(0));
        System.out.println(json.get("data"));*/
        System.out.println(transformaJson(jsonArray));

    }
}
