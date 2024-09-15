package io.github.qe7.events;

import net.minecraft.src.ScaledResolution;

public final class ScreenEvent {

    private final ScaledResolution scaledResolution;

    public ScreenEvent(ScaledResolution scaledResolution) {
        this.scaledResolution = scaledResolution;
    }

    public ScaledResolution getScaledResolution() {
        return scaledResolution;
    }
}
