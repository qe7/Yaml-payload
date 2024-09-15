package io.github.qe7.managers.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.github.qe7.Client;
import io.github.qe7.events.KeyPressEvent;
import io.github.qe7.features.impl.modules.api.Module;
import io.github.qe7.features.impl.modules.api.settings.api.Setting;
import io.github.qe7.features.impl.modules.impl.HUDModule;
import io.github.qe7.features.impl.modules.impl.SlimeChunkModule;
import io.github.qe7.features.impl.modules.impl.SprintModule;
import io.github.qe7.managers.api.Manager;
import io.github.qe7.utils.configs.FileUtil;
import me.zero.alpine.listener.Listener;
import me.zero.alpine.listener.Subscribe;
import me.zero.alpine.listener.Subscriber;

import java.lang.reflect.Field;
import java.util.*;

public final class ModuleManager extends Manager<Class<? extends Module>, Module> implements Subscriber {

    private final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final Map<Module, List<Setting<?>>> setting = new HashMap<>();

    public void initialize() {
        final List<Module> modules = new ArrayList<>();

        modules.add(new HUDModule());
        modules.add(new SlimeChunkModule());
        modules.add(new SprintModule());

        modules.forEach(module -> registerModule(module.getClass()));

        this.loadModules();
        Client.getInstance().getEventBus().subscribe(this);
    }

    public void registerModule(final Class<? extends Module> clazz) {
        try {
            final Module module = clazz.newInstance();
            getMap().put(clazz, module);

            for (Field declaredField : clazz.getDeclaredFields()) {
                if (declaredField.getType().getSuperclass() == null) continue;
                if (!declaredField.getType().getSuperclass().equals(Setting.class)) continue;

                declaredField.setAccessible(true);

                this.addSetting(this.getMap().get(clazz), (Setting<?>) declaredField.get(this.getMap().get(clazz)));
            }
        } catch (InstantiationException | IllegalAccessException e) {
            System.out.println("Failed to register module: " + clazz.getSimpleName());
        }
    }

    public List<Setting<?>> getSettingsByModule(Module module) {
        return setting.getOrDefault(module, Collections.emptyList());
    }

    public void addSetting(Module feature, Setting<?> property) {
        setting.putIfAbsent(feature, new ArrayList<>());
        setting.get(feature).add(property);
    }

    public void saveModules() {
        JsonObject jsonObject = new JsonObject();

        for (Module module : this.getMap().values()) {
            jsonObject.add(module.getName(), module.serialize());
        }

        FileUtil.writeFile("modules", GSON.toJson(jsonObject));
    }

    public void loadModules() {
        String config = FileUtil.readFile("modules");

        if (config == null) {
            return;
        }

        JsonObject jsonObject = GSON.fromJson(config, JsonObject.class);

        for (Module module : this.getMap().values()) {
            if (jsonObject.has(module.getName())) {
                try {
                    module.deserialize(jsonObject.getAsJsonObject(module.getName()));
                } catch (Exception e) {
                    System.out.println("Failed to load config for module: " + module.getName() + " - " + e.getMessage());
                }
            }
        }
    }

    @Subscribe
    public final Listener<KeyPressEvent> keyListener = new Listener<>(KeyPressEvent.class, event -> {
        for (final Module module : this.getMap().values()) {
            if (module.getKeyBind() == event.getKeyCode()) {
                module.toggle();
            }
        }
    });
}
