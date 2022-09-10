package net.orcinus.goodending.entities.ai;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockStateRaycastContext;
import net.orcinus.goodending.entities.WoodpeckerEntity;
import net.orcinus.goodending.init.GoodEndingSoundEvents;

import java.util.List;

public class MoveToWoodGoal extends Goal {
    private final WoodpeckerEntity woodpecker;
    private final Random random;
    private final int initialPeckingTicks;
    private final int initialPeckingCooldownTicks;
    private boolean cancel = false;
    private int peckingTicks;
    private int peckingCooldownTicks;
    private int drummingTicks;

    public MoveToWoodGoal(WoodpeckerEntity woodpecker, Random random) {
        this.woodpecker = woodpecker;
        this.random = random;
        this.initialPeckingTicks = 20 * 30 + random.nextInt(20 * 120);
        this.initialPeckingCooldownTicks = 20 * 2 + random.nextInt(20 * 5);
    }

    @Override
    public boolean canStart() {
        BlockPos woodPos = this.woodpecker.getWoodPos();
        if (woodPos != null) {
            List<WoodpeckerEntity> woodpeckerEntities = this.woodpecker.world.getNonSpectatingEntities(WoodpeckerEntity.class, new Box(woodPos.offset(this.woodpecker.getAttachedFace())));
            if (woodpeckerEntities.size() > 1) {
                return false;
            }
        }
        if (this.woodpecker.getAttacker() != null) {
            return false;
        }
        return this.woodpecker.getWoodPos() != null && this.woodpecker.world.getBlockState(this.woodpecker.getWoodPos()).isIn(BlockTags.LOGS) && this.woodpecker.getPeckingWoodCooldown() == 0;
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
    public boolean shouldContinue() {
        BlockPos woodPos = this.woodpecker.getWoodPos();
        if (woodPos != null) {
            List<WoodpeckerEntity> woodpeckerEntities = this.woodpecker.world.getNonSpectatingEntities(WoodpeckerEntity.class, new Box(woodPos.offset(this.woodpecker.getAttachedFace())));
            if (woodpeckerEntities.size() > 1 && this.peckingTicks == 100) {
                this.cancel = true;
                this.woodpecker.setPeckingWoodCooldown(200);
                return false;
            }
        }
        if (this.woodpecker.getAttacker() != null) {
            return false;
        }
        if (this.cancel) {
            return false;
        }
        if (this.peckingTicks == 0) {
            return false;
        }
        return this.woodpecker.getWoodPos() != null && this.woodpecker.world.getBlockState(this.woodpecker.getWoodPos()).isIn(BlockTags.LOGS);
    }

    @Override
    public void stop() {
        super.stop();
        this.woodpecker.setWoodPos(null);
        this.woodpecker.setPeckingWoodCooldown(this.cancel ? 200 : 400);
        this.woodpecker.setPose(EntityPose.FALL_FLYING);
        this.peckingTicks = 20 * 30 + random.nextInt(20 * 120);
    }

    @Override
    public void tick() {

        BlockPos woodPos = this.woodpecker.getWoodPos();
        if (woodPos != null) {
            Direction attachedFace = this.woodpecker.getAttachedFace();
            boolean flag = attachedFace == Direction.UP || attachedFace == Direction.DOWN;
            if (!flag) {
                Vec3d center = Vec3d.ofBottomCenter(woodPos.offset(attachedFace));
                this.woodpecker.getLookControl().lookAt(Vec3d.ofCenter(woodPos));
                if (this.woodpecker.getBlockPos().getSquaredDistance(center) <= 3D) {
                    center = Vec3d.ofBottomCenter(woodPos);
                }
                this.woodpecker.getNavigation().startMovingTo(center.getX(), center.getY(), center.getZ(), 1.2F);
                double squaredDistance = MathHelper.sqrt((float) this.woodpecker.getBlockPos().getSquaredDistance(Vec3d.ofBottomCenter(woodPos)));
                if (squaredDistance <= 1.2D) {
                    BlockHitResult blockHitResult = this.woodpecker.world.raycast(new BlockStateRaycastContext(this.woodpecker.getPos(), Vec3d.ofCenter(woodPos), state -> state.isIn(BlockTags.LOGS)));
                    this.woodpecker.setVelocity(Vec3d.ZERO);
                    this.woodpecker.getLookControl().lookAt(Vec3d.ofCenter(woodPos));
                    if (blockHitResult.getType() == HitResult.Type.BLOCK) {
                        List<WoodpeckerEntity> woodpeckerEntities = this.woodpecker.world.getNonSpectatingEntities(WoodpeckerEntity.class, new Box(woodPos.offset(this.woodpecker.getAttachedFace())));
                        if (woodpeckerEntities.size() > 1 && this.peckingTicks > this.initialPeckingTicks - 10) this.cancel = true;
                        BlockPos pos = new BlockPos(Vec3d.ofBottomCenter(woodPos));
                        this.woodpecker.getLookControl().lookAt(Vec3d.ofCenter(pos));
                        double xPosition = pos.getX() + (attachedFace.getAxis() == Direction.Axis.Z ? 0.5D : (attachedFace == Direction.WEST ? -0.2D : 1.2D));
                        double yPosition = pos.getY() + 0.25D;
                        double zPosition = pos.getZ() + (attachedFace.getAxis() == Direction.Axis.X ? 0.5D : (attachedFace == Direction.NORTH ? -0.2D : 1.2D));
                        this.woodpecker.refreshPositionAndAngles(xPosition, yPosition, zPosition, this.woodpecker.getYaw(), this.woodpecker.getPitch());
                        this.woodpecker.getNavigation().stop();
                        if (this.peckingTicks > 0) {
                            this.peckingTicks--;
                            this.peckingCooldownTicks--;
                            this.drummingTicks--;
                            this.woodpecker.setPose(EntityPose.STANDING);
                        }

                        if (this.drummingTicks < 0 || this.drummingTicks > 50) this.woodpecker.setPose(EntityPose.STANDING);
                        if (this.drummingTicks > 0 && this.drummingTicks < 50) this.woodpecker.setPose(EntityPose.DIGGING);

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
                    if (this.woodpecker.getNavigation().isIdle()) {
                        Path path = this.woodpecker.getNavigation().findPathTo(this.woodpecker.getWoodPos().offset(this.woodpecker.getAttachedFace().getOpposite()).down(), 3);
                        this.woodpecker.getNavigation().startMovingAlong(path, 1.2F);
                    }
                }
            }
        }
    }

    private void peckWood(BlockPos woodPos) {
        BlockState blockState = this.woodpecker.world.getBlockState(woodPos);
        for (int i = 0; i < 10; ++i) {
            double d = woodPos.getX() + 0.5D;
            double e = woodPos.getY() + 0.5D;
            double f = woodPos.getZ() + 0.5D;
            ((ServerWorld) this.woodpecker.world).spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState), d, e, f, 1, 0.5, 0.25, 0.5, 0.0);
        }
    }

}
