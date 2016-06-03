package net.fs.utils;

public interface LogListener {

    void onAppendContent(LogOutputStream los, String text);

}
