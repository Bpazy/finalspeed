package net.fs.rudp;

import net.fs.utils.MLog;

import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;


public class ClientManager {

    final Object syn_clientTable = new Object();
    HashMap<Integer, ClientControl> clientTable = new HashMap<>();
    Thread mainThread;
    Route route;
    int receivePingTimeout = 8 * 1000;
    int sendPingInterval = 1000;

    ClientManager(Route route) {
        this.route = route;
        mainThread = new Thread() {
            @SuppressWarnings("InfiniteLoopStatement")
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    scanClientControl();
                }
            }
        };
        mainThread.start();
    }

    void scanClientControl() {
        CopiedIterator<Integer> it = getClientTableIterator();
        long current = System.currentTimeMillis();
        //MLog.println("ffffffffffff "+clientTable.size());
        while (it.hasNext()) {
            ClientControl cc = clientTable.get(it.next());
            if (cc != null) {
                if (current - cc.getLastReceivePingTime() < receivePingTimeout) {
                    if (current - cc.getLastSendPingTime() > sendPingInterval) {
                        cc.sendPingMessage();
                    }
                } else {
                    //超时关闭client
                    MLog.println("超时关闭client " + cc.dstIp.getHostAddress() + ":" + cc.dstPort + " " + new Date());
//					System.exit(0);
                    synchronized (syn_clientTable) {
                        cc.close();
                    }
                }
            }
        }
    }

    void removeClient(int clientId) {
        clientTable.remove(clientId);
    }

    CopiedIterator<Integer> getClientTableIterator() {
        CopiedIterator<Integer> it;
        synchronized (syn_clientTable) {
            it = new CopiedIterator<>(clientTable.keySet().iterator());
        }
        return it;
    }

    ClientControl getClientControl(int clientId, InetAddress dstIp, int dstPort) {
        ClientControl c = clientTable.get(clientId);
        if (c == null) {
            c = new ClientControl(route, clientId, dstIp, dstPort);
            synchronized (syn_clientTable) {
                clientTable.put(clientId, c);
            }
        }
        return c;
    }

}
