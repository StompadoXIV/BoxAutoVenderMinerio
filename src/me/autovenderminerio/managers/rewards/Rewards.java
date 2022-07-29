package me.autovenderminerio.managers.rewards;

import org.bukkit.Sound;

import java.util.List;

public class Rewards {

    private int chance;
    private boolean isTitle;
    private String title;
    private String subTitle;
    private boolean isSound;
    private Sound sound;
    private List<String> commands;

    public Rewards(int chance, boolean isTitle, String title, String subTitle, boolean isSound, Sound sound, List<String> commands) {
        this.chance = chance;
        this.isTitle = isTitle;
        this.title = title;
        this.subTitle = subTitle;
        this.isSound = isSound;
        this.sound = sound;
        this.commands = commands;
    }

    public int getChance() {
        return chance;
    }

    public boolean isTitle() {
        return isTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public boolean isSound() {
        return isSound;
    }

    public Sound getSound() {
        return sound;
    }

    public List<String> getCommands() {
        return commands;
    }
}
