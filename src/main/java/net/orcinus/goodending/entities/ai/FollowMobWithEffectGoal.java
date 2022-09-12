package net.orcinus.goodending.entities.ai;

import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.util.math.BlockPos;
import net.orcinus.goodending.entities.MarshEntity;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class FollowMobWithEffectGoal extends Goal {
    protected final MarshEntity marsh;
    @Nullable
    protected LivingEntity entity;

    public FollowMobWithEffectGoal(MarshEntity marsh) {
        this.marsh = marsh;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    @Override
    public boolean canStart() {
        this.entity = this.findMate();
        return this.entity != null;
    }

    @Override
    public boolean shouldContinue() {
        return this.entity.isAlive() && !this.entity.getActiveStatusEffects().isEmpty();
    }

    @Override
    public void stop() {
        this.entity = null;
    }

    @Override
    public void tick() {
        if (this.marsh.squaredDistanceTo(this.entity) >= 144.0) {
            this.tryTeleport();
        } else {
            this.marsh.getLookControl().lookAt(this.entity, 10.0f, this.marsh.getMaxLookPitchChange());
            this.marsh.getNavigation().startMovingTo(this.entity, 1.2D);
        }
    }

    private void tryTeleport() {
        BlockPos blockPos = this.entity.getBlockPos();
        for (int i = 0; i < 10; ++i) {
            int j = this.getRandomInt(-3, 3);
            int k = this.getRandomInt(-1, 1);
            int l = this.getRandomInt(-3, 3);
            boolean bl = this.tryTeleportTo(blockPos.getX() + j, blockPos.getY() + k, blockPos.getZ() + l);
            if (!bl) continue;
            return;
        }
    }

    private boolean tryTeleportTo(int x, int y, int z) {
        if (Math.abs((double)x - this.entity.getX()) < 2.0 && Math.abs((double)z - this.entity.getZ()) < 2.0) {
            return false;
        }
        if (!this.canTeleportTo(new BlockPos(x, y, z))) {
            return false;
        }
        this.marsh.refreshPositionAndAngles((double)x + 0.5, y, (double)z + 0.5, this.marsh.getYaw(), this.marsh.getPitch());
        this.marsh.getNavigation().stop();
        return true;
    }

    private boolean canTeleportTo(BlockPos pos) {
        PathNodeType pathNodeType = LandPathNodeMaker.getLandNodeType(this.marsh.world, pos.mutableCopy());
        if (pathNodeType != PathNodeType.WALKABLE) {
            return false;
        }
        BlockState blockState = this.marsh.world.getBlockState(pos.down());
        if (blockState.getBlock() instanceof LeavesBlock) {
            return false;
        }
        BlockPos blockPos = pos.subtract(this.marsh.getBlockPos());
        return this.marsh.world.isSpaceEmpty(this.marsh, this.marsh.getBoundingBox().offset(blockPos));
    }

    private int getRandomInt(int min, int max) {
        return this.marsh.getRandom().nextInt(max - min + 1) + min;
    }

    @Nullable
    private LivingEntity findMate() {
        double d = Double.MAX_VALUE;
        LivingEntity target = null;
        for (LivingEntity targets : this.marsh.world.getEntitiesByClass(LivingEntity.class, this.marsh.getBoundingBox().expand(8.0D), livingEntity -> livingEntity.getUuid() != this.marsh.getUuid() && !livingEntity.getActiveStatusEffects().isEmpty())) {
            if (!(this.marsh.squaredDistanceTo(targets) < d)) continue;
            target = targets;
            d = this.marsh.squaredDistanceTo(targets);
        }
        return target;
    }

}
