package ru.se4oev;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by se4oev on 15.03.2024.
 * Description:
 */
public class CastUtils {

    public static <T> List<T> checkedListCast(Object value, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (value instanceof List) {
            ((List<?>) value).forEach(item -> {
                if (clazz.isInstance(item)) {
                    result.add(clazz.cast(item));
                } else {
                    throw new ClassCastException("Cannot cast " + item.getClass() + " to " + clazz);
                }
            });
        }
        return result;
    }

}
