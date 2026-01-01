package com.example.endoffset;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.EndGatewayFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.HashMap;
import java.util.Map;

@Mixin(EndGatewayFeature.class)
public class EndGatewayFeatureMixin {

    private static final Map<BlockPos, BlockPos> GATEWAY_MAP = new HashMap<>();

    static {
        GATEWAY_MAP.put(new BlockPos(96, 75, 0), new BlockPos(96, 75, 0));
        GATEWAY_MAP.put(new BlockPos(91, 75, -30), new BlockPos(91, 75, 29));
        GATEWAY_MAP.put(new BlockPos(77, 75, -57), new BlockPos(77, 75, 56));
        GATEWAY_MAP.put(new BlockPos(56, 75, -78), new BlockPos(56, 75, 77));
        GATEWAY_MAP.put(new BlockPos(29, 75, -92), new BlockPos(29, 75, 91));
        GATEWAY_MAP.put(new BlockPos(0, 75, -96), new BlockPos(0, 75, 96));
        GATEWAY_MAP.put(new BlockPos(-30, 75, -92), new BlockPos(-29, 75, 91));
        GATEWAY_MAP.put(new BlockPos(-57, 75, -78), new BlockPos(-56, 75, 77));
        GATEWAY_MAP.put(new BlockPos(-78, 75, -57), new BlockPos(-77, 75, 56));
        GATEWAY_MAP.put(new BlockPos(-92, 75, -30), new BlockPos(-91, 75, 29));
        GATEWAY_MAP.put(new BlockPos(-96, 75, -1), new BlockPos(-96, 75, 0));
        GATEWAY_MAP.put(new BlockPos(-92, 75, 29), new BlockPos(-91, 75, -29));
        GATEWAY_MAP.put(new BlockPos(-78, 75, 56), new BlockPos(-77, 75, -56));
        GATEWAY_MAP.put(new BlockPos(-57, 75, 77), new BlockPos(-56, 75, -77));
        GATEWAY_MAP.put(new BlockPos(-30, 75, 91), new BlockPos(-29, 75, -91));
        GATEWAY_MAP.put(new BlockPos(-1, 75, 96), new BlockPos(0, 75, -96));
        GATEWAY_MAP.put(new BlockPos(29, 75, 91), new BlockPos(29, 75, -91));
        GATEWAY_MAP.put(new BlockPos(56, 75, 77), new BlockPos(56, 75, -77));
        GATEWAY_MAP.put(new BlockPos(77, 75, 56), new BlockPos(77, 75, -56));
        GATEWAY_MAP.put(new BlockPos(91, 75, 29), new BlockPos(91, 75, -29));
    }

    @ModifyArg(
        method = "generate",
        at = @At(
         value = "INVOKE",
            target =
     "Lnet/minecraft/world/gen/feature/EndGatewayFeature;configure(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/world/gen/feature/EndGatewayFeature;"
         ),
        index = 0
    )
   private BlockPos offsetGatewayPosition(BlockPos originalPos) {
        BlockPos targetPos = GATEWAY_MAP.get(originalPos);
        if (targetPos != null) {
            return targetPos;
        }
        return originalPos;
    }
}
