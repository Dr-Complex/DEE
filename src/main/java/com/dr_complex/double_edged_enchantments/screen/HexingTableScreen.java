package com.dr_complex.double_edged_enchantments.screen;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.DiffuseLighting;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class HexingTableScreen extends HandledScreen<HexingTableScreenHandler> {
    private static final Identifier MainTexture = DEE_Common.id("textures/gui/sprites/container/hexing_table.png");
    private static final Identifier EnchantmentTexture = DEE_Common.id("textures/gui/sprites/container/enchantment.png");
    private static final Identifier CurseTexture = DEE_Common.id("textures/gui/sprites/container/curse.png");
    private static final Identifier UpTexture = DEE_Common.id("textures/gui/sprites/container/up.png");
    private static final Identifier DownTexture = DEE_Common.id("textures/gui/sprites/container/down.png");
    private int ticks;


    public HexingTableScreen(HexingTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        handler.setInventoryChangeListener(this::onInventoryChanged);
        this.ticks = 0;
    }

    @Override
    protected void drawBackground(@NotNull DrawContext context, float delta, int mouseX, int mouseY) {
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        int x = this.x;
        int y = this.y;
        context.drawTexture(RenderLayer::getGuiTextured, MainTexture,x,y,0,0,backgroundWidth,backgroundHeight,256,256);
        Slot LevelerSlot = this.handler.getMaterial_UD_Slot();
        Slot ConductorSlot = this.handler.getMaterial_EC_Slot();

        if(!LevelerSlot.hasStack()){
            if(ticks >= 300){
                context.drawTexture(RenderLayer::getGuiTextured,UpTexture,x + LevelerSlot.x, y + LevelerSlot.y, 0, 0,16,16,16,16);
            }else {
                context.drawTexture(RenderLayer::getGuiTextured,DownTexture,x + LevelerSlot.x, y + LevelerSlot.y, 0, 0,16,16,16,16);
            }
        }
        if(!ConductorSlot.hasStack()){
            if(ticks % 300 >= 150){
                context.drawTexture(RenderLayer::getGuiTextured,EnchantmentTexture,x + ConductorSlot.x, y + ConductorSlot.y, 0, 0,16,16,16,16);
            }else {
                context.drawTexture(RenderLayer::getGuiTextured,CurseTexture,x + ConductorSlot.x, y + ConductorSlot.y, 0, 0,16,16,16,16);
            }
        }

        int l = x + 62;
        int m = y + 12;

        List<RegistryEntry<Enchantment>> list = this.handler.getEnchants();

        label64:
        if (list != null) {
            for (int n = 0; n < 3; n++) {
                for (int o = 0; o < 6; o++) {
                    int q = n * 6 + o;
                    if (q >= list.size()) {
                        break label64;
                    }
                    int r = l + o * 16;
                    int s = m + n * 16;
                    boolean bl = mouseX >= r && mouseY >= s && mouseX < r + 16 && mouseY < s + 16;
                    int highlight;
                    if (bl) {
                        if(ConductorSlot.getStack().isOf(Items.LAPIS_LAZULI)){
                            highlight = 0x75ffffff;
                        }else {
                            highlight = 0x75010101;
                        }

                    } else {
                        highlight = 0x75808080;
                    }

                    context.drawText(this.textRenderer,String.valueOf(q),r,s,highlight,false);
                    if(bl){
                        context.drawTooltip(this.textRenderer,Text.translatable(list.get(q).value().toString()),r,s);
                    }
                }
            }
            context.draw();
            DiffuseLighting.enableGuiDepthLighting();
            this.ticks = MathHelper.floorMod(ticks + 1,600);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        super.render(context, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(context,mouseX,mouseY);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title))/2;
        titleY -= 3;
    }

    @Override
    protected void applyBlur() {
        super.applyBlur();
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int i = this.x + 62;
        int j = this.y + 12;
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 6; l++) {
                double d = mouseX - (double) (i + l * 16);
                double e = mouseY - (double) (j + k * 16);
                int n = k * 6 + l;
                n = n >= this.handler.getEnchants().size() ? -1:n;
                if (d >= 0.0 && e >= 0.0 && d < 16 && e < 16 && this.handler.onButtonClick(Objects.requireNonNull(this.client).player, n)) {
                    Objects.requireNonNull(this.client.interactionManager).clickButton(this.handler.syncId, n);
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void onInventoryChanged() {
        ItemStack MAIN_INPUT = this.handler.getMainSlot().getStack();
        ItemStack EC_INPUT = this.handler.getMaterial_EC_Slot().getStack();
        ItemStack UD_INPUT = this.handler.getMaterial_UD_Slot().getStack();
        if(!MAIN_INPUT.isEmpty() && !EC_INPUT.isEmpty() && !UD_INPUT.isEmpty()){
            ItemEnchantmentsComponent temp = MAIN_INPUT.getOrDefault(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);
            DEE_Common.LOGGER.info("enchantments = {}", temp);
        }else{
            this.handler.setEnchants(List.of());
        }
    }
}
