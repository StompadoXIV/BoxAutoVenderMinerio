package me.autovenderminerio.listeners;

import com.gmail.nossr50.api.ExperienceAPI;
import me.autovenderminerio.BoxAutoVenderMinerio;
import me.autovenderminerio.ConfigManager;
import me.autovenderminerio.apis.BoxAPI;
import me.autovenderminerio.managers.Blocks;
import me.autovenderminerio.managers.BlocksDAO;
import me.autovenderminerio.managers.bonus.BonusDAO;
import me.autovenderminerio.methods.RewardsMethods;
import me.autovenderminerio.utils.Format;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BlockEvent implements Listener {

    public BlockEvent(BoxAutoVenderMinerio main) {
        Bukkit.getPluginManager().registerEvents(this, main);
    }

    ConfigManager Config = BoxAutoVenderMinerio.Config;

    @EventHandler
    void onBlockEvent(BlockBreakEvent e) {
        Player p = e.getPlayer();

        Blocks blocks = BlocksDAO.findBlockByMaterial(e.getBlock().getType());
        if (blocks == null) return;

        if (p.getGameMode() == GameMode.CREATIVE) return;

        if (Config.worldsDisabled.contains(p.getWorld().getName())) return;

        e.setCancelled(true);
        e.getBlock().setType(Material.AIR);

        double value = blocks.getValue();
        double[] bonus = BonusDAO.getBonus(p, value);

        value += bonus[0];

        if (blocks.isFortune())
            value *= getFortune(p);

        String amount = Format.formatNumber(value);
        String bonuses = Format.formatNumber(bonus[0]);

        BoxAutoVenderMinerio.getEconomy().depositPlayer(p, value);
        p.giveExp(blocks.getExp());

        if (Bukkit.getPluginManager().getPlugin("McMMO") != null) {
            ExperienceAPI.addLevel(p, "MINING", blocks.getMcmmo());
        }

        RewardsMethods.raffleReward(p, blocks);

        String bonusFormat = BonusDAO.getBonusByPermission(p) != null ? Config.bonusFormat.replace("{quantia}", bonuses).replace("{porcentagem}", "" + bonus[1]).replace("{grupo}", BonusDAO.getGroup(p)) : "§cSem bônus";

        String message = Config.message.replace("{bloco}", blocks.getName()).replace("{coins}", amount)
                .replace("{bonus}", bonusFormat);

        if (Config.isActionBar)
            BoxAPI.sendActionBar(message, p);
        else
            p.sendMessage(message);
    }

    public int getFortune(Player player) {
        ItemStack item = player.getItemInHand();
        if (item.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS))
            return item.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);

        return 1;
    }
}