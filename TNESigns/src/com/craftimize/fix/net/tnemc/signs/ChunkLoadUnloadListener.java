package com.craftimize.fix.net.tnemc.signs;

import net.tnemc.core.common.module.ModuleListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

public class ChunkLoadUnloadListener implements ModuleListener {

    private SignDataStore signDataStore;

    public ChunkLoadUnloadListener(SignDataStore signDataStore) {
        this.signDataStore = signDataStore;
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        signDataStore.loadSigns(event.getChunk());
    }

    @EventHandler
    public void onChunkUnload(ChunkUnloadEvent event) {
        signDataStore.unloadSigns(event.getChunk());
    }

}
