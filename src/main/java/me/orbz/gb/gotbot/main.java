/*   1:    */ package me.orbz.gb.gotbot;
/*   2:    */ 
/*   3:    */ import java.util.logging.Logger;
/*   4:    */ import org.bukkit.Bukkit;
/*   5:    */ import org.bukkit.ChatColor;
/*   6:    */ import org.bukkit.Server;
/*   7:    */ import org.bukkit.command.Command;
/*   8:    */ import org.bukkit.command.CommandSender;
/*   9:    */ import org.bukkit.configuration.file.FileConfiguration;
/*  10:    */ import org.bukkit.configuration.file.FileConfigurationOptions;
/*  11:    */ import org.bukkit.entity.Player;
/*  12:    */ import org.bukkit.plugin.PluginManager;
/*  13:    */ import org.bukkit.plugin.java.JavaPlugin;
/*  14:    */ 
/*  15:    */ public class main
/*  16:    */   extends JavaPlugin
/*  17:    */ {
/*  18: 19 */   public final Logger logger = Logger.getLogger("Minecraft");
/*  19: 20 */   public final ChatListener cl = new ChatListener(this);
/*  20:    */   public static main plugin;
/*  21:    */   
/*  22:    */   public void onEnable()
/*  23:    */   {
/*  24: 25 */     this.logger.info("Enabling GodBot");
/*  25: 26 */     this.logger.info("Version: 1.1");
/*  26: 27 */     getConfig().options().copyDefaults(true);
/*  27: 28 */     saveConfig();
/*  28: 29 */     this.logger.info("GotBot has been loaded!");
/*  29:    */     
/*  30: 31 */     PluginManager pm = getServer().getPluginManager();
/*  31: 32 */     pm.registerEvents(this.cl, this);
/*  32:    */   }
/*  33:    */   
/*  34:    */   public void onDisable()
/*  35:    */   {
/*  36: 36 */     this.logger.info("Disabling GodBot ...");
/*  37: 37 */     this.logger.info("GodBot Disabled!");
/*  38:    */   }
/*  39:    */   
/*  40:    */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
/*  41:    */   {
/*  42: 41 */     Player player = (Player)sender;
/*  43: 43 */     if (commandLabel.equalsIgnoreCase("bab")) {
/*  44: 44 */       if (player.hasPermission("bab.bab"))
/*  45:    */       {
/*  46: 45 */         if (args.length == 0)
/*  47:    */         {
/*  48: 46 */           player.sendMessage(ChatColor.BOLD + "Commands & Help");
/*  49: 47 */           player.sendMessage(ChatColor.RED + "Format: " + ChatColor.WHITE + "/bab <command> <input>");
/*  50: 48 */           player.sendMessage(ChatColor.BLUE + "setname " + ChatColor.BOLD + "-" + ChatColor.YELLOW + " sets the name of your bot");
/*  51: 49 */           player.sendMessage(ChatColor.BLUE + "saveconfig" + ChatColor.BOLD + "-" + ChatColor.YELLOW + " resets the config to what was loaded in.");
/*  52: 50 */           player.sendMessage(ChatColor.BLUE + "setplayermessage" + ChatColor.BOLD + "-" + ChatColor.YELLOW + " turns player messsaging on or off <on or off>");
/*  53:    */         }
/*  54: 52 */         if (args.length == 2)
/*  55:    */         {
/*  56: 53 */           if (args[0].equalsIgnoreCase("setname")) {
/*  57: 54 */             if (player.hasPermission("bab.setname"))
/*  58:    */             {
/*  59: 55 */               getConfig().set("name", args[1]);
/*  60: 56 */               player.sendMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + "BuildABot" + ChatColor.BLUE + "] " + ChatColor.YELLOW + "Your bot's name is now: " + args[1]);
/*  61:    */             }
/*  62:    */             else
/*  63:    */             {
/*  64: 58 */               player.sendMessage("No Permission!");
/*  65:    */             }
/*  66:    */           }
/*  67: 63 */           if (args[0].equalsIgnoreCase("setplayermessage")) {
/*  68: 64 */             if (player.hasPermission("bab.setplayermessage"))
/*  69:    */             {
/*  70: 65 */               if (args[1].equalsIgnoreCase("on"))
/*  71:    */               {
/*  72: 66 */                 getConfig().set("player-message", Boolean.valueOf(true));
/*  73: 67 */                 player.sendMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + "BuildABot" + ChatColor.BLUE + "] " + ChatColor.YELLOW + "Player Messaging is now on!");
/*  74:    */               }
/*  75: 68 */               else if (args[1].equalsIgnoreCase("off"))
/*  76:    */               {
/*  77: 69 */                 getConfig().set("player-message", Boolean.valueOf(false));
/*  78: 70 */                 player.sendMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + "BuildABot" + ChatColor.BLUE + "] " + ChatColor.YELLOW + "Player Messaging is now off!");
/*  79:    */               }
/*  80:    */             }
/*  81:    */             else {
/*  82: 73 */               player.sendMessage(ChatColor.RED + "No Permission!");
/*  83:    */             }
/*  84:    */           }
/*  85:    */         }
/*  86: 77 */         if (args.length == 1)
/*  87:    */         {
/*  88: 78 */           if (args[0].equalsIgnoreCase("setname")) {
/*  89: 79 */             if (player.hasPermission("bab.setname")) {
/*  90: 80 */               player.sendMessage(ChatColor.BOLD + "USAGE: " + ChatColor.RED + "/bab setname <name>");
/*  91:    */             } else {
/*  92: 82 */               player.sendMessage(ChatColor.RED + "No Permission!");
/*  93:    */             }
/*  94:    */           }
/*  95: 85 */           if (args[0].equalsIgnoreCase("saveconfig")) {
/*  96: 86 */             if (player.hasPermission("bab.saveconfig"))
/*  97:    */             {
/*  98: 87 */               saveConfig();
/*  99: 88 */               player.sendMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + "BuildABot" + ChatColor.BLUE + "] " + ChatColor.YELLOW + "Config saved!");
/* 100:    */             }
/* 101:    */             else
/* 102:    */             {
/* 103: 90 */               player.sendMessage(ChatColor.RED + "No Permission!");
/* 104:    */             }
/* 105:    */           }
/* 106: 93 */           if (args[0].equalsIgnoreCase("setplayermessage")) {
/* 107: 94 */             if (player.hasPermission("bab.setplayermessage")) {
/* 108: 95 */               player.sendMessage(ChatColor.BOLD + "USAGE: " + ChatColor.RED + "/bab setplayermessage <on-off>");
/* 109:    */             } else {
/* 110: 97 */               player.sendMessage(ChatColor.RED + "No Permission!");
/* 111:    */             }
/* 112:    */           }
/* 113:    */         }
/* 114:    */       }
/* 115:    */       else
/* 116:    */       {
/* 117:102 */         player.sendMessage(ChatColor.RED + "No Permission!");
/* 118:    */       }
/* 119:    */     }
/* 120:106 */     if (commandLabel.equalsIgnoreCase("buildabot")) {
/* 121:107 */       if (player.hasPermission("bab.buildabot")) {
/* 122:108 */         player.sendMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + "BuildABot" + ChatColor.BLUE + "] " + ChatColor.YELLOW + "Type /bab to get command help!");
/* 123:    */       } else {
/* 124:110 */         player.sendMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + "BuildABot" + ChatColor.BLUE + "] " + ChatColor.YELLOW + " Running " + Bukkit.getVersion() + ", made by Xpod78.");
/* 125:    */       }
/* 126:    */     }
/* 127:114 */     commandLabel.equalsIgnoreCase("bab");
/* 128:    */     
/* 129:    */ 
/* 130:117 */     return false;
/* 131:    */   }
/* 132:    */ }


/* Location:           C:\Documents and Settings\Admin\Desktop\test server\plugins\BuildABot_STANDARD.jar
 * Qualified Name:     me.xpod78.bab.main
 * JD-Core Version:    0.7.0.1
 */