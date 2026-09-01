package de.teamlapen.vampirism.api.entity.hunter;

import de.teamlapen.vampirism.api.difficulty.IAdjustableLevel;
// 删除 IVillageCaptureEntity 导入
// import de.teamlapen.vampirism.api.entity.IVillageCaptureEntity;
import net.minecraft.util.math.AxisAlignedBB;

/**
 * Interface for basic hunter entity.
 * Village-related methods (makeVillageHunter) are deprecated as village system has been removed.
 */
public interface IBasicHunter extends IHunterMob, IAdjustableLevel { // 移除 , IVillageCaptureEntity
    
    boolean isLookingForHome();

    void makeCampHunter(AxisAlignedBB box);

    void makeNormalHunter();

    /**
     * @deprecated Village system has been removed. This method does nothing.
     */
    @Deprecated
    default void makeVillageHunter(AxisAlignedBB box) {
        // No-op
    }

}
