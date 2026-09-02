package de.teamlapen.vampirism.client.core;

import de.teamlapen.lib.lib.util.InventoryRenderHelper;
import de.teamlapen.vampirism.api.entity.factions.IPlayableFaction;
import de.teamlapen.vampirism.blocks.*;
import de.teamlapen.vampirism.client.render.tiles.CoffinTESR;
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
        // 图腾颜色注册已删除
    }

    private static void registerRenderer() {
        InventoryRenderHelper renderHelper = new InventoryRenderHelper(REFERENCE.MODID);
        // altar_pillar 渲染注册已移除
        // altar_tip 渲染注册已移除
        renderHelper.registerRender(ModBlocks.cursed_earth);
        renderHelper.registerRender(ModBlocks.blood_container);
        // VampirismFlower 渲染注册已移除
        renderHelper.registerRender(Item.getItemFromBlock(ModBlocks.weapon_table), "inventory");
        renderHelper.registerRenderAllMeta(Item.getItemFromBlock(ModBlocks.blood_grinder), EnumFacing.HORIZONTALS);
        // blood_sieve 已移除

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
        // TileSieve 已移除
    }
}