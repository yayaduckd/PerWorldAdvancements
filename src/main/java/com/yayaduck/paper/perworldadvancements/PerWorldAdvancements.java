package com.yayaduck.paper.perworldadvancements;
import org.bukkit.plugin.java.JavaPlugin;

public final class PerWorldAdvancements extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("\"PerWorldAdvancements: Enabled! 🦆");
        getServer().getPluginManager().registerEvents(new AdvancementListener(this), this);

        this.getConfig().addDefault("enableDebug", false);
        this.saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        getLogger().info("PerWorldAdvancements: Byebye! 🦃");
    }
}
