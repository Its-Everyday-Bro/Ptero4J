import com.stanjg.ptero4j.PteroAPI;
import com.stanjg.ptero4j.PteroAPIBuilder;
import com.stanjg.ptero4j.entities.PteroServer;
import com.stanjg.ptero4j.entities.PteroUser;

/**
 * Created by Stan Gabes on 10-10-2017.
 * Ask permission to Stan#1185 on discord in order to use this class. Unless specified otherwise
 * http://stangabes.com/
 */
public class Main {

    public static void main(String[] args) {
        PteroAPI api = new PteroAPIBuilder()
                .withPublicToken("")
                .withPrivateToken("")
                .withBaseUrl("")
                .withCaching(true)
                .build();

        System.out.println(api.getServer(32).getOwner().getFullName());
    }

    /**
     * A test to see how big the impact of caching is
     */
    private static void testCachingWithServer(PteroAPI api) {

        long start = System.currentTimeMillis();

        PteroServer server = api.getServer(32);
        System.out.println(server.getId() + " " + server.getCpu());

        System.out.println(System.currentTimeMillis() - start);


        start = System.currentTimeMillis();

        server = api.getServer(32);
        System.out.println(server.getId() + " " + server.getCpu());

        System.out.println(System.currentTimeMillis() - start);
    }

    /**
     * A test to see how big the impact of caching is
     */
    private static void testCachingWithUser(PteroAPI api) {

        long start = System.currentTimeMillis();

        PteroUser user = api.getUser(2);
        System.out.println(user.getId() + " " + user.getFullName());

        System.out.println(System.currentTimeMillis() - start);


        start = System.currentTimeMillis();

        user = api.getUser(2);
        System.out.println(user.getId() + " " + user.getFullName());

        System.out.println(System.currentTimeMillis() - start);
    }

}
