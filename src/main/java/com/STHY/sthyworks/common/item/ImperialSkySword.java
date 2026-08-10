package com.STHY.sthyworks.common.item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

import org.lwjgl.input.Keyboard;

import com.STHY.sthyworks.client.key.KeyLoader;
import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
import com.STHY.sthyworks.common.potion.PotionLoader;
import com.google.common.collect.Multimap;

public class ImperialSkySword extends ItemSword {

    public static final Item.ToolMaterial ImperialSkySwordMaterial = EnumHelper
        .addToolMaterial("ImperialSkySwordMaterial", 0, 0, 10.0F, 4.0F, 22);

    public ImperialSkySword() {
        super(ImperialSkySwordMaterial);
        this.setUnlocalizedName("imperialSkySword");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
        this.setTextureName("sthyworks:imperialSkySword");
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean par4) {
        KeyBinding toggleTooltipsDisplayKey = KeyLoader.toggleTooltipsDisplay;
        if (!Keyboard.isKeyDown(toggleTooltipsDisplayKey.getKeyCode())) {
            list.add(StatCollector.translateToLocal("item.imperialSkySword.tooltips.routine.line1"));
            list.add(StatCollector.translateToLocal("item.imperialSkySword.tooltips.routine.line2"));
            list.add(StatCollector.translateToLocal("item.imperialSkySword.tooltips.routine.line3"));
            list.add(StatCollector.translateToLocal("item.imperialSkySword.tooltips.routine.line4"));
            list.add(
                String.format(
                    StatCollector.translateToLocal("key.sthyworks.toggleTooltipsDisplay.tooltips"),
                    GameSettings.getKeyDisplayString(toggleTooltipsDisplayKey.getKeyCode())));
        } else {
            list.add(StatCollector.translateToLocal("item.imperialSkySword.tooltips.toggle.line1"));
            list.add(StatCollector.translateToLocal("item.imperialSkySword.tooltips.toggle.line2"));
            list.add(StatCollector.translateToLocal("item.imperialSkySword.tooltips.toggle.line3"));
            list.add(StatCollector.translateToLocal("item.imperialSkySword.tooltips.toggle.line4"));
            list.add(StatCollector.translateToLocal("item.imperialSkySword.tooltips.toggle.line5"));
            list.add(StatCollector.translateToLocal("item.imperialSkySword.tooltips.toggle.line6"));
            list.add(StatCollector.translateToLocal("item.imperialSkySword.tooltips.toggle.line7"));
            list.add(StatCollector.translateToLocal("item.imperialSkySword.tooltips.toggle.line8"));
            list.add(StatCollector.translateToLocal("item.imperialSkySword.tooltips.toggle.line9"));
            list.add(StatCollector.translateToLocal("item.imperialSkySword.tooltips.toggle.line10"));
        }
    }

    @Override
    public boolean isItemTool(ItemStack itemStack) {
        return this.getItemStackLimit(itemStack) == 1;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int slotIndex, boolean isHeld) {
        if (!worldIn.isRemote) {
            if (stack.hasTagCompound()) {
                NBTTagCompound tag = stack.getTagCompound();
                if (tag.hasKey("continuousAttackCountdown")) {
                    int count = tag.getInteger("continuousAttackCountdown");
                    if (count > 0) {
                        count--;
                        tag.setInteger("continuousAttackCountdown", count);
                        if (count == 0 && tag.hasKey("combo")) {
                            tag.setInteger("combo", 0);
                        }
                    }
                }
                if (tag.hasKey("secondSwordDanceAllowCountdown")) {
                    int count = tag.getInteger("secondSwordDanceAllowCountdown");
                    if (count > 0) {
                        count--;
                        tag.setInteger("secondSwordDanceAllowCountdown", count);
                        if (count == 0) {
                            tag.setBoolean("secondSwordDanceAllow", false);
                        }
                    }
                }
            }
        }
        super.onUpdate(stack, worldIn, entityIn, slotIndex, isHeld);
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase entityLivingBase, EntityLivingBase player) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tag = stack.getTagCompound();

        // 如果正在进行剑舞攻击，就不触发剑势积累和连击
        if (tag.getBoolean("swordDance")) {
            return true;
        }

        // 第二次剑舞攻击
        if (tag.getBoolean("secondSwordDanceAllow")) {
            tag.setBoolean("secondSwordDanceAllow", false);
            tag.setBoolean("swordDance", true);
            AxisAlignedBB searchBox = AxisAlignedBB.getBoundingBox(
                entityLivingBase.posX - 1.5,
                entityLivingBase.posY - 0.5,
                entityLivingBase.posZ - 1.5,
                entityLivingBase.posX + 1.5,
                entityLivingBase.posY + 0.5 + entityLivingBase.getEyeHeight(),
                entityLivingBase.posZ + 1.5);
            List<EntityLivingBase> entityLivingBases = entityLivingBase.worldObj
                .getEntitiesWithinAABB(EntityLivingBase.class, searchBox);
            List<EntityLivingBase> targets = new ArrayList<>();
            for (EntityLivingBase livingBase : entityLivingBases) {
                if (livingBase != player) {
                    targets.add(livingBase);
                }
            }
            Random rand = player.getRNG();
            for (int i = 0; i < 2; i++) {
                if (!targets.isEmpty()) {
                    EntityLivingBase target = targets.get(rand.nextInt(targets.size()));
                    swordDanceAttack(target, player, 2);
                }
            }
            tag.setBoolean("swordDance", false);
            return true;
        }

