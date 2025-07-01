package com.dr_complex.double_edged_enchantments.item.custom;

import com.dr_complex.double_edged_enchantments.entity.projectiles.GoldenSpearEntity;
import net.minecraft.block.BlockState;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.item.consume.UseAction;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class GoldenSpearItem extends Item implements ProjectileItem {

    private static final float roll = 0f;
    private static final float power = 1f;
    private static final float spread = 2.5f;
    private static final float speed = 1.75f;
    private static final float draw_time = 5f;
    public static final float damage = 2f;

    public GoldenSpearItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public boolean canMine(ItemStack stack, BlockState state, World world, BlockPos pos, LivingEntity user) {
        return !user.isInCreativeMode();
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.SPEAR;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }


    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        GoldenSpearEntity spearEntity = new GoldenSpearEntity(pos.getX(),pos.getY(),pos.getZ(),world,stack.copyWithCount(1),null);
        spearEntity.pickupType = PersistentProjectileEntity.PickupPermission.ALLOWED;
        return spearEntity;
    }



    @Override
    public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity player) {
            int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
            if (i < draw_time) {
                return false;
            } else {
                player.incrementStat(Stats.USED.getOrCreateStat(this));
                if(world instanceof ServerWorld serverWorld){
                    stack.damage(1,player);
                    GoldenSpearEntity goldenSpear = GoldenSpearEntity.spawnWithVelocity(GoldenSpearEntity::new,serverWorld, stack, player, roll, power, spread);
                    if (player.isInCreativeMode()) {
                        goldenSpear.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                    } else {
                        player.getInventory().removeOne(stack);
                    }
                    world.playSoundFromEntity(null, player,SoundEvents.ENTITY_ARMOR_STAND_HIT,SoundCategory.NEUTRAL,1.0f,1.05f);
                    return true;
                }
            }
        }return false;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack.willBreakNextUse()) {
            return ActionResult.FAIL;
        } else {
            user.setCurrentHand(hand);
            return ActionResult.CONSUME;
        }
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.ATTACK_DAMAGE, new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, damage, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .add(EntityAttributes.ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -4+speed, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                .build();
    }
}
