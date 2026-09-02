package com.STHY.sthyworks.common.item;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import org.lwjgl.input.Keyboard;

import com.STHY.sthyworks.client.key.KeyLoader;
import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.potion.PotionLoader;
import com.STHY.sthyworks.common.util.ItemStoreEntityUUID;
import com.STHY.sthyworks.common.util.sthyUtils;
import com.google.common.collect.Multimap;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class SacredBlade extends ItemSword {

    @SideOnly(Side.CLIENT)
    IIcon iconPowerUp;
    @SideOnly(Side.CLIENT)
    IIcon iconIce;

    public static final Item.ToolMaterial SacredBladeMaterial = EnumHelper
        .addToolMaterial("SacredBladeMaterial", 0, 0, 10.0F, -3.0F, 22);

    public SacredBlade() {
        super(SacredBladeMaterial);
        this.setUnlocalizedName("sacredBlade");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        if (stack.hasTagCompound() && stack.getTagCompound()
            .getInteger("kshana") > 0) {
            return super.getUnlocalizedName() + "_Ice";
        }
        return super.getUnlocalizedName(stack);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean par4) {
        KeyBinding toggleTooltipsDisplayKey = KeyLoader.toggleTooltipsDisplay;
        if (!Keyboard.isKeyDown(toggleTooltipsDisplayKey.getKeyCode())) {
            list.add(StatCollector.translateToLocal("item.sacredBlade.tooltips.routine.line1"));
            list.add(StatCollector.translateToLocal("item.sacredBlade.tooltips.routine.line2"));
            list.add(StatCollector.translateToLocal("item.sacredBlade.tooltips.routine.line3"));
            list.add(StatCollector.translateToLocal("item.sacredBlade.tooltips.routine.line4"));
            list.add(StatCollector.translateToLocal("item.sacredBlade.tooltips.routine.line5"));
            list.add(
                String.format(
                    StatCollector.translateToLocal("key.sthyworks.toggleTooltipsDisplay.tooltips"),
                    GameSettings.getKeyDisplayString(toggleTooltipsDisplayKey.getKeyCode())));
        } else {
            list.add(StatCollector.translateToLocal("item.sacredBlade.tooltips.toggle.line1"));
            list.add(StatCollector.translateToLocal("item.sacredBlade.tooltips.toggle.line2"));
            list.add(StatCollector.translateToLocal("item.sacredBlade.tooltips.toggle.line3"));
            list.add(StatCollector.translateToLocal("item.sacredBlade.tooltips.toggle.line4"));
            list.add(StatCollector.translateToLocal("item.sacredBlade.tooltips.toggle.line5"));
        }
    }

    @Override
    public boolean isItemTool(ItemStack itemStack) {
        return this.getItemStackLimit(itemStack) == 1;
    }

    @Override
    public void registerIcons(IIconRegister register) {
        itemIcon = register.registerIcon("sthyworks:sacredBlade");
        iconPowerUp = register.registerIcon("sthyworks:sacredBlade_powerUp");
        iconIce = register.registerIcon("sthyworks:sacredBlade_ice");
    }

    @Override
    public IIcon getIconIndex(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tag = stack.getTagCompound();
        if (tag.getInteger("kshana") > 0) {
            return iconIce;
        }
        return itemIcon;
    }

    @Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tag = stack.getTagCompound();
        if (tag.getInteger("kshana") > 0) {
            return iconIce;
        }
        if (usingItem != stack) {
            return itemIcon;
        }
        int usedTime = this.getMaxItemUseDuration(stack) - useRemaining;
        if (usedTime >= 16) {
            return iconPowerUp;
        }
        return itemIcon;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int slotIndex, boolean isHeld) {
        if (!(entityIn instanceof EntityPlayer)) return;

        EntityPlayer player = (EntityPlayer) entityIn;

        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tag = stack.getTagCompound();

        // 刹那状态
        if (tag.getInteger("kshana") > 0) {
            if (!worldIn.isRemote) {
                if (isHeld && player.swingProgressInt == 1) {
                    EntityLivingBase target = sthyUtils.getClosestTarget(worldIn, player, 12);
                    if (target != null) {
                        if (target.getDistanceSq(player.posX, player.posY, player.posZ) <= 1.4) return;
                        edgeReturn(player, target, true);
                    }
                }
                tag.setInteger("kshana", tag.getInteger("kshana") - 1);
            }
            return;
        }

        // 常规状态 一闪的冲刺
        if (tag.getInteger("sprint") > 0) {
            if (!worldIn.isRemote && tag.getInteger("sprint") < 8) {
                List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity(
                    player,
                    player.boundingBox,
                    entity -> entity instanceof EntityLivingBase);
                if (!list.isEmpty() && list.get(0) instanceof EntityLivingBase) {
                    EntityLivingBase target = (EntityLivingBase) list.get(0);
                    target.hurtResistantTime = 0;
                    player.attackTargetEntityWithCurrentItem(target);
                    ItemStoreEntityUUID.storeEntityUUID(stack, target.getUniqueID());
                    tag.setInteger("sprint", 0);
                    return;
                }
            }
            if (!worldIn.isRemote) {
                tag.setInteger("sprint", tag.getInteger("sprint") - 1);
            }
            return;
        }

        // 常规状态 一闪
        if (isHeld && player.swingProgressInt == 1) {
            if (player.getFoodStats()
                .getFoodLevel() > 1) {
                player.getFoodStats()
                    .addExhaustion(4.0F);
            } else {
                return;
            }
            sprint(player, 4.5D);
            if (!worldIn.isRemote) {
                tag.setInteger("sprint", 8);
            }
            return;
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        if (!itemStackIn.hasTagCompound()) {
            itemStackIn.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tag = itemStackIn.getTagCompound();
        if (tag.getInteger("kshana") > 0) {
            // 刹那状态
            AxisAlignedBB searchBox = AxisAlignedBB.getBoundingBox(
                player.posX - 1.2D,
                player.posY - 0.25D,
                player.posZ - 1.2D,
                player.posX + 1.2D,
                player.posY + 2.0D,
                player.posZ + 1.2D);
            List<EntityLivingBase> list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, searchBox);
            for (EntityLivingBase entityLivingBase : list) {
                if (entityLivingBase != player) {
                    entityLivingBase.hurtResistantTime = 0;
                    player.attackTargetEntityWithCurrentItem(entityLivingBase);
                }
            }
            playerSpawnParticle(player, "snowshovel", 32, 1.0D);
            sprint(player, -2.5D);
        } else {
            // 常规状态
            player.setItemInUse(itemStackIn, this.getMaxItemUseDuration(itemStackIn));
        }
        return itemStackIn;
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count) {
        if (ItemStoreEntityUUID.hasStoredEntityUUID(stack)) {
            int usedTime = this.getMaxItemUseDuration(stack) - count;
            if (player.worldObj.isRemote && usedTime == 16) {
                playerSpawnParticle(player, "instantSpell", 32, 1.0D);
            }
        }
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int count) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tag = stack.getTagCompound();
        int usedTime = this.getMaxItemUseDuration(stack) - count;
        if (usedTime >= 16) {
            EntityLivingBase target = ItemStoreEntityUUID.getItemStoredEntity(world, stack, EntityLivingBase.class);
            ItemStoreEntityUUID.clearStoredEntityUUID(stack);
            if (target == null) return;
            if (usedTime < 18) {
                edgeReturn(player, target, true);
                tag.setInteger("kshana", 80);
                player.getFoodStats()
                    .addStats(20, 1.0F);
            } else {
                edgeReturn(player, target, false);
            }
        } else if (usedTime >= 4) {
            sprint(player, -1.8D);
        }
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(stack);
        if (stack.hasTagCompound() && stack.getTagCompound()
            .getInteger("kshana") > 0) {
            multimap.put(
                SharedMonsterAttributes.knockbackResistance.getAttributeUnlocalizedName(),
                new AttributeModifier(
                    UUID.fromString("8eebe4b9-f539-4633-9c6f-db21455a747a"),
                    "SacredBlade knockbackResistance 0",
                    1.0D,
                    0));
            multimap.put(
                SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
                new AttributeModifier(
                    UUID.fromString("e1cbc9dc-cf0e-454a-92a2-dde205c91644"),
                    "SacredBlade attackDamage 1",
                    0.5D,
                    1));
        }
        return multimap;
    }

    public void edgeReturn(EntityPlayer player, EntityLivingBase target, boolean perfect) {
        target.addPotionEffect(new PotionEffect(PotionLoader.receivedDamageIncrease.getId(), 1, perfect ? 29 : 9));
        player.setPositionAndUpdate(target.posX, target.posY, target.posZ);
        target.hurtResistantTime = 0;
        player.attackTargetEntityWithCurrentItem(target);
    }

    public void playerSpawnParticle(EntityPlayer player, String particleName, int particleCount,
        double velocityCoefficient) {
        Random rand = player.getRNG();
        for (int i = 0; i < particleCount; i++) {
            player.worldObj.spawnParticle(
                particleName,
                player.posX + (rand.nextDouble() * 2.0D - 1.0D) * player.width,
                player.posY + player.getEyeHeight() / 2,
                player.posZ + (rand.nextDouble() * 2.0D - 1.0D) * player.width,
                (rand.nextDouble() - 0.5D) * velocityCoefficient,
                (rand.nextDouble() - 0.5D) * velocityCoefficient,
                (rand.nextDouble() - 0.5D) * velocityCoefficient);
        }
    }

    private void sprint(EntityPlayer player, double boost) {
        Vec3 lookVec = player.getLookVec();
        double xCoord = lookVec.xCoord / (Math.abs(lookVec.xCoord) + Math.abs(lookVec.zCoord));
        double zCoord = lookVec.zCoord / (Math.abs(lookVec.xCoord) + Math.abs(lookVec.zCoord));
        player.motionX += xCoord * boost;
        player.motionZ += zCoord * boost;
    }
}
