package net.orcinus.goodending.entities.ai;

import net.minecraft.entity.EntityPose;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.text.Text;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockStateRaycastContext;
import net.orcinus.goodending.entities.WoodpeckerEntity;

import java.util.List;
import java.util.Optional;

public class FindWoodGoal extends Goal {
    private final WoodpeckerEntity woodpeckerEntity;
    private boolean running;

    public FindWoodGoal(WoodpeckerEntity woodpeckerEntity) {
        this.woodpeckerEntity = woodpeckerEntity;
    }

    @Override
    public boolean canStart() {
        if (this.woodpeckerEntity.getWoodPos() != null) {
            return false;
        }
        if (this.woodpeckerEntity.woodAttachingCooldownTicks > 0) {
            return false;
        }
        Optional<BlockPos> woodPos = this.getNearbyWood();
        if (woodPos.isPresent()) {
            this.woodpeckerEntity.setWoodPos(woodPos.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldContinue() {
        if (!this.woodpeckerEntity.hasWoodPos()) {
            return false;
        }
        if (!this.running) {
            return false;
        }
        return true;
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
        this.woodpeckerEntity.woodAttachingCooldownTicks = 20 * 90;
        this.woodpeckerEntity.setPose(EntityPose.STANDING);
    }

    @Override
    public void tick() {
        super.tick();

        Vec3d vec3d = Vec3d.ofBottomCenter(this.woodpeckerEntity.getWoodPos());

        BlockHitResult hitResult = this.woodpeckerEntity.world.raycast(new BlockStateRaycastContext(this.woodpeckerEntity.getSyncedPos(), vec3d, state -> state.isIn(BlockTags.LOGS)));

        this.woodpeckerEntity.getNavigation().startMovingAlong(this.woodpeckerEntity.getNavigation().findPathTo(vec3d.x, vec3d.y, vec3d.z, 0), 1.0F);
        this.woodpeckerEntity.getLookControl().lookAt(vec3d);

        Direction direction = hitResult.getSide();
        if (direction != Direction.UP && direction != Direction.DOWN) {
            BlockPos relativePosition = new BlockPos(vec3d).offset(direction);
            float dist = MathHelper.sqrt((float) this.woodpeckerEntity.squaredDistanceTo(new Vec3d(relativePosition.getX(), relativePosition.getY(), relativePosition.getZ())));
            List<WoodpeckerEntity> woodpeckerEntities = this.woodpeckerEntity.world.getNonSpectatingEntities(WoodpeckerEntity.class, new Box(relativePosition));
            if (woodpeckerEntities.size() == 1 && dist <= 1.3D) {
                double axisDirection = direction.getDirection() == Direction.AxisDirection.POSITIVE ? 0.175D : 0.825D;
                double xPosition = relativePosition.getX() + (direction.getAxis() == Direction.Axis.Z ? 0.5D : axisDirection);
                double zPosition = relativePosition.getZ() + (direction.getAxis() == Direction.Axis.X ? 0.5D : axisDirection);
                double yPosition = relativePosition.getY() + 0.25D;
                this.woodpeckerEntity.teleport(xPosition, yPosition, zPosition);
//                this.woodpeckerEntity.setWoodPos(new BlockPos(xPosition, yPosition, zPosition));
            }
        }
        for (PlayerEntity player : this.woodpeckerEntity.world.getPlayers()) {
            player.sendMessage(Text.translatable("The tick is still ticking"));
        }
    }

    private Optional<BlockPos> getNearbyWood() {
        BlockPos blockPos = this.woodpeckerEntity.getBlockPos();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        double searchDistance = 5.0D;
        for(int y = 0; (double)y <= searchDistance; y = y > 0 ? -y : 1 - y) {
            for(int j = 0; (double)j < searchDistance; ++j) {
                for(int x = 0; x <= j; x = x > 0 ? -x : 1 - x) {
                    for(int z = x < j && x > -j ? j : 0; z <= j; z = z > 0 ? -z : 1 - z) {
                        mutable.set(blockPos, x, y - 1, z);
                        if (blockPos.isWithinDistance(mutable, searchDistance) && this.woodpeckerEntity.world.getBlockState(mutable).isIn(BlockTags.LOGS)) {
                            return Optional.of(mutable);
                        }
                    }
                }
            }
        }

        return Optional.empty();
    }

}
