package com.draconincdomain.mc;

import com.draconincdomain.mc.Annotations.Commands;
import com.draconincdomain.mc.Annotations.Events;
import com.draconincdomain.mc.Annotations.Runnable;
import com.draconincdomain.mc.Core.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.util.Set;

public final class OHHCore extends JavaPlugin {
    
    private static OHHCore instance;
    private GameManager gameManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        registerCommands();
        registerEvents();
        registerRunnables();

        gameManager = new GameManager();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        Reflections reflections = new Reflections("com.draconincdomain.mc.Commands", new TypeAnnotationsScanner(), new SubTypesScanner());
        Set<Class<?>> customCommandClasses = reflections.getTypesAnnotatedWith(Commands.class);

        for (Class<?> commandClass : customCommandClasses) {
            try {
                commandClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void registerEvents() {
        Reflections reflections = new Reflections("com.draconincdomain.mc.Events", new TypeAnnotationsScanner(), new SubTypesScanner());
        Set<Class<?>> customEventClasses = reflections.getTypesAnnotatedWith(Events.class);

        for (Class<?> eventClass : customEventClasses) {
            try {
                Listener listener = (Listener) eventClass.getDeclaredConstructor().newInstance();
                Bukkit.getServer().getPluginManager().registerEvents( listener, this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void registerRunnables() {
        Reflections reflections = new Reflections("com.draconincdomain.mc.Runnables", new TypeAnnotationsScanner(), new SubTypesScanner());
        Set<Class<?>> customRunnableClasses = reflections.getTypesAnnotatedWith(Runnable.class);

        for (Class<?> runableClass : customRunnableClasses) {
            try {
                runableClass.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public static OHHCore getInstance() {
        return instance;
    }
}
