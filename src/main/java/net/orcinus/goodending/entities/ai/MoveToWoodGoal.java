package net.orcinus.goodending.entities.ai;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockStateRaycastContext;
import net.orcinus.goodending.entities.WoodpeckerEntity;

import java.util.List;

public class MoveToWoodGoal extends Goal {
    private final WoodpeckerEntity woodpecker;
    private boolean cancel = false;
    private int peckingTicks = 100;

    public MoveToWoodGoal(WoodpeckerEntity woodpecker) {
        this.woodpecker = woodpecker;
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
        this.peckingTicks = 100;
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
                        if (woodpeckerEntities.size() > 1 && this.peckingTicks > 90) {
                            this.cancel = true;
                        }
                        BlockPos pos = new BlockPos(Vec3d.ofBottomCenter(woodPos));
                        this.woodpecker.getLookControl().lookAt(Vec3d.ofCenter(pos));
                        double xPosition = pos.getX() + (attachedFace.getAxis() == Direction.Axis.Z ? 0.5D : (attachedFace == Direction.WEST ? -0.2D : 1.2D));
                        double yPosition = pos.getY() + 0.25D;
                        double zPosition = pos.getZ() + (attachedFace.getAxis() == Direction.Axis.X ? 0.5D : (attachedFace == Direction.NORTH ? -0.2D : 1.2D));
                        this.woodpecker.refreshPositionAndAngles(xPosition, yPosition, zPosition, this.woodpecker.getYaw(), this.woodpecker.getPitch());
                        this.woodpecker.getNavigation().stop();
                        if (this.peckingTicks > 0) {
                            this.peckingTicks--;
                        }
                        EntityPose pose = EntityPose.STANDING;
                        if (this.peckingTicks > 70 && this.peckingTicks < 95) {
                            pose = EntityPose.DIGGING;
                            if (this.peckingTicks > 75 && this.peckingTicks < 85) {
                                peckWood(woodPos);
                            }
                        }
                        if (this.peckingTicks > 40 && this.peckingTicks < 65) {
                            pose = EntityPose.DIGGING;
                            if (this.peckingTicks > 45 && this.peckingTicks < 50) {
                                peckWood(woodPos);
                            }
                        }
                        if (this.peckingTicks > 10 && this.peckingTicks < 35) {
                            pose = EntityPose.DIGGING;
                            if (this.peckingTicks > 15 && this.peckingTicks < 20) {
                                peckWood(woodPos);
                            }
                        }
                        this.woodpecker.setPose(pose);
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
