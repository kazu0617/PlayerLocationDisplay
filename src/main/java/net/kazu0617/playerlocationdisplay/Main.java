package net.kazu0617.playerlocationdisplay;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * @author     kazu0617
 * @license    LGPLv3
 * @copyright  Copyright kazu0617 2015
 */
public class Main extends JavaPlugin
{
    String Pluginprefix = "[" + ChatColor.GREEN + getDescription().getName() + ChatColor.RESET +"] ";
    String Pluginname = "[" + getDescription().getName() +"] ";
    public ConsoleLog cLog = new ConsoleLog(this);
    boolean DebugMode = false;
    @Override
    public void onEnable()
    {
        cLog.info("DebugMode is now ["+DebugMode+"].");
    }
    @Override
    public void onDisable()
    {
        
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if( (args.length >= 1) && ( "get".equalsIgnoreCase(args[0]) ))
        {
            if((args.length >= 2) && ( "player".equalsIgnoreCase(args[1]) ))
            {
                if(getPlayer(args[2])==null)
                {
                    cLog.Message(sender, "指定したプレイヤーは存在しません！");
                    return false;
                }
                Player p = getPlayer(args[2]); 
                double p_x = p.getLocation().getX();
                double p_y = p.getLocation().getY();
                double p_z = p.getLocation().getZ();
                cLog.Message(sender, args[2] +"の座標は" );
                cLog.Message(sender, "X: " + p_x );
                cLog.Message(sender, "Y: " + p_y );
                cLog.Message(sender, "Z: " + p_z );
                return true;       
            }
        }
        else if( ( args.length == 1 ) && ( "help".equalsIgnoreCase(args[0]) ) )
        {
            cLog.Message(sender, "CommandList");
            cLog.Message(sender, "/pld get player <playername> ... プレイヤーの位置を取得します");
            return true;
        }
        return false;
    }
    private Player getPlayer(String name)//指定されたプレイヤーが居るかどうかの判別
    {
        for (Player player : Bukkit.getOnlinePlayers())
        {
            if (player.getName().equals(name))
            {
                return player;
            }
        }
        return null;
    }
}
