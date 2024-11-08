// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;


// Referenced classes of package net.minecraft.src:
//            IInventory, ItemStack, Container, EntityPlayer

public class InventoryCrafting
    implements IInventory
{

    public InventoryCrafting(Container container, int i, int j)
    {
        int k = i * j;
        stackList = new ItemStack[k];
        eventHandler = container;
        inventoryWidth = i;
    }

    public int getSizeInventory()
    {
        return stackList.length;
    }

    public ItemStack getStackInSlot(int i)
    {
        if(i >= getSizeInventory())
        {
            return null;
        } else
        {
            return stackList[i];
        }
    }

    public ItemStack getStackInRowAndColumn(int i, int j)
    {
        if(i < 0 || i >= inventoryWidth)
        {
            return null;
        } else
        {
            int k = i + j * inventoryWidth;
            return getStackInSlot(k);
        }
    }

    public String getInvName()
    {
        return "Crafting";
    }

    public ItemStack decrStackSize(int i, int j)
    {
        if(stackList[i] != null)
        {
            if(stackList[i].stackSize <= j)
            {
                ItemStack itemstack = stackList[i];
                stackList[i] = null;
                eventHandler.onCraftMatrixChanged(this);
                return itemstack;
            }
            ItemStack itemstack1 = stackList[i].splitStack(j);
            if(stackList[i].stackSize == 0)
            {
                stackList[i] = null;
            }
            eventHandler.onCraftMatrixChanged(this);
            return itemstack1;
        } else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int i, ItemStack itemstack)
    {
        stackList[i] = itemstack;
        eventHandler.onCraftMatrixChanged(this);
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    public void l()
    {
    }

    public boolean canInteractWith(EntityPlayer entityplayer)
    {
        return true;
    }

    public void func_35142_x_()
    {
    }

    public void func_35141_y_()
    {
    }

    private ItemStack stackList[];
    private int inventoryWidth;
    private Container eventHandler;
}
