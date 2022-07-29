package me.autovenderminerio.managers.rewards;

import java.util.HashMap;
import java.util.Map;

public class RewardsDAO {

    private static Map<String, Rewards> rewardsMap = new HashMap<>();

    public static void addRewards(String path, Rewards rewards) {
        rewardsMap.put(path, rewards);
    }

    public static Rewards getReward(String key) {
        return rewardsMap.get(key);
    }

    public static Map<String, Rewards> getRewardsMap() {
        return rewardsMap;
    }
}
