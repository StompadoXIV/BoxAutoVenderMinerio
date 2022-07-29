package me.autovenderminerio.managers;

import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class BlocksDAO {

    private static Map<String, Blocks> blocksMap = new HashMap<>();

    public static void addBlocks(String path, Blocks blocks) {
        blocksMap.put(path, blocks);
    }

    public static Blocks findBlockByMaterial(Material material) {
        return blocksMap.entrySet().stream().map(Map.Entry::getValue).filter(blocks -> blocks.getMaterial().equals(material)).findFirst().orElse(null);
    }

    public static Map<String, Blocks> getBlocksMap() {
        return blocksMap;
    }
}
