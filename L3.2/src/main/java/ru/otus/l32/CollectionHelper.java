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
@SuppressWarnings({"unused", "WeakerAccess"})
public class CollectionHelper {
    
    public static Object[] listToArray(List<?> list) {
        return list.toArray(new Object[list.size()]);
    }

    public static Object[] setToArray(Set<?> set) {
        return set.toArray(new Object[set.size()]);
    }

    public static List<Object> setToArrayList(Set<?> set) {
        return new ArrayList<>(set);
    }

    public static Set<Object> listToHashSet(List<?> list) {
        return new HashSet<>(list);
    }

    public static List<Object> singletonList(Object object) {
        return Collections.singletonList(object);
    }

    public static Set<Object> singletonSet(Object object) {
        return Collections.singleton(object);
    }

    public static List<Object> listFromArray(Object[] array) {
        //return new ArrayList<Object>(array);
        return Arrays.asList(array);
    }

    public static Collection<Object> immutable(Collection<?> collection) {
        return Collections.unmodifiableCollection(collection);
    }
}
