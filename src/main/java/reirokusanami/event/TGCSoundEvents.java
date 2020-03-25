package reirokusanami.event;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import reirokusanami.TinkersGunsConstruction;

import java.util.ArrayList;

@GameRegistry.ObjectHolder(TinkersGunsConstruction.MODID)
public class TGCSoundEvents {
    private static ArrayList<SoundEvent> events = new ArrayList<>();

    public static SoundEvent OnFireHandGun = createSoundEvents("gun.firehandgun");

    public static void registerSounds(RegistryEvent.Register<SoundEvent> event){
        IForgeRegistry<SoundEvent> reg = event.getRegistry();
        events.forEach(reg::register);
        events = null;
    }

    private static SoundEvent createSoundEvents(String name){
        SoundEvent event = new SoundEvent(new ResourceLocation(TinkersGunsConstruction.MODID, name));
        event.setRegistryName(TinkersGunsConstruction.MODID, name);
        events.add(event);
        return event;
    }
}
