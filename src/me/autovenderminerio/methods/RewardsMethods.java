package me.autovenderminerio.methods;

import me.autovenderminerio.apis.BoxAPI;
import me.autovenderminerio.managers.Blocks;
import me.autovenderminerio.managers.rewards.Rewards;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class RewardsMethods {

    private static Rewards getRandomReward(Blocks blocks) {
        List<Rewards> rewards = blocks.getRewards();
        Random random = new Random();

        Rewards reward = rewards.get(random.nextInt(rewards.size()));
        if (random.nextInt(100) == reward.getChance())
            return reward;

        return null;
    }

    public static void raffleReward(Player p, Blocks blocks) {
        Rewards reward = getRandomReward(blocks);
        if (reward == null) return;

        BoxAPI.runCommandList(p, reward.getCommands());

        if (reward.isTitle())
            BoxAPI.sendTitle(p, reward.getTitle(), reward.getSubTitle());

        if (reward.isSound())
            BoxAPI.playSound(p, reward.getSound());
    }
}