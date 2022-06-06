package owmii.lib.item;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.IItemRenderProperties;
import owmii.lib.client.renderer.item.TEItemRenderer;

import java.util.function.Consumer;

public class ItemBase extends Item implements IItem {
    public ItemBase(Properties properties) {
        super(properties);
    }

    @Override
    public void initializeClient(Consumer<IItemRenderProperties> consumer) {
        consumer.accept(new IItemRenderProperties() {
            @Override
            public BlockEntityWithoutLevelRenderer getItemStackRenderer() {
                return new TEItemRenderer();
            }
        });
        super.initializeClient(consumer);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        return context.getPlayer() != null ? onItemUse(context.getLevel(), context.getClickedPos(), context.getPlayer(), context.getHand(), context.getClickedFace(), context.getClickLocation()) : super.useOn(context);
    }

    public InteractionResult onItemUse(Level world, BlockPos pos, Player player, InteractionHand hand, Direction side, Vec3 hit) {
        return InteractionResult.PASS;
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        return context.getPlayer() != null ? onItemUseFirst(stack, context.getLevel(), context.getClickedPos(), context.getPlayer(), context.getHand(), context.getClickedFace(), context.getClickLocation()) : super.onItemUseFirst(stack, context);
    }

    public InteractionResult onItemUseFirst(ItemStack stack, Level world, BlockPos pos, Player player, InteractionHand hand, Direction side, Vec3 hit) {
        return InteractionResult.PASS;
    }
}