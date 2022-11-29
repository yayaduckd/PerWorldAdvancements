package com.yayaduck.paper.perworldadvancements;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class PerWorldAdvancements extends JavaPlugin {
    @Override
    public void onEnable() {
        setDefaults();
        getServer().getPluginManager().registerEvents(new AdvancementListener(this), this);
        getLogger().info("PerWorldAdvancements: Enabled! 🦆");
        getLogger().info("PerWorldAdvancements: Worlds configured: "
                + getConfig().getStringList("worlds"));
    }

    @Override
    public void onDisable() {
        getLogger().info("PerWorldAdvancements: Byebye! 🦃");
    }

    private void setDefaults() {
        FileConfiguration config = this.getConfig();

        config.addDefault("enableDebug", false);

        ArrayList<String> worlds = new ArrayList<>();
        worlds.add("exampleworld1");
        worlds.add("exampleworld2");
        worlds.add("exampleworld2_nether");
        config.addDefault("worlds", worlds);

        saveDefaultConfig();
    }
}
