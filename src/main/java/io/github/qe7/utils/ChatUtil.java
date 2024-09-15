package io.github.qe7.utils;

import net.minecraft.client.Minecraft;

public final class ChatUtil {

    private ChatUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    /**
     * Add a chat message to the local player's chat.
     *
     * @param message The message to add.
     */
    public static void addChatMessage(String message) {
        final Minecraft mc = Minecraft.getMinecraft();

        if (mc.thePlayer != null) {
            mc.thePlayer.addChatMessage(message);
        }
    }

    /**
     * Add a chat message with a prefix to the local player's chat.
     *
     * @param prefix The prefix to add.
     * @param message The message to add.
     */
    public static void addPrefixChatMessage(final String prefix, final String message) {
        addChatMessage("§7(§b" + prefix + "§7) §f" + message);
    }

    /**
     * Send a chat message to the server.
     *
     * @param message The message to send.
     */
    public static void sendChatMessage(final String message) {
        final Minecraft mc = Minecraft.getMinecraft();

        if (mc.thePlayer != null) {
            mc.thePlayer.sendChatMessage(message);
        }
    }
}
