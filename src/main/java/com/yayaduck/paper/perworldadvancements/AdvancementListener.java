package com.yayaduck.paper.perworldadvancements;

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AdvancementListener implements Listener {

    private final PerWorldAdvancements plugin;
    AdvancementListener(PerWorldAdvancements plugin) {
        this.plugin = plugin;
    }

    /**
     * Cancels all advancements and recipes when the player is not in survival mode.
     *
     * This method is called when a player unlocks an advancement or recipe.
     *
     * @pre {@code event != null}
     * @post {@code event.isCancelled() == true}
     */
    @EventHandler(priority = org.bukkit.event.EventPriority.HIGH)
    void onAdvancement(PlayerAdvancementCriterionGrantEvent event) {
        if (event.getPlayer().getWorld().equals(Bukkit.getWorld("Creative"))) {
            event.setCancelled(true);
            if (plugin.getConfig().getBoolean("enableDebug")) {
                Bukkit.getLogger().info(
                        "PerWorldAdvancements: Advancement "
                                + event.getAdvancement().getKey().getKey() +
                                " was cancelled for " + event.getPlayer().getName() +
                                " in world " + event.getPlayer().getWorld().getName() + "!");
            }
        }
    }
}
