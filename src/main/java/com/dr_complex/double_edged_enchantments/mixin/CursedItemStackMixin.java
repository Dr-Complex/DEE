package com.dr_complex.double_edged_enchantments.mixin;

import com.dr_complex.double_edged_enchantments.enchantments.DEE_Enchantments;
import com.google.common.collect.Lists;
import net.minecraft.component.*;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.component.type.MapIdComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockPredicatesChecker;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class CursedItemStackMixin implements ComponentHolder {

    @Inject(method = "getTooltip",at = @At("HEAD"), cancellable = true)
    private void ShowToolTip(Item.TooltipContext context, @Nullable PlayerEntity player, TooltipType type, CallbackInfoReturnable<List<Text>> cir){

        Set<RegistryEntry<Enchantment>> enchantments;
        boolean Skip;
        boolean Show;

        if(this.hasEnchantments()) {
            enchantments = this.getEnchantments().getEnchantments();
            Skip = enchantments.stream().map(RegistryEntry::getIdAsString).toList().contains(DEE_Enchantments.CURSE_HIDDEN.getValue().toString());
            Show = enchantments.stream().map(RegistryEntry::getIdAsString).toList().contains(DEE_Enchantments.ENCHANTMENT_REVEALING.getValue().toString());
        }else {
            Skip = false;
            Show = false;
        }

        boolean bl = this.getItem().shouldShowOperatorBlockWarnings(this.copy(), player);
        if (!type.isCreative() &&( (this.contains(DataComponentTypes.HIDE_TOOLTIP ) || Skip ))) {
            cir.setReturnValue(bl ? OPERATOR_WARNINGS : List.of());
        } else {
            List<Text> list = Lists.newArrayList();
            list.add(this.getFormattedName());
            if (!type.isAdvanced() && !this.contains(DataComponentTypes.CUSTOM_NAME)) {
                MapIdComponent mapIdComponent = this.get(DataComponentTypes.MAP_ID);
                if (mapIdComponent != null) {
                    list.add(FilledMapItem.getIdText(mapIdComponent));
                }
            }

            Consumer<Text> consumer = list::add;
            if (!this.contains(DataComponentTypes.HIDE_ADDITIONAL_TOOLTIP)) {
                this.getItem().appendTooltip(this.copy(), context, list, type);
            }

            this.appendTooltip(DataComponentTypes.JUKEBOX_PLAYABLE, context, consumer, type);
            this.appendTooltip(DataComponentTypes.TRIM, context, consumer, type);
            this.appendTooltip(DataComponentTypes.STORED_ENCHANTMENTS, context, consumer, type);
            this.appendTooltip(DataComponentTypes.ENCHANTMENTS, context, consumer, type);
            this.appendTooltip(DataComponentTypes.DYED_COLOR, context, consumer, type);
            this.appendTooltip(DataComponentTypes.LORE, context, consumer, type);
            this.appendAttributeModifiersTooltip(consumer, player);
            this.appendTooltip(DataComponentTypes.UNBREAKABLE, context, consumer, type);
            this.appendTooltip(DataComponentTypes.OMINOUS_BOTTLE_AMPLIFIER, context, consumer, type);
            this.appendTooltip(DataComponentTypes.SUSPICIOUS_STEW_EFFECTS, context, consumer, type);
            BlockPredicatesChecker blockPredicatesChecker = this.get(DataComponentTypes.CAN_BREAK);
            if (blockPredicatesChecker != null && blockPredicatesChecker.showInTooltip()) {
                consumer.accept(ScreenTexts.EMPTY);
                consumer.accept(BlockPredicatesChecker.CAN_BREAK_TEXT);
                blockPredicatesChecker.addTooltips(consumer);
            }

            BlockPredicatesChecker blockPredicatesChecker2 = this.get(DataComponentTypes.CAN_PLACE_ON);
            if (blockPredicatesChecker2 != null && blockPredicatesChecker2.showInTooltip()) {
                consumer.accept(ScreenTexts.EMPTY);
                consumer.accept(BlockPredicatesChecker.CAN_PLACE_TEXT);
                blockPredicatesChecker2.addTooltips(consumer);
            }

            if (type.isAdvanced() || Show) {
                if (this.isDamaged()) {
                    list.add(Text.translatable("item.durability", this.getMaxDamage() - this.getDamage(), this.getMaxDamage()));
                }

                if(type.isAdvanced()){
                    list.add(Text.literal(Registries.ITEM.getId(this.getItem()).toString()).formatted(Formatting.DARK_GRAY));
                    int i = this.components.size();
                    if (i > 0) {
                        list.add(Text.translatable("item.components", i).formatted(Formatting.DARK_GRAY));
                    }
                }
            }

            if (player != null && !this.getItem().isEnabled(player.getWorld().getEnabledFeatures())) {
                list.add(DISABLED_TEXT);
            }

            if (bl) {
                list.addAll(OPERATOR_WARNINGS);
            }

            cir.setReturnValue(list);
        }
    }

    @Inject(method = "getFormattedName", at =@At("TAIL"), cancellable = true)
    public void getFormattedNameRework(CallbackInfoReturnable<Text> cir) {
        MutableText mutableText = Text.empty().append(this.getName()).formatted(this.getRarity().getFormatting());

        if (this.contains(DataComponentTypes.CUSTOM_NAME)) {
            mutableText.formatted(Formatting.ITALIC);
        }

        if(this.hasEnchantments()) {
            var enchantments = this.getEnchantments().getEnchantments();
            var contention = enchantments.stream().map(enchantmentRegistryEntry -> enchantmentRegistryEntry.isIn(EnchantmentTags.CURSE));
            if(contention.toList().contains(true)){
                if(!enchantments.stream().map(RegistryEntry::getIdAsString).toList().contains(DEE_Enchantments.CURSE_HIDDEN.getValue().toString())) {
                    mutableText.formatted(Formatting.RED,Formatting.UNDERLINE);
                }
            }
        }
        cir.setReturnValue(mutableText);
    }

    @Shadow public abstract Text getName();

    @Shadow public abstract Rarity getRarity();

    @Shadow public abstract ItemEnchantmentsComponent getEnchantments();

    @Shadow public abstract boolean hasEnchantments();

    @Shadow public abstract boolean isEmpty();

    @Shadow @Final
    MergedComponentMap components;

    @Shadow @Final private static List<Text> OPERATOR_WARNINGS;

    @Shadow @Final private static Text DISABLED_TEXT;

    @Shadow public abstract Item getItem();

    @Shadow public abstract boolean isDamaged();

    @Shadow public abstract int getMaxDamage();

    @Shadow public abstract int getDamage();

    @Shadow protected abstract <T extends TooltipAppender> void appendTooltip(ComponentType<T> componentType, Item.TooltipContext context, Consumer<Text> textConsumer, TooltipType type);

    @Shadow protected abstract void appendAttributeModifiersTooltip(Consumer<Text> textConsumer, @Nullable PlayerEntity player);

    @Shadow public abstract ItemStack copy();

    @Shadow public abstract Text getFormattedName();

    @Shadow public abstract String toString();

    @Override
    public ComponentMap getComponents() {
        return (!this.isEmpty() ? this.components : ComponentMap.EMPTY);
    }

}
