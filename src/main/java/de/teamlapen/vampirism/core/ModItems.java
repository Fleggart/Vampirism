// ModItems.java - 移除 holy_water_bottle, holy_water_splash_bottle, vampire_book, blood_infused_iron_ingot 和 blood_infused_enhanced_iron_ingot 相关内容
package de.teamlapen.vampirism.core;

import de.teamlapen.lib.lib.util.UtilLib;
import de.teamlapen.vampirism.VampirismMod;
import de.teamlapen.vampirism.api.VReference;
import de.teamlapen.vampirism.api.entity.player.skills.ISkill;
import de.teamlapen.vampirism.api.general.BloodConversionRegistry;
import de.teamlapen.vampirism.api.items.IItemWithTier;
import de.teamlapen.vampirism.config.BloodGrinderValueLoader;
import de.teamlapen.vampirism.inventory.HunterWeaponCraftingManager;
import de.teamlapen.vampirism.items.*;
import de.teamlapen.vampirism.player.hunter.skills.HunterSkills;
import de.teamlapen.vampirism.util.REFERENCE;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

import static de.teamlapen.lib.lib.util.UtilLib.getNull;

/**
 * Handles all item registrations and reference.
 */
@GameRegistry.ObjectHolder(REFERENCE.MODID)
public class ModItems {

    public static final ItemVampireFang vampire_fang = getNull();
    public static final ItemHumanHeart human_heart = getNull();
    public static final ItemHumanHeartWeak weak_human_heart = getNull();
    public static final ItemBloodBottle blood_bottle = getNull();
    public static final ItemTent item_tent = getNull();
    public static final ItemCoffin item_coffin = getNull();
    public static final ItemPureBlood pure_blood = getNull();
    
    public static final ItemGarlic item_garlic = getNull();
    public static final ItemInjection injection = getNull();
    public static final ItemMedChair item_med_chair = getNull();
    public static final ItemSimpleCrossbow basic_crossbow = getNull();
    public static final ItemDoubleCrossbow basic_double_crossbow = getNull();
    public static final ItemSimpleCrossbow enhanced_crossbow = getNull();
    public static final ItemDoubleCrossbow enhanced_double_crossbow = getNull();
    public static final ItemCrossbowArrow crossbow_arrow = getNull();
    public static final ItemStake stake = getNull();
    public static final ItemVampireBloodBottle vampire_blood_bottle = getNull();
    public static final ItemBloodPotion blood_potion = getNull();
    public static final ItemTechCrossbow basic_tech_crossbow = getNull();
    public static final ItemTechCrossbow enhanced_tech_crossbow = getNull();
    public static final VampirismItem tech_crossbow_ammo_package = getNull();
    // public static final ItemVampireBook vampire_book = getNull();  // 已移除
    // public static final ItemHolyWaterBottle holy_water_bottle = getNull();  // 已移除
    // public static final ItemHolyWaterSplashBottle holy_water_splash_bottle = getNull();  // 已移除
    public static final VampirismItem holy_salt = getNull();
    public static final VampirismItem holy_salt_water = getNull();
    // public static final VampirismItem blood_infused_iron_ingot = getNull();  // 已移除
    // public static final VampirismItem blood_infused_enhanced_iron_ingot = getNull();  // 已移除
    public static final VampirismItem soul_orb_vampire = getNull();
    public static final ItemVampireCloak vampire_cloak = getNull();
    public static final ItemGarlicBread garlic_bread = getNull();

