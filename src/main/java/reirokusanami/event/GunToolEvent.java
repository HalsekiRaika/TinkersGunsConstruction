package reirokusanami.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import reirokusanami.core.GunCore;
import slimeknights.tconstruct.library.events.TinkerEvent;
import slimeknights.tconstruct.library.events.TinkerToolEvent;

public class GunToolEvent extends TinkerEvent {
    public static class OnGunShoot extends TinkerToolEvent {

        public final EntityPlayer entityPlayer;
        public final GunCore bowCore;
        public final ItemStack ammo;
        public final int useTime;
        private float baseInaccuracy;

        public int projectileCount = 1;
        public boolean consumeAmmoPerProjectile = true;
        public boolean consumeDurabilityPerProjectile = true;
        public float bonusInaccuracy = 0;

        public OnGunShoot(ItemStack bow, ItemStack ammo, EntityPlayer entityPlayer, int useTime, float baseInaccuracy) {
            super(bow);
            this.bowCore = (GunCore) bow.getItem();
            this.ammo = ammo;
            this.entityPlayer = entityPlayer;
            this.useTime = useTime;
            this.baseInaccuracy = baseInaccuracy;
        }

        public static OnGunShoot fireEvent(ItemStack bow, ItemStack ammo, EntityPlayer entityPlayer, int useTime, float baseInaccuracy) {
            OnGunShoot event = new OnGunShoot(bow, ammo, entityPlayer, useTime, baseInaccuracy);
            MinecraftForge.EVENT_BUS.post(event);
            return event;
        }

        public void setProjectileCount(int projectileCount) {
            this.projectileCount = projectileCount;
        }

        public void setConsumeAmmoPerProjectile(boolean consumeAmmoPerProjectile) {
            this.consumeAmmoPerProjectile = consumeAmmoPerProjectile;
        }

        public void setConsumeDurabilityPerProjectile(boolean consumeDurabilityPerProjectile) {
            this.consumeDurabilityPerProjectile = consumeDurabilityPerProjectile;
        }

        public void setBonusInaccuracy(float bonusInaccuracy) {
            this.bonusInaccuracy = bonusInaccuracy;
        }

        public float getBaseInaccuracy() {
            return baseInaccuracy;
        }

        public void setBaseInaccuracy(float baseInaccuracy) {
            this.baseInaccuracy = baseInaccuracy;
        }
    }
}
