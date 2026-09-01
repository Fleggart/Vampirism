package de.teamlapen.vampirism.api.entity.vampire;

import de.teamlapen.vampirism.api.difficulty.IAdjustableLevel;
// 删除 IVillageCaptureEntity 导入
// import de.teamlapen.vampirism.api.entity.IVillageCaptureEntity;

/**
 * Interface for the basic vampire mob
 * Village capture functionality has been removed.
 */
public interface IBasicVampire extends IVampireMob, IAdjustableLevel { // 移除 , IVillageCaptureEntity
}
