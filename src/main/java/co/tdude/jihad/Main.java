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

    ArrayList<ItemStack> kit;
    ItemStack tnt;
    ItemMeta lightermeta;
    ItemStack lighter;
    Map<UUID, Long> users;

    @Override
    public void onEnable() {
        getLogger().info("Jihad by Tristan enabled!");
        kit = new ArrayList<ItemStack>();
        users = new HashMap<UUID, Long>();
        tnt = new ItemStack(Material.TNT, 64);
        lighter = new ItemStack(Material.FLINT_AND_STEEL, 1);
        lightermeta = lighter.getItemMeta();
        lightermeta.setDisplayName("Tristan's ALLAHU AKBAR"); //:-)
        lightermeta.addEnchant(Enchantment.DURABILITY, 3, false);
        lighter.setItemMeta(lightermeta);
        kit.add(lighter);
        kit.add(tnt);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("jihad")) {
            if (sender instanceof Player) {
                if ((users.get(((Player) sender).getUniqueId()) == null)) {
                    for (ItemStack itemStack : kit) {
                        ((Player) sender).getInventory().addItem(itemStack);
                    }
                    users.put(((Player) sender).getUniqueId(), System.currentTimeMillis());
                    return true;
                } else {
                    if (users.get(((Player) sender).getUniqueId()) + (60 * 1000) <= System.currentTimeMillis()) {
                        for (ItemStack itemStack : kit) {
                            ((Player) sender).getInventory().addItem(itemStack);
                        }
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
