package com.example.examination.utils;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;

public class ObjectUtils {
    public static boolean isNull(Object object){
        return object == null;
    }

    public static boolean allNotNull(final Object... values) {
        if (values == null) {
            return false;
        }
        for (final Object val : values) {
            if (val == null) {
                return false;
            }
        }
        return true;
    }


    public static boolean isContainsIn(AbstractCollection<Object> objects, Object keys) {
        if (!allNotNull(objects, keys)) {
            return false;
        }
        return objects.contains(keys);
    }

    public static boolean isContainsIn(List<Integer> objects, Integer keys) {
        if (!allNotNull(objects, keys)) {
            return false;
        }
        return objects.contains(keys);
    }

    public static <T> List<T>[] partition(List<T> listObject, Integer limitRow) {
        Integer currentSize = listObject.size();
        Integer sodu = currentSize / limitRow;
        if (currentSize % limitRow != 0) {
            sodu++;
        }

        List<T>[] subList = new ArrayList[sodu];
        for (int i = 0; i < sodu; i++) {
            subList[i] = new ArrayList();
        }

        for (int i = 0; i < currentSize; i++) {
            Integer index = i / limitRow;
            subList[index].add(listObject.get(i));
        }
        return subList;
    }
}
