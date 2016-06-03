package net.fs.rudp;

public interface Trafficlistener {
    void trafficDownload(TrafficEvent event);

    void trafficUpload(TrafficEvent event);
}
