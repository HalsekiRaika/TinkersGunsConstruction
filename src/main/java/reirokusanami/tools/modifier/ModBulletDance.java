package reirokusanami.tools.modifier;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import reirokusanami.event.GunToolEvent;
import slimeknights.tconstruct.library.entity.EntityProjectileBase;
import slimeknights.tconstruct.library.modifiers.ModifierAspect;
import slimeknights.tconstruct.library.modifiers.ProjectileModifierTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

import javax.annotation.Nullable;

public class ModBulletDance extends ProjectileModifierTrait {
    public ModBulletDance() {
        super("bulletdance", 0xffffff);
        MinecraftForge.EVENT_BUS.register(this);
        addAspects(ModifierAspect.projectileOnly);
    }

    @Override
    public boolean isHidden() {
        return true;
    }

    @SubscribeEvent
    public void onGunShoot(GunToolEvent.OnGunShoot event){
        if(TinkerUtil.hasTrait(TagUtil.getTagSafe(event.ammo), this.getModifierIdentifier())){
            event.setBaseInaccuracy(event.getBaseInaccuracy()*2f/3f);
        }
    }

    @Override
    public void onLaunch(EntityProjectileBase projectileBase, World world, @Nullable EntityLivingBase shooter) {
        projectileBase.motionX /= 10d;
        projectileBase.motionY /= 10d;
        projectileBase.motionZ /= 10d;

        projectileBase.setNoGravity(true);
    }

    @Override
    public void onMovement(EntityProjectileBase projectile, World world, double slowdown) {
        projectile.motionX *= 1d / slowdown;
        projectile.motionY *= 1d / slowdown;
        projectile.motionZ *= 1d / slowdown;
        projectile.motionY -= projectile.getGravity() / 250d;
    }

    @Override
    public void onProjectileUpdate(EntityProjectileBase projectile, World world, ItemStack toolStack) {
        double updateDistance = 0d;
        double lastParticle = 0d;
        int TickBuffer = projectile.ticksExisted;
        while (!projectile.onGround && projectile.ticksExisted > 1 && updateDistance < 40) {
            double x = projectile.posX;
            double y = projectile.posY;
            double z = projectile.posZ;

            projectile.ticksExisted = TickBuffer;
            projectile.updateInAir();

            x -= projectile.posX;
            y -= projectile.posY;
            z -= projectile.posZ;

            double distance = x*x + y*y + z*z;
            updateDistance += distance;
            if(distance < 0.001) break;

            lastParticle += distance;
            if (lastParticle > 0.3d){
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,
                        projectile.posX, projectile.posY, projectile.posZ, 0.0d, 0.0d, 0.0d);
                lastParticle = 0;
            }
            projectile.ticksExisted = TickBuffer;
        }

    }

}