    static void registerCraftingRecipes() {

        // TODO CRAFTING
        HunterWeaponCraftingManager weaponCraftingManager = HunterWeaponCraftingManager.getInstance();
        weaponCraftingManager.addRecipe(new ItemStack(basic_crossbow), 1, (ISkill) null, 1, "YXXY", " ZZ ", " ZZ ", 'X',
                Items.IRON_INGOT, 'Y', Items.STRING, 'Z', Blocks.PLANKS);
        weaponCraftingManager.addRecipe(new ItemStack(basic_double_crossbow), 1, HunterSkills.double_crossbow, 1,
                "YXXY", "YXXY", " ZZ ", " ZZ ", 'X', Items.IRON_INGOT, 'Y', Items.STRING, 'Z', Blocks.PLANKS);
        weaponCraftingManager.addRecipe(new ItemStack(enhanced_crossbow), 1, HunterSkills.enhanced_crossbow, 2, "YXXY",
                " XX ", " XX ", 'X', Items.IRON_INGOT, 'Y', Items.STRING);
        weaponCraftingManager.addRecipe(new ItemStack(enhanced_double_crossbow), 1,
                new ISkill[]{HunterSkills.double_crossbow, HunterSkills.enhanced_crossbow}, 3, "YXXY", "YXXY",
                " XX ", " XX ", 'X', Items.IRON_INGOT, 'Y', Items.STRING);
        weaponCraftingManager.addRecipe(
                ItemCrossbowArrow.setType(new ItemStack(crossbow_arrow, 3),
                        ItemCrossbowArrow.EnumArrowType.VAMPIRE_KILLER),
                1, (ISkill) null, 1, " X  ", "XYX ", " Z  ", " W  ", 'X', item_garlic, 'Y', Items.GOLD_INGOT, 'Z',
                Items.STICK, 'W', Items.FEATHER);
        weaponCraftingManager.addRecipe(new ItemStack(tech_crossbow_ammo_package), 1, (ISkill) null, 1, " XZ ", "YYYY",
                "YYYY", "YYYY", 'X', Items.IRON_INGOT, 'Y', crossbow_arrow, 'Z', Blocks.PLANKS);
        weaponCraftingManager.addRecipe(new ItemStack(basic_tech_crossbow), 1, HunterSkills.tech_weapons, 5, "XYYX",
                "YZZY", " YY ", " YY ", 'X', Items.STRING, 'Y', Items.IRON_INGOT, 'Z', Items.DIAMOND);
        weaponCraftingManager.addRecipe(new ItemStack(enhanced_tech_crossbow), 1, HunterSkills.tech_weapons, 5, "XYYX",
                "YZZY", "YZZY", " YY ", 'X', Items.STRING, 'Y', Items.IRON_INGOT, 'Z', Items.DIAMOND);

        // Brewing
        BrewingRecipeRegistry.addRecipe(
                PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER),
                new ItemStack(holy_salt), new ItemStack(holy_salt_water));
        // 移除了 holy_water_bottle 和 holy_water_splash_bottle 的酿造配方
    }

    public static ItemStack createStack(IItemWithTier item, IItemWithTier.TIER tier) {
        return item.setTier(new ItemStack((Item) item), tier);
    }

    static void registerItems(IForgeRegistry<Item> registry) {
        registry.register(new ItemVampireFang());
        registry.register(new ItemHumanHeart());
        registry.register(new ItemHumanHeartWeak());
        registry.register(new ItemBloodBottle());
        registry.register(new ItemTent());
        registry.register(new ItemCoffin());
        registry.register(new ItemPureBlood());
        
        registry.register(new ItemGarlic());
        registry.register(new ItemMedChair());
        registry.register(new ItemInjection());
        ItemSimpleCrossbow basic_crossbow = new ItemSimpleCrossbow("basic_crossbow", 1, 20, 300);
        basic_crossbow.setEnchantability(Item.ToolMaterial.WOOD);
        registry.register(basic_crossbow);
        ItemDoubleCrossbow basic_double_crossbow = new ItemDoubleCrossbow("basic_double_crossbow", 1, 20, 300);
        basic_double_crossbow.setEnchantability(Item.ToolMaterial.WOOD);
        registry.register(basic_double_crossbow);
        ItemSimpleCrossbow enhanced_crossbow = new ItemSimpleCrossbow("enhanced_crossbow", 1.5F, 15, 350);
        enhanced_crossbow.setEnchantability(Item.ToolMaterial.IRON);
        registry.register(enhanced_crossbow);
        ItemDoubleCrossbow enhanced_double_crossbow = new ItemDoubleCrossbow("enhanced_double_crossbow", 1.5F, 15, 350);
        enhanced_double_crossbow.setEnchantability(Item.ToolMaterial.IRON);
        registry.register(enhanced_double_crossbow);
        registry.register(new ItemCrossbowArrow());
        registry.register(new ItemStake());
        registry.register(new ItemVampireBloodBottle());
        registry.register(new ItemBloodPotion());
        ItemTechCrossbow basic_tech_crossbow = new ItemTechCrossbow("basic_tech_crossbow", 1.6F, 6, 300);
        basic_tech_crossbow.setEnchantability(Item.ToolMaterial.DIAMOND);
        registry.register(basic_tech_crossbow);
        ItemTechCrossbow enhanced_tech_crossbow = new ItemTechCrossbow("enhanced_tech_crossbow", 1.7F, 4, 450);
        enhanced_tech_crossbow.setEnchantability(Item.ToolMaterial.DIAMOND);
        registry.register(enhanced_tech_crossbow);
        registry.register(new VampirismItem("tech_crossbow_ammo_package") {

            @SideOnly(Side.CLIENT)
            @Override
            public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
                tooltip.add(UtilLib.translateFormatted("item.vampirism." + regName + ".tooltip",
                        basic_tech_crossbow.getLocalizedName()));
            }

        });
        // registry.register(new ItemVampireBook());  // 已移除
        // registry.register(new ItemHolyWaterBottle(ItemHolyWaterBottle.regName));  // 已移除
        // registry.register(new ItemHolyWaterSplashBottle(ItemHolyWaterSplashBottle.regName));  // 已移除
        registry.register(new VampirismItem("holy_salt") {

            @Override
            public boolean hasEffect(ItemStack stack) {

                return true;
            }
        });
        registry.register(new VampirismItem("holy_salt_water") {

            @Override
            public boolean hasEffect(ItemStack stack) {

                return true;
            }
        }.setMaxStackSize(1));

        // registry.register(new VampirismItem("blood_infused_iron_ingot"));  // 已移除
        // registry.register(new VampirismItem("blood_infused_enhanced_iron_ingot"));  // 已移除
        registry.register(new VampirismItem("soul_orb_vampire"));
        registry.register(new ItemVampireCloak());
        registry.register(new ItemGarlicBread());
    }

    /**
     * Fix item mappings
     */
    static boolean fixMapping(RegistryEvent.MissingMappings.Mapping<Item> mapping) {

        // Removed battle Axe
        if ("battleaxe".equals(mapping.key.getPath())) {
            mapping.ignore();
            return true;
        }
        // Removed hunter hats (hunter_hat0_head, hunter_hat1_head)
        String old = mapping.key.getPath();
        if ("hunterhat0head".equals(old) || "hunterhat1head".equals(old)) {
            mapping.ignore();
            return true;
        }
        // Removed holy_water_bottle, holy_water_splash_bottle, vampire_book, blood_infused_iron_ingot and blood_infused_enhanced_iron_ingot (these items no longer exist)
        if ("holywaterbottle".equals(old) || "holywatersplashbottle".equals(old) || "vampirebook".equals(old) || "bloodinfusedironingot".equals(old) || "bloodinfusedenhancedironingot".equals(old)) {
            mapping.ignore();
            return true;
        }
        // Check for mappings changed for 1.11 CamelCase to lower underscore
        boolean r = checkMapping(mapping, old, basic_crossbow, basic_double_crossbow,
                basic_tech_crossbow, blood_bottle, blood_potion, crossbow_arrow, enhanced_crossbow,
                enhanced_double_crossbow);
        if (!r)
            r = checkMapping(mapping, old, enhanced_tech_crossbow, human_heart, weak_human_heart,
                   injection, item_coffin, item_garlic,
                   item_med_chair);

        if (!r)
            r = checkMapping(mapping, old, item_tent, pure_blood, tech_crossbow_ammo_package,
                    vampire_blood_bottle, vampire_fang);
        return r;
    }

    private static boolean checkMapping(RegistryEvent.MissingMappings.Mapping<Item> mapping, String name,
                                        Item... items) {

        for (Item i : items) {
            String oldRegisteredName;
            if (i instanceof VampirismHunterArmor) {
                oldRegisteredName = ((VampirismHunterArmor) i).getOldRegisteredName();
            } else {
                String newRegisteredName = i instanceof VampirismItem ? ((VampirismItem) i).getRegisteredName()
                        : (i instanceof VampirismItemBloodFood ? ((VampirismItemBloodFood) i).getRegisteredName()
                        : null);
                if (newRegisteredName == null) {
                    VampirismMod.log.w("ModItems",
                            "Unknown item class. Unable to determine new registered name during mapping fix",
                            i.getClass());
                    continue;
                }
                oldRegisteredName = newRegisteredName.replaceAll("_", "");
            }

            if (oldRegisteredName.equals(name)) {
                mapping.remap(i);
                return true;
            }
        }
        return false;
    }

    static void registerBloodConversionRates() {

        Map<ResourceLocation, Integer> valuesIn = BloodGrinderValueLoader.getBloodGrinderValues();
        for (ResourceLocation e : valuesIn.keySet()) {
            BloodConversionRegistry.registerItem(e, valuesIn.get(e) * VReference.FOOD_TO_FLUID_BLOOD);
        }
    }
}
