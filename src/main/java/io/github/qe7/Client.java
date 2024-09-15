package io.github.qe7;

import io.github.qe7.managers.impl.CommandManager;
import io.github.qe7.managers.impl.ModuleManager;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;

public final class Client {

    private static final Client instance = new Client();

    private final EventBus eventBus;

    private final ModuleManager moduleManager;
    private final CommandManager commandManager;

    private Client() {
        this.eventBus = EventManager.builder().setName("client").build();
        this.moduleManager = new ModuleManager();
        this.commandManager = new CommandManager();
    }

    public void initialize() {
        this.getModuleManager().initialize();
        this.getCommandManager().initialize();
    }

    public static Client getInstance() {
        return instance;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
