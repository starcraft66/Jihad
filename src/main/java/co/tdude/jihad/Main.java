package co.tdude.jihad;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Tristan on 2015-04-01.
 */
public class Main extends JavaPlugin {

    Map<UUID, Long> users;

    @Override
    public void onEnable() {
        getLogger().info("Jihad by Tristan enabled!");
        users = new HashMap<UUID, Long>();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("jihad")) {
            if (sender instanceof Player) {
                if ((users.get(((Player) sender).getUniqueId()) == null)) {
                    ItemStack tnt = new ItemStack(Material.TNT, 64);
                    ItemStack lighter = new ItemStack(Material.FLINT_AND_STEEL, 1);
                    ItemMeta lightermeta = lighter.getItemMeta();
                    lightermeta.setDisplayName("Tristan's ALLAHU AKBAR"); //:-)
                    lightermeta.addEnchant(Enchantment.DURABILITY, 3, false);
                    lighter.setItemMeta(lightermeta);
                    ((Player) sender).getInventory().addItem(tnt, lighter);
                    users.put(((Player) sender).getUniqueId(), System.currentTimeMillis());
                    return true;
                } else {
                    if (users.get(((Player) sender).getUniqueId()) + (60 * 1000) <= System.currentTimeMillis()) {
                        ItemStack tnt = new ItemStack(Material.TNT, 64);
                        ItemStack lighter = new ItemStack(Material.FLINT_AND_STEEL, 1);
                        ItemMeta lightermeta = lighter.getItemMeta();
                        lightermeta.setDisplayName("Tristan's ALLAHU AKBAR"); //:-)
                        lightermeta.addEnchant(Enchantment.DURABILITY, 3, false);
                        lighter.setItemMeta(lightermeta);
                        ((Player) sender).getInventory().addItem(tnt, lighter);
                        users.put(((Player) sender).getUniqueId(), System.currentTimeMillis());
                        return true;
                    }
                }
                sender.sendMessage(ChatColor.RED + "You have to wait before doing that again m8.");
                return true;
            }
        } else {
            sender.sendMessage("You are not a player!");
            return true;
        }
        return false;
    }
}
