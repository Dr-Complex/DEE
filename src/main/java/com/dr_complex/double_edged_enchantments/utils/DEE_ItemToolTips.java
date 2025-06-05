package com.dr_complex.double_edged_enchantments.utils;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import com.dr_complex.double_edged_enchantments.item.DEE_Items;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Objects;

public class DEE_ItemToolTips {
    public static void LoadItemToolTips(){
        DEE_Common.LOGGER.info("Making the new & improved ToolTips!");
        ItemTooltipCallback.EVENT.register(((itemStack, tooltipContext, tooltipType, list) -> {
            if(itemStack.isOf(DEE_Items.EXP_NEEDLE)){
                if(itemStack.get(DEE_DataComponentTypes.XP_CONTAINER) != null){
                    list.add(Text.translatable("item.double_edged_enchantments.exp_needle.tooltip",itemStack.get(DEE_DataComponentTypes.XP_CONTAINER)));
                }else {
                    list.add(Text.translatable("item.double_edged_enchantments.exp_needle.tooltip.fallback"));
                }
            } else if (itemStack.isOf(DEE_Items.REVERED_ENDER_PEARL)) {
                if(itemStack.get(DEE_DataComponentTypes.POS_CONTAINER) != null){
                    Vec3d temp3D = new Vec3d(
                            MathHelper.roundDownToMultiple(Objects.requireNonNull(itemStack.get(DEE_DataComponentTypes.POS_CONTAINER)).x,1),
                            MathHelper.roundDownToMultiple(Objects.requireNonNull(itemStack.get(DEE_DataComponentTypes.POS_CONTAINER)).y,1),
                            MathHelper.roundDownToMultiple(Objects.requireNonNull(itemStack.get(DEE_DataComponentTypes.POS_CONTAINER)).z,1));
                    list.add(Text.translatable("item.double_edged_enchantments.reverted_ender_pearl.tooltip",temp3D).formatted(Formatting.ITALIC,Formatting.DARK_PURPLE));
                }else {
                    list.add(Text.translatable("item.double_edged_enchantments.reverted_ender_pearl.tooltip.fallback").formatted(Formatting.OBFUSCATED));
                }
            }
        }));
    }
}
