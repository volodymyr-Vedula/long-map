package de.comparus.opensource.longmap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongMapImplTest {
    private LongMapImpl<Integer> map;

    @BeforeEach
    public void init(){
        map = new LongMapImpl<>();
    }

    @Test
    void put() {
        assertEquals(0, map.size());
        map.put(1,1);
        map.put(2,2);
        assertEquals(2, map.size());
        map.put(2,222);
        assertEquals(2, map.size());
        map.put(3,null);
        assertEquals(3, map.size());
    }

    @Test
    void get() {
        map.put(1,1);
        map.put(25,2);
        map.put(451,3);
        assertEquals(1, map.get(1));
        assertEquals(2, map.get(25));
        assertEquals(3, map.get(451));
        assertEquals(3, map.size());
    }

    @Test
    void remove() {
        map.put(1,1);
        map.put(2,2);
        assertEquals(2, map.size());
        map.remove(2);
        assertEquals(1, map.size());
        map.remove(1);
        assertEquals(0, map.size());
    }

    @Test
    void isEmpty() {
        assertTrue(map.isEmpty());
        map.put(1,1);
        assertFalse(map.isEmpty());
        map.remove(1);
        assertTrue(map.isEmpty());
    }

    @Test
    void containsKey() {
        map.put(1,1);
        assertTrue(map.containsKey(1));
        assertFalse(map.isEmpty());
    }

    @Test
    void containsValue() {
        map.put(1,1);
        assertTrue(map.containsValue(1));
        assertFalse(map.containsValue(2));
    }

    @Test
    void keys() {
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,null);
        long[] expected = {1,2,3,4};
        assertArrayEquals(expected, map.keys());
    }

    @Test
    void values() {
        map.put(1,1);
        map.put(2,2);
        map.put(3,3);
        map.put(4,null);
        Integer[] expected = {1,2,3,null};
        assertArrayEquals(expected, map.values());
    }

    @Test
    void nullValues() {
        map.put(1,null);
        map.put(2,null);
        map.put(3,null);
        map.put(4,null);
        Integer[] expected = {null,null,null,null};
        assertArrayEquals(expected, map.values());
    }

    @Test
    void size() {
        assertEquals(0,map.size());
        map.put(1,1);
        assertEquals(1,map.size());
        map.put(1,2);
        assertEquals(1,map.size());
        map.remove(1);
        assertEquals(0,map.size());
    }

    @Test
    void clear() {
        map.put(1,1);
        assertEquals(1,map.size());
        map.clear();
        assertEquals(0,map.size());
        assertNull(map.get(1));
    }
}
