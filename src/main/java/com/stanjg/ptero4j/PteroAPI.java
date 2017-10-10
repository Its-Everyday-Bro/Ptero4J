package com.stanjg.ptero4j;

import com.stanjg.ptero4j.entities.PteroUser;
import org.json.JSONObject;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Stan Gabes on 10-10-2017.
 * Ask permission to Stan#1185 on discord in order to use this class. Unless specified otherwise
 * http://stangabes.com/
 */
public class PteroAPI {

    private String publicToken, privateToken;

    private Queue<PteroUser> cachedUsers;

    private boolean withCaching;
    private int cacheSize;

    public PteroAPI(String publicToken, String privateToken, String baseUrl, boolean withCache, int cacheSize) {
        this.publicToken = publicToken;
        this.privateToken = privateToken;
        this.withCaching = withCache;
        this.cacheSize = cacheSize;

        new RequestHandler(baseUrl);

        if (withCaching) {
            cachedUsers = new ConcurrentLinkedQueue<>();
        }
    }

    public PteroUser getUser(int id) {
        if (withCaching) {
            PteroUser user = cachedUsers.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
            if (user != null)
                return user;
        }

        JSONObject res = getJsonObject("api/admin/users/" + id);
        System.out.println(res);
        if (res.has("http_code"))
            if (res.getInt("http_code") != 200)
                return null;
        JSONObject user = res.getJSONObject("data");
        JSONObject attr = user.getJSONObject("attributes");

        String firstname = attr.getString("name_first");
        String lastname = attr.getString("name_last");
        String updated = attr.getString("updated_at");
        String created = attr.getString("created_at");
        String language = attr.getString("language");
        boolean isAdmin = attr.getInt("root_admin") == 1;
        String uuid = attr.getString("uuid");
        String email = attr.getString("email");
        String username = attr.getString("username");

        PteroUser puser = new PteroUser(id, firstname, lastname, updated, created, language, isAdmin, uuid, email, username);

        if (withCaching) {
            if (cachedUsers.size() >= cacheSize)
                cachedUsers.poll();
            cachedUsers.add(puser);
        }

        return puser;
    }

    private JSONObject getJsonObject(String url) {
        return RequestHandler.getInstance().requestJsonObject(privateToken, publicToken, url);
    }

}
