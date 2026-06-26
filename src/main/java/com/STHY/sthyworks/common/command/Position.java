package com.STHY.sthyworks.common.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.Vec3;

public class Position extends CommandBase {

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getCommandName() {
        return "position";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "commands.position.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 1) {
            throw new WrongUsageException("commands.position.usage");
        } else {
            EntityPlayerMP entityPlayerMP = args.length == 0 ? getCommandSenderAsPlayer(sender)
                : getPlayer(sender, args[0]);
            Vec3 pos = entityPlayerMP.getPosition(1.0F);
            sender.addChatMessage(
                new ChatComponentTranslation(
                    "command.position.success",
                    entityPlayerMP.getDisplayName(),
                    pos,
                    entityPlayerMP.worldObj.provider.getDimensionName()));
        }
    }
}
