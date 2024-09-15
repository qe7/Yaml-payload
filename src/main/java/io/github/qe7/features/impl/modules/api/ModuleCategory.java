package io.github.qe7.features.impl.modules.api;

public enum ModuleCategory {
    RENDER("Render"),
    MISC("Misc");

    private final String name;

    ModuleCategory(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