        // 剑势积累
        if (tag.hasKey("swordAura")) {
            int count = tag.getInteger("swordAura");
            if (count < 7) {
                tag.setInteger("swordAura", count + 1);
            }
        } else {
            tag.setInteger("swordAura", 1);
        }

        // 连击
        if (tag.hasKey("combo")) {
            switch (tag.getInteger("combo")) {
                case 1:
                    secondAttack(stack, entityLivingBase, player);
                    tag.setInteger("combo", 2);
                    break;
                case 2:
                    thirdAttack(stack, entityLivingBase, player);
                    tag.setInteger("combo", 3);
                    break;
                case 3:
                    fourthAttack(stack, entityLivingBase, player);
                    tag.setInteger("combo", 0);
                    break;
                default:
                    firstAttack(stack, entityLivingBase, player);
                    tag.setInteger("combo", 1);
            }
        } else {
            firstAttack(stack, entityLivingBase, player);
            tag.setInteger("combo", 1);
        }
        tag.setInteger("continuousAttackCountdown", 30);
        return true;
    }

    public void firstAttack(ItemStack stack, EntityLivingBase entityLivingBase, EntityLivingBase player) {
        // 清除伤害抵抗时间
        entityLivingBase.hurtResistantTime = 0;
        float attackDamage = Math.max(entityLivingBase.getHealth() * 0.08F, 1.0F);
        entityLivingBase
            .attackEntityFrom(new EntityDamageSource(DamageSource.onFire.getDamageType(), player), attackDamage);
        entityLivingBase.setFire(1);
    }

    public void secondAttack(ItemStack stack, EntityLivingBase entityLivingBase, EntityLivingBase player) {
        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 30, 1));
        player.addPotionEffect(new PotionEffect(Potion.digSpeed.getId(), 30, 0));
        player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 30, 0));
    }

    public void thirdAttack(ItemStack stack, EntityLivingBase entityLivingBase, EntityLivingBase player) {
        entityLivingBase.hurtResistantTime = 0;
        player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 30, 0));
        float attackDamage = Math.max((entityLivingBase.getMaxHealth() - entityLivingBase.getHealth()) * 0.08F, 1.0F);
        entityLivingBase.attackEntityFrom(new EntityDamageSource("player", player), attackDamage);
    }

    public void fourthAttack(ItemStack stack, EntityLivingBase entityLivingBase, EntityLivingBase player) {
        Collection<PotionEffect> potionEffects = player.getActivePotionEffects();
        int beforeCount = potionEffects.size();
        potionEffects.removeIf(potionEffect -> Potion.potionTypes[potionEffect.getPotionID()].isBadEffect());
        int badPotionCount = beforeCount - potionEffects.size();
        player.heal(player.getMaxHealth() * (0.1F + badPotionCount * 0.05F));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        if (!itemStackIn.hasTagCompound()) {
            itemStackIn.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tag = itemStackIn.getTagCompound();
        if (tag.hasKey("swordAura")) {
            int count = tag.getInteger("swordAura");
            if (count >= 7) {
                tag.setBoolean("swordDance", true);
                AxisAlignedBB searchBox = AxisAlignedBB.getBoundingBox(
                    player.posX - 2,
                    player.posY - 0.5,
                    player.posZ - 2,
                    player.posX + 2,
                    player.posY + 2.8,
                    player.posZ + 2);
                List<EntityLivingBase> list = worldIn.getEntitiesWithinAABB(EntityLivingBase.class, searchBox);
                for (EntityLivingBase entityLivingBase : list) {
                    if (entityLivingBase != player) {
                        swordDanceAttack(entityLivingBase, player, 4);
                    }
                }
                tag.setInteger("swordAura", 0);
                tag.setBoolean("swordDance", false);
                tag.setBoolean("secondSwordDanceAllow", true);
                tag.setInteger("secondSwordDanceAllowCountdown", 40);
            }
        }
        super.onItemRightClick(itemStackIn, worldIn, player);
        return itemStackIn;
    }

    public void swordDanceAttack(EntityLivingBase entityLivingBase, EntityLivingBase LivingBasePlayer, int amplifier) {
        if (LivingBasePlayer instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) LivingBasePlayer;
            entityLivingBase
                .addPotionEffect(new PotionEffect(PotionLoader.receivedDamageIncrease.getId(), 1, amplifier));
            entityLivingBase.hurtResistantTime = 0;
            player.attackTargetEntityWithCurrentItem(entityLivingBase);
        }
    }

    @Override
    public Multimap<String, AttributeModifier> getAttributeModifiers(ItemStack stack) {
        Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(stack);

        int swordAura = 0;
        if (stack.hasTagCompound()) {
            swordAura = stack.getTagCompound()
                .getInteger("swordAura");
        }
        if (swordAura > 0) {
            multimap.put(
                SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
                new AttributeModifier(
                    UUID.fromString("b084ff37-4f6b-4c4a-a71d-ddb8e18586c2"),
                    "ImperialSkySword attackDamage 2",
                    swordAura * 0.02D,
                    2));
        }
        return multimap;
    }
}
