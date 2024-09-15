package io.github.qe7.features.api;

public abstract class Feature {

    private final String name, description;

    public Feature(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
