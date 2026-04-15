package com.example.create_colonies;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(CreateColonies.MODID)
public class CreateColonies {

    public static final String MODID = "create_colonies";
    private static final Logger LOGGER = LogUtils.getLogger();

    public CreateColonies(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        LOGGER.info("Create Colonies initializing...");
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        LOGGER.info("Create Colonies common setup complete.");
    }
}
