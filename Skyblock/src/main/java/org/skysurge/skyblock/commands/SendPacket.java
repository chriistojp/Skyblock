package org.skysurge.skyblock.commands;

import net.minecraft.server.v1_8_R3.PacketPlayOutWorldBorder;
import net.minecraft.server.v1_8_R3.WorldBorder;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.skysurge.skyblock.island.IslandManager;

/**
 * Copy Right ©
 * This code is private
 * Owner: Christo
 * From: 10/22/19-2023
 * Any attempts to use these program(s) may result in a penalty of up to $1,000 USD
 **/

public class SendPacket implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        WorldBorder wb = new WorldBorder();
        wb.setSize(20);
        wb.setCenter(p.getLocation().getX(), p.getLocation().getZ());
        PacketPlayOutWorldBorder wbPacket = new PacketPlayOutWorldBorder(wb, PacketPlayOutWorldBorder.EnumWorldBorderAction.INITIALIZE);
        ((CraftPlayer) p).getHandle().playerConnection.sendPacket(wbPacket);
        p.sendMessage(IslandManager.getIslandManager().getIsland(p).toString());



        return false;
    }

    //lol

}