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
    public static final ItemCoffin item_coffin = getNull();
    public static final ItemPureBlood pure_blood = getNull();
    public static final ItemGarlic item_garlic = getNull();
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
    public static final VampirismItem holy_salt = getNull();
    public static final VampirismItem holy_salt_water = getNull();
    public static final VampirismItem soul_orb_vampire = getNull();
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
    }

    public static ItemStack createStack(IItemWithTier item, IItemWithTier.TIER tier) {
        return item.setTier(new ItemStack((Item) item), tier);
    }

    static void registerItems(IForgeRegistry<Item> registry) {
        registry.register(new ItemVampireFang());
        registry.register(new ItemHumanHeart());
        registry.register(new ItemHumanHeartWeak());
        registry.register(new ItemBloodBottle());
        registry.register(new ItemCoffin());
        registry.register(new ItemPureBlood());
        registry.register(new ItemGarlic());
        
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

        registry.register(new VampirismItem("soul_orb_vampire"));
        registry.register(new ItemGarlicBread());
    }

    /**
     * Fix item mappings - simply ignore all missing items
     */
    static boolean fixMapping(RegistryEvent.MissingMappings.Mapping<Item> mapping) {
        mapping.ignore();
        return true;
    }

    static void registerBloodConversionRates() {

        Map<ResourceLocation, Integer> valuesIn = BloodGrinderValueLoader.getBloodGrinderValues();
        for (ResourceLocation e : valuesIn.keySet()) {
            BloodConversionRegistry.registerItem(e, valuesIn.get(e) * VReference.FOOD_TO_FLUID_BLOOD);
        }
    }
}
