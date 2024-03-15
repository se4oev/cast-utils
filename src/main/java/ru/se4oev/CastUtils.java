package ru.se4oev;

import java.util.*;

/**
 * Created by se4oev on 15.03.2024.
 * Description:
 */
public class CastUtils {

    public static <T> List<T> checkedListCast(Object value, Class<T> clazz) {
        Objects.requireNonNull(value, "Value must not be empty");
        Objects.requireNonNull(clazz, "Type must not be empty");
        assertIsList(value);

        List<T> result = new ArrayList<>();

        ((List<?>) value).forEach(item -> {
            assertIs(item, clazz);
            result.add(cast(item, clazz));
        });
        return result;
    }

    public static <K, V> List<Map<K, V>> listOfMapCheckedCast(Object value, Class<K> keyClazz, Class<V> valueClazz) {
        Objects.requireNonNull(value, "Value must not be empty");
        Objects.requireNonNull(keyClazz, "Type must not be empty");
        Objects.requireNonNull(valueClazz, "Type must not be empty");
        assertIsList(value);

        List<Map<K, V>> result = new ArrayList<>();
        ((List<?>) value).forEach(item -> {
            assertIsMap(item);
            Map<K, V> resultMap = new HashMap<>();
            ((Map<?, ?>) item).forEach((k, v) -> {
                if (isInstance(k, keyClazz) && isInstance(v, valueClazz)) {
                    resultMap.put(keyClazz.cast(k), valueClazz.cast(v));
                }
            });
            result.add(resultMap);
        });
        return result;
    }

    private static boolean isInstance(Object value, Class<?> clazz) {
        return clazz.isInstance(value);
    }

    private static <T> T cast(Object value, Class<T> clazz) {
        return clazz.cast(value);
    }

    private static void assertIs(Object value, Class<?> clazz) {
        if (!isInstance(value, clazz)) {
            throw new ClassCastException("Cannot cast " + value.getClass() + " to " + clazz);
        }
    }

    private static void assertIsList(Object value) {
        assertIs(value, List.class);
    }

    private static void assertIsMap(Object value) {
        assertIs(value, Map.class);
    }

}
