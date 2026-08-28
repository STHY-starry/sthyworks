package com.STHY.sthyworks.mixins.late.thaumcraft.common.items.wands;

import net.minecraft.entity.player.EntityPlayer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.STHY.sthyworks.common.entity.withoutEgg.Seat;
import com.STHY.sthyworks.common.util.ObsessionManager;

import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.items.wands.WandManager;

@Mixin(value = WandManager.class, remap = false)
public class MixinWandManager_VisDiscount {

    @Inject(
        method = "getTotalVisDiscount(Lnet/minecraft/entity/player/EntityPlayer;Lthaumcraft/api/aspects/Aspect;)F",
        at = @At("RETURN"),
        cancellable = true)
    private static void injectSeatDiscount(EntityPlayer player, Aspect aspect, CallbackInfoReturnable<Float> cir) {
        if (player != null) {

            float currentResult = cir.getReturnValue();

            if (player.ridingEntity instanceof Seat) {
                Seat seat = (Seat) player.ridingEntity;
                if (seat.getDataWatcher()
                    .getWatchableObjectByte(16) == (byte) 1) {
                    currentResult += 0.08F;
                }
            }

            float obsessionDiscount = ObsessionManager.calculateVisDiscount(player);
            currentResult += obsessionDiscount;

            cir.setReturnValue(currentResult);
        }
    }
}
