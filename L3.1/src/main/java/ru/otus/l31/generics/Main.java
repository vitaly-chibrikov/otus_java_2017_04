package ru.otus.l31.generics;

import java.lang.reflect.ParameterizedType;

/**
 * Created by tully.
 */
public class Main {
    public static void main(String... args) throws NoSuchFieldException, IllegalAccessException {
        GenericClassExample integerClassExample = new GenericClassExample<Number>(1);
        printTypeFromClass(integerClassExample.getClass());
        printTypeFromObject(integerClassExample);
        System.out.println();

        GenericClassExample doubleClassExample = new GenericClassExample<Number>(1.);
        printTypeFromClass(doubleClassExample.getClass());
        printTypeFromObject(doubleClassExample);
        System.out.println();

        GenericClassExample stringClassExample = new GenericClassExample<String>();
        printTypeFromClass(stringClassExample.getClass());
        //printTypeFromObject(stringClassExample);
        System.out.println();

        //NumberClassExample
        ParameterizedType t = (ParameterizedType) NumberClassExample.class.getGenericSuperclass();
        System.out.println(t.getActualTypeArguments()[0]);
    }

    private static void printTypeFromClass(Class<? extends GenericClassExample> clazz) throws IllegalAccessException, NoSuchFieldException {
        System.out.println(clazz
                        .getDeclaredField("value")
                        .getType()
        );
    }

    private static void printTypeFromObject(GenericClassExample genericClassExample) throws IllegalAccessException, NoSuchFieldException {
        System.out.println(genericClassExample.getClass()
                .getDeclaredField("value")
                .get(genericClassExample)
                .getClass()
                .getCanonicalName()
        );
    }

}
