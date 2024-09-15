package io.github.qe7.managers.impl;

import io.github.qe7.Client;
import io.github.qe7.events.packet.OutgoingPacketEvent;
import io.github.qe7.features.impl.commands.api.Command;
import io.github.qe7.features.impl.commands.impl.TestCommand;
import io.github.qe7.managers.api.Manager;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;
import net.minecraft.src.Packet3Chat;
import sun.security.provider.NativePRNG;

import java.util.ArrayList;
import java.util.List;

public final class CommandManager extends Manager<Class<? extends Command>, Command> implements Subscriber {

    public void initialize() {
        final List<Command> commands = new ArrayList<>();

        commands.add(new TestCommand());

        commands.forEach(command -> register(command.getClass()));

        Client.getInstance().getModuleManager().getMap().values().forEach(module -> getMap().putIfAbsent(module.getClass(), module));

        Client.getInstance().getEventBus().subscribe(this);
    }

    public void register(final Class<? extends Command> clazz) {
        try {
            final Command command = clazz.newInstance();
            getMap().putIfAbsent(clazz, command);
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("Failed to register command: " + clazz.getSimpleName());
        }
    }

    @Subscribe
    public final Listener<OutgoingPacketEvent> outgoingPacketListener = new Listener<>(OutgoingPacketEvent.class, event -> {
        if (event.getPacket() instanceof Packet3Chat) {
            final Packet3Chat packet = (Packet3Chat) event.getPacket();

            if (!packet.message.startsWith(".")) return;

            event.cancel();

            final String[] args = packet.message.substring(1).split(" ");

            Command command = null;

            for (final Command c : getMap().values()) {
                if (c.getName().equalsIgnoreCase(args[0])) {
                    command = c;
                    break;
                }
            }

            if (command != null) {
                command.execute(args);
            }
        }
    });
}
