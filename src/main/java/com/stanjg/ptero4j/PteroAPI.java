package com.stanjg.ptero4j;

import com.stanjg.ptero4j.entities.PteroServer;
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

    private static PteroAPI instance;
    public static PteroAPI getInstance() {
        return instance;
    }

    private String publicToken, privateToken;

    private Queue<PteroUser> cachedUsers;
    private Queue<PteroServer> cachedServers;

    private boolean withCaching;
    private int cacheSize;

    public PteroAPI(String publicToken, String privateToken, String baseUrl, boolean withCache, int cacheSize) {
        instance = this;

        this.publicToken = publicToken;
        this.privateToken = privateToken;
        this.withCaching = withCache;
        this.cacheSize = cacheSize;

        new RequestHandler(baseUrl);

        if (withCaching) {
            cachedUsers = new ConcurrentLinkedQueue<>();
            cachedServers = new ConcurrentLinkedQueue<>();
        }
    }

    public PteroServer getServer(int id) {
        if (withCaching) {
            PteroServer server = cachedServers.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
            if (server != null)
                return server;
        }

        JSONObject res = getJsonObject("api/admin/servers/" + id);
        if (failed(res))
            return null;

        JSONObject attr = res.getJSONObject("data").getJSONObject("attributes");

        String image = attr.getString("image");
        boolean installed = attr.getInt("installed") == 1;
        int memory = attr.getInt("memory");
        String shortUuid = attr.getString("uuidShort");
        String uuid = attr.getString("uuid");
        int swap = attr.getInt("swap");
        int ownerId = attr.getInt("owner_id");
        int io = attr.getInt("io");
        String description = attr.getString("description");
        int cpu = attr.getInt("cpu");
        String created = attr.getString("created_at");
        String updated = attr.getString("updated_at");
        boolean suspended = attr.getInt("suspended") == 1;
        boolean oomDisabled = attr.getInt("oom_disabled") == 1;
        int disk = attr.getInt("disk");
        String startup = attr.getString("startup");
        int serviceId = attr.getInt("service_id");
        int optionId = attr.getInt("option_id");
        String name = attr.getString("name");
        int allocationId = attr.getInt("allocation_id");
        int nodeId = attr.getInt("node_id");
        String username = attr.getString("username");

        PteroServer pserver = new PteroServer(id,
                image, installed,
                memory, shortUuid,
                uuid, swap,
                ownerId, io,
                description, updated,
                cpu, created,
                suspended, oomDisabled,
                disk, startup,
                serviceId, optionId,
                name, allocationId,
                nodeId, username);

        if (withCaching) {
            if (cachedServers.size() >= cacheSize)
                cachedServers.poll();
            cachedServers.add(pserver);
        }

        return pserver;
    }

    public PteroUser getUser(int id) {
        if (withCaching) {
            PteroUser user = cachedUsers.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
            if (user != null)
                return user;
        }

        JSONObject res = getJsonObject("api/admin/users/" + id);

        if (failed(res))
            return null;

        JSONObject attr = res.getJSONObject("data").getJSONObject("attributes");

        String firstname = attr.getString("name_first");
        String lastname = attr.getString("name_last");
        String updated = attr.getString("updated_at");
        String created = attr.getString("created_at");
        String language = attr.getString("language");
        boolean isAdmin = attr.getInt("root_admin") == 1;
        String uuid = attr.getString("uuid");
        String email = attr.getString("email");
        String username = attr.getString("username");

        PteroUser puser = new PteroUser(id,
                firstname, lastname,
                updated, created,
                language, isAdmin,
                uuid, email,
                username);

        if (withCaching) {
            if (cachedUsers.size() >= cacheSize)
                cachedUsers.poll();
            cachedUsers.add(puser);
        }

        return puser;
    }

    private boolean failed(JSONObject response) {
        if (!response.has("http_code"))
            return false;
        if (response.getInt("http_code") != 200)
            return true;
        else
            return false;
    }

    private JSONObject getJsonObject(String url) {
        return RequestHandler.getInstance().requestJsonObject(privateToken, publicToken, url);
    }

}
