// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            Block, Material, World, EntitySilverfish, 
//            EntityPlayer

public class BlockSilverfish extends Block
{

    public BlockSilverfish(int i)
    {
        super(i, 1, Material.clay);
        setHardness(0.0F);
    }

    public void harvestBlock(World world, EntityPlayer entityplayer, int i, int j, int k, int l)
    {
        super.harvestBlock(world, entityplayer, i, j, k, l);
    }

    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        if(j == 1)
        {
            return Block.cobblestone.blockIndexInTexture;
        }
        if(j == 2)
        {
            return Block.field_35285_bn.blockIndexInTexture;
        } else
        {
            return Block.stone.blockIndexInTexture;
        }
    }

    public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l)
    {
        if(!world.multiplayerWorld)
        {
            EntitySilverfish entitysilverfish = new EntitySilverfish(world);
            entitysilverfish.setLocationAndAngles((double)i + 0.5D, j, (double)k + 0.5D, 0.0F, 0.0F);
            world.entityJoinedWorld(entitysilverfish);
            entitysilverfish.spawnExplosionParticle();
        }
        super.onBlockDestroyedByPlayer(world, i, j, k, l);
    }

    public int quantityDropped(Random random)
    {
        return 0;
    }

    public static boolean func_35305_d(int i)
    {
        return i == Block.stone.blockID || i == Block.cobblestone.blockID || i == Block.field_35285_bn.blockID;
    }

    public static int func_35304_f(int i)
    {
        if(i == Block.cobblestone.blockID)
        {
            return 1;
        }
        return i != Block.field_35285_bn.blockID ? 0 : 2;
    }
}
