package com.gamesync.bot;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GameSyncBot extends JavaPlugin
{
    public final Logger logger = Logger.getLogger("Minecraft");

    public static GameSyncBot plugin;

    public String getMessage(String message)
    {
        return ChatColor.BLUE + "[" + ChatColor.YELLOW + ChatListener.plugin.getConfig().getString("name") + ChatColor.BLUE + "] " + ChatColor.WHITE + message;
    }

    public void onEnable ()
    {
        saveDefaultConfig();
        this.logger.info("Enabling GameSync bot");
        getConfig().options().copyDefaults(true);
        saveConfig();

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new ChatListener(this), this);
        this.logger.info("The GameSync bot has been loaded!");
    }

    public void onDisable ()
    {
        this.logger.info("Disabling GameSync Bot ...");
        this.logger.info("GameSync Bot Disabled!");
    }

    public boolean onCommand (CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        Player player = (Player) sender;
        if (commandLabel.equalsIgnoreCase("bab") && player.hasPermission("bab.bab")) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.BOLD + "Commands & Help");
                player.sendMessage(ChatColor.RED + "Format: " + ChatColor.WHITE + "/bab <command> <input>");
                player.sendMessage(ChatColor.BLUE + "setname " + ChatColor.BOLD + "-" + ChatColor.YELLOW + " sets the name of your bot");
                player.sendMessage(ChatColor.BLUE + "saveconfig" + ChatColor.BOLD + "-" + ChatColor.YELLOW + " resets the config to what was loaded in.");
                player.sendMessage(ChatColor.BLUE + "setplayermessage" + ChatColor.BOLD + "-" + ChatColor.YELLOW + " turns player messsaging on or off <on or off>");
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("setname")) {
                    if (player.hasPermission("bab.setname")) {
                        getConfig().set("name", args[1]);
                        player.sendMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + "BuildABot" + ChatColor.BLUE + "] " + ChatColor.YELLOW + "Your bot's name is now: " + args[1]);
                    } else {
                        player.sendMessage("No Permission!");
                    }
                }
                if (args[0].equalsIgnoreCase("setplayermessage")) {
                    if (player.hasPermission("bab.setplayermessage")) {
                        if (args[1].equalsIgnoreCase("on")) {
                            getConfig().set("player-message", Boolean.valueOf(true));
                            player.sendMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + "BuildABot" + ChatColor.BLUE + "] " + ChatColor.YELLOW + "Player Messaging is now on!");
                        } else if (args[1].equalsIgnoreCase("off")) {
                            getConfig().set("player-message", Boolean.valueOf(false));
                            player.sendMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + "BuildABot" + ChatColor.BLUE + "] " + ChatColor.YELLOW + "Player Messaging is now off!");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "No Permission!");
                    }
                }
            }
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("setname")) {
                    if (player.hasPermission("bab.setname")) {
                        player.sendMessage(ChatColor.BOLD + "USAGE: " + ChatColor.RED + "/bab setname <name>");
                    } else {
                        player.sendMessage(ChatColor.RED + "No Permission!");
                    }
                }
                if (args[0].equalsIgnoreCase("saveconfig")) {
                    if (player.hasPermission("bab.saveconfig")) {
                        saveConfig();
                        player.sendMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + "BuildABot" + ChatColor.BLUE + "] " + ChatColor.YELLOW + "Config saved!");
                    } else {
                        player.sendMessage(ChatColor.RED + "No Permission!");
                    }
                }
                if (args[0].equalsIgnoreCase("setplayermessage")) {
                    if (player.hasPermission("bab.setplayermessage")) {
                        player.sendMessage(ChatColor.BOLD + "USAGE: " + ChatColor.RED + "/bab setplayermessage <on-off>");
                    } else {
                        player.sendMessage(ChatColor.RED + "No Permission!");
                    }
                }
            }
        }
        if (commandLabel.equalsIgnoreCase("buildabot")) {
            if (player.hasPermission("bab.buildabot")) {
                player.sendMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + "BuildABot" + ChatColor.BLUE + "] " + ChatColor.YELLOW + "Type /bab to get command help!");
            } else {
                player.sendMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + "BuildABot" + ChatColor.BLUE + "] " + ChatColor.YELLOW + " Running " + Bukkit.getVersion() + ", made by Xpod78.");
            }
        }
        commandLabel.equalsIgnoreCase("bab");

        return false;
    }
}