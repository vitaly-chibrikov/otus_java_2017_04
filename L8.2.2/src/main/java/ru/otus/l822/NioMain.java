package ru.otus.l822;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioMain {
    public static void main(String[] args) throws Exception {
        try (RandomAccessFile aFile = new RandomAccessFile("data/data.txt", "rw")) {
            FileChannel inChannel = aFile.getChannel();

            ByteBuffer buf = ByteBuffer.allocate(64);

            int bytesRead = inChannel.read(buf);
            while (bytesRead != -1) {

                System.out.println("Read " + bytesRead);
                buf.flip();

                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                System.out.print("\n");
                buf.clear();
                bytesRead = inChannel.read(buf);
            }
        }
    }
}
