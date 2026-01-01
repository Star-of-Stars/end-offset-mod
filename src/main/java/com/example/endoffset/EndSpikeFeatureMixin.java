 package com.example.endoffset;
 
 import net.minecraft.util.math.BlockPos;
 import org.spongepowered.asm.mixin.Mixin;
 import org.spongepowered.asm.mixin.injection.At;
 import org.spongepowered.asm.mixin.injection.ModifyArg;

 import java.util.HashMap;
 import java.util.Map;

 @Mixin(targets = "net.minecraft.world.gen.feature.EndSpikeFeature")
 public class EndSpikeFeatureMixin {

     // 原版坐标到目标坐标的映射（只映射X和Z）
     private static final Map<BlockPos, BlockPos> SPIKE_MAP = new HashMap<>();

     static {
         // 初始化映射表（Y坐标固定为0）
         SPIKE_MAP.put(new BlockPos(42, 0, 0), new BlockPos(42, 0, 0)); // 不变
         SPIKE_MAP.put(new BlockPos(33, 0, -25), new BlockPos(33, 0, -25)); // 不变
         SPIKE_MAP.put(new BlockPos(12, 0, -40), new BlockPos(12, 0, -40)); // 不变
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
             target = "Lnet/minecraft/world/gen/feature/EndSpikeFeature$Spike;setCenterPos(Lnet/minecraft/util/math/BlockPos;)V",
             ordinal = 0
         ),
         index = 0
     )
     private BlockPos offsetSpikePosition(BlockPos originalPos) {
         // 创建一个只包含X和Z的键（Y坐标设为0）
       BlockPos key = new BlockPos(originalPos.getX(), 0, originalPos.getZ());

         // 检查是否在映射表中
         BlockPos targetPos = SPIKE_MAP.get(key);
         if (targetPos != null) {
             // 返回目标坐标，但保持原始Y坐标
             return new BlockPos(targetPos.getX(), originalPos.getY(), targetPos.getZ());
         }
         // 如果不在映射表中，返回原坐标
         return originalPos;
     }
 }
