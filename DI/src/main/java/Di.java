import java.net.URLEncoder;
import java.util.Base64;

public class Di {

    public static void main(String[] args) {

        String url = "www.newver.com/books/it?page=10&size=10&name=spring-boot";
        System.out.println("===============Before DI==========================");

        /*Base64Encoder encoder = new Base64Encoder();
        String result = encoder.encode(url);

        System.out.println(result);

        UrlEncoder urlEncoder = new UrlEncoder();

        String urlMsg=urlEncoder.encode(url);

        System.out.println(urlMsg);*/


        System.out.println("===============End OF Before DI==========================");

        System.out.println("---------------Di means Dependency Injection . " +
                "                          new Object injected where it is out of place   " +
                "                          Object depend on Interface . " +
                "                          it is used because loosing relations ----------------- ");

        System.out.println("===============After DI==========================");
        Encoder encoder = new Encoder(new Base64Encoder());
        String result = encoder.encode(url);

        System.out.println(result);

        Encoder encoder2 = new Encoder(new UrlEncoder());

        String urlMsg=encoder2.encode(url);

        System.out.println(urlMsg);


        System.out.println("===============End of After DI==========================");


    }
}
