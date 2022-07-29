package me.autovenderminerio.managers;

import me.autovenderminerio.managers.rewards.Rewards;
import org.bukkit.Material;

import java.util.List;

public class Blocks {

    private Material material;
    private String name;
    private double value;
    private int exp;
    private int mcmmo;
    private boolean isFortune;
    public List<Rewards> rewards;

    public Blocks(Material material, String name, double value, int exp, int mcmmo, boolean isFortune, List<Rewards> rewards) {
        this.material = material;
        this.name = name;
        this.value = value;
        this.exp = exp;
        this.mcmmo = mcmmo;
        this.isFortune = isFortune;
        this.rewards = rewards;
    }

    public Material getMaterial() {
        return material;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public int getExp() {
        return exp;
    }

    public int getMcmmo() {
        return mcmmo;
    }

    public boolean isFortune() {
        return isFortune;
    }

    public List<Rewards> getRewards() {
        return rewards;
    }
}
