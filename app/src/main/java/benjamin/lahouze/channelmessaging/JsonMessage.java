package benjamin.lahouze.channelmessaging;

/**
 * Created by Benjamin
 */

public class JsonMessage {

    private String userID;

    public String getUserID() {
        return userID;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    private String message;
    private String date;
    private String imageUrl;
    private String username;
    private String longitude;
    private String latitude;

    public String getUsername() {
        return username;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }
}
