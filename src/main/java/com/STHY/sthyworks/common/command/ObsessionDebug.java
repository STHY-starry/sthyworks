package com.STHY.sthyworks.common.command;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;

import com.STHY.sthyworks.common.util.ObsessionManager;

public class ObsessionDebug extends CommandBase {

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public String getCommandName() {
        return "obsessiondebug";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "command.obsessiondebug.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            throw new WrongUsageException(getCommandUsage(sender));
        }

        String subCommand = args[0].toLowerCase();
        switch (subCommand) {
            case "get":
                handleGet(sender, args);
                break;
            case "add":
                handleAdd(sender, args);
                break;
            case "update":
                handleUpdate(sender, args);
                break;
            default:
                throw new WrongUsageException(getCommandUsage(sender));
        }
    }

    private void handleGet(ICommandSender sender, String[] args) {
        EntityPlayerMP target;
        if (args.length == 1) {
            target = getCommandSenderAsPlayer(sender);
        } else if (args.length == 2) {
            target = getPlayer(sender, args[1]);
        } else {
            throw new WrongUsageException(getCommandUsage(sender));
        }
        int obsession = ObsessionManager.getPlayerObsession(target);
        sender.addChatMessage(
            new ChatComponentTranslation("command.obsessiondebug.get", target.getCommandSenderName(), obsession));
    }

    private void handleAdd(ICommandSender sender, String[] args) {
        EntityPlayerMP target;
        int amount;
        if (args.length == 2) {
            target = getCommandSenderAsPlayer(sender);
            amount = parseInt(sender, args[1]);
        } else if (args.length == 3) {
            target = getPlayer(sender, args[1]);
            amount = parseInt(sender, args[2]);
        } else {
            throw new WrongUsageException(getCommandUsage(sender));
        }
        ObsessionManager.addPlayerObsession(target, amount);
        sender.addChatMessage(
            new ChatComponentTranslation("command.obsessiondebug.add", target.getCommandSenderName(), amount));
    }

    private void handleUpdate(ICommandSender sender, String[] args) {
        EntityPlayerMP target;
        if (args.length == 1) {
            target = getCommandSenderAsPlayer(sender);
        } else if (args.length == 2) {
            target = getPlayer(sender, args[1]);
        } else {
            throw new WrongUsageException(getCommandUsage(sender));
        }
        ObsessionManager.syncObsessionToPlayer(target);
        sender.addChatMessage(
            new ChatComponentTranslation("command.obsessiondebug.update", target.getCommandSenderName()));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, "get", "add", "update");
        }
        if (args.length == 2 && ("get".equalsIgnoreCase(args[0]) || "update".equalsIgnoreCase(args[0]))) {
            return getListOfStringsMatchingLastWord(
                args,
                MinecraftServer.getServer()
                    .getAllUsernames());
        }
        return null;
    }
}
