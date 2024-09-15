// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.src;

import java.util.Random;

// Referenced classes of package net.minecraft.src:
//            EntityMob, DamageSource, EntityArrow, EntityPlayer, 
//            AchievementList, World, MathHelper, Entity, 
//            Item, ItemStack, NBTTagCompound

public class EntitySkeleton extends EntityMob
{

    public EntitySkeleton(World world)
    {
        super(world);
        texture = "/mob/skeleton.png";
    }

    protected String getLivingSound()
    {
        return "mob.skeleton";
    }

    protected String getHurtSound()
    {
        return "mob.skeletonhurt";
    }

    protected String getDeathSound()
    {
        return "mob.skeletonhurt";
    }

    public boolean attackEntityFrom(DamageSource damagesource, int i)
    {
        return super.attackEntityFrom(damagesource, i);
    }

    public void onDeath(DamageSource damagesource)
    {
        super.onDeath(damagesource);
        if((damagesource.func_35526_e() instanceof EntityArrow) && (damagesource.func_35532_a() instanceof EntityPlayer))
        {
            EntityPlayer entityplayer = (EntityPlayer)damagesource.func_35532_a();
            double d = entityplayer.posX - posX;
            double d1 = entityplayer.posZ - posZ;
            if(d * d + d1 * d1 >= 2500D)
            {
                entityplayer.triggerAchievement(AchievementList.field_35608_v);
            }
        }
    }

    public void onLivingUpdate()
    {
        if(worldObj.isDaytime() && !worldObj.multiplayerWorld)
        {
            float f = getEntityBrightness(1.0F);
            if(f > 0.5F && worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)) && rand.nextFloat() * 30F < (f - 0.4F) * 2.0F)
            {
                fire = 300;
            }
        }
        super.onLivingUpdate();
    }

    protected void attackEntity(Entity entity, float f)
    {
        if(f < 10F)
        {
            double d = entity.posX - posX;
            double d1 = entity.posZ - posZ;
            if(attackTime == 0)
            {
                EntityArrow entityarrow = new EntityArrow(worldObj, this, 1.0F);
                double d2 = (entity.posY + (double)entity.getEyeHeight()) - 0.69999998807907104D - entityarrow.posY;
                float f1 = MathHelper.sqrt_double(d * d + d1 * d1) * 0.2F;
                worldObj.playSoundAtEntity(this, "random.bow", 1.0F, 1.0F / (rand.nextFloat() * 0.4F + 0.8F));
                worldObj.entityJoinedWorld(entityarrow);
                entityarrow.setArrowHeading(d, d2 + (double)f1, d1, 1.6F, 12F);
                attackTime = 60;
            }
            rotationYaw = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - 90F;
            hasAttacked = true;
        }
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
    }

    protected int getDropItemId()
    {
        return Item.arrow.shiftedIndex;
    }

    protected void a(boolean flag)
    {
        int i = rand.nextInt(3);
        for(int j = 0; j < i; j++)
        {
            dropItem(Item.arrow.shiftedIndex, 1);
        }

        i = rand.nextInt(3);
        for(int k = 0; k < i; k++)
        {
            dropItem(Item.bone.shiftedIndex, 1);
        }

    }

    public ItemStack getHeldItem()
    {
        return defaultHeldItem;
    }

    private static final ItemStack defaultHeldItem;

    static 
    {
        defaultHeldItem = new ItemStack(Item.bow, 1);
    }
}
