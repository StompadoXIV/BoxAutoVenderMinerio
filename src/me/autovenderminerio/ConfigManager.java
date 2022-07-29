package me.autovenderminerio;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigManager {

    public List<String> worldsDisabled;

    public boolean isActionBar;
    public String message;
    public String bonusFormat;

    public void loadConfig() {

        FileConfiguration config = BoxAutoVenderMinerio.Instance.getConfig();

        worldsDisabled = config.getStringList("MundosBloqueados");

        isActionBar = config.getBoolean("Mensagens.AoVender.ActionBar");
        message = config.getString("Mensagens.AoVender.Mensagem");
        bonusFormat = config.getString("BonusFormato");

    }
}