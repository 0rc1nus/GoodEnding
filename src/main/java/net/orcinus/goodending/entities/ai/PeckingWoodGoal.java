package net.orcinus.goodending.entities.ai;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.BlockStateRaycastContext;
import net.orcinus.goodending.entities.WoodpeckerEntity;
import net.orcinus.goodending.init.GoodEndingSoundEvents;

public class PeckingWoodGoal extends Goal {
    private final WoodpeckerEntity woodpeckerEntity;
    private int peckingTicks;

    public PeckingWoodGoal(WoodpeckerEntity woodpeckerEntity) {
        this.woodpeckerEntity = woodpeckerEntity;
    }

    @Override
    public boolean canStart() {
        return this.woodpeckerEntity.getPeckingWoodCooldownTicks() == 0 && this.woodpeckerEntity.getWoodPos() != null;
    }

    @Override
    public boolean shouldContinue() {
        return this.peckingTicks > 0 && this.woodpeckerEntity.getWoodPos() != null && super.shouldContinue();
    }

    @Override
    public void start() {
        super.start();
        if (this.woodpeckerEntity.getWoodPos() != null) {
            this.woodpeckerEntity.getLookControl().lookAt(Vec3d.ofBottomCenter(this.woodpeckerEntity.getWoodPos()));
        }
        this.peckingTicks = 600;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.peckingTicks > 0) {
            this.peckingTicks--;
            BlockPos woodPos = this.woodpeckerEntity.getWoodPos();
            if (woodPos != null) {
                this.woodpeckerEntity.setPose(EntityPose.DIGGING);
                double lookX = this.woodpeckerEntity.getLookControl().getLookX();
                double lookY = this.woodpeckerEntity.getLookControl().getLookY();
                double lookZ = this.woodpeckerEntity.getLookControl().getLookZ();
                this.woodpeckerEntity.getLookControl().lookAt(Vec3d.ofBottomCenter(new Vec3i(lookX, lookY, lookZ)));
                if (this.peckingTicks % 5 == 0) {
                    this.woodpeckerEntity.playSound(GoodEndingSoundEvents.ENTITY_WOODPECKER_DRUM, 1.0F, 1.0F);
                }
                if (this.woodpeckerEntity.world.getBlockState(new BlockPos(lookX, lookY, lookZ)).isIn(BlockTags.LOGS) && this.peckingTicks < 590) {
                    this.addDigParticles(woodPos);
                }
            }
        }
    }

    private void addDigParticles(BlockPos woodPos) {
        BlockState blockState = this.woodpeckerEntity.world.getBlockState(woodPos);
        for (int i = 0; i < 30; ++i) {
            double d = woodPos.getX() + 0.5D;
            double e = woodPos.getY() + 0.5D;
            double f = woodPos.getZ() + 0.5D;
            ((ServerWorld)this.woodpeckerEntity.world).spawnParticles(new BlockStateParticleEffect(ParticleTypes.BLOCK, blockState), d, e, f, 1, 0.5, 0.25, 0.5, 0.0);
        }
    }

    @Override
    public void stop() {
        this.woodpeckerEntity.setPose(EntityPose.FALL_FLYING);
        this.woodpeckerEntity.setWoodPos(null);
        this.woodpeckerEntity.setPeckingWoodCooldownTicks(600);
    }
}
