package ru.otus.l21;


import java.lang.instrument.Instrumentation;

/**
 * Created by tully.
 */
public class Agent {
    public static void premain(String args, Instrumentation instrumentation) {
        System.out.println("Classes loaded: " + instrumentation.getAllLoadedClasses().length);
        System.out.println("Object size: " + instrumentation.getObjectSize(new Object()));
        System.out.println("String size: " + instrumentation.getObjectSize(new String("test"))); //"shallow" size.
        System.out.println("int[10] size: " + instrumentation.getObjectSize(new int[10]));
    }
}
