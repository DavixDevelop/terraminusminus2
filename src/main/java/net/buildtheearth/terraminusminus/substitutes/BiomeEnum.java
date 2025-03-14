package net.buildtheearth.terraminusminus.substitutes;

/**
 * All vanilla Minecraft 1.12.2 biomes.
 *
 * Terra++'s biomes are not data-driven yet, so this is a simple enum.
 *
 * @author SmylerMC
 *
 */
public enum BiomeEnum {
    OCEAN(0, "minecraft:ocean"),
    PLAINS(1, "minecraft:plains"),
    DESERT(2, "minecraft:desert"),
    EXTREME_HILLS(3, "minecraft:extreme_hills"),
    FOREST(4, "minecraft:forest"),
    TAIGA(5, "minecraft:taiga"),
    SWAMPLAND(6, "minecraft:swampland"),
    RIVER(7, "minecraft:river"),
    HELL(8, "minecraft:hell"),
    SKY(9, "minecraft:sky"),
    FROZEN_OCEAN(10, "minecraft:frozen_ocean"),
    FROZEN_RIVER(11, "minecraft:frozen_river"),
    ICE_PLAINS(12, "minecraft:ice_flats"),
    ICE_MOUNTAINS(13, "minecraft:ice_mountains"),
    MUSHROOM_ISLAND(14, "minecraft:mushroom_island"),
    MUSHROOM_ISLAND_SHORE(15, "minecraft:mushroom_island_shore"),
    BEACH(16, "minecraft:beaches"),
    DESERT_HILLS(17, "minecraft:desert_hills"),
    FOREST_HILLS(18, "minecraft:forest_hills"),
    TAIGA_HILLS(19, "minecraft:taiga_hills"),
    EXTREME_HILLS_EDGE(20, "minecraft:smaller_extreme_hills"),
    JUNGLE(21, "minecraft:jungle"),
    JUNGLE_HILLS(22, "minecraft:jungle_hills"),
    JUNGLE_EDGE(23, "minecraft:jungle_edge"),
    DEEP_OCEAN(24, "minecraft:deep_ocean"),
    STONE_BEACH(25, "minecraft:stone_beach"),
    COLD_BEACH(26, "minecraft:cold_beach"),
    BIRCH_FOREST(27, "minecraft:birch_forest"),
    BIRCH_FOREST_HILLS(28, "minecraft:birch_forest_hills"),
    ROOFED_FOREST(29, "minecraft:roofed_forest"),
    COLD_TAIGA(30, "minecraft:taiga_cold"),
    COLD_TAIGA_HILLS(31, "minecraft:taiga_cold_hills"),
    REDWOOD_TAIGA(32, "minecraft:redwood_taiga"),
    REDWOOD_TAIGA_HILLS(33, "minecraft:redwood_taiga_hills"),
    EXTREME_HILLS_WITH_TREES(34, "minecraft:extreme_hills_with_trees"),
    SAVANNA(35, "minecraft:savanna"),
    SAVANNA_PLATEAU(36, "minecraft:savanna_rock"),
    MESA(37, "minecraft:mesa"),
    MESA_ROCK(38, "minecraft:mesa_rock"),
    MESA_CLEAR_ROCK(39, "minecraft:mesa_clear_rock"),
    VOID(127, "minecraft:void"),
    MUTATED_PLAINS(129, "minecraft:mutated_plains"),
    MUTATED_DESERT(130, "minecraft:mutated_desert"),
    MUTATED_EXTREME_HILLS(131, "minecraft:mutated_extreme_hills"),
    MUTATED_FOREST(132, "minecraft:mutated_forest"),
    MUTATED_TAIGA(133, "minecraft:mutated_taiga"),
    MUTATED_SWAMPLAND(134, "minecraft:mutated_swampland"),
    MUTATED_ICE_FLATS(140, "minecraft:mutated_ice_flats"),
    MUTATED_JUNGLE(149, "minecraft:mutated_jungle"),
    MUTATED_JUNGLE_EDGE(151, "minecraft:mutated_jungle_edge"),
    MUTATED_BIRCH_FOREST(155, "minecraft:mutated_birch_forest"),
    MUTATED_BIRCH_FOREST_HILLS(156, "minecraft:mutated_birch_forest_hills"),
    MUTATED_ROOFED_FOREST(157, "minecraft:mutated_roofed_forest"),
    MUTATED_TAIGA_COLD(158, "minecraft:mutated_taiga_cold"),
    MUTATED_REDWOOD_TAIGA(160, "minecraft:mutated_redwood_taiga"),
    MUTATED_REDWOOD_TAIGA_HILLS(161, "minecraft:mutated_redwood_taiga_hills"),
    MUTATED_EXTREME_HILLS_WITH_TREES(162, "minecraft:mutated_extreme_hills_with_trees"),
    MUTATED_SAVANNA(163, "minecraft:mutated_savanna"),
    MUTATED_SAVANNA_ROCK(164, "minecraft:mutated_savanna_rock"),
    MUTATED_MESA(165, "minecraft:mutated_mesa"),
    MUTATED_MESA_ROCK(166, "minecraft:mutated_mesa_rock"),
    MUTATED_MESA_CLEAR_ROCK(167, "minecraft:mutated_mesa_clear_rock");

    public final String biomeId;
    public final int numericId;

    BiomeEnum(int numericId, String biomeId) {
        this.biomeId = biomeId;
        this.numericId = numericId;
    }

    public static BiomeEnum byId(String biomeId) {
        for(BiomeEnum b: values()) {
            if(b.biomeId.equals(biomeId)) return b;
        }
        return null;
    }

    public static BiomeEnum getDefault() {
        return OCEAN;
    }
}
