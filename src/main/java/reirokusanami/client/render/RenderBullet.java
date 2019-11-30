package reirokusanami.client.render;

import net.minecraft.client.renderer.entity.RenderManager;
import reirokusanami.Entity.EntityProjectile;
import slimeknights.tconstruct.library.client.renderer.RenderProjectileBase;

public class RenderBullet extends RenderProjectileBase<EntityProjectile> {
    public RenderBullet(RenderManager renderManager) {
        super(renderManager);
    }
}