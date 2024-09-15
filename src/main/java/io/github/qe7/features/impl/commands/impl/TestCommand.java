package io.github.qe7.features.impl.commands.impl;

import io.github.qe7.features.impl.commands.api.Command;
import io.github.qe7.utils.ChatUtil;

public class TestCommand extends Command {

    public TestCommand() {
        super("test", "Test command");
    }

    @Override
    public void execute(String[] args) {
        ChatUtil.addChatMessage("Test command executed!");
    }
}
