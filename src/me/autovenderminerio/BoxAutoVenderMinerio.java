package me.autovenderminerio;

import me.autovenderminerio.listeners.BlockEvent;
import me.autovenderminerio.managers.Blocks;
import me.autovenderminerio.managers.BlocksDAO;
import me.autovenderminerio.managers.bonus.Bonus;
import me.autovenderminerio.managers.bonus.BonusDAO;
import me.autovenderminerio.managers.rewards.Rewards;
import me.autovenderminerio.managers.rewards.RewardsDAO;
import me.autovenderminerio.utils.DateManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class BoxAutoVenderMinerio extends JavaPlugin {

    public static BoxAutoVenderMinerio Instance;
    public static ConfigManager Config;

    private static Economy economy = null;

    public void onEnable() {
        Instance = this;
        registerYaml();
        registerRewards();
        registerBlocks();
        registerBonus();
        setupEconomy();
        registerEvents();
        sendMessage();
    }

    private void registerEvents() {
        new BlockEvent(this);
    }

    private void registerYaml() {
        Config = new ConfigManager();
        saveDefaultConfig();
        Config.loadConfig();
        DateManager.createConfig("blocos");
        DateManager.createConfig("bonus");
        DateManager.createConfig("recompensas");
    }

    private void sendMessage() {
        Bukkit.getConsoleSender().sendMessage("§e[BoxAutoVenderMinerio] §fCriado por §b[Stompado]");
        Bukkit.getConsoleSender().sendMessage("§b[Discord] §fhttps://discord.gg/Z6PbQgdweB");
        Bukkit.getConsoleSender().sendMessage("§e[BoxAutoVenderMinerio] §aO plugin §eBoxAutoVenderMinerio §afoi iniciado com sucesso.");
    }

    private void registerBlocks() {

        for (String path : DateManager.getConfig("blocos").getConfigurationSection("Blocos").getKeys(false)) {

            ConfigurationSection key = DateManager.getConfig("blocos").getConfigurationSection("Blocos." + path);

            Material material = Material.valueOf(key.getString("Material"));
            String name = key.getString("Nome").replace("&", "§");
            double value = key.getDouble("Valor");
            int exp = key.getInt("XP");
            int mcmmo = key.getInt("McMMO");
            boolean isFortune = key.getBoolean("Fortuna");


            List<Rewards> rewards = new ArrayList<>();
            key.getStringList("Recompensas").forEach(reward -> rewards.add(RewardsDAO.getReward(reward)));

            Blocks blocks = new Blocks(material, name, value, exp, mcmmo, isFortune, rewards);
            BlocksDAO.addBlocks(path, blocks);

        }
    }

    private void registerBonus() {

        for (String path : DateManager.getConfig("bonus").getConfigurationSection("Bonus").getKeys(false)) {

            ConfigurationSection key = DateManager.getConfig("bonus").getConfigurationSection("Bonus." + path);

            String permission = key.getString("Permissao");
            String group = key.getString("Grupo");
            double percentage = key.getDouble("Porcentagem");

            Bonus bonus = new Bonus(permission, group, percentage);
            BonusDAO.addBonus(bonus);
        }
    }

    private void registerRewards() {

        for (String path : DateManager.getConfig("recompensas").getConfigurationSection("Recompensas").getKeys(false)) {

            ConfigurationSection key = DateManager.getConfig("recompensas").getConfigurationSection("Recompensas." + path);

            int chance = key.getInt("Chance");

            boolean isTitle = key.getBoolean("Titulos.Ativar");
            String title = key.getString("Titulos.Titulo");
            String subTitle = key.getString("Titulos.SubTitulo");

            boolean isSound = key.getBoolean("Sons.Som");
            Sound sound = Sound.valueOf(key.getString("Sons.Som"));

            List<String> commands = key.getStringList("Comandos");

            Rewards rewards = new Rewards(chance, isTitle, title, subTitle, isSound, sound, commands);
            RewardsDAO.addRewards(path, rewards);

        }
    }

    private void setupEconomy() {
        RegisteredServiceProvider<Economy> econ = getServer().getServicesManager().getRegistration(Economy.class);
        if (econ == null)
            return;

        economy = econ.getProvider();

    }

    public static Economy getEconomy() {
        return economy;
    }
}