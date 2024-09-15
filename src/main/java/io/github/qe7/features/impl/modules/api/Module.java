package io.github.qe7.features.impl.modules.api;

import io.github.qe7.Client;
import io.github.qe7.features.impl.commands.api.Command;
import io.github.qe7.utils.ChatUtil;
import me.zero.alpine.listener.Subscriber;

public abstract class Module extends Command implements Subscriber {

    private final ModuleCategory moduleCategory;

    private int keyBind;

    private boolean toggled;

    public Module(final String name, final String description, final ModuleCategory moduleCategory) {
        super(name, description);
        this.moduleCategory = moduleCategory;
    }

    public void onEnable() {
        Client.getInstance().getEventBus().subscribe(this);
    }

    public void onDisable() {
        Client.getInstance().getEventBus().unsubscribe(this);
    }

    public ModuleCategory getModuleCategory() {
        return moduleCategory;
    }

    public int getKeyBind() {
        return keyBind;
    }

    public void setKeyBind(int keyBind) {
        this.keyBind = keyBind;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public void toggle() {
        toggled = !toggled;
        if (toggled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    @Override
    public void execute(String[] args) {
        if (args.length == 1) {
            toggle();
            ChatUtil.addChatMessage("Toggled " + getName() + " " + (isToggled() ? "on" : "off"));
        }
    }
}
