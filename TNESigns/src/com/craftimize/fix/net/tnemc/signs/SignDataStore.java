package com.craftimize.fix.net.tnemc.signs;

import com.boydti.fawe.bukkit.wrapper.AsyncWorld;
import net.tnemc.core.TNE;
import net.tnemc.signs.SignsData;
import net.tnemc.signs.signs.TNESign;
import org.bukkit.*;

import java.util.*;
import java.util.concurrent.*;

public class SignDataStore {

    private ExecutorService asyncPool = Executors.newCachedThreadPool();
    private Map<Location, TNESign> loaded = new HashMap<>();
    private TNE plugin;

    public SignDataStore(TNE plugin) {
        this.plugin = plugin;
    }

    void loadSigns(Chunk chunk) {
        asyncPool.execute(() -> {
            World world = chunk.getWorld();
            AsyncWorld asyncWorld = AsyncWorld.wrap(world);

            List<Location> locations = new ArrayList<>();
            int chunkX = chunk.getX() * 16;
            int chunkZ = chunk.getZ() * 16;
            for(int x=0; x < 16; x++) {
                for(int y=0; y < 256; y++) {
                    for(int z=0; z < 16; z++) {
                        Material material = asyncWorld.getBlockAt(chunkX + x, y, chunkZ + z).getType();
                        if(material == Material.SIGN || material == Material.WALL_SIGN) {
                            locations.add(new Location(world, chunkX + x, y, chunkZ + z));
                        }
                    }
                }
            }
            if(locations.size() > 0) {
                Map<Location, TNESign> signs = SignsData.loadSignsFromDatabase(locations);
                if(signs.size() > 0) {
                    Bukkit.getScheduler().runTask(plugin, () -> loaded.putAll(signs));
                }
            }
        });
    }

    public void unloadSign(Location location) {
        loaded.remove(location);
    }

    void unloadSigns(Chunk chunk) {
        loaded.keySet().removeIf(location -> location.getChunk().getX() == chunk.getX() && location.getChunk().getZ() == chunk.getZ());
    }

    public void setSignData(Location location, TNESign sign) {
        loaded.put(location, sign);
    }

    public TNESign getSign(Location location) {
        return loaded.get(location);
    }

    public Collection<TNESign> getSigns() {
        return Collections.unmodifiableCollection(loaded.values());
    }

}
