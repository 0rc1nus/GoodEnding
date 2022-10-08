package net.orcinus.goodending.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.orcinus.goodending.init.GoodEndingBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WaterlilyBlock.class)
public abstract class LilyPadBlockMixin extends BushBlock {

    public LilyPadBlockMixin(Properties settings) {
        super(settings);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState p_60572_, BlockGetter p_60573_, BlockPos p_60574_, CollisionContext context) {
        if (context instanceof EntityCollisionContext entityShapeContext && entityShapeContext.getEntity() != null) {
            if (entityShapeContext.getEntity() instanceof Boat) {
                return Shapes.empty();
            }
        }
        return super.getCollisionShape(p_60572_, p_60573_, p_60574_, context);
    }

    @Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;destroyBlock(Lnet/minecraft/core/BlockPos;ZLnet/minecraft/world/entity/Entity;)Z"), method = "entityInside", cancellable = true)
    private void GE$onEntityCollision(BlockState p_58164_, Level p_58165_, BlockPos p_58166_, Entity p_58167_, CallbackInfo ci) {
        ci.cancel();
    }


    @Inject(at = @At(value = "HEAD"), method = "mayPlaceOn", cancellable = true)
    private void GE$canPlantOnTop(BlockState floor, BlockGetter world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos).is(GoodEndingBlocks.LARGE_LILY_PAD.get())) cir.setReturnValue(false);
    }

}
