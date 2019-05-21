package reirokusanami.events;

import reirokusanami.materials.TGCMaterial;
import reirokusanami.miscellaneous.TGCConfig;
import slimeknights.tconstruct.library.events.MaterialEvent;
import slimeknights.tconstruct.library.materials.Material;

public class TGCEventRegistry {
    public void onMaterialRegistry(MaterialEvent.MaterialRegisterEvent event) {
        Material material = event.material;
        if(!(material instanceof TGCMaterial) && TGCConfig.isAllowedMaterial(material.getIdentifier())){
            event.setCanceled(true);
        }
        event.setCanceled(false);
    }
}
