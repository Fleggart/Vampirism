package de.teamlapen.vampirism.core;

import de.teamlapen.vampirism.VampirismMod;
import de.teamlapen.vampirism.util.REFERENCE;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Handles all biome registrations and reference.
 */
public class ModBiomes {

    static void registerBiomes(IForgeRegistry<Biome> registry) {
        // No biomes registered
        VampirismMod.log.d("ModBiomes", "No biomes registered");
    }
}
