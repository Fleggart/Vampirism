package de.teamlapen.vampirism.client.core;

import de.teamlapen.lib.lib.util.InventoryRenderHelper;
import de.teamlapen.vampirism.VampirismMod;
import de.teamlapen.vampirism.api.items.IItemWithTier;
import de.teamlapen.vampirism.core.ModItems;
import de.teamlapen.vampirism.items.ItemBloodBottle;
import de.teamlapen.vampirism.items.ItemCrossbowArrow;
import de.teamlapen.vampirism.items.ItemInjection;
import de.teamlapen.vampirism.items.ItemPureBlood;
import de.teamlapen.vampirism.items.ItemVampireCloak.EnumCloakColor;
import de.teamlapen.vampirism.player.hunter.HunterLevelingConf;
import de.teamlapen.vampirism.util.REFERENCE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Handles item render registration
 */
@SideOnly(Side.CLIENT)
public class ModItemsRender {

    public static void register() {
        registerRenderers();
    }

    static void registerColors() {

        Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
            if (tintIndex == 1) {
                return ItemCrossbowArrow.getType(stack).color;
            }
            return 0xFFFFFF;
        }, ModItems.crossbow_arrow);
    }

    private static void registerRenderers() {
        VampirismMod.log.d("ModItemsRender", "Registering renderer");
        InventoryRenderHelper renderHelper = new InventoryRenderHelper(REFERENCE.MODID);
        renderHelper.registerRender(ModItems.vampire_fang, "normal");
        renderHelper.registerRender(ModItems.human_heart, "normal");
        renderHelper.registerRender(ModItems.weak_human_heart, "normal");
        renderHelper.registerRender(ModItems.item_tent, "normal");
        renderHelper.registerRenderAllMeta(ModItems.blood_bottle, ItemBloodBottle.AMOUNT + 1);
        renderHelper.registerRender(ModItems.item_coffin, "normal");
        renderHelper.registerRenderAllMeta(ModItems.pure_blood, ItemPureBlood.COUNT);
        renderHelper.registerRender(ModItems.item_garlic, "normal");
        renderHelper.registerRenderAllMeta(ModItems.injection, ItemInjection.META_COUNT);
        renderHelper.registerRender(ModItems.item_med_chair, "normal");
        renderHelper.registerRender(ModItems.basic_crossbow, "normal");
        renderHelper.registerRender(ModItems.crossbow_arrow, "normal");
        renderHelper.registerRender(ModItems.basic_double_crossbow, "normal");
        renderHelper.registerRender(ModItems.enhanced_crossbow, "normal");
        renderHelper.registerRender(ModItems.enhanced_double_crossbow, "normal");
        renderHelper.registerRender(ModItems.stake, "normal");
        renderHelper.registerRender(ModItems.vampire_blood_bottle, "normal");
        renderHelper.registerRender(ModItems.blood_potion, "normal");
        renderHelper.registerRender(ModItems.basic_tech_crossbow, "normal");
        renderHelper.registerRender(ModItems.enhanced_tech_crossbow, "normal");
        renderHelper.registerRender(ModItems.tech_crossbow_ammo_package, "normal");
        renderHelper.registerRender(ModItems.holy_salt, "normal");
        renderHelper.registerRender(ModItems.holy_salt_water, "normal");
        renderHelper.registerRender(ModItems.soul_orb_vampire, "normal");
        registerVampireCloakWithColor(ModItems.vampire_cloak, "vampire_cloak");
        renderHelper.registerRender(ModItems.garlic_bread, "normal");
    }

    /**
     * Register all variants of an Item based on {@link EnumCloakColor} Only works
     * with vampirecloaks
     */
    private static void registerVampireCloakWithColor(Item item, String baseName) {
        for (EnumCloakColor e : EnumCloakColor.values()) {
            ModelLoader.setCustomModelResourceLocation(item, e.getMetadata(), new ModelResourceLocation(new ResourceLocation(REFERENCE.MODID, "item/" + baseName), "color=" + e.getDyeColorName()));
        }
    }

}
