package com.STHY.sthyworks.common.entity.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.damege.DamageLoader;

public class GuguProjectile extends EntityThrowable {

    private float damage;

    public GuguProjectile(World world) {
        super(world);
    }

    public GuguProjectile(World world, EntityLivingBase thrower) {
        super(world, thrower);
    }

    public GuguProjectile(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getDamage() {
        return damage;
    }

    @Override
    protected float getGravityVelocity() {
        return 0.0F;
    }

    @Override
    protected void onImpact(MovingObjectPosition position) {

        if (position.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            if (!worldObj.isRemote) {
                this.setDead();
                return;
            }
        }

        if (position.entityHit != null) {
            EntityLivingBase thrower = this.getThrower();
            DamageSource damageSource;
            if (thrower == null) {
                damageSource = DamageLoader.Soul;
            } else {
                damageSource = new EntityDamageSource(DamageLoader.Soul.getDamageType(), thrower).setProjectile();
            }
            position.entityHit.attackEntityFrom(damageSource, damage);
            if (!this.worldObj.isRemote) {
                this.setDead();
            }
        }
    }
}
