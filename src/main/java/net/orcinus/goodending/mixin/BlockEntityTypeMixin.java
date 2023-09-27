package net.orcinus.goodending.mixin;

import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityType.class)
public class BlockEntityTypeMixin {

    @Inject(at = @At("HEAD"), method = "isValid", cancellable = true)
    private void GE$isValid(BlockState state, CallbackInfoReturnable<Boolean> info) {
        if (BlockEntityType.SIGN.equals(this) && (state.getBlock() instanceof StandingSignBlock || state.getBlock() instanceof WallSignBlock)) {
            info.setReturnValue(true);
        }
        if (BlockEntityType.HANGING_SIGN.equals(this) && (state.getBlock() instanceof WallHangingSignBlock || state.getBlock() instanceof CeilingHangingSignBlock)) {
            info.setReturnValue(true);
        }
    }

}
