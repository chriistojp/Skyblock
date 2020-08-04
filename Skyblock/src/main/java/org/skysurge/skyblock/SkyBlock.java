package org.skysurge.skyblock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import org.skysurge.skyblock.commands.IslandCommand;
import org.skysurge.skyblock.commands.SendPacket;
import org.skysurge.skyblock.listeners.*;
import org.skysurge.skyblock.tpa.TpaManager;
import org.skysurge.skyblock.island.IslandManager;
import org.skysurge.skyblock.island.IslandManager;
import org.skysurge.skyblock.world.SkyblockGen;

public class SkyBlock extends JavaPlugin {

    String worldName = "world_skyblock";
    public World world;
    public WorldEditPlugin worldedit;

    private static SkyBlock skyBlock;
    private static SkyBlock instance;

    @Override
    public void onEnable() {
        skyBlock = this;
        instance = this;
        this.worldedit = (WorldEditPlugin) Bukkit.getServer().getPluginManager().getPlugin("WorldEdit");
        if (worldedit == null) {
            sendMessage("ERROR, You must have worldedit on your server!");
        } else {
            makeWorld();
            new IslandManager();
            new TpaManager();
            registerPermissions();
            registerCommands();
            registerListeners();

        }
    }

    private void registerPermissions() {
        PluginManager pm = Bukkit.getPluginManager();
        Permission p = new Permission("skyblock.admin");
        p.setDefault(PermissionDefault.OP);
        pm.addPermission(p);
    }

    @Override
    public void onDisable() {
        IslandManager.getIslandManager().onDisable();
    }

    private void registerCommands() {

        getCommand("island").setExecutor(new IslandCommand());
        getCommand("sendpacket").setExecutor(new SendPacket());
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoin(), this);
        pm.registerEvents(new PlayerQuit(), this);
        pm.registerEvents(new PlayerMove(), this);
        pm.registerEvents(new PlayerRespawn(), this);
        pm.registerEvents(new BlockPlace(), this);
    }

    private void makeWorld() {
        if (Bukkit.getWorld(worldName) == null) {
            sendMessage("Generating world: '" + worldName + "'");
            WorldCreator wc = new WorldCreator(worldName);
            wc.generateStructures(false);
            wc.generator(new SkyblockGen());
            Bukkit.getServer().createWorld(wc);
        }
        sendMessage("Loaded world: '" + worldName + "'");
        world = Bukkit.getWorld(worldName);
        world.setDifficulty(Difficulty.NORMAL);
    }

    public static SkyBlock getSkyBlock() {
        return skyBlock;
    }

    public void sendMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "(SkyBlock) " + ChatColor.AQUA + message);
    }

    public static SkyBlock getInstance() {
        return instance;
    }
}

