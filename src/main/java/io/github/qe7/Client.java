package io.github.qe7;

import io.github.qe7.managers.impl.CommandManager;
import io.github.qe7.managers.impl.ModuleManager;
import io.github.qe7.utils.configs.FileUtil;
import me.zero.alpine.bus.EventBus;
import me.zero.alpine.bus.EventManager;

/**
 * The main class of the client.
 */
public final class Client {

    // Singleton instance of the client.
    private static final Client instance = new Client();

    // Event bus for the client.
    private final EventBus eventBus;

    // Managers for the client.
    private final ModuleManager moduleManager;
    private final CommandManager commandManager;

    // Private constructor to prevent instantiation.
    private Client() {
        this.eventBus = EventManager.builder().setName("client").build();
        this.moduleManager = new ModuleManager();
        this.commandManager = new CommandManager();
    }

    /**
     * Initializes the client.
     */
    public void initialize() {
        FileUtil.createDirectory();

        this.getModuleManager().initialize();
        this.getCommandManager().initialize();

        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
    }

    /**
     * Shuts down the client.
     */
    private void shutdown() {
        this.getModuleManager().saveModules();
    }

    /**
     * Returns the singleton instance of the client.
     *
     * @return the singleton instance of the client
     */
    public static Client getInstance() {
        return instance;
    }

    /**
     * Returns the event bus for the client.
     *
     * @return the event bus for the client
     */
    public EventBus getEventBus() {
        return eventBus;
    }

    /**
     * Returns the module manager for the client.
     *
     * @return the module manager for the client
     */
    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    /**
     * Returns the command manager for the client.
     *
     * @return the command manager for the client
     */
    public CommandManager getCommandManager() {
        return commandManager;
    }
}
