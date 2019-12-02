/**
 * CopyRight (c) 2019 <ReiRokusanami>
 *
 * This Java Class is distribute Tinkers' Guns Construction.
 * Tinkers' Guns Construction is open-source distribute under Lesser General Public License v3.
 *
 * Some Methods are taken and modified from the parent Mod: "Tinkers' Construct".
 * TiC is open-source distribute under MIT License.
 * View Source https://github.com/SlimeKnights/TinkersConstruct
*/
package reirokusanami.handler;

import com.google.common.collect.ImmutableList;
import gnu.trove.map.hash.THashMap;
import slimeknights.tconstruct.library.TinkerAPIException;
import slimeknights.tconstruct.library.events.TinkerRegisterEvent;
import slimeknights.tconstruct.library.modifiers.IModifier;

import java.util.Collection;
import java.util.Map;

import static reirokusanami.TinkersGunsConstruction.logger;

public class TGCModifierRegister {
    private static final Map<String, IModifier> modifiers = new THashMap<>();

    public static void registerModifierAlias(IModifier modifier, String alias) {
        if (modifiers.containsKey(alias)){
            throw new TinkerAPIException("Trying to register a modifier with the name " + alias + " but it already is registered");
        }
        if (new TinkerRegisterEvent.ModifierRegisterEvent(modifier).fire()){
            modifiers.put(alias, modifier);
        } else {
            logger.debug("Registration of modifier " + alias + " has been cancelled by event");
        }
    }

    public static void registerModifier(IModifier modifier){
        registerModifierAlias(modifier, modifier.getIdentifier());
    }

    public static IModifier getModifier(String identifier) {
        return modifiers.get(identifier);
    }

    public static Collection<IModifier> getAllModifier(){
        return ImmutableList.copyOf(modifiers.values());
    }
}
