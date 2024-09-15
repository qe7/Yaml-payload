package io.github.qe7.features.impl.modules.api.settings.api;

import io.github.qe7.utils.configs.Serialized;

import java.util.function.BooleanSupplier;

public abstract class Setting<T> implements Serialized {

    private BooleanSupplier supplier;

    private final T defaultValue;
    private T value;

    private final String name;

    public Setting(String name, T defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    public BooleanSupplier getSupplier() {
        return supplier;
    }

    public void setSupplier(BooleanSupplier supplier) {
        this.supplier = supplier;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public boolean shouldHide() {
        return supplier != null && !supplier.getAsBoolean();
    }

    public <V extends Setting<?>> V supplyIf(BooleanSupplier supplier) {
        this.supplier = supplier;
        return (V) this;
    }
}
