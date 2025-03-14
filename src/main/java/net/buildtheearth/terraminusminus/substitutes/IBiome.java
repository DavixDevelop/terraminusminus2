package net.buildtheearth.terraminusminus.substitutes;

import java.util.List;
import java.util.Map;

/**
 * All vanilla Minecraft 1.12.2 biomes.
 *
 * Terra++'s biomes are not data-driven yet, so this is a simple enum.
 * 
 * @author SmylerMC
 *
 */
public interface IBiome<T> {
    int getNumericId();
    String getId();
    T getBiome();
}
