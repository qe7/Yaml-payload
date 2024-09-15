package io.github.qe7.features.impl.modules.api;

import io.github.qe7.Client;
import io.github.qe7.features.impl.commands.api.Command;
import io.github.qe7.features.impl.modules.api.settings.api.Setting;
import io.github.qe7.features.impl.modules.api.settings.impl.BooleanSetting;
import io.github.qe7.features.impl.modules.api.settings.impl.DoubleSetting;
import io.github.qe7.features.impl.modules.api.settings.impl.IntSetting;
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
            StringBuilder usage = new StringBuilder(this.getName());

            System.out.println("Settings: " + Client.getInstance().getModuleManager().getSettingsByModule(this));

            for (Setting<?> setting : Client.getInstance().getModuleManager().getSettingsByModule(this)) {
                usage.append(" <").append(setting.getName().replace(" ", "")).append(">");
            }

            ChatUtil.addPrefixChatMessage("Settings", "Usage: " + usage);
            return;
        }

        if (args.length == 2 || args.length == 3) {
            if (args[1].equalsIgnoreCase("toggle")) {
                toggle();
                ChatUtil.addChatMessage("Toggled " + getName() + " " + (isToggled() ? "on" : "off"));
                return;
            }

            for (Setting<?> setting : Client.getInstance().getModuleManager().getSettingsByModule(this)) {
                if (!setting.getName().replace(" ", "").equalsIgnoreCase(args[1])) {
                    continue;
                }

                if (args.length == 2) {
                    ChatUtil.addPrefixChatMessage("Settings", setting.getName() + ": " + setting.getValue());
                    return;
                }

                if (setting instanceof BooleanSetting) {
                    BooleanSetting booleanSetting = (BooleanSetting) setting;

                    if (!args[2].equalsIgnoreCase("true") && !args[2].equalsIgnoreCase("false")) {
                        ChatUtil.addPrefixChatMessage("Settings", "Invalid value");
                        return;
                    }

                    booleanSetting.setValue(Boolean.parseBoolean(args[2]));
                    ChatUtil.addPrefixChatMessage("Settings", "Set " + setting.getName() + " to " + args[2]);
                    return;
                }
                if (setting instanceof IntSetting) {
                    IntSetting intSetting = (IntSetting) setting;

                    if (!args[2].matches("[0-9]+")) {
                        ChatUtil.addPrefixChatMessage("Settings", "Invalid value");
                        return;
                    }

                    if (intSetting.getMinimum() != Integer.MIN_VALUE && Integer.parseInt(args[2]) < intSetting.getMinimum()) {
                        intSetting.setValue(intSetting.getMinimum());
                        ChatUtil.addPrefixChatMessage("Settings", "Set " + setting.getName() + " to " + intSetting.getMinimum());
                        return;
                    }

                    if (intSetting.getMaximum() != Integer.MAX_VALUE && Integer.parseInt(args[2]) > intSetting.getMaximum()) {
                        intSetting.setValue(intSetting.getMaximum());
                        ChatUtil.addPrefixChatMessage("Settings", "Set " + setting.getName() + " to " + intSetting.getMaximum());
                        return;
                    }

                    intSetting.setValue(Integer.parseInt(args[2]));
                    ChatUtil.addPrefixChatMessage("Settings", "Set " + setting.getName() + " to " + args[2]);
                    return;
                }
                if (setting instanceof DoubleSetting) {
                    DoubleSetting doubleSetting = (DoubleSetting) setting;

                    if (!args[2].matches("[-+]?[0-9]*\\.?[0-9]+")) {
                        ChatUtil.addPrefixChatMessage("Settings", "Invalid value");
                        return;
                    }

                    if (doubleSetting.getMinimum() != Double.MIN_VALUE && Double.parseDouble(args[2]) < doubleSetting.getMinimum()) {
                        doubleSetting.setValue(doubleSetting.getMinimum());
                        ChatUtil.addPrefixChatMessage("Settings", "Set " + setting.getName() + " to " + doubleSetting.getMinimum());
                        return;
                    }

                    if (doubleSetting.getMaximum() != Double.MAX_VALUE && Double.parseDouble(args[2]) > doubleSetting.getMaximum()) {
                        doubleSetting.setValue(doubleSetting.getMaximum());
                        ChatUtil.addPrefixChatMessage("Settings", "Set " + setting.getName() + " to " + doubleSetting.getMaximum());
                        return;
                    }

                    doubleSetting.setValue(Double.parseDouble(args[2]));
                    ChatUtil.addPrefixChatMessage("Settings", "Set " + setting.getName() + " to " + args[2]);
                    return;
                }
                ChatUtil.addPrefixChatMessage("Settings", "Invalid setting type");
                return;
            }
        }
    }
}
