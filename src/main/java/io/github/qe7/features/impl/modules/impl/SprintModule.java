package io.github.qe7.features.impl.modules.impl;

import io.github.qe7.events.UpdateEvent;
import io.github.qe7.features.impl.modules.api.Module;
import io.github.qe7.features.impl.modules.api.ModuleCategory;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import net.minecraft.client.Minecraft;

public class SprintModule extends Module {

    public SprintModule() {
        super("Sprint", "Automatically sprint", ModuleCategory.MISC);
    }

    @Subscribe
    public final Listener<UpdateEvent> updateListener = new Listener<>(UpdateEvent.class, event -> {
        final Minecraft mc = Minecraft.getMinecraft();

        if (mc.thePlayer == null) return;
        mc.thePlayer.setSprinting(mc.thePlayer.movementInput.moveForward > 0);
    });
}
