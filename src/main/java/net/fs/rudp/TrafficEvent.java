package net.fs.rudp;

public class TrafficEvent {

    public static int type_downloadTraffic = 10;
    public static int type_uploadTraffic = 11;
    long eventId;
    int traffic;
    int type = type_downloadTraffic;

    String userId;

    TrafficEvent(long eventId, int traffic, int type) {
        this(null, eventId, traffic, type);
    }

    public TrafficEvent(String userId, long eventId, int traffic, int type) {
        this.userId = userId;
        this.eventId = eventId;
        this.traffic = traffic;
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public int getTraffic() {
        return traffic;
    }

    public void setTraffic(int traffic) {
        this.traffic = traffic;
    }


}
