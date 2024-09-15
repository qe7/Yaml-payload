package io.github.qe7;

import io.github.qe7.managers.impl.CommandManager;
import io.github.qe7.managers.impl.ModuleManager;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;
import net.minecraft.client.Minecraft;

public final class Client {

    private static final Client instance = new Client();

    private final EventBus eventBus = EventManager.builder().setName("client").build();

    private final String name, version;

    private final ModuleManager moduleManager = new ModuleManager();
    private final CommandManager commandManager = new CommandManager();

    private Client() {
        this.name = "Client";
        this.version = "1.0.0";
    }

    public void initialize() {
        Minecraft.getMinecraft().session.username = "rg4";

        this.getModuleManager().initialize();
        this.getCommandManager().initialize();
    }

    public static Client getInstance() {
        return instance;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
