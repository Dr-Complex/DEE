package com.dr_complex.double_edged_enchantments.screen;

import com.dr_complex.double_edged_enchantments.DEE_Common;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class HexingTableScreen extends HandledScreen<HexingTableScreenHandler> {
    private static final Identifier MainTexture = DEE_Common.id("textures/gui/container/background.png");
    private static final Identifier Upgrade = DEE_Common.id("container/slot/upgrade_gen");
    private static final Identifier Downgrade = DEE_Common.id("container/slot/downgrade_gen");
    float time = 0.0f;

    public HexingTableScreen(HexingTableScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void drawBackground(@NotNull DrawContext context, float delta, int mouseX, int mouseY) {
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;

        time += 0.05f;
        if(time >= 360f){
            time = 0.0f;
        }

        context.drawTexture(RenderPipelines.GUI_TEXTURED, MainTexture,
                i, j, 0, 0,
                backgroundWidth, backgroundHeight,
                256, 256);

        int tempF = this.handler.getFuel();
        int F = MathHelper.clamp(tempF, 0, 31);
        if(F > 0){
            for (int k1 = 0; k1 < F; k1++) {
                context.drawHorizontalLine(
                        MathHelper.floor(MathHelper.sin((time + 2*k1)/(2*MathHelper.PI)) * 5)+i+15,
                        MathHelper.floor(MathHelper.sin((time + 2*k1)/(2*MathHelper.PI)) * 5)+i+19,
                        47-k1+j, Colors.PURPLE
                );
            }for (int k2 = 0; k2 < F; k2++) {
                context.drawHorizontalLine(
                        MathHelper.floor(MathHelper.sin((time + 2*k2)/(2*MathHelper.PI)) * 5)+i+19,
                        MathHelper.floor(MathHelper.sin((time + 2*k2)/(2*MathHelper.PI)) * 5)+i+20,
                        47-k2+j, Colors.LIGHT_PINK
                );
            }
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        DrawExtraSlots(context, mouseX, mouseY, delta);
        drawMouseoverTooltip(context,mouseX,mouseY);
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
        for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 6; x++) {
                double d = mouseX - (double) (i + x * 16);
                double e = mouseY - (double) (j + y * 16);
                int n = y * 6 + x;
                if (d >= 0.0 && e >= 0.0 && d < 16 && e < 16 && this.handler.onButtonClick(Objects.requireNonNull(this.client).player, n)) {
                    Objects.requireNonNull(this.client.interactionManager).clickButton(this.handler.syncId, n);
                    return true;
                }
            }
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void DrawExtraSlots(DrawContext context,int MouseX, int MouseY, float delta) {
        int i = this.x + 62;
        int j = this.y + 12;
        if (!this.handler.getSlot(0).getStack().isEmpty() && !this.handler.getSlot(1).getStack().isEmpty() && !this.handler.getSlot(2).getStack().isEmpty()) {
            if (this.handler.getEnchants() != null) {
                for (int y = 0; y < 3; y++) {
                    for (int x = 0; x < 6; x++) {
                        double d = MouseX - (double) (i + x * 16);
                        double e = MouseY - (double) (j + y * 16);
                        int n = y * 6 + x;
                        int colour = Colors.DARK_GRAY;

                        if (d >= 0.0 && e >= 0.0 && d < 16 && e < 16 && n < this.handler.getEnchants().size()) {
                            colour = Colors.LIGHT_YELLOW;
                        }
                        if(this.handler.getFuel() <= 0){
                            colour = Colors.RED;
                        }

                        if (this.handler.getSlot(1).getStack().isOf(Items.LAPIS_LAZULI)) {
                            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, Upgrade, 16, 16, 0, 0, (i + x * 16), (j + y * 16), 16, 16, colour);
                        } else {
                            context.drawGuiTexture(RenderPipelines.GUI_TEXTURED, Downgrade, 16, 16, 0, 0, (i + x * 16), (j + y * 16), 16, 16, colour);
                        }
                    }
                }
                for (int Y = 0; Y < 3; Y++) {
                    for (int X = 0; X < 6; X++) {
                        if (this.handler.getEnchants() != null) {
                            double d = MouseX - (double) (i + X * 16);
                            double e = MouseY - (double) (j + Y * 16);
                            int n = Y * 6 + X;
                            if (d >= 0.0 && e >= 0.0 && d < 16 && e < 16 && n < this.handler.getEnchants().size()) {
                                var name = this.handler.getEnchants().get(n);
                                var NewName = name.value().toString().replaceAll("Enchantment","");
                                context.drawText(textRenderer, Text.translatable(NewName), MouseX, MouseY, Colors.ALTERNATE_WHITE, true);
                            }
                        }
                    }
                }
            }
        }
    }
}
