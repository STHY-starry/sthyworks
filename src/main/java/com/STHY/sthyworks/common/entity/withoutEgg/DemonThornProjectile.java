package com.STHY.sthyworks.common.entity.withoutEgg;

import static com.STHY.sthyworks.common.util.ItemStoreEntityUUID.clearStoredEntityUUID;
import static com.STHY.sthyworks.common.util.ItemStoreEntityUUID.getStoredEntityUUID;
import static com.STHY.sthyworks.common.util.ItemStoreEntityUUID.hasStoredEntityUUID;

import java.util.UUID;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.damege.DamageLoader;
import com.STHY.sthyworks.common.item.DemonThorn;
import com.STHY.sthyworks.common.potion.PotionLoader;

public class DemonThornProjectile extends EntityThrowable {

    private float baseDamage;
    private int magicBoostLevel;
    private int maxPenetration;
    private int penetrationCount = 0;

    public DemonThornProjectile(World world) {
        super(world);
        this.setSize(0.4F, 0.4F);
    }

    public DemonThornProjectile(World world, EntityLivingBase thrower) {
        super(world, thrower);
        this.setSize(0.4F, 0.4F);
    }

    public DemonThornProjectile(World world, double x, double y, double z) {
        super(world, x, y, z);
        this.setSize(0.4F, 0.4F);
    }

    public float getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(float baseDamage) {
        this.baseDamage = baseDamage;
    }

    public int getMaxPenetration() {
        return maxPenetration;
    }

    public void setMaxPenetration(int maxPenetration) {
        this.maxPenetration = maxPenetration;
    }

    public int getPenetrationCount() {
        return penetrationCount;
    }

    public void setPenetrationCount(int penetrationCount) {
        this.penetrationCount = penetrationCount;
    }

    public int getMagicBoostLevel() {
        return magicBoostLevel;
    }

    public void setMagicBoostLevel(int magicBoostLevel) {
        this.magicBoostLevel = magicBoostLevel;
    }

    @Override
    protected float getGravityVelocity() {
        return 0.0F;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound tagCompound) {
        super.writeEntityToNBT(tagCompound);
        tagCompound.setFloat("baseDamage", baseDamage);
        tagCompound.setInteger("magicBoostLevel", magicBoostLevel);
        tagCompound.setInteger("maxPenetration", maxPenetration);
        tagCompound.setInteger("penetrationCount", penetrationCount);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound tagCompund) {
        super.readEntityFromNBT(tagCompund);
        baseDamage = tagCompund.getFloat("baseDamage");
        magicBoostLevel = tagCompund.getInteger("magicBoostLevel");
        maxPenetration = tagCompund.getInteger("maxPenetration");
        penetrationCount = tagCompund.getInteger("penetrationCount");
    }

    @Override
    public void setDead() {
        if (!worldObj.isRemote) {
            EntityLivingBase thrower = this.getThrower();
            if (thrower instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) thrower;
                ItemStack heldItemStack = player.getHeldItem();

                if (heldItemStack != null) {
                    Item heldItem = heldItemStack.getItem();
                    if (heldItem instanceof DemonThorn) {
                        DemonThorn demonThorn = (DemonThorn) heldItem;
                        if (hasStoredEntityUUID(heldItemStack)) {
                            if (getStoredEntityUUID(heldItemStack).equals(this.getUniqueID())) {
                                clearStoredEntityUUID(heldItemStack);
                                super.setDead();
                                return;
                            }
                        }
                    }
                }

                for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                    ItemStack stack = player.inventory.getStackInSlot(i);
                    if (stack != null && stack.getItem() instanceof DemonThorn) {
                        if (hasStoredEntityUUID(stack)) {
                            UUID uuid = getStoredEntityUUID(stack);
                            if (uuid != null && uuid.equals(this.getUniqueID())) {
                                clearStoredEntityUUID(stack);
                                super.setDead();
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            super.setDead();
        }
    }

    @Override
    protected void onImpact(MovingObjectPosition position) {
        if (worldObj.isRemote) return;

        if (position.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            this.setDead();
            return;
        }

        if (position.entityHit instanceof EntityLivingBase) {
            EntityLivingBase thrower = this.getThrower();
            DamageSource damageSource;
            if (thrower == null) {
                damageSource = DamageLoader.Ordinary;
            } else {
                damageSource = new EntityDamageSource(DamageLoader.Ordinary.getDamageType(), thrower);
            }
            damageSource.setProjectile();
            damageSource.setMagicDamage();
            ((EntityLivingBase) position.entityHit)
                .addPotionEffect(new PotionEffect(PotionLoader.receivedDamageIncrease.getId(), 1, 4));

            position.entityHit.attackEntityFrom(damageSource, calculateDamage());
            penetrationCount++;
            if (penetrationCount > maxPenetration) {
                this.setDead();
            }
        }
    }

    private float calculateDamage() {
        float damage = baseDamage;
        damage *= 1 + (magicBoostLevel * 0.1F);
        damage *= (float) Math.pow(0.8D, penetrationCount);
        return damage;
    }
}
