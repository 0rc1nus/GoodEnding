package net.orcinus.goodending.entities.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.ClipBlockStateContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.orcinus.goodending.entities.WoodpeckerEntity;
import net.orcinus.goodending.init.GoodEndingSoundEvents;

import java.util.List;

public class MoveToWoodGoal extends Goal {
    private final WoodpeckerEntity woodpecker;
    private final RandomSource random;
    private final int initialPeckingTicks;
    private final int initialPeckingCooldownTicks;
    private boolean cancel = false;
    private int peckingTicks;
    private int peckingCooldownTicks;
    private int drummingTicks;

    public MoveToWoodGoal(WoodpeckerEntity woodpecker, RandomSource random) {
        this.woodpecker = woodpecker;
        this.random = random;
        this.initialPeckingTicks = 20 * 30 + random.nextInt(20 * 120);
        this.initialPeckingCooldownTicks = 20 * 2 + random.nextInt(20 * 5);
    }

    @Override
    public boolean canUse() {
        BlockPos woodPos = this.woodpecker.getWoodPos();
        if (woodPos != null) {
            List<WoodpeckerEntity> woodpeckerEntities = this.woodpecker.level().getEntitiesOfClass(WoodpeckerEntity.class, new AABB(woodPos.relative(this.woodpecker.getAttachedFace())));
            if (woodpeckerEntities.size() > 1) {
                return false;
            }
        }
        if (this.woodpecker.getLastHurtByMob() != null) {
            return false;
        }
        return this.woodpecker.getWoodPos() != null && this.woodpecker.level().getBlockState(this.woodpecker.getWoodPos()).is(BlockTags.LOGS) && this.woodpecker.getPeckingWoodCooldown() == 0;
    }

    @Override
    public void start() {
        super.start();
        if (woodpecker.hasWood()) {
            this.peckingTicks = this.initialPeckingTicks;
            this.peckingCooldownTicks = this.initialPeckingCooldownTicks;
        }
    }

    @Override
    public boolean canContinueToUse() {
        BlockPos woodPos = this.woodpecker.getWoodPos();
        if (woodPos != null) {
            List<WoodpeckerEntity> woodpeckerEntities = this.woodpecker.level().getEntitiesOfClass(WoodpeckerEntity.class, new AABB(woodPos.relative(this.woodpecker.getAttachedFace())));
            if (woodpeckerEntities.size() > 1 && this.peckingTicks == 100) {
                this.cancel = true;
                this.woodpecker.setPeckingWoodCooldown(200);
                return false;
            }
        }
        if (this.woodpecker.getLastHurtByMob() != null) {
            return false;
        }
        if (this.cancel) {
            return false;
        }
        if (this.peckingTicks == 0) {
            return false;
        }
        return this.woodpecker.getWoodPos() != null && this.woodpecker.level().getBlockState(this.woodpecker.getWoodPos()).is(BlockTags.LOGS);
    }

    @Override
    public void stop() {
        super.stop();
        this.woodpecker.setWoodPos(null);
        this.woodpecker.setPeckingWoodCooldown(this.cancel ? 200 : 400);
        this.woodpecker.setPose(Pose.FALL_FLYING);
        this.peckingTicks = 20 * 30 + random.nextInt(20 * 120);
    }

