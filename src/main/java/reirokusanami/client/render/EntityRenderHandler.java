package reirokusanami.client.render;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import reirokusanami.Entity.EntityProjectile;

@SideOnly(Side.CLIENT)
public class EntityRenderHandler {
    public static void renderEntity() {
        RenderingRegistry.registerEntityRenderingHandler(EntityProjectile.class, RenderBullet::new);
    }
}
