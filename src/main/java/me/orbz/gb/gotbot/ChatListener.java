/*   1:    */ package me.orbz.gb.gotbot;
/*   2:    */ 
/*   3:    */ import org.bukkit.Bukkit;
/*   4:    */ import org.bukkit.ChatColor;
/*   5:    */ import org.bukkit.Server;
/*   6:    */ import org.bukkit.configuration.file.FileConfiguration;
/*   7:    */ import org.bukkit.entity.Player;
/*   8:    */ import org.bukkit.event.EventHandler;
/*   9:    */ import org.bukkit.event.EventPriority;
/*  10:    */ import org.bukkit.event.Listener;
/*  11:    */ import org.bukkit.event.player.AsyncPlayerChatEvent;
/*  14:    */ import org.bukkit.event.player.PlayerJoinEvent;
/*  15:    */ import org.bukkit.scheduler.BukkitScheduler;
/*  16:    */ import org.bukkit.command.*;

/*  17:    */ public class ChatListener
/*  18:    */   implements Listener
/*  19:    */ {
/*  20:    */   public static main plugin;
/*  21:    */   
/*  22:    */   public ChatListener(main instance){
/*  24: 21 */     plugin = instance;
/*  25:    */   }

//*  53:    */  @EventHandler(priority=EventPriority.LOW)
/*  54:    */   public void onPlayerJoin(final PlayerJoinEvent event){
				final Player player = event.getPlayer();  
				plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
/*  57:    */     
/*  58:    */       public void run(){
						player.sendMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + ChatListener.plugin.getConfig().getString("name") + ChatColor.BLUE + "] " + ChatColor.WHITE + "welcome to Gamesync!");
/*  62:    */       }
/*  63: 58 */     }, plugin.getConfig().getInt("delay"));
/*  64:    */   }
/*  65:    */   
/*  66:    */   @EventHandler(priority=EventPriority.HIGH)
/*  67:    */   public void onPlayerChat(final AsyncPlayerChatEvent event){

/*  70: 65 */     final Player player = event.getPlayer();
/*  71:    */     final ConsoleCommandSender console = Bukkit.getConsoleSender();

/*  74: 69 */     plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

/*  76:    */       public void run() {
/*  77:    */       
/*  78: 71 */         String message = event.getMessage();
/*  79: 74 */         if (ChatListener.plugin.getConfig().getBoolean("player-message")) {
	
						if (message.equalsIgnoreCase("day")) {
							player.sendMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + ChatListener.plugin.getConfig().getString("name") + ChatColor.BLUE + "] " + ChatColor.WHITE + "Good morning!");
							Bukkit.getServer().dispatchCommand(console, "time set 0");
						}
						
     					String[] splitMsg = message.split("\\s+");
     					
     					if (splitMsg[0].equals("tp")) {
     						if (splitMsg.length == 2){
     						Bukkit.getServer().dispatchCommand(console, "tp " + player.getName() + " " + splitMsg[1] );
     						}
     					}
     					
     					if (message.equals("mode s")){
     						Bukkit.getServer().dispatchCommand(console, "gamemode 3 " + player.getName());
     					}
     					if (message.equals("mode c")){
     						Bukkit.getServer().dispatchCommand(console, "gamemode 1 " + player.getName());
     					}
     					
/*  81: 75 */           if (message.equals("respawn")) {
						  player.sendMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + ChatListener.plugin.getConfig().getString("name") + ChatColor.BLUE + "] " + ChatColor.WHITE + "Respawning " + player.getName());
/*  83: 76 */             player.setHealth(0);
/*  84: 77 */           }
					
/*  86: 81 */           for (int j = 0; j < 100; j++) {
/*  87: 82 */             if (message.equalsIgnoreCase(ChatListener.plugin.getConfig().getString("input-" + j))) {
							String[] response = ChatListener.plugin.getConfig().getString(new StringBuilder("response-").append(j).toString()).split("\\s+");
							if (response[0].equals("give")){
								Bukkit.getServer().dispatchCommand(console, "give " + player.getName() + " " + response[1] );
							} else {
/*  88: 83 */               player.sendMessage(ChatColor.BLUE + "[" + ChatColor.YELLOW + ChatListener.plugin.getConfig().getString("name") + ChatColor.BLUE + "] " + ChatColor.WHITE + ChatListener.plugin.getConfig().getString(new StringBuilder("response-").append(j).toString()));
/*  89:    */             }
							
                          }
/*  90:    */           }
/*  91:    */         } 

/*  92: 87 */         else if (!ChatListener.plugin.getConfig().getBoolean("player-message")) {
/*  93:    */         
      
/* 104:    */         }
/* 105:    */       }
/* 106:104 */     }, plugin.getConfig().getInt("delay"));
/* 107:    */   }
/* 108:    */ }


