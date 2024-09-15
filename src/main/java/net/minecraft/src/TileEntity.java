// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package net.minecraft.src:
//            NBTTagCompound, World, Block, TileEntityFurnace, 
//            TileEntityChest, TileEntityRecordPlayer, TileEntityDispenser, TileEntitySign, 
//            TileEntityMobSpawner, TileEntityNote, TileEntityPiston

public class TileEntity
{

    public TileEntity()
    {
        field_35145_n = -1;
    }

    private static void addMapping(Class class1, String s)
    {
        if(classToNameMap.containsKey(s))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Duplicate id: ").append(s).toString());
        } else
        {
            nameToClassMap.put(s, class1);
            classToNameMap.put(class1, s);
            return;
        }
    }

    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        xCoord = nbttagcompound.getInteger("x");
        yCoord = nbttagcompound.getInteger("y");
        zCoord = nbttagcompound.getInteger("z");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        String s = (String)classToNameMap.get(getClass());
        if(s == null)
        {
            throw new RuntimeException((new StringBuilder()).append(getClass()).append(" is missing a mapping! This is a bug!").toString());
        } else
        {
            nbttagcompound.setString("id", s);
            nbttagcompound.setInteger("x", xCoord);
            nbttagcompound.setInteger("y", yCoord);
            nbttagcompound.setInteger("z", zCoord);
            return;
        }
    }

    public void updateEntity()
    {
    }

    public static TileEntity createAndLoadEntity(NBTTagCompound nbttagcompound)
    {
        TileEntity tileentity = null;
        try
        {
            Class class1 = (Class)nameToClassMap.get(nbttagcompound.getString("id"));
            if(class1 != null)
            {
                tileentity = (TileEntity)class1.newInstance();
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        if(tileentity != null)
        {
            tileentity.readFromNBT(nbttagcompound);
        } else
        {
            System.out.println((new StringBuilder()).append("Skipping TileEntity with id ").append(nbttagcompound.getString("id")).toString());
        }
        return tileentity;
    }

    public int getBlockMetadata()
    {
        if(field_35145_n == -1)
        {
            field_35145_n = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        }
        return field_35145_n;
    }

    public void l()
    {
        if(worldObj != null)
        {
            field_35145_n = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
            worldObj.updateTileEntityChunkAndDoNothing(xCoord, yCoord, zCoord, this);
        }
    }

    public double getDistanceFrom(double d, double d1, double d2)
    {
        double d3 = ((double)xCoord + 0.5D) - d;
        double d4 = ((double)yCoord + 0.5D) - d1;
        double d5 = ((double)zCoord + 0.5D) - d2;
        return d3 * d3 + d4 * d4 + d5 * d5;
    }

    public Block getBlockType()
    {
        if(field_35146_o == null)
        {
            field_35146_o = Block.blocksList[worldObj.getBlockId(xCoord, yCoord, zCoord)];
        }
        return field_35146_o;
    }

    public boolean isInvalid()
    {
        return tileEntityInvalid;
    }

    public void invalidate()
    {
        tileEntityInvalid = true;
    }

    public void validate()
    {
        tileEntityInvalid = false;
    }

    public void func_35143_b(int i, int j)
    {
    }

    public void func_35144_b()
    {
        field_35146_o = null;
        field_35145_n = -1;
    }

    static Class _mthclass$(String s)
    {
        try
        {
            return Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
    }

    private static Map nameToClassMap = new HashMap();
    private static Map classToNameMap = new HashMap();
    public World worldObj;
    public int xCoord;
    public int yCoord;
    public int zCoord;
    protected boolean tileEntityInvalid;
    public int field_35145_n;
    public Block field_35146_o;

    static 
    {
        addMapping(TileEntityFurnace.class, "Furnace");
        addMapping(TileEntityChest.class, "Chest");
        addMapping(TileEntityRecordPlayer.class, "RecordPlayer");
        addMapping(TileEntityDispenser.class, "Trap");
        addMapping(TileEntitySign.class, "Sign");
        addMapping(TileEntityMobSpawner.class, "MobSpawner");
        addMapping(TileEntityNote.class, "Music");
        addMapping(TileEntityPiston.class, "Piston");
    }
}
