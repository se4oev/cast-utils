package ru.se4oev;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CastUtilsTest {

    @Test
    void checkedListCast() {
        List<String> list = Arrays.asList("First", "Second", "Third");
        Object input = list;
        List<String> result = CastUtils.checkedListCast(input, String.class);

        assertNotNull(result);
        assertEquals(list.size(), result.size());
        assertInstanceOf(String.class, result.get(0));
        assertEquals(list, result);
    }

    @Test
    void checkedListCastRaw() {
        List list = Arrays.asList("First", "Second", "Third");
        List<String> result = CastUtils.checkedListCast(list, String.class);

        assertNotNull(result);
        assertEquals(list.size(), result.size());
        assertInstanceOf(String.class, result.get(0));
        assertEquals(list, result);
    }

    @Test
    void checkedListCastRawError() {
        List list = new ArrayList();
        list.add("First");
        list.add("Second");
        list.add("Third");
        list.add(new Integer(299));

        ClassCastException exception =
                assertThrows(ClassCastException.class, () -> CastUtils.checkedListCast(list, String.class));

        assertEquals("Cannot cast class java.lang.Integer to class java.lang.String", exception.getMessage());
    }

}