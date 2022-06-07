package owmii.powah.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemHandlerHelper;
import owmii.powah.lib.item.ItemBase;
import owmii.powah.config.Configs;

public class PhotoelectricPaneItem extends ItemBase {
    public PhotoelectricPaneItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player playerIn, LivingEntity target, InteractionHand hand) {
        if (Configs.GENERAL.lens_of_ender.get()) {
            if (target.getClass() == EnderMan.class || target.getClass() == Endermite.class) {
                if (!playerIn.level.isClientSide) {
                    ItemStack stack1 = playerIn.getItemInHand(hand);
                    if (!playerIn.isCreative()) {
                        stack1.shrink(1);
                    }
                    ItemHandlerHelper.giveItemToPlayer(playerIn, new ItemStack(Itms.LENS_OF_ENDER.get()));
                    target.playSound(SoundEvents.ENDERMAN_DEATH, 0.5F, 1.0F);
                    target.remove(Entity.RemovalReason.KILLED);
                }
                return InteractionResult.SUCCESS;
            }
        }
        return super.interactLivingEntity(stack, playerIn, target, hand);
    }
}
