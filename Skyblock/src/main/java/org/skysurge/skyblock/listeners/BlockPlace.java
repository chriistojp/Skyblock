package org.skysurge.skyblock.listeners;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.skysurge.skyblock.SkyBlock;
import org.skysurge.skyblock.island.Island;
import org.skysurge.skyblock.island.IslandManager;

import java.io.File;
import java.util.ArrayList;
import java.util.Set;

public class BlockPlace implements Listener {

    /*
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p =  (Player) e.getPlayer();
        Location loc = p.getLocation();

        if (p.getWorld().getName().equals(SkyBlock.getSkyBlock().world.getName())) {
            Island i = IslandManager.getIslandManager().getIsland(p);
            p.sendMessage(loc.toString());
        }

    }
    */

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {

        e.getPlayer().sendMessage(Integer.toString(e.getTo().getBlockX()));

      File islandFile = new File(SkyBlock.getInstance().getDataFolder(), "islands.yml");
        FileConfiguration islandConfig = YamlConfiguration.loadConfiguration(islandFile);


        for(String key : islandConfig.getKeys(false)) {
            for(String x : islandConfig.getString(key + ".x").split(toString())) {
                for (String z : islandConfig.getString(key + ".z").split(toString())) {
                    int xLoc = e.getTo().getBlockX();
                    int roundedX = Math.round(xLoc/10000) * 10000;
                    e.getPlayer().sendMessage(Integer.toString((Math.round(xLoc / 100)) * 100));
                }
            }

        }
        e.getPlayer().sendMessage(islandConfig.getKeys(false).toString());


    }

}
