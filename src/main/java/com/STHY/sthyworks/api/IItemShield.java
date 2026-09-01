package com.STHY.sthyworks.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public interface IItemShield {

    void onOwnerHurt(ItemStack stack, EntityPlayer player, DamageSource source, float amount);
}
