package com.STHY.sthyworks.common.item;

// import com.STHY.sthyworks.common.block.BlockLoader;
// import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;
// import com.STHY.sthyworks.common.entity.AdorableGugu;
// import com.STHY.sthyworks.common.potion.PotionLoader;
// import net.minecraft.entity.Entity;
// import net.minecraft.entity.item.EntityFireworkRocket;
// import net.minecraft.entity.item.EntityXPOrb;
// import net.minecraft.entity.passive.EntityPig;
// import net.minecraft.entity.player.EntityPlayer;
// import net.minecraft.init.Blocks;
// import net.minecraft.init.Items;
// import net.minecraft.item.Item;
// import net.minecraft.item.ItemStack;
// import net.minecraft.nbt.NBTTagCompound;
// import net.minecraft.potion.Potion;
// import net.minecraft.potion.PotionEffect;
// import net.minecraft.util.ChatComponentTranslation;
// import net.minecraft.util.StatCollector;
// import net.minecraft.world.World;
// import net.minecraft.world.biome.BiomeGenBase;
// import thaumcraft.api.ThaumcraftApi;
// import thaumcraft.common.Thaumcraft;
// import thaumcraft.common.config.ConfigItems;
// import thaumcraft.common.lib.potions.PotionWarpWard;
//
// import java.util.List;
// import java.util.Random;
//
// public class Gift extends Item {
// public Gift() {
// setUnlocalizedName("gift");
// setTextureName("sthyworks:gift");
// setCreativeTab(CreativeTabsLoader.tabsthyworks);
// this.setMaxStackSize(1);
// }
//
// @Override
// public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List<String> list, boolean par4) {
// list.add(StatCollector.translateToLocal("item.gift.tooltips.line1"));
// list.add(StatCollector.translateToLocal("item.gift.tooltips.line2"));
// }
//
// @Override
// public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int slotIndex, boolean isHeld) {
// if (worldIn.isRemote) return;
// if (!(entityIn instanceof EntityPlayer)) return;
// if (worldIn.rand.nextInt(6400) == 0) {
// triggerEffect(stack, worldIn, (EntityPlayer) entityIn, slotIndex, isHeld);
// }
// }
//
// public void triggerEffect(ItemStack stack, World worldIn, EntityPlayer player, int slotIndex, boolean isHeld) {
// Random rand = worldIn.rand;
// int intensity = rand.nextInt(10) + 1;
// switch (rand.nextInt(37)) {
// case 0:
// player.heal(1.25F * intensity);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.0"));
// break;
// case 1:
// player.getFoodStats().addStats(6 + intensity / 2, 0.2F);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.1"));
// break;
// case 2:
// player.addPotionEffect(new PotionEffect(Potion.jump.getId(), 30 * intensity, 0));
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.2"));
// break;
// case 3:
// player.addPotionEffect(new PotionEffect(Potion.moveSpeed.getId(), 30 * intensity, 0));
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.3"));
// break;
// case 4:
// player.addExperience(11 * intensity);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.4"));
// break;
// case 5:
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.5"));
// break;
// case 6:
// player.dropItem(ItemLoader.milkTea, 1);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.6"));
// break;
// case 7:
// player.dropItem(Items.cake, 1);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.7"));
// break;
// case 8:
// player.addPotionEffect(new PotionEffect(Potion.digSpeed.getId(), 30 * intensity, 0));
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.8"));
// break;
// case 9:
// ItemStack itemStack9 = player.getHeldItem();
// if (itemStack9.isItemStackDamageable()) {
// itemStack9.setItemDamage(itemStack9.getItemDamage() - Math.max(1, (int) (itemStack9.getMaxDamage() * 0.005F *
// intensity)));
// } else {
// player.addPotionEffect(new PotionEffect(Potion.digSpeed.getId(), 15 * intensity, 0));
// player.addPotionEffect(new PotionEffect(Potion.damageBoost.getId(), 15 * intensity, 0));
// }
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.9"));
// break;
// case 10:
// player.heal(0.2F * intensity);
// player.addExperience(3 * intensity);
// if (isHeld) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.10_isHeld"));
// } else {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.10_notHeld"));
// }
// break;
// case 11:
// player.addPotionEffect(new PotionEffect(PotionWarpWard.instance.getId(), 25 * intensity, 0));
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.11"));
// break;
// case 12:
// if (rand.nextInt(2333) == 0) {
// player.entityDropItem(new ItemStack(ConfigItems.itemEldritchObject, 1, 3), 0.0F);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.12_pearl"));
// } else {
// player.entityDropItem(new ItemStack(ConfigItems.itemShard, 1, rand.nextInt(6)), 0.0F);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.12_shard"));
// }
// break;
// case 13:
// player.addPotionEffect(new PotionEffect(PotionLoader.soulAnnihilation.getId(), 15 * intensity, 0));
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.13"));
// break;
// case 14:
// player.entityDropItem(new ItemStack(Blocks.red_flower), 0.0F);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.14"));
// break;
// case 15:
// player.heal(0.5F * intensity);
// player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 15 * intensity, 0));
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.15"));
// break;
// case 16:
// if (rand.nextInt(3) == 0) {
// player.entityDropItem(new ItemStack(Items.gold_ingot), 0.0F);
// } else {
// player.entityDropItem(new ItemStack(Items.gold_nugget), 0.0F);
// }
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.16"));
// break;
// case 17:
// player.entityDropItem(new ItemStack(ConfigItems.itemResource, 1, 9), 0.0F);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.17"));
// break;
// case 18:
// if (rand.nextInt(6) == 0) {
// player.entityDropItem(new ItemStack(Items.diamond), 0.0F);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.18_diamond"));
// } else {
// player.entityDropItem(new ItemStack(Items.coal), 0.0F);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.18_coal"));
// }
// break;
// case 19:
// EntityXPOrb xpOrb = new EntityXPOrb(worldIn);
// xpOrb.setPosition(player.posX, player.posY, player.posZ);
// xpOrb.xpValue = 2 * intensity;
// for (int i = 0; i < rand.nextInt(8) + 1; i++) {
// worldIn.spawnEntityInWorld(xpOrb);
// }
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.19"));
// break;
// case 20:
// EntityFireworkRocket fireworkRocket = new EntityFireworkRocket(worldIn);
// worldIn.spawnEntityInWorld(fireworkRocket);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.20"));
// case 21:
// ItemStack itemStack21 = new ItemStack(BlockLoader.guguBlock);
// itemStack21.setTagCompound(new NBTTagCompound());
// itemStack21.getTagCompound().setString("displayname", new
// ChatComponentTranslation("item.Gift.message.21_name").toString());
// player.entityDropItem(itemStack21, 0.0F);
// player.addChatComponentMessage(new ChatComponentTranslation("item.Gift.message.21"));
// break;
// case 22:
// player.addPotionEffect(new PotionEffect(Potion.nightVision.getId(), 30 * intensity, 0));
// player.addChatComponentMessage(new ChatComponentTranslation("item.Gift.message.22"));
// break;
// case 23:
// EntityPig pig23 = new EntityPig(worldIn);
// pig23.setPosition(player.posX, player.posY, player.posZ);
// pig23.setCustomNameTag(new ChatComponentTranslation("item.Gift.message.23_name").toString());
// worldIn.spawnEntityInWorld(pig23);
// player.addChatComponentMessage(new ChatComponentTranslation("item.Gift.message.23"));
// break;
// case 24:
// AdorableGugu gugu = new AdorableGugu(worldIn);
// gugu.setPosition(player.posX, player.posY, player.posZ);
// worldIn.spawnEntityInWorld(gugu);
// gugu.mountEntity(player);
// player.addChatComponentMessage(new ChatComponentTranslation("item.Gift.message.24"));
// break;
// case 25:
// int time = (int) worldIn.getWorldTime();
// if (time < 4283) {
// player.addChatComponentMessage(new ChatComponentTranslation("item.Gift.message.25_morning"));
// } else if (time < 8000) {
// player.addChatComponentMessage(new ChatComponentTranslation("item.Gift.message.25_noon"));
// } else if (time < 12610) {
// player.addChatComponentMessage(new ChatComponentTranslation("item.Gift.message.25_afternoon"));
// } else if (time < 13800) {
// player.addChatComponentMessage(new ChatComponentTranslation("item.Gift.message.25_evening"));
// } else {
// player.addChatComponentMessage(new ChatComponentTranslation("item.Gift.message.25_night"));
// }
// break;
// case 26:
// if (worldIn.isRaining()) {
// if (worldIn.isThundering()) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.26_thunder"));
// }
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.26_rain"));
// } else {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.26_sunny"));
// }
// break;
// case 27:
// BiomeGenBase biome = worldIn.getBiomeGenForCoords((int) player.posX, (int) player.posZ);
// if (biome.isEqualTo(BiomeGenBase.plains)) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.27_plains"));
// } else if (biome.isEqualTo(BiomeGenBase.forest)) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.27_forest"));
// } else if (biome.isEqualTo(BiomeGenBase.ocean)) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.27_ocean"));
// } else {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.27_default"));
// }
// break;
// case 28:
// if (player.isSprinting()) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.28_sprint"));
// } else {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.28_notSprint"));
// }
// break;
// case 29:
// if (player.isUsingItem()) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.29_isUsing"));
// } else {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.29_notUsing"));
// }
// break;
// case 30:
// if (player.hurtResistantTime != 0) {
// player.addPotionEffect(new PotionEffect(Potion.resistance.getId(), 15 + intensity, 4));
// player.addChatComponentMessage(new ChatComponentTranslation("item.Gift.message.30_receiveDamage"));
// } else {
// player.addChatComponentMessage(new ChatComponentTranslation("item.Gift.message.30_notReceiveDamage"));
// }
// break;
// case 31:
// if (rand.nextInt(5) == 0) {
// player.entityDropItem(new ItemStack(ItemLoader.superPork), 0.0F);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.31_superPork"));
// } else if (!player.inventory.hasItem(Items.carrot)) {
// player.entityDropItem(new ItemStack(Items.carrot), 0.0F);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.31_carrot"));
// } else if (!player.inventory.hasItem(Items.potato)) {
// player.entityDropItem(new ItemStack(Items.potato), 0.0F);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.31_potato"));
// }else {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.31_nothing"));
// }
// break;
// case 32:
// float maxHealth = player.getMaxHealth();
// if (maxHealth > 200) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.32_maxHealth1"));
// } else if (maxHealth > 100) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.32_maxHealth2"));
// } else if (maxHealth > 50) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.32_maxHealth3"));
// } else if (maxHealth > 25) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.32_maxHealth4"));
// } else if (maxHealth > 12.5F) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.32_maxHealth5"));
// } else {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.32_maxHealth6"));
// }
// break;
// case 33:
// //测运气
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.33_1"));
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.33_2", (50 * intensity + 400) / 9));
// break;
// case 34:
// if (player.isPotionActive(PotionLoader.deadlyPoison)) {
// player.removePotionEffect(PotionLoader.deadlyPoison.getId());
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.34_deadlyPoison"));
// } else if (player.isPotionActive(Potion.poison)) {
// player.removePotionEffect(Potion.poison.getId());
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.34_poison"));
// } else if (player.isPotionActive(Potion.wither)) {
// player.removePotionEffect(Potion.wither.getId());
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.34_wither"));
// } else {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.34"));
// }
// break;
// case 35:
// int warp = Thaumcraft.proxy.getPlayerKnowledge().getWarpTotal(player.getCommandSenderName());
// if (warp >= 200) {
// player.entityDropItem(new ItemStack(ConfigItems.itemBathSalts, 4), 0.0F);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.35_warp200"));
// } else if (warp >= 100) {
// player.entityDropItem(new ItemStack(ConfigItems.itemBathSalts, 1), 0.0F);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.35_warp100"));
// } else if (warp >= 50) {
// player.entityDropItem(new ItemStack(ConfigItems.itemResearchNotes, 1, 24), 0.0F);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.35_warp50"));
// } else {
// player.entityDropItem(new ItemStack(ConfigItems.itemResearchNotes, 1, 24), 0.0F);
// player.entityDropItem(new ItemStack(ConfigItems.itemResource, 1, 14), 0.0F);
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.35"));
// }
// break;
// case 36:
// if (player.getHeldItem() == null || player.getHeldItem().getItem() == null) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.36"));
// break;
// }
// Item item36 = player.getHeldItem().getItem();
// if (item36 == ItemLoader.gift) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.36_gift"));
// } else if (item36 == ItemLoader.demonThorn) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.36_demonThorn"));
// } else if (item36 == ItemLoader.immortalSword) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.36_immortalSword"));
// } else if (item36 == ItemLoader.starrySky) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.36_starrySky"));
// } else if (item36 == ItemLoader.milkTea) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.36_milkTea"));
// } else if (item36 == ItemLoader.angelEtherealWing || item36 == ItemLoader.devilEtherealWing) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.36_etherealWing"));
// } else if (item36 == ItemLoader.pathogenesis) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.36_pathogenesis"));
// } else if (item36 == ItemLoader.sacredBlade) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.36_sacredBlade"));
// } else if (item36 == ItemLoader.instructionManual) {
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.36_instructionManual"));
// }
// break;
// default:
// player.addChatMessage(new ChatComponentTranslation("item.Gift.message.default"));
// break;
// }
// }
// }
