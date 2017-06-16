package ru.otus.l111.references;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tully.
 * <p>
 * VM options: -Xmx256m -Xms256m
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        weak();
        //soft();
        //phantom();
    }

    private static void weak() {
        int size = 1000;
        List<WeakReference<BigObject>> weakReferences = new ArrayList<>(size);

        for (int k = 0; k < size; k++) {
            weakReferences.add(new WeakReference<>(new BigObject()));
        }

        //System.gc();

        int sum = 0;
        for (int k = 0; k < size; k++) {
            if (weakReferences.get(k).get() != null) sum++;
        }

        System.out.println("Weak references: " + sum);
    }

    private static void soft() {
        int size = 1000;
        List<SoftReference<BigObject>> weakReferences = new ArrayList<>(size);

        for (int k = 0; k < size; k++) {
            weakReferences.add(new SoftReference<>(new BigObject()));
        }

        //System.gc();

        int sum = 0;
        for (int k = 0; k < size; k++) {
            if (weakReferences.get(k).get() != null) sum++;
        }

        System.out.println("Soft references: " + sum);
    }


    private static void phantom() throws InterruptedException {
        //Strong Reference
        BigObject a = new BigObject();

        //Creating ReferenceQueue
        ReferenceQueue<BigObject> refQueue = new ReferenceQueue<>();

        //Creating Phantom Reference to A-type object to which 'a' is also pointing
        PhantomReference<BigObject> phantomA = new PhantomReference<>(a, refQueue);
        System.out.println("Big object length:" + refQueue.poll());

        a = null;
        //Now, A-type object to which 'a' is pointing earlier is available for garbage collection.
        //But, this object is kept in 'refQueue' before removing it from the memory.

        a = phantomA.get();    //it always returns null

        System.gc();
        Thread.sleep(100);
        System.out.println("Big object length:" + refQueue.poll());

    }

    static class BigObject {
        final byte[] array = new byte[1024 * 1024];

        public byte[] getArray() {
            return array;
        }
    }
}
