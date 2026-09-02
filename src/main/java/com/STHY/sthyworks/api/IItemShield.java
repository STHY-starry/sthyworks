package com.STHY.sthyworks.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public interface IItemShield {

    void onOwnerHurt(ItemStack stack, EntityPlayer player, LivingHurtEvent event);
}
