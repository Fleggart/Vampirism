package de.teamlapen.vampirism.network;

import de.teamlapen.vampirism.client.gui.*;
import de.teamlapen.vampirism.inventory.*;
import de.teamlapen.vampirism.items.ItemVampireBook;
import de.teamlapen.vampirism.tileentity.TileAltarInfusion;
import de.teamlapen.vampirism.tileentity.TileGrinder;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Handle GUIs on server and client side
 */
public class ModGuiHandler implements IGuiHandler {
    public final static int ID_ACTION = 0;
    public final static int ID_SKILL = 1;
    public final static int ID_ALTAR_INFUSION = 2;
    // ID_HUNTER_TABLE = 3 已删除
    public final static int ID_REVERT_BACK = 5;
    public final static int ID_WEAPON_TABLE = 6;
    public final static int ID_HUNTER_BASIC = 8;
    public final static int ID_VAMPIRE_BOOK = 9;
    public final static int ID_BLOOD_GRINDER = 12;

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {

        switch (id) {
            case ID_ACTION:
                return new GuiSelectAction();
            case ID_SKILL:
                return new GuiSkills();
            case ID_ALTAR_INFUSION:
                TileAltarInfusion tile = (TileAltarInfusion) world.getTileEntity(new BlockPos(x, y, z));

                return new GuiAltarInfusion(player.inventory, tile);
            // ID_HUNTER_TABLE 的 GUI 处理已删除
            case ID_REVERT_BACK:
                return new GuiRevertBack();
            case ID_WEAPON_TABLE:
                return new GuiHunterWeaponTable(player.inventory, world, new BlockPos(x, y, z));
            case ID_HUNTER_BASIC:
                return new GuiHunterBasic(player);
            case ID_VAMPIRE_BOOK:
                ItemStack itemStack = player.getHeldItem(EnumHand.MAIN_HAND);
                if (!itemStack.isEmpty() && itemStack.getItem() instanceof ItemVampireBook) {
                    return new GuiScreenBook(player, itemStack, false);
                }
                return null;
            case ID_BLOOD_GRINDER:
                TileGrinder tileGrinder = (TileGrinder) world.getTileEntity(new BlockPos(x, y, z));
                if (tileGrinder != null)
                    return new GuiBloodGrinder(tileGrinder.getNewInventoryContainer(player.inventory));
            default:
                return null;
        }
    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == ID_ALTAR_INFUSION) {
            TileAltarInfusion tile = (TileAltarInfusion) world.getTileEntity(new BlockPos(x, y, z));
            if (tile != null) return tile.getNewInventoryContainer(player.inventory);
        }
        // ID_HUNTER_TABLE 的服务器端 GUI 处理已删除
        if (id == ID_WEAPON_TABLE) {
            return new HunterWeaponTableContainer(player.inventory, world, new BlockPos(x, y, z));
        }
        if (id == ID_HUNTER_BASIC) {
            return new HunterBasicContainer(player.inventory);
        }
        if (id == ID_BLOOD_GRINDER) {
            TileGrinder tileGrinder = (TileGrinder) world.getTileEntity(new BlockPos(x, y, z));
            if (tileGrinder != null) return tileGrinder.getNewInventoryContainer(player.inventory);
        }
        return null;
    }
}
