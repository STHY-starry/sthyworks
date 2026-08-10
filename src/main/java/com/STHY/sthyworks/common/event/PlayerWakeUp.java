package com.STHY.sthyworks.common.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;

import com.STHY.sthyworks.common.entity.AdorableGugu;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PlayerWakeUp {

    private static Potion[] benefitPotions = new Potion[] { Potion.moveSpeed, Potion.digSpeed, Potion.damageBoost,
        Potion.jump, Potion.regeneration, Potion.resistance, Potion.fireResistance, Potion.waterBreathing,
        Potion.invisibility, Potion.nightVision, Potion.field_76434_w, Potion.field_76444_x, Potion.field_76443_y };

    @SubscribeEvent
    public void onPlayerSleep(PlayerWakeUpEvent event) {
        if (event.entityPlayer.worldObj.isRemote) {
            return;
        }
        double range = 1.0D;
        AxisAlignedBB searchBox = AxisAlignedBB.getBoundingBox(
            event.entityPlayer.posX - range,
            event.entityPlayer.posY - range,
            event.entityPlayer.posZ - range,
            event.entityPlayer.posX + range,
            event.entityPlayer.posY + range,
            event.entityPlayer.posZ + range);
        List<AdorableGugu> gugus = event.entityPlayer.worldObj.getEntitiesWithinAABB(AdorableGugu.class, searchBox);

        if (!gugus.isEmpty()) {
            applyRandomPotion(event.entityPlayer);
        }
    }

    private void applyRandomPotion(EntityPlayer player) {
        Random rand = player.getRNG();

        List<Potion> availablePotions = new ArrayList<>();
        for (Potion potion : benefitPotions) {
            if (potion != null) {
                availablePotions.add(potion);
            }
        }

        List<Potion> selectedPotions = new ArrayList<>();
        int count = rand.nextInt(3) + 1;
        for (int i = 0; i < count && availablePotions != null; i++) {
            int index = rand.nextInt(availablePotions.size());
            selectedPotions.add(availablePotions.get(index));
            availablePotions.remove(index);
        }

        for (Potion potion : selectedPotions) {
            int duration = 9600 + rand.nextInt(4800);
            int amplifier = rand.nextInt(2);
            player.addPotionEffect(new PotionEffect(potion.id, duration, amplifier));
        }
    }
}
