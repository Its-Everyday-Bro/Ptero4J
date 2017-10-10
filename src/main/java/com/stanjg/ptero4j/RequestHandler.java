package com.stanjg.ptero4j;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

/**
 * Created by Stan Gabes on 10-10-2017.
 * Ask permission to Stan#1185 on discord in order to use this class. Unless specified otherwise
 * http://stangabes.com/
 */
public class RequestHandler {

    private String baseUrl;

    private final String ALGORITHM = "HmacSHA256";

    private OkHttpClient client;

    public RequestHandler(String baseUrl) {
        instance = this;
        this.baseUrl = baseUrl;
        client = new OkHttpClient();
    }

    public JSONObject requestJsonObject(String privateToken, String publicToken, String url) {
        return new JSONObject(request(privateToken, publicToken, url));
    }

    public JSONArray requestJsonArray(String privateToken, String publicToken, String url) {
        return new JSONArray(request(privateToken, publicToken, url));
    }

    public String request(String privateToken, String publicToken, String url) {
        try {
            String to = baseUrl + url;

            Request request = new Request.Builder().url(to)
                    .header("Authorization", getAuthorizationHeader(publicToken, privateToken, to))
                    .build();

            return client.newCall(request).execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getAuthorizationHeader(String publicToken, String privateToken, String to) {
        try {
            Mac sha256_MAC = Mac.getInstance(ALGORITHM);
            SecretKeySpec secretKeySpec = new SecretKeySpec(privateToken.getBytes(), ALGORITHM);
            sha256_MAC.init(secretKeySpec);

            return "Bearer " + publicToken + "." + Base64.encodeBase64String(sha256_MAC.doFinal(to.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static RequestHandler instance;
    public static RequestHandler getInstance() {
        return instance;
    }

}
