package ru.otus.l41;

/**
 * Created by tully.
 */
class Benchmark implements BenchmarkMBean {
    private int size = 0;

    void run() {
        Object[] array = new Object[size];
        System.out.println("Array of size: " + array.length + " created");

        int n = 0;
        int currentSize = size;
        System.out.println("Starting the loop");
        while (n < Integer.MAX_VALUE) {
            int i = n % currentSize;
            array[i] = new String(new char[0]);
            n++;
            if (n % currentSize == 0) {
                logs(n);
                currentSize = size;
                array = new Object[currentSize];
            }
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    private void logs(int n) {
        System.out.println("Created " + n + " objects");
        System.out.println("Creating new array of size: " + size);
    }
}
