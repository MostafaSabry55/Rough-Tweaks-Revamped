package ms55.roughtweaksrevamped.common.item;

import ms55.roughtweaksrevamped.RoughTweaksRevamped;
import ms55.roughtweaksrevamped.client.RoughConfig;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RoughTweaksRevamped.MODID);

	public static RegistryObject<Item> SALVE = ITEMS.register(
		"salve", () -> new HealItem(RoughConfig.HEAL_COUNT.SALVE_HEAL_COUNT,
				RoughConfig.HEAL_TIME.SALVE_USE_TIME,
				RoughConfig.HEAL_AMOUNT.SALVE_HEAL_AMOUNT, 
				null, 
				new ItemStack(Items.BOWL)));

	public static RegistryObject<Item> PLASTER = ITEMS.register(
		"plaster", () -> new HealItem(RoughConfig.HEAL_COUNT.PLASTER_HEAL_COUNT,
				RoughConfig.HEAL_TIME.PLASTER_USE_TIME,
				RoughConfig.HEAL_AMOUNT.PLASTER_HEAL_AMOUNT,
				null,
				ItemStack.EMPTY));

	public static RegistryObject<Item> BANDAGE = ITEMS.register(
		"bandage", () -> new HealItem(RoughConfig.HEAL_COUNT.BANDAGE_HEAL_COUNT,
				RoughConfig.HEAL_TIME.BANDAGE_USE_TIME,
				RoughConfig.HEAL_AMOUNT.BANDAGE_HEAL_AMOUNT,
				null,
				ItemStack.EMPTY));

	public static RegistryObject<Item> MEDKIT = ITEMS.register(
		"medkit", () -> new HealItem(RoughConfig.HEAL_COUNT.MEDKIT_HEAL_COUNT,
				RoughConfig.HEAL_TIME.MEDKIT_USE_TIME,
				RoughConfig.HEAL_AMOUNT.MEDKIT_HEAL_AMOUNT,
				null,
				ItemStack.EMPTY));

	public static RegistryObject<Item> ENCHANTED_MEDKIT = ITEMS.register(
		"medkit_enchanted", () -> new HealItem(RoughConfig.HEAL_COUNT.MEDKIT_ENCHANTED_HEAL_COUNT,
				RoughConfig.HEAL_TIME.MEDKIT_ENCHANTED_USE_TIME,
				RoughConfig.HEAL_AMOUNT.MEDKIT_ENCHANTED_HEAL_AMOUNT,
				Effects.ABSORPTION,
				ItemStack.EMPTY));

    public static void register() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }
}