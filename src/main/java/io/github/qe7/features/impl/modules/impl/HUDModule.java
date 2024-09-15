package io.github.qe7.features.impl.modules.impl;

import io.github.qe7.Client;
import io.github.qe7.events.ScreenEvent;
import io.github.qe7.features.impl.modules.api.Module;
import io.github.qe7.features.impl.modules.api.ModuleCategory;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;

import java.util.List;
import java.util.stream.Collectors;

public class HUDModule extends Module {

    public HUDModule() {
        super("HUD", "Display information about the client", ModuleCategory.RENDER);
    }

    @Subscribe
    public final Listener<ScreenEvent> screenListener = new Listener<>(ScreenEvent.class, event -> {
        final net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getMinecraft();
        final net.minecraft.src.FontRenderer fontRenderer = mc.fontRenderer;

        final List<Module> modules = Client.getInstance().getModuleManager().getMap().values().stream().filter(Module::isToggled).collect(Collectors.toList());

        int y = 12;
        if(Client.getInstance().getModuleManager().getMap().get(SlimeChunkModule.class).isToggled())
            y = 22;
        for (Module module : modules) {
            fontRenderer.drawStringWithShadow(module.getName(), 2, y, 0xFFFFFF);
            y += 10;
        }
    });
}
