package reirokusanami.Entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.entity.EntityProjectileBase;

public class EntityProjectile extends EntityProjectileBase {
    public EntityProjectile(World world) {
        super(world);
    }

    public EntityProjectile(World world, double d, double d1, double d2) {
        super(world, d, d1, d2);
    }

    public EntityProjectile(World world, EntityPlayer player, float speed, float inaccuracy, float power, ItemStack stack, ItemStack launchingStack) {
        super(world, player, speed, inaccuracy, power, stack, launchingStack);
    }

    @Override
    protected void onEntityHit(Entity entityHit) {
        super.onEntityHit(entityHit);
        if(!this.getEntityWorld().isRemote && entityHit instanceof EntityLivingBase){
            EntityLivingBase LivingBase = (EntityLivingBase)entityHit;
            LivingBase.setArrowCountInEntity(LivingBase.getArrowCountInEntity()+1);
        }
    }
/*
    public void shoot(double x, double y, double z, float velocity, float inaccuracy) {
        float _float = MathHelper.sqrt();
    }
*/
    @Override
    public double getGravity() {
        return 0.002;
    }

    @Override
    public double getSlowdown() {
        return 0.0001;
    }
}
