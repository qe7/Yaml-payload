// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import org.lwjgl.opengl.GL11;

// Referenced classes of package net.minecraft.src:
//            Render, RenderBlocks, EntityXPOrb, Tessellator, 
//            MathHelper, RenderManager, Entity

public class RenderXPOrb extends Render
{

    public RenderXPOrb()
    {
        field_35439_b = new RenderBlocks();
        field_35440_a = true;
        shadowSize = 0.15F;
        field_194_c = 0.75F;
    }

    public void func_35438_a(EntityXPOrb entityxporb, double d, double d1, double d2, 
            float f, float f1)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
        int i = entityxporb.func_35120_i();
        loadTexture("/item/xporb.png");
        Tessellator tessellator = Tessellator.instance;
        float f2 = (float)((i % 4) * 16 + 0) / 64F;
        float f3 = (float)((i % 4) * 16 + 16) / 64F;
        float f4 = (float)((i / 4) * 16 + 0) / 64F;
        float f5 = (float)((i / 4) * 16 + 16) / 64F;
        float f6 = 1.0F;
        float f7 = 0.5F;
        float f8 = 0.25F;
        float f9 = entityxporb.getEntityBrightness(f1) * 255F;
        float f10 = ((float)entityxporb.field_35127_a + f1) / 2.0F;
        int j = (int)((MathHelper.sin(f10 + 0.0F) + 1.0F) * 0.5F * f9);
        int k = (int)f9;
        int l = (int)((MathHelper.sin(f10 + 4.18879F) + 1.0F) * 0.1F * f9);
        int i1 = j << 16 | k << 8 | l;
        GL11.glRotatef(180F - renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
        float f11 = 0.3F;
        GL11.glScalef(f11, f11, f11);
        tessellator.startDrawingQuads();
        tessellator.setColorRGBA_I(i1, 128);
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(0.0F - f7, 0.0F - f8, 0.0D, f2, f5);
        tessellator.addVertexWithUV(f6 - f7, 0.0F - f8, 0.0D, f3, f5);
        tessellator.addVertexWithUV(f6 - f7, 1.0F - f8, 0.0D, f3, f4);
        tessellator.addVertexWithUV(0.0F - f7, 1.0F - f8, 0.0D, f2, f4);
        tessellator.draw();
        GL11.glDisable(3042 /*GL_BLEND*/);
        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        GL11.glPopMatrix();
    }

    public void doRender(Entity entity, double d, double d1, double d2, 
            float f, float f1)
    {
        func_35438_a((EntityXPOrb)entity, d, d1, d2, f, f1);
    }

    private RenderBlocks field_35439_b;
    public boolean field_35440_a;
}
