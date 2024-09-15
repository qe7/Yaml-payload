package io.github.qe7.features.impl.modules.impl;

import io.github.qe7.Client;
import io.github.qe7.events.ScreenEvent;
import io.github.qe7.features.impl.modules.api.Module;
import io.github.qe7.features.impl.modules.api.ModuleCategory;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import net.minecraft.client.Minecraft;
import net.minecraft.src.FontRenderer;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SlimeChunkModule extends Module {
    public SlimeChunkModule() {
        super("IsSlimeChunk", "Shows is chunk slimeChunk?!", ModuleCategory.RENDER);
    }

    @Subscribe
    public final Listener<ScreenEvent> screenListener = new Listener<>(ScreenEvent.class, event -> {
        final net.minecraft.client.Minecraft mc = net.minecraft.client.Minecraft.getMinecraft();
        final net.minecraft.src.FontRenderer fontRenderer = mc.fontRenderer;
        boolean isSlimeChunk = isSlimeChunk(mc);
        String append = "§atrue";
        if(!isSlimeChunk)
            append = "§cfalse";
        fontRenderer.drawStringWithShadow("SlimeChunk: " + append, 2, 12, 0xFFFFFF);
    });

    private static boolean isSlimeChunk(Minecraft mc) {
        long seed = mc.theWorld.getRandomSeed();
        int xPosition = (int)(mc.thePlayer.posX / 16);
        int zPosition = (int)(mc.thePlayer.posZ / 16);

        Random rnd = new Random(
                seed +
                        (int) (xPosition * xPosition * 0x4c1906) +
                        (int) (xPosition * 0x5ac0db) +
                        (int) (zPosition * zPosition) * 0x4307a7L +
                        (int) (zPosition * 0x5f24f) ^ 0x3ad8025fL
        );

        boolean isSlimeChunk = rnd.nextInt(10) == 0;
        return isSlimeChunk;
    }
}