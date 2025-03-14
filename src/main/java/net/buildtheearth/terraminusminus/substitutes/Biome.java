package net.buildtheearth.terraminusminus.substitutes;

import net.buildtheearth.terraminusminus.generator.BiomesRegistry;

public class Biome implements IBiome<BiomeEnum> {

    private final BiomeEnum biome;

    public Biome(BiomeEnum biome) {
        this.biome = biome;
    }

    @Override
    public String getId() {
        return biome.biomeId;
    }

    @Override
    public int getNumericId() {
        return biome.numericId;
    }

    @Override
    public BiomeEnum getBiome() {
        return biome;
    }

    public static IBiome getByBiomeEnum(BiomeEnum biomeEnum){
        return  BiomesRegistry.REGISTRY.getOrDefault(biomeEnum.biomeId, new Biome(BiomeEnum.OCEAN));
    }
}
