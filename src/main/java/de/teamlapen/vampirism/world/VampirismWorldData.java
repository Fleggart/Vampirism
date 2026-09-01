package de.teamlapen.vampirism.world;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.storage.WorldSavedData;

import javax.annotation.Nonnull;

/**
 * Store all kinds of data which needs to be stored and related to a world
 */
public class VampirismWorldData extends WorldSavedData {

    private static final String IDENTIFIER = "vampirism";

    public static @Nonnull
    VampirismWorldData get(@Nonnull World world) {
        String s = fileNameForProvider(world.provider);
        VampirismWorldData data = (VampirismWorldData) world.getPerWorldStorage().getOrLoadData(VampirismWorldData.class, s);
        if (data == null) {
            data = new VampirismWorldData(world);
            world.getPerWorldStorage().setData(s, data);
        } else {
            data.world = world;
        }
        return data;
    }

    private static String fileNameForProvider(WorldProvider provider) {
        return IDENTIFIER + provider.getDimensionType().getSuffix();
    }

    private World world;

    public VampirismWorldData(String name) {
        super(name);
    }

    private VampirismWorldData(World world) {
        this(fileNameForProvider(world.provider));
        this.world = world;
        this.markDirty();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        // No data to read currently
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        // No data to write currently
        return compound;
    }
}
