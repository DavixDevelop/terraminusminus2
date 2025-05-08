package net.buildtheearth.terraminusminus.substitutes;

import java.util.List;
import java.util.Map;

/**
 *
 * Base interface for Biomes to be data-driven, ex. as in MetaBiome via MetaBiome
 *
 * @author DavixDevelop
 *
 */
public interface IBiome<T> {
    /**
     *
     * @return The numerical ID of the biome
     */
    int getNumericId();

    /**
     *
     * @return The string ID of the biome
     */
    String getId();

    /**
     *
     * @return  The actual biome data
     */
    T getBiome();
}
