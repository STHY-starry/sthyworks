package com.STHY.sthyworks.common.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.damege.DamageLoader;
import com.STHY.sthyworks.common.item.DemonThorn;
import com.STHY.sthyworks.common.potion.PotionLoader;

public class DemonThornProjectile extends EntityThrowable {

    private float damage;
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

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
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

    @Override
    protected float getGravityVelocity() {
        return 0.0F;
    }

    @Override
    public void setDead() {
        if (!worldObj.isRemote) {
            EntityLivingBase thrower = this.getThrower();
            if (thrower instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) thrower;
                for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                    ItemStack stack = player.inventory.getStackInSlot(i);
                    if (stack != null && stack.getItem() instanceof DemonThorn) {
                        DemonThorn demonThorn = (DemonThorn) stack.getItem();
                        if (demonThorn.hasStoredProjectile(stack)) {
                            if (demonThorn.getStoredProjectileUUID(stack)
                                .equals(this.getUniqueID())) {
                                demonThorn.clearStoredProjectile(stack);
                            }
                        }
                    }
                }
            }
        }
        super.setDead();
    }

    @Override
    protected void onImpact(MovingObjectPosition position) {

        if (position.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            if (!worldObj.isRemote) {
                this.setDead();
            }
        }

        if (position.entityHit instanceof EntityLivingBase) {
            if (!worldObj.isRemote) {
                EntityLivingBase thrower = this.getThrower();
                DamageSource damageSource;
                if (thrower == null) {
                    damageSource = DamageSource.generic;
                } else {
                    damageSource = new EntityDamageSource(DamageLoader.Ordinary.getDamageType(), thrower);
                }
                damageSource.setProjectile();
                damageSource.setMagicDamage();
                ((EntityLivingBase) position.entityHit)
                    .addPotionEffect(new PotionEffect(PotionLoader.receivedDamageIncrease.getId(), 60, 4));
                position.entityHit.attackEntityFrom(damageSource, (float) Math.pow(0.8D, penetrationCount) * damage);
                penetrationCount++;
                if (penetrationCount > maxPenetration) {
                    this.setDead();
                }
            }
        }
    }
}
