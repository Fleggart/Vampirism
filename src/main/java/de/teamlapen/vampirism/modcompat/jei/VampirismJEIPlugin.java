package de.teamlapen.vampirism.modcompat.jei;

import de.teamlapen.vampirism.client.gui.GuiHunterWeaponTable;
import de.teamlapen.vampirism.core.ModBlocks;
import de.teamlapen.vampirism.core.ModItems;
import de.teamlapen.vampirism.inventory.HunterWeaponCraftingManager;
import de.teamlapen.vampirism.inventory.HunterWeaponTableContainer;
import mezz.jei.api.*;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.Nonnull;

/**
 * Plugin for Just Enough Items
 */
@JEIPlugin
public class VampirismJEIPlugin extends BlankModPlugin {
    public static final String HUNTER_WEAPON_RECIPE_UID = "vampirism.hunter_weapon";

    @Override
    public void register(@Nonnull IModRegistry registry) {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();

        jeiHelpers.getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(ModBlocks.block_blood_fluid));
        jeiHelpers.getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(ModItems.blood_potion, 1, OreDictionary.WILDCARD_VALUE));
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();
        IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

        //Weapon crafting table
        registry.addRecipeCategories(new HunterWeaponRecipeCategory(guiHelper));
        registry.addRecipeHandlers(new ShapedHunterWeaponRecipesHandler());
        registry.addRecipeHandlers(new ShapelessHunterWeaponRecipeHandler());
        registry.addRecipeClickArea(GuiHunterWeaponTable.class, 113, 46, 28, 23, HUNTER_WEAPON_RECIPE_UID);
        registry.addRecipeCategoryCraftingItem(new ItemStack(ModBlocks.weapon_table), HUNTER_WEAPON_RECIPE_UID);
        registry.addRecipes(HunterWeaponCraftingManager.getInstance().getRecipes());
        recipeTransferRegistry.addRecipeTransferHandler(HunterWeaponTableContainer.class, HUNTER_WEAPON_RECIPE_UID, 1, 16, 17, 36);
        
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistry registry) {
        registry.useNbtForSubtypes(ModItems.armor_of_swiftness_feet, ModItems.armor_of_swiftness_chest, ModItems.armor_of_swiftness_head, ModItems.armor_of_swiftness_legs);
        registry.useNbtForSubtypes(ModItems.holy_water_bottle);
        registry.useNbtForSubtypes(ModItems.crossbow_arrow);
    }
}
