// ModEnchantments.java - 移除 VampireSlayer 相关内容
package de.teamlapen.vampirism.core;

import de.teamlapen.vampirism.items.enchantment.EnchantmentArrowFrugality;
import de.teamlapen.vampirism.items.enchantment.EnchantmentCrossbowInfinite;
// 移除 EnchantmentVampireSlayer 导入
import de.teamlapen.vampirism.util.REFERENCE;
import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import static de.teamlapen.lib.lib.util.UtilLib.getNull;


@GameRegistry.ObjectHolder(REFERENCE.MODID)
public class ModEnchantments {

    public static final EnchantmentCrossbowInfinite crossbowinfinite = getNull();
    public static final EnchantmentArrowFrugality crossbowfrugality = getNull();
    // 移除 vampireslayer 声明


    static void registerEnchantments(IForgeRegistry<Enchantment> registry) {
        registry.register(new EnchantmentCrossbowInfinite(Enchantment.Rarity.VERY_RARE));
        registry.register(new EnchantmentArrowFrugality(Enchantment.Rarity.VERY_RARE));
        // 移除 VampireSlayer 注册
    }

}
