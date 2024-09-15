package io.github.qe7.features.impl.commands.api;

import io.github.qe7.features.api.Feature;

public abstract class Command extends Feature {

    public Command(String name, String description) {
        super(name, description);
    }

    public abstract void execute(String[] args);
}
