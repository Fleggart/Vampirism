package de.teamlapen.vampirism.core;

import com.google.common.base.CaseFormat;
import com.google.common.collect.Maps;
import de.teamlapen.lib.lib.item.ItemMetaBlock;
import de.teamlapen.vampirism.VampirismMod;
import de.teamlapen.vampirism.blocks.*;
import de.teamlapen.vampirism.tileentity.*;
import de.teamlapen.vampirism.util.REFERENCE;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.datafix.IFixableData;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import java.util.Map;

import static de.teamlapen.lib.lib.util.UtilLib.getNull;

/**
 * Handles all block registrations and reference.
 */
@GameRegistry.ObjectHolder(REFERENCE.MODID)
public class ModBlocks {
    public static final BlockFluidBlood block_blood_fluid = getNull();
    public static final BlockFluidBlood block_impure_blood_fluid = getNull();
    public static final BlockCursedEarth cursed_earth = getNull();
    public static final BlockTent tent = getNull();
    public static final BlockCoffin block_coffin = getNull();
    public static final BlockMedChair med_chair = getNull();
    public static final BlockGarlic garlic = getNull();
    public static final BlockBloodContainer blood_container = getNull();
    public static final BlockWeaponTable weapon_table = getNull();
    
    private static final Map<String, String> OLD_TO_NEW_TILE_MAP = Maps.newHashMap();

    private static void registerTiles() {
        registerTileEntity(TileTent.class, "tent", "VampirismTent");
        registerTileEntity(TileCoffin.class, "coffin", "VampirismCoffin");
        registerTileEntity(TileBloodContainer.class, "blood_container", "VampirismBloodContainer");
    }

    /**
     * Register the given tile entity and add pre 1.11 name to DATA FIXER
     *
     * @param clazz Tile class
     * @param id    Tile id. Is converted to resource location  MODID:<id>
     */
    private static void registerTileEntity(Class<? extends TileEntity> clazz, String id, String old) {
        registerTileEntity(clazz, id);
        OLD_TO_NEW_TILE_MAP.put(old, REFERENCE.MODID + ":" + id);
    }

    public static IFixableData getTileEntityIDFixer() {
        return new IFixableData() {
            @Nonnull
            @Override
            public NBTTagCompound fixTagCompound(@Nonnull NBTTagCompound compound) {
                String id = compound.getString("id");
                String newId = OLD_TO_NEW_TILE_MAP.get(id);

                if (newId != null) {
                    compound.setString("id", newId);
                }
                return compound;
            }

            @Override
            public int getFixVersion() {
                return 1;
            }
        };
    }

    /**
     * Register the given tile entity
     *
     * @param clazz Tile class
     * @param id    Tile id. Is converted to resource location  MODID:<id>
     */
    private static void registerTileEntity(Class<? extends TileEntity> clazz, String id) {
        GameRegistry.registerTileEntity(clazz, REFERENCE.MODID + ":" + id);
    }

    static void registerItemBlocks(IForgeRegistry<Item> registry) {
        Item itemBloodContainer = new ItemBlock(blood_container);
        itemBloodContainer.setRegistryName(blood_container.getRegistryName());
        itemBloodContainer.setMaxStackSize(1);
        registry.register(itemBloodContainer);
        registry.register(itemBlock(block_blood_fluid));
        registry.register(itemBlock(cursed_earth));
        registry.register(itemBlock(weapon_table));
    }

    private static @Nonnull ItemBlock itemBlock(@Nonnull Block b) {
        ItemBlock item = new ItemBlock(b);
        //noinspection ConstantConditions
        item.setRegistryName(b.getRegistryName());
        return item;
    }

    static void registerBlocks(IForgeRegistry<Block> registry) {
        registry.register(new BlockFluidBlood(ModFluids.blood, "block_blood_fluid"));
        registry.register(new BlockFluidBlood(ModFluids.impure_blood, "block_impure_blood_fluid"));
        registry.register(new BlockCursedEarth());
        registry.register(new BlockTent());
        registry.register(new BlockCoffin());
        registry.register(new BlockMedChair());
        registry.register(new BlockGarlic());
        registry.register(new BlockBloodContainer());
        registry.register(new BlockWeaponTable());
        registerTiles();
    }

    static void registerCraftingRecipes() {
        
    }

    /**
     * Fix block mappings
     *
     * @return if it was fixed
     */
    static boolean fixMapping(RegistryEvent.MissingMappings.Mapping<Block> mapping) {
        return checkMapping(mapping, mapping.key.getPath(), false, blood_container, block_coffin, cursed_earth, block_blood_fluid, med_chair, weapon_table);
    }

    private static boolean checkMapping(RegistryEvent.MissingMappings.Mapping mapping, String name, boolean itemBlock, Block... blocks) {
        for (Block b : blocks) {
            String newRegisteredName = null;
            if (b instanceof VampirismBlock) {
                newRegisteredName = ((VampirismBlock) b).getRegisteredName();
            } else if (b instanceof VampirismBlockContainer) {
                newRegisteredName = ((VampirismBlockContainer) b).getRegisteredName();
            } else if (b instanceof BlockFluidBlood) {
                newRegisteredName = ((BlockFluidBlood) b).getRegisteredName();
            }
            
            if (newRegisteredName == null) {
                VampirismMod.log.w("ModBlocks", "Unknown block class %s. Unable to determine new registered name during mapping fix", b.getClass());
                continue;
            }
            String oldRegisteredName = newRegisteredName.replaceAll("_", "");

            if (oldRegisteredName.equals(name)) {
                if (itemBlock) {
                    mapping.remap(Item.getItemFromBlock(b));
                } else {
                    mapping.remap(b);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Fix item block mappings
     *
     * @return if it was fixed
     */
    static boolean fixMappingItemBlock(RegistryEvent.MissingMappings.Mapping<Item> mapping) {
        //Check for mappings changed for 1.11 CamelCase to lower underscore
        String converted = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, mapping.key.getPath());
        return checkMapping(mapping, converted, true, blood_container, cursed_earth, block_blood_fluid, weapon_table);
    }
}