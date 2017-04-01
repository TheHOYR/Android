package benjamin.lahouze.channelmessaging;

/**
 * Created by Benjamin
 */

public class JsonAccess {

    public String getAccesstoken() {
        return accesstoken;
    }

    public String getResponse() {
        return response;
    }

    private String response;
    private String code;
    private String accesstoken;

    public String getCode() {
        return code;
    }
}
