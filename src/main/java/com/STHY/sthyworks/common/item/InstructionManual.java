package com.STHY.sthyworks.common.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.creativetab.CreativeTabsLoader;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class InstructionManual extends ItemEditableBook {

    private final String bookTitle = StatCollector.translateToLocal("book.instructionManual.title");
    private final String bookAuthor = "STHY";
    private final String[] bookPages = { buildPage(1), buildPage(2), buildPage(3), buildPage(4), buildPage(5), };

    public InstructionManual() {
        super();
        this.setUnlocalizedName("instructionManual");
        this.setTextureName("sthyworks:InstructionManual");
        this.setCreativeTab(CreativeTabsLoader.tabsthyworks);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer player) {
        ItemStack writtenBook = new ItemStack(Items.written_book);
        initializeBook(writtenBook);
        player.displayGUIBook(writtenBook);
        return itemStackIn;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        if (stack.hasTagCompound()) {
            String author = stack.getTagCompound()
                .getString("author");
            if (!StringUtils.isNullOrEmpty(author)) {
                tooltip.add(EnumChatFormatting.GRAY + StatCollector.translateToLocalFormatted("book.byAuthor", author));
            }
        }
    }

    private void initializeBook(ItemStack itemStackIn) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString("title", bookTitle);
        nbtTagCompound.setString("author", bookAuthor);

        NBTTagList pages = new NBTTagList();
        for (String page : bookPages) {
            pages.appendTag(new NBTTagString(page));
        }

        nbtTagCompound.setTag("pages", pages);

        itemStackIn.setTagCompound(nbtTagCompound);
    }

    private String buildPage(int pageNumber) {
        StringBuilder page = new StringBuilder();
        int lineNumber = 1;
        while (true) {
            String key = "book.instructionManual.page" + pageNumber + ".line" + lineNumber;
            String line = StatCollector.translateToLocal(key);
            if (key.equals(line)) {
                break;
            }
            if (lineNumber > 1) {
                page.append("\n");
            }
            page.append(line);
            lineNumber++;
        }
        return page.toString();
    }
}
