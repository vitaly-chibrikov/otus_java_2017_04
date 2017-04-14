package ru.otus.l32;

import java.util.*;

/**
 * Created by tully.
 * <p>
 * Common cases:
 * {@link #listToArray(List) List to Array},
 * {@link #setToArray(Set) Set to Array},
 * {@link #setToArrayList(Set) Set to ArrayList},
 * {@link #listToHashSet(List) List to HashSet},
 * {@link #singletonList(Object) List with one element},
 * {@link #singletonSet(Object) Set with one element},
 * {@link #listFromArray(Object[]) List from array},
 * {@link #immutable(Collection) Immutable collection}
 */
public class Main {
    public static void main(String... args) {

    }

    private static Object[] listToArray(List<Object> list) {
        return list.toArray(new Object[list.size()]);
    }

    private static Object[] setToArray(Set<Object> set) {
        return set.toArray(new Object[set.size()]);
    }

    private static List<Object> setToArrayList(Set<Object> set) {
        return new ArrayList<>(set);
    }

    private static Set<Object> listToHashSet(List<Object> list) {
        return new HashSet<>(list);
    }

    private static List<Object> singletonList(Object object) {
        return Collections.singletonList(object);
    }

    private static Set<Object> singletonSet(Object object) {
        return Collections.singleton(object);
    }

    private static List<Object> listFromArray(Object[] array) {
        //return new ArrayList<Object>(array);
        return Arrays.asList(array);
    }

    private static Collection<Object> immutable(Collection<Object> collection) {
        return Collections.unmodifiableCollection(collection);
    }
}
