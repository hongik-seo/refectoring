import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UrlEncoder implements  IEncoder{

    public String encode(String msg){

        try {
            return URLEncoder.encode(msg, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
