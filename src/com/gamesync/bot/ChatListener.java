package com.gamesync.bot;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.command.*;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatListener implements Listener
{
    public static GameSyncBot plugin;

    public ChatListener (GameSyncBot instance)
    {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerJoin (final PlayerJoinEvent event)
    {
        plugin.getLogger().info("Player joined!");

        final Player player = event.getPlayer();

        ConfigurationSection section = plugin.getConfig().getConfigurationSection("nicknames");
        Map<String, Object> map = section.getValues(false);

        if (map.containsKey(player.getName().toLowerCase())) {
            player.setDisplayName(map.get(player.getName().toLowerCase()).toString());
            player.setPlayerListName(map.get(player.getName().toLowerCase()).toString());
        }

        player.sendMessage(plugin.getMessage("Welcome to Gamesync " + player.getDisplayName() + "!"));
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChat (final AsyncPlayerChatEvent event)
    {
        final Player player = event.getPlayer();
        final ConsoleCommandSender console = Bukkit.getConsoleSender();
        final String message = event.getMessage();

        if (message.equalsIgnoreCase("day")) {
            player.sendMessage(plugin.getMessage("Good morning!"));
            Bukkit.getServer().dispatchCommand(console, "time set 0");

            return;
        }

        Pattern p = Pattern.compile("^tp ([\\w]+)$");
        Matcher m = p.matcher(message);

        if (m.find()) {
            Player target = Bukkit.getPlayer(m.group(1));
            if (target == null) {
                player.sendMessage(plugin.getMessage(m.group(1) + " isn't in the game."));

                return;
            }

            plugin.getLogger().info("Teleporting " + player.getName() + " to " + target.getName());
            player.sendMessage(plugin.getMessage("Teleporting you to " + target.getDisplayName()));
            Bukkit.getServer().dispatchCommand(console, "tp " + player.getName() + " " + target.getName());

            return;
        }

        // Game Mode Commands
        if (message.equalsIgnoreCase("mode s") || message.equalsIgnoreCase("modes")) {
            player.sendMessage(plugin.getMessage("You are in survival!"));
            Bukkit.getServer().dispatchCommand(console, "gamemode 3 " + player.getName());

            return;
        }

        if (message.equalsIgnoreCase("mode c") || message.equalsIgnoreCase("modec")) {
            player.sendMessage(plugin.getMessage("You are in creative!"));
            Bukkit.getServer().dispatchCommand(console, "gamemode 1 " + player.getName());

            return;
        }

        // Respawn Commands
        if (message.equals("respawn")) {
            player.sendMessage(plugin.getMessage("Respawning " + player.getDisplayName()));
            player.setHealth(0.0);

            return;
        }

        ConfigurationSection section = plugin.getConfig().getConfigurationSection("items");
        ConfigurationSection items = section.getConfigurationSection(message.toLowerCase());
        if (items != null) {
            Map<String, Object> map = items.getValues(false);

            plugin.getLogger().info(map.toString());
            Bukkit.getServer().dispatchCommand(console, "give " + player.getName() + " " + map.get("id").toString() + " " + map.get("count").toString());
        }
    }
}



