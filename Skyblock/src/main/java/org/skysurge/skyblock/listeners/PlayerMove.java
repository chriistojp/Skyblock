package org.skysurge.skyblock.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.skysurge.skyblock.SkyBlock;
import org.skysurge.skyblock.interfaces.TextFormat;
import org.skysurge.skyblock.island.Island;
import org.skysurge.skyblock.island.IslandManager;

/**
 * Copy Right ©
 * This code is private
 * Owner: Christo
 * From: 10/22/19-2023
 * Any attempts to use these program(s) may result in a penalty of up to $1,000 USD
 **/

public class PlayerMove implements Listener, TextFormat {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (p.getWorld().getName().equals(SkyBlock.getSkyBlock().world.getName())) {
            Island i = IslandManager.getIslandManager().getIsland(p);
            if (i != null) {
                if (i.isAt(e.getFrom()) && !i.isAt(e.getTo())) {
                    e.setCancelled(true);
                    p.sendMessage(textColor + "You may not leave the bounds of your island.");
                }
            }
        }
    }

}