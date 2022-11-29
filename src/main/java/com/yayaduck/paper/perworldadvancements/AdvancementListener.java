package com.yayaduck.paper.perworldadvancements;

import com.destroystokyo.paper.event.player.PlayerAdvancementCriterionGrantEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;

public class AdvancementListener implements Listener {

    private final PerWorldAdvancements plugin;
    AdvancementListener(PerWorldAdvancements plugin) {
        this.plugin = plugin;
    }

    /**
     * Cancels all advancements and recipes when the player is not in survival mode.
     * This method is called when a player unlocks an advancement or recipe.
     *
     * @pre {@code event != null}
     * @post {@code event.isCancelled() == true}
     */
    @EventHandler(priority = org.bukkit.event.EventPriority.HIGH)
    void onAdvancement(PlayerAdvancementCriterionGrantEvent event) {
        ArrayList<String> worlds = (ArrayList<String>) plugin.getConfig().getStringList("worlds");
        String playerWorld = event.getPlayer().getWorld().getName();

        for (String world : worlds) {
            if (playerWorld.equals(world)) {
                cancelAdvancement(event);
            }
        }
    }

    /**
     * Cancels the advancement provided.
     * @pre {@code event != null}
     * @post {@code event.isCancelled() == true}
     * @throws NullPointerException if provided event is null.
     * @param event The advancement to cancel.
     */
    private void cancelAdvancement(PlayerAdvancementCriterionGrantEvent event) throws NullPointerException {
        if (event == null) {
            throw new NullPointerException("event cannot be null");
        }

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