    @Override
    public void tick() {

        BlockPos woodPos = this.woodpecker.getWoodPos();
        if (woodPos != null) {
            Direction attachedFace = this.woodpecker.getAttachedFace();
            boolean flag = attachedFace == Direction.UP || attachedFace == Direction.DOWN;
            if (!flag) {
                Vec3 center = Vec3.atBottomCenterOf(woodPos.relative(attachedFace));
                this.woodpecker.getLookControl().setLookAt(Vec3.atCenterOf(woodPos));
                if (this.woodpecker.blockPosition().distToCenterSqr(center) <= 3D) {
                    center = Vec3.atBottomCenterOf(woodPos);
                }
                this.woodpecker.getNavigation().moveTo(center.x(), center.y(), center.z(), 1.2F);
                double squaredDistance = Mth.sqrt((float) this.woodpecker.blockPosition().distToCenterSqr(Vec3.atBottomCenterOf(woodPos)));
                if (squaredDistance <= 1.2D) {
                    BlockHitResult blockHitResult = this.woodpecker.level().isBlockInLine(new ClipBlockStateContext(this.woodpecker.position(), Vec3.atCenterOf(woodPos), state -> state.is(BlockTags.LOGS)));
                    this.woodpecker.setDeltaMovement(Vec3.ZERO);
                    this.woodpecker.getLookControl().setLookAt(Vec3.atCenterOf(woodPos));
                    if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                        List<WoodpeckerEntity> woodpeckerEntities = this.woodpecker.level().getEntitiesOfClass(WoodpeckerEntity.class, new AABB(woodPos.relative(this.woodpecker.getAttachedFace())));
                        if (woodpeckerEntities.size() > 1 && this.peckingTicks > this.initialPeckingTicks - 10) this.cancel = true;
                        BlockPos pos = BlockPos.containing(Vec3.atBottomCenterOf(woodPos));
                        this.woodpecker.getLookControl().setLookAt(Vec3.atCenterOf(pos));
                        double xPosition = pos.getX() + (attachedFace.getAxis() == Direction.Axis.Z ? 0.5D : (attachedFace == Direction.WEST ? -0.2D : 1.2D));
                        double yPosition = pos.getY() + 0.25D;
                        double zPosition = pos.getZ() + (attachedFace.getAxis() == Direction.Axis.X ? 0.5D : (attachedFace == Direction.NORTH ? -0.2D : 1.2D));
                        float yaw = (float) (Mth.atan2(pos.getZ() + 0.5D - this.woodpecker.getZ(), pos.getX() + 0.5D - this.woodpecker.getX()) * (180.0F / Math.PI) - 90.0F);
                        this.woodpecker.moveTo(xPosition, yPosition, zPosition, this.woodpecker.getYRot(), yaw);
                        this.woodpecker.setYBodyRot(yaw);
                        this.woodpecker.setYHeadRot(yaw);
                        this.woodpecker.getNavigation().stop();
                        if (this.peckingTicks > 0) {
                            this.peckingTicks--;
                            this.peckingCooldownTicks--;
                            this.drummingTicks--;
                            this.woodpecker.setPose(Pose.STANDING);
                        }

                        if (this.drummingTicks < 0 || this.drummingTicks > 50) this.woodpecker.setPose(Pose.STANDING);
                        if (this.drummingTicks > 0 && this.drummingTicks < 50) this.woodpecker.setPose(Pose.DIGGING);

                        if (peckingCooldownTicks == 0) {
                            this.drummingTicks = 50;

                            this.woodpecker.playSound(GoodEndingSoundEvents.ENTITY_WOODPECKER_DRUM, 1.0F, 1.0F);
                            this.peckingCooldownTicks = 20 * 10 + random.nextInt(20 * 15);
                        }

                        if (this.drummingTicks > 25 && this.drummingTicks < 40) {
                            this.peckWood(woodPos);
                        }
                    }
                } else {
                    if (this.woodpecker.getNavigation().isDone()) {
                        Path path = this.woodpecker.getNavigation().createPath(this.woodpecker.getWoodPos().relative(this.woodpecker.getAttachedFace().getOpposite()).below(), 3);
                        this.woodpecker.getNavigation().moveTo(path, 1.2F);
                    }
                }
            }
        }
    }

    private void peckWood(BlockPos woodPos) {
        BlockState blockState = this.woodpecker.level().getBlockState(woodPos);
        for (int i = 0; i < 10; ++i) {
            double d = woodPos.getX() + 0.5D;
            double e = woodPos.getY() + 0.5D;
            double f = woodPos.getZ() + 0.5D;
            ((ServerLevel) this.woodpecker.level()).sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, blockState), d, e, f, 1, 0.5, 0.25, 0.5, 0.0);
        }
    }

}