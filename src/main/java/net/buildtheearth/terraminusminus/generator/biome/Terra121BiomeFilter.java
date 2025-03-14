package net.buildtheearth.terraminusminus.generator.biome;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.buildtheearth.terraminusminus.dataset.IScalarDataset;
import net.buildtheearth.terraminusminus.generator.ChunkBiomesBuilder;
import net.buildtheearth.terraminusminus.generator.EarthBiomeProvider;
import net.buildtheearth.terraminusminus.generator.EarthGeneratorPipelines;
import net.buildtheearth.terraminusminus.generator.GeneratorDatasets;
import net.buildtheearth.terraminusminus.projection.OutOfProjectionBoundsException;
import net.buildtheearth.terraminusminus.substitutes.Biome;
import net.buildtheearth.terraminusminus.substitutes.BiomeEnum;
import net.buildtheearth.terraminusminus.substitutes.ChunkPos;
import net.buildtheearth.terraminusminus.substitutes.IBiome;
import net.buildtheearth.terraminusminus.util.CornerBoundingBox2d;
import net.buildtheearth.terraminusminus.util.bvh.Bounds2d;

/**
 * Implementation of {@link IEarthBiomeFilter} that emits the same Biome as legacy terra121.
 *
 * @author DaPorkchop_
 */
public class Terra121BiomeFilter implements IEarthBiomeFilter<Terra121BiomeFilter.Data> {
    private static final IBiome DEFAULT_BIOME = Biome.getByBiomeEnum(BiomeEnum.OCEAN);

    @Override
    public CompletableFuture<Terra121BiomeFilter.Data> requestData(ChunkPos pos, GeneratorDatasets datasets, Bounds2d bounds, CornerBoundingBox2d boundsGeo) throws OutOfProjectionBoundsException {
        CompletableFuture<double[]> precipitationFuture = datasets.<IScalarDataset>getCustom(EarthGeneratorPipelines.KEY_DATASET_TERRA121_PRECIPITATION).getAsync(boundsGeo, 16, 16);
        CompletableFuture<double[]> soilFuture = datasets.<IScalarDataset>getCustom(EarthGeneratorPipelines.KEY_DATASET_TERRA121_SOIL).getAsync(boundsGeo, 16, 16);
        CompletableFuture<double[]> temperatureFuture = datasets.<IScalarDataset>getCustom(EarthGeneratorPipelines.KEY_DATASET_TERRA121_TEMPERATURE).getAsync(boundsGeo, 16, 16);

        return CompletableFuture.allOf(precipitationFuture, soilFuture, temperatureFuture)
                .thenApply(unused -> new Data(precipitationFuture.join(), soilFuture.join(), temperatureFuture.join()));
    }

    @Override
    public void bake(ChunkPos pos, ChunkBiomesBuilder builder, Terra121BiomeFilter.Data data) {
        IBiome[] biome = builder.state();

        if (data == null) {
            Arrays.fill(biome, DEFAULT_BIOME);
            return;
        }

        double[] precipitation = data.precipitation;
        double[] soil = data.soil;
        double[] temperature = data.temperature;

        for (int i = 0; i < 16 * 16; i++) {
            biome[i] = this.classify(precipitation[i], soil[i], temperature[i]);
        }
    }

    /**
     * This monstrosity of a piece of garbage is copied directly from the original terra121 implementation of {@link EarthBiomeProvider}.
     */
    protected IBiome classify(double precipitation, double soil, double temperature) {
        switch ((int) soil) {
            case 0: //Ocean
                return Biome.getByBiomeEnum(BiomeEnum.OCEAN);
            case 1: //Shifting Sand
                return Biome.getByBiomeEnum(BiomeEnum.DESERT);
            case 2: //Rock
                return Biome.getByBiomeEnum(BiomeEnum.DESERT); //cant find it (rock mountians)
            case 3: //Ice
                return Biome.getByBiomeEnum(BiomeEnum.ICE_MOUNTAINS);
            case 5:
            case 6:
            case 7: //Permafrost
                return Biome.getByBiomeEnum(BiomeEnum.ICE_PLAINS);
            case 10, 34:
                return Biome.getByBiomeEnum(BiomeEnum.JUNGLE);
            case 11:
            case 12, 41, 42, 43, 44, 45, 70, 72, 73, 74, 75, 76, 77, 82, 85:
                return Biome.getByBiomeEnum(BiomeEnum.PLAINS);
            case 15:
                if (temperature < 5) {
                    return Biome.getByBiomeEnum(BiomeEnum.COLD_TAIGA);
                } else if (temperature > 15) {
                    return Biome.getByBiomeEnum(BiomeEnum.SWAMPLAND);
                }
                return Biome.getByBiomeEnum(BiomeEnum.FOREST);
            case 16:
            case 17:
            case 18:
            case 19:
                if (temperature < 15) {
                    if (temperature < 0) {
                        return Biome.getByBiomeEnum(BiomeEnum.COLD_TAIGA);
                    }
                    return Biome.getByBiomeEnum(BiomeEnum.SWAMPLAND);
                }
                if (temperature > 20) {
                    return Biome.getByBiomeEnum(BiomeEnum.SWAMPLAND);
                }
                return Biome.getByBiomeEnum(BiomeEnum.FOREST);
            case 29:
            case 30:
            case 31:
            case 32:
            case 33, 54, 56, 96:
                return Biome.getByBiomeEnum(BiomeEnum.SAVANNA);
            case 50:
                return Biome.getByBiomeEnum(BiomeEnum.COLD_TAIGA);
            case 51: //salt flats always desert
                return Biome.getByBiomeEnum(BiomeEnum.DESERT);
            case 52:
            case 53:
            case 55:
            case 99: //hot and dry
                if (temperature < 2) {
                    return Biome.getByBiomeEnum(BiomeEnum.COLD_TAIGA);
                } else if (temperature < 5) {
                    return Biome.getByBiomeEnum(BiomeEnum.TAIGA);
                } else if (precipitation < 5) {
                    return Biome.getByBiomeEnum(BiomeEnum.DESERT);
                }
                return Biome.getByBiomeEnum(BiomeEnum.MESA); //TODO: this soil can also be desert i.e. saudi Arabia (base on percip?)
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
                if (temperature < 10) {
                    return Biome.getByBiomeEnum(BiomeEnum.TAIGA);
                }
                return Biome.getByBiomeEnum(BiomeEnum.FOREST);
            case 13:
            case 40:
            case 71:
            case 80:
            case 95:
            case 98:
                return Biome.getByBiomeEnum(BiomeEnum.SWAMPLAND);
            case 81:
            case 83:
            case 84:
            case 86, 90, 91, 92, 93, 94:
                return Biome.getByBiomeEnum(BiomeEnum.FOREST);
            case 97:
                return Biome.getByBiomeEnum(BiomeEnum.DESERT);
        }

        return Biome.getByBiomeEnum(BiomeEnum.PLAINS);
    }

    @RequiredArgsConstructor
    protected static class Data {
        @NonNull
        protected final double[] precipitation;
        @NonNull
        protected final double[] soil;
        @NonNull
        protected final double[] temperature;
    }
}
