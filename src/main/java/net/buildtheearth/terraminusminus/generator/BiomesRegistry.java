package net.buildtheearth.terraminusminus.generator;

import net.buildtheearth.terraminusminus.substitutes.Biome;
import net.buildtheearth.terraminusminus.substitutes.BiomeEnum;
import net.buildtheearth.terraminusminus.substitutes.IBiome;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A registry class to hold all IBiome's used, get the default registered IBiome,
 * and map all IBiome's, including pseudo based ones, like BiomeEnum based on, to real biome based IBiome which your mod/plugin uses.
 * <p>
 * Must be extended and new class set via BiomesRegistry.setDefaultBiomesRegistry, as by default BiomesRegistry.map(IBiome biome) returns biome as it is.
 *
 * @author DavixDevelop
 */
public abstract class BiomesRegistry {
    private static BiomesRegistry INSTANCE;

    public static Map<String, IBiome> REGISTRY = new ConcurrentHashMap<>();

    public static IBiome DEFAULT_BIOME = new Biome(BiomeEnum.getDefault());

    static {
        //Populate registry by initial biome list based on BiomeEnum
        for(BiomeEnum biomeEnum : BiomeEnum.values()){
            Biome b = new Biome(biomeEnum);
            //Register the biome in the registry
            BiomesRegistry.registerBiome(b);
        }
    }

    public static BiomesRegistry get(){
        return INSTANCE;
    }

    public static void setDefaultBiomesRegistry(BiomesRegistry biomesRegistry){
        INSTANCE = biomesRegistry;
    }

    public static void registerBiome(IBiome biome){
        if(REGISTRY.containsKey(biome.getId()))
            REGISTRY.replace(biome.getId(), biome);
        else
            REGISTRY.put(biome.getId(), biome);
    }

    /**
     * Get the IBiome from the REGISTRY.
     * Register all biomes in classes that implement IBiome to REGISTRY beforehand
     *
     * @param biomeID
     * @return
     */
    public static IBiome getById(String biomeID){
        return REGISTRY.getOrDefault(biomeID, null);
    }

    /**
     * Return the default IBiome instance
     *
     * @return
     */
    public static IBiome getDefault() {
        return DEFAULT_BIOME;
    }

    /**
     * Map all receiving biomes, including pseudo biomes, like the substitute {@link Biome} based on {@link BiomeEnum},
     * to real Minecraft biomes, when used in a mod or plugin, hence when used in such cases, you must implement
     * your own BiomesRegistry, and set it with BiomesRegistry.setDefaultBiomesRegistry
     * <p>
     * Ex:
     * <code>
     * <pre>
     * switch(biome.getId()){
     *      case "ocean":
     *          return RealBiome.getByEnum(org.bukkit.block.Biome.OCEAN);
     * }</pre>
     * </code>
     *
     * @param biome Any IBiome baked by biome bakers
     * @return The real Minecraft biome based IBiome
     */
    public IBiome map(IBiome biome){
        return biome;
    }


}
