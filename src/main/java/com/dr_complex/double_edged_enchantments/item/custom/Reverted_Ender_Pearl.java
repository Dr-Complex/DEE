package com.dr_complex.double_edged_enchantments.item.custom;

import com.dr_complex.double_edged_enchantments.utils.DEE_DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

public class Reverted_Ender_Pearl extends Item {
    public Reverted_Ender_Pearl(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, @NotNull PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getMainHandStack();
        if (itemStack.get(DEE_DataComponentTypes.POS_CONTAINER) != null) {
            if (!world.isClient) {
                if (!user.isSneaking()) {
                    user.teleport((ServerWorld) world, itemStack.get(DEE_DataComponentTypes.POS_CONTAINER).getX(), itemStack.get(DEE_DataComponentTypes.POS_CONTAINER).getY(), itemStack.get(DEE_DataComponentTypes.POS_CONTAINER).getZ(), HashSet.newHashSet(1), user.headYaw, user.lastPitch, false);
                    itemStack.decrementUnlessCreative(1, user);
                    return ActionResult.CONSUME;
                } else {
                    itemStack.set(DEE_DataComponentTypes.POS_CONTAINER, user.getPos());
                    return ActionResult.SUCCESS;
                }
            } else {
                if (!user.isSneaking()) {
                    for (int i = 0; i < 25; i++) {
                        world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_PLAYER_TELEPORT, SoundCategory.PLAYERS, 0.5f, 0.5f);
                        world.addParticleClient(ParticleTypes.PORTAL, user.getParticleX(0.25), user.getRandomBodyY(), user.getParticleZ(0.25), (user.getRandom().nextDouble() - 0.5d) * 2.0, 0, (user.getRandom().nextDouble() - 0.5d) * 2.0);
                    }
                } else {
                    world.playSound(user, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.PLAYERS, 0.25f, 0.5f + 1/(world.random.nextFloat() + 1f));
                }
                return ActionResult.PASS;
            }
        }else {
            if(user.isSneaking()){
                itemStack.set(DEE_DataComponentTypes.POS_CONTAINER, user.getPos());
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }

}
