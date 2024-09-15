// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.List;
import net.minecraft.client.Minecraft;

// Referenced classes of package net.minecraft.src:
//            PlayerController, EntityPlayer, PlayerCapabilities, InventoryPlayer, 
//            ItemStack, Session, Block, World

public class PlayerControllerTest extends PlayerController
{

    public PlayerControllerTest(Minecraft minecraft)
    {
        super(minecraft);
        isInTestMode = true;
    }

    public static void func_35646_d(EntityPlayer entityplayer)
    {
        entityplayer.field_35212_aW.field_35758_c = true;
        entityplayer.field_35212_aW.field_35756_d = true;
        entityplayer.field_35212_aW.field_35759_a = true;
    }

    public static void func_35645_e(EntityPlayer entityplayer)
    {
        entityplayer.field_35212_aW.field_35758_c = false;
        entityplayer.field_35212_aW.field_35757_b = false;
        entityplayer.field_35212_aW.field_35756_d = false;
        entityplayer.field_35212_aW.field_35759_a = false;
    }

    public void func_6473_b(EntityPlayer entityplayer)
    {
        func_35646_d(entityplayer);
        for(int i = 0; i < 9; i++)
        {
            if(entityplayer.inventory.mainInventory[i] == null)
            {
                entityplayer.inventory.mainInventory[i] = new ItemStack((Block)Session.registeredBlocksList.get(i));
            }
        }

    }

    public static void func_35644_a(Minecraft minecraft, PlayerController playercontroller, int i, int j, int k, int l)
    {
        minecraft.theWorld.onBlockHit(minecraft.thePlayer, i, j, k, l);
        playercontroller.sendBlockRemoved(i, j, k, l);
    }

    public boolean sendPlaceBlock(EntityPlayer entityplayer, World world, ItemStack itemstack, int i, int j, int k, int l)
    {
        int i1 = world.getBlockId(i, j, k);
        if(i1 > 0 && Block.blocksList[i1].blockActivated(world, i, j, k, entityplayer))
        {
            return true;
        }
        if(itemstack == null)
        {
            return false;
        } else
        {
            int j1 = itemstack.getItemDamage();
            int k1 = itemstack.stackSize;
            boolean flag = itemstack.useItem(entityplayer, world, i, j, k, l);
            itemstack.setItemDamage(j1);
            itemstack.stackSize = k1;
            return flag;
        }
    }

    public void clickBlock(int i, int j, int k, int l)
    {
        func_35644_a(mc, this, i, j, k, l);
        field_35647_c = 5;
    }

    public void sendBlockRemoving(int i, int j, int k, int l)
    {
        field_35647_c--;
        if(field_35647_c <= 0)
        {
            field_35647_c = 5;
            func_35644_a(mc, this, i, j, k, l);
        }
    }

    public void resetBlockRemoving()
    {
    }

    public boolean shouldDrawHUD()
    {
        return false;
    }

    public void func_717_a(World world)
    {
        super.func_717_a(world);
    }

    public float getBlockReachDistance()
    {
        return 5F;
    }

    public boolean func_35641_g()
    {
        return false;
    }

    public boolean func_35640_h()
    {
        return true;
    }

    public boolean func_35636_i()
    {
        return true;
    }

    private int field_35647_c;
}
