package ms55.roughtweaksrevamped.common.events;

import ms55.roughtweaksrevamped.RoughTweaksRevamped;
import ms55.roughtweaksrevamped.client.RoughConfig;
import ms55.roughtweaksrevamped.common.item.ModItems;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.GameRules;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = RoughTweaksRevamped.MODID, bus = Bus.MOD)
public class RoughEvents {

	@SubscribeEvent
    public void logIn(PlayerLoggedInEvent event) {
		GameRules rules = event.getPlayer().world.getGameRules();
        if(!RoughConfig.GENERAL.HEALTH_REGEN.get() && event.getPlayer().getEntityWorld().getGameRules().getBoolean(GameRules.NATURAL_REGENERATION)) {
        	rules.get(GameRules.NATURAL_REGENERATION).set(false, event.getPlayer().getEntityWorld().getServer());
        	System.out.println("set natural regeneration to false");
        }
    }

	@SubscribeEvent
	public void tickEvent(PlayerTickEvent event) {
		if (event.player.isPlayerFullyAsleep()) {
			event.player.heal(RoughConfig.GENERAL.SLEEP_HEAL_AMOUNT.get().floatValue());
		}
	}

	@SubscribeEvent
	public void dropEvent(LivingDropsEvent event) {
		if (RoughConfig.GENERAL.DROP_CHANCE.get() > 0 && event.getEntityLiving() instanceof MobEntity) {
			ItemStack stack = ItemStack.EMPTY;

			switch(event.getEntity().world.rand.nextInt(RoughConfig.GENERAL.DROP_CHANCE.get() * getEnabledItems())) {
				case 0: 
					if (RoughConfig.HEAL_AMOUNT.SALVE_HEAL_AMOUNT.get() > 0) 
						stack = new ItemStack(ModItems.SALVE.get()); break;
				case 1: 
					if (RoughConfig.HEAL_AMOUNT.PLASTER_HEAL_AMOUNT.get() > 0) 
						stack = new ItemStack(ModItems.PLASTER.get()); break;
				case 2: 
					if (RoughConfig.HEAL_AMOUNT.BANDAGE_HEAL_AMOUNT.get() > 0) 
						stack = new ItemStack(ModItems.BANDAGE.get()); break;
			}

			if (stack != null)
				event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(), event.getEntity().getPosY(), event.getEntity().getPosZ(), stack));
		}
	}

	private int getEnabledItems() {
		int items = 0;
		if (RoughConfig.HEAL_COUNT.SALVE_HEAL_COUNT.get() > 0)
			items++;
		if (RoughConfig.HEAL_COUNT.PLASTER_HEAL_COUNT.get() > 0)
			items++;
		if (RoughConfig.HEAL_COUNT.BANDAGE_HEAL_COUNT.get() > 0)
			items++;

		return items;
	}
}