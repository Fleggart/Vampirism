package de.teamlapen.vampirism.core;

import de.teamlapen.vampirism.recipes.RecipeVampireSword;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Handles all recipe registrations and reference.
 */
public class ModRecipes {

    /**
     * _X_ <br>
     * XYX
     * <p>
     * X = type(blood_iron)
     * Y = HeartSeeker
     */
    private static final RecipeVampireSword recipeHeartSeeker = new RecipeVampireSword("heartseeker", ModItems.heart_seeker) {
    };

    /**
     * XXX <br>
     * XYX
     * <p>
     * X = type(blood_iron)
     * Y = HeartStriker
     */
    // 已删除 heart_striker 配方

    static void registerRecipes(IForgeRegistry<IRecipe> registry) {

        registry.register(recipeHeartSeeker);
        // registry.register(recipeHeartStriker); // 已删除
    }
}
