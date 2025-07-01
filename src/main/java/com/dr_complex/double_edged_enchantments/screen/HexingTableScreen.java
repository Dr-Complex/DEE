package com.dr_complex.double_edged_enchantments.screen;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.StringVisitable;
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
        this.ticks = 0;
    }

    @Override
    protected void drawBackground(@NotNull DrawContext context, float delta, int mouseX, int mouseY) {
        int x = this.x;
        int y = this.y;
        context.drawTexture(RenderPipelines.GUI_TEXTURED, MainTexture,x,y,0,0,backgroundWidth,backgroundHeight,256,256);
        Slot LevelerSlot = this.handler.getSlot(1);
        Slot ConductorSlot = this.handler.getSlot(2);

        if(!LevelerSlot.hasStack()){
            if(ticks >= 150){
                context.drawGuiTexture(RenderPipelines.GUI_TEXTURED,UpTexture,x + LevelerSlot.x, y + LevelerSlot.y, 0, 0,16,16,16,16);
            }else {
                context.drawGuiTexture(RenderPipelines.GUI_TEXTURED,DownTexture,x + LevelerSlot.x, y + LevelerSlot.y, 0, 0,16,16,16,16);
            }
        }
        if(!ConductorSlot.hasStack()){
            if(ticks % 150 >= 75){
                context.drawGuiTexture(RenderPipelines.GUI_TEXTURED,EnchantmentTexture,x + ConductorSlot.x, y + ConductorSlot.y, 0, 0,16,16,16,16);
            }else {
                context.drawGuiTexture(RenderPipelines.GUI_TEXTURED,CurseTexture,x + ConductorSlot.x, y + ConductorSlot.y, 0, 0,16,16,16,16);
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

                    context.drawWrappedText(this.textRenderer, StringVisitable.plain(String.valueOf(q)),r,s,highlight,0x000000,false);
                    if(bl){
                        context.drawTooltip(this.textRenderer,Text.translatable(list.get(q).value().toString()),r,s);
                    }
                }
            }


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
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        int i = this.x + 62;
        int j = this.y + 12;
        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 6; l++) {
                double d = mouseX - (double) (i + l * 16);
                double e = mouseY - (double) (j + k * 16);
                int n = k * 6 + l;
                if (d >= 0.0 && e >= 0.0 && d < 16 && e < 16 && this.handler.onButtonClick(Objects.requireNonNull(this.client).player, n)) {
                    Objects.requireNonNull(this.client.interactionManager).clickButton(this.handler.syncId, n);
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

}
