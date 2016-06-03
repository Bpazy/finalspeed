package net.fs.rudp;


public interface ConnectionProcessor {
    void process(final ConnectionUDP conn);
}
