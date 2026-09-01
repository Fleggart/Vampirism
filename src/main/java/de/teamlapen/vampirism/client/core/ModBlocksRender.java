package de.teamlapen.vampirism.client.core;

import de.teamlapen.lib.lib.util.InventoryRenderHelper;
import de.teamlapen.vampirism.api.entity.factions.IPlayableFaction;
import de.teamlapen.vampirism.blocks.*;
import de.teamlapen.vampirism.client.render.tiles.CoffinTESR;
import de.teamlapen.vampirism.client.render.tiles.TotemTESR;
import de.teamlapen.vampirism.core.ModBlocks;
import de.teamlapen.vampirism.tileentity.*;
import de.teamlapen.vampirism.util.REFERENCE;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Handles all block render registration including TileEntities
 */
@SideOnly(Side.CLIENT)
public class ModBlocksRender {


    public static void register() {
        registerRenderer();
        registerTileRenderer();
    }

    static void registerColors() {
        Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> {
            if (tintIndex == 255) {
                TileEntity tile = (worldIn == null || pos == null) ? null : worldIn.getTileEntity(pos);
                if (tile instanceof TileTotem) {
                    IPlayableFaction f = ((TileTotem) tile).getControllingFaction();
                    if (f != null) return f.getColor();
                }
            }
            return 0xFFFFFF;
        }, ModBlocks.totem_top);
    }

    private static void registerRenderer() {
        InventoryRenderHelper renderHelper = new InventoryRenderHelper(REFERENCE.MODID);
        renderHelper.registerRenderAllMeta(Item.getItemFromBlock(ModBlocks.castle_block), BlockCastleBlock.EnumType.values());
        renderHelper.registerRenderAllMeta(Item.getItemFromBlock(ModBlocks.altar_pillar), BlockAltarPillar.EnumPillarType.values());
        renderHelper.registerRender(ModBlocks.altar_tip);
        renderHelper.registerRender(ModBlocks.cursed_earth);
        renderHelper.registerRender(ModBlocks.blood_container);
        renderHelper.registerRender(ModBlocks.altar_inspiration);
        renderHelper.registerRender(ModBlocks.fire_place);
        // hunter_table 和 hunter_table2 的渲染注册已删除
        renderHelper.registerRenderAllMeta(Item.getItemFromBlock(ModBlocks.vampirism_flower), VampirismFlower.EnumFlowerType.values());
        renderHelper.registerRender(Item.getItemFromBlock(ModBlocks.weapon_table), "inventory");
        renderHelper.registerRenderAllMeta(Item.getItemFromBlock(ModBlocks.blood_grinder), EnumFacing.HORIZONTALS);
        renderHelper.registerRender(ModBlocks.blood_sieve);
        renderHelper.registerRender(ModBlocks.totem_base);
        renderHelper.registerRender(ModBlocks.totem_top);

        ModelLoader.setCustomStateMapper(ModBlocks.weapon_table, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(new ResourceLocation(REFERENCE.MODID, BlockWeaponTable.regName), "normal");
            }
        });
        ModelBakery.registerItemVariants(Item.getItemFromBlock(ModBlocks.block_blood_fluid));
        ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(ModBlocks.block_blood_fluid), stack -> new ModelResourceLocation(new ResourceLocation(REFERENCE.MODID, "fluids"), "blood"));
        ModelLoader.setCustomStateMapper(ModBlocks.block_blood_fluid, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(new ResourceLocation(REFERENCE.MODID, "fluids"), "blood");
            }
        });
        ModelLoader.setCustomStateMapper(ModBlocks.block_impure_blood_fluid, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(new ResourceLocation(REFERENCE.MODID, "fluids"), "impure_blood");
            }
        });
        ModelLoader.setCustomStateMapper(ModBlocks.block_coffin, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(new ResourceLocation(REFERENCE.MODID, "block_coffin"), "normal");
            }
        });
        ModelLoader.setCustomStateMapper(ModBlocks.tent_main, new StateMapperBase() {
            @Override
            protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
                return new ModelResourceLocation(Block.REGISTRY.getNameForObject(ModBlocks.tent), this.getPropertyString(state.getProperties()));

            }
        });
    }

    private static void registerTileRenderer() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileCoffin.class, new CoffinTESR());
        ClientRegistry.bindTileEntitySpecialRenderer(TileTotem.class, new TotemTESR());
    }


}
