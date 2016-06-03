package net.fs.utils;

import java.net.DatagramPacket;


public class MessageCheck {
    public static int checkVer(DatagramPacket dp) {
        return (int) ByteShortConvert.toShort(dp.getData(), 0);
    }

    public static int checkSType(DatagramPacket dp) {
        return (int) ByteShortConvert.toShort(dp.getData(), 2);
    }
}
