// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.List;
import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            BiomeGenBase, SpawnListEntry, EntityWolf, BiomeDecorator, 
//            WorldGenTaiga1, WorldGenTaiga2, WorldGenerator

public class BiomeGenTaiga extends BiomeGenBase
{

    public BiomeGenTaiga(int i)
    {
        super(i);
        spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 4));
        field_35488_u.field_35911_r = 10;
        field_35488_u.field_35909_t = 1;
    }

    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        if(random.nextInt(3) == 0)
        {
            return new WorldGenTaiga1();
        } else
        {
            return new WorldGenTaiga2();
        }
    }
}
