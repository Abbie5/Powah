package owmii.powah;

import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import owmii.lib.Lollipop;
import owmii.lib.api.IClient;
import owmii.lib.api.IMod;
import owmii.lib.config.IConfig;
import owmii.lib.network.Network;
import owmii.powah.api.PowahAPI;
import owmii.powah.block.Blcks;
import owmii.powah.block.Tiles;
import owmii.powah.client.Client;
import owmii.powah.config.ConfigHandler;
import owmii.powah.config.Configs;
import owmii.powah.entity.Entities;
import owmii.powah.inventory.Containers;
import owmii.powah.item.Itms;
import owmii.powah.network.Packets;
import owmii.powah.recipe.Recipes;

import javax.annotation.Nullable;

@Mod(Powah.MOD_ID)
public class Powah implements IMod {
    public static final String MOD_ID = "powah";
    public static final Network NET = new Network(MOD_ID);
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public Powah() {
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(Lollipop::setup);

        Blcks.REG.init();
        Tiles.REG.init();
        Itms.REG.init();
        Containers.REG.init();
        Entities.REG.init();
        Recipes.REG.init();

        loadListeners();
        Configs.register();
    }

    @Override
    public void setup(FMLCommonSetupEvent event) {
        PowahAPI.registerSolidCoolant(Blocks.SNOW_BLOCK, 48, -3);
        PowahAPI.registerSolidCoolant(Items.SNOWBALL, 12, -3);
        PowahAPI.registerSolidCoolant(Blocks.ICE, 48, -5);
        PowahAPI.registerSolidCoolant(Blocks.PACKED_ICE, 192, -8);
        PowahAPI.registerSolidCoolant(Blocks.BLUE_ICE, 568, -17);
        PowahAPI.registerSolidCoolant(Blcks.DRY_ICE, 712, -32);

        Packets.register();
    }

    @Override
    public void loadComplete(FMLLoadCompleteEvent event) {
        Configs.ALL.forEach(IConfig::reload);
        ConfigHandler.post();
    }

    @Nullable
    @Override
    public IClient getClient() {
        return Client.INSTANCE;
    }
}