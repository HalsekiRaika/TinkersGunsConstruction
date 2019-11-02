package reirokusanami.event;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import reirokusanami.TinkersGunsConstruction;

public class TGCSoundEvent {
    public static SoundEvent Handgun_sound;

    @SubscribeEvent
    private static void SoundRegister(RegistryEvent.Register<SoundEvent> soundEventRegister) {
        ResourceLocation Handgun_sound_l = new ResourceLocation(TinkersGunsConstruction.MODID, "gunfire_9mm");

        soundEventRegister.getRegistry().registerAll(
                new SoundEvent(Handgun_sound_l).setRegistryName("handgunfire")
        );
    }
}
