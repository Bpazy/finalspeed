package net.fs.rudp;

import java.net.DatagramPacket;

public interface MessageInterface {
    int getVer();

    int getSType();

    DatagramPacket getDatagramPacket();
}
  
