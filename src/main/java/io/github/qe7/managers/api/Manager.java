package io.github.qe7.managers.api;

import java.util.HashMap;

public abstract class Manager<T, V> {

    private final HashMap<T, V> map = new HashMap<>();

    public HashMap<T, V> getMap() {
        return map;
    }
}
