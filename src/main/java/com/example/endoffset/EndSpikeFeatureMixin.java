package com.example.endoffset;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.EndSpikeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.HashMap;
import java.util.Map;

@Mixin(EndSpikeFeature.class)
public class EndSpikeFeatureMixin {

    private static final Map<BlockPos, BlockPos> SPIKE_MAP = new HashMap<>();
 
    static {
        SPIKE_MAP.put(new BlockPos(42, 0, 0), new BlockPos(42, 0, 0)); 
        SPIKE_MAP.put(new BlockPos(33, 0, -25), new BlockPos(33, 0, -25)); 
        SPIKE_MAP.put(new BlockPos(12, 0, -40), new BlockPos(12, 0, -40)); 
        SPIKE_MAP.put(new BlockPos(33, 0, 24), new BlockPos(33, 0, 25));
        SPIKE_MAP.put(new BlockPos(12, 0, 39), new BlockPos(12, 0, 40));
        SPIKE_MAP.put(new BlockPos(-13, 0, -40), new BlockPos(-12, 0, -40));
        SPIKE_MAP.put(new BlockPos(-13, 0, -39), new BlockPos(-12, 0, -40));
        SPIKE_MAP.put(new BlockPos(-34, 0, -25), new BlockPos(-33, 0, -25));
        SPIKE_MAP.put(new BlockPos(-34, 0, 24), new BlockPos(-33, 0, 25));
        SPIKE_MAP.put(new BlockPos(-42, 0, -1), new BlockPos(-42, 0, 0));
    }
 
    @ModifyArg(
        method = "generate",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/gen/feature/EndSpikeFeature$Spike;setCenterPos(Lnet/minecraft/util/math/BlockPos;)V"
            ),
        index = 0
    )
    private BlockPos offsetSpikePosition(BlockPos originalPos) {
     BlockPos key = new BlockPos(originalPos.getX(), 0, originalPos.getZ());
     BlockPos targetPos = SPIKE_MAP.get(key);
     if (targetPos != null) {
      return new BlockPos(targetPos.getX(), originalPos.getY(), targetPos.getZ());
     }
     return originalPos;
    }
}
