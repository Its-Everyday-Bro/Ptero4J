import com.stanjg.ptero4j.PteroAPI;
import com.stanjg.ptero4j.PteroAPIBuilder;
import com.stanjg.ptero4j.entities.PteroUser;

/**
 * Created by Stan Gabes on 10-10-2017.
 * Ask permission to Stan#1185 on discord in order to use this class. Unless specified otherwise
 * http://stangabes.com/
 */
public class Main {

    public static void main(String[] args) {
        PteroAPI pteroAPI = new PteroAPIBuilder()
                .withPublicToken("")
                .withPrivateToken("")
                .withBaseUrl("")
                .withCaching(true)
                .build();

        /*
        A test to test how big the effect of caching is
         */

        long start = System.currentTimeMillis();

        PteroUser user = pteroAPI.getUser(2);
        System.out.println(user.getId() + " " + user.getFullName());

        System.out.println(System.currentTimeMillis() - start);


        start = System.currentTimeMillis();

        user = pteroAPI.getUser(2);
        System.out.println(user.getId() + " " + user.getFullName());

        System.out.println(System.currentTimeMillis() - start);
    }

}
