package com.dr_complex.double_edged_enchantments.mixin;

import com.dr_complex.double_edged_enchantments.enchantments.custom.Enchantment_Return;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

@Mixin(ServerWorld.class)
public class ReturnItemStackMixin {

    @Unique
    private static ArrayList<ItemStack> returnableItems = Enchantment_Return.returnable;

    @Inject(method = "onPlayerRespawned",at = @At("TAIL"))
    private void GiveItems(ServerPlayerEntity player, CallbackInfo ci){
        for(ItemStack itemStack : returnableItems){
            player.giveItemStack(itemStack);
        }
    }
}
