package vn.edu.lethanhtung.messengappproject;

public class msgModelclass {
    String message;
    String senderid;
    long timeStamp;
    String imageUrl;

    public msgModelclass() {
    }

    public msgModelclass(String message, String senderid, long timeStamp, String imageUrl) {
        this.message = message;
        this.senderid = senderid;
        this.timeStamp = timeStamp;
        this.imageUrl = imageUrl;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}