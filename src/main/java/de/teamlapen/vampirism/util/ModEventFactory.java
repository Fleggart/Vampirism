package de.teamlapen.vampirism.util;

import de.teamlapen.vampirism.api.entity.factions.IFaction;
import de.teamlapen.vampirism.api.entity.factions.IFactionPlayerHandler;
import de.teamlapen.vampirism.api.entity.factions.IPlayableFaction;
import de.teamlapen.vampirism.api.event.FactionEvent;
// 删除村庄事件导入
// import de.teamlapen.vampirism.api.event.VampirismVillageEvent;
// import de.teamlapen.vampirism.api.world.IVampirismVillage;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event.Result;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ModEventFactory {

    // ========== 村庄事件 - 已删除 ==========
    // public static boolean fireVillagerCaptureEvent(...) { ... }
    // public static ResourceLocation fireSpawnCaptureEntityEvent(...) { ... }
    // public static VampirismVillageEvent.SpawnNewVillager fireSpawnNewVillagerEvent(...) { ... }
    // public static void fireReplaceVillageBlockEvent(...) { ... }
    // public static boolean fireInitiateCaptureEvent(...) { ... }
    // public static VampirismVillageEvent.SpawnFactionVillager fireSpawnFactionVillagerEvent(...) { ... }
    // public static void fireUpdateBoundingBoxEvent(...) { ... }

    // ========== 保留阵营事件 ==========
    public static Result fireCanJoinFactionEvent(@Nonnull IFactionPlayerHandler playerHandler, @Nullable IPlayableFaction<?> currentFaction, IPlayableFaction<?> newFaction){
        FactionEvent.CanJoinFaction event = new FactionEvent.CanJoinFaction(playerHandler, currentFaction, newFaction);
        MinecraftForge.EVENT_BUS.post(event);
        return event.getResult();
    }

    public static boolean fireChangeLevelOrFactionEvent(@Nonnull IFactionPlayerHandler player, @Nullable IPlayableFaction currentFaction, int currentLevel, @Nullable IPlayableFaction newFaction, int newLevel){
        FactionEvent.ChangeLevelOrFaction event = new FactionEvent.ChangeLevelOrFaction(player, currentFaction, currentLevel, newFaction, newLevel);
        return MinecraftForge.EVENT_BUS.post(event);
    }
}
