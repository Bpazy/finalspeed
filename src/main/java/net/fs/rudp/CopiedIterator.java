package net.fs.rudp;

import java.util.Iterator;
import java.util.LinkedList;

public class CopiedIterator<T> implements Iterator {
    private Iterator<T> iterator = null;

    public CopiedIterator(Iterator<T> itr) {
        LinkedList<T> list = new LinkedList<>();
        while (itr.hasNext()) {
            list.add(itr.next());
        }
        this.iterator = list.iterator();
    }

    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    public void remove() {
        throw new UnsupportedOperationException("This is a read-only iterator.");
    }

    public T next() {
        return this.iterator.next();
    }
}
