package net.fs.cap;


public class CapServer {

    CapServer() {
        CapEnv capEnv;
        try {
            capEnv = new CapEnv(false, true);
            capEnv.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
