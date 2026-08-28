package com.STHY.sthyworks.common.tileentity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.STHY.sthyworks.common.block.BlockLoader;
import com.STHY.sthyworks.common.block.VoidGate;
import com.STHY.sthyworks.sthyworks;
import com.cleanroommc.modularui.api.IGuiHolder;
import com.cleanroommc.modularui.api.drawable.IKey;
import com.cleanroommc.modularui.factory.PosGuiData;
import com.cleanroommc.modularui.screen.ModularPanel;
import com.cleanroommc.modularui.screen.ModularScreen;
import com.cleanroommc.modularui.screen.UISettings;
import com.cleanroommc.modularui.utils.Alignment;
import com.cleanroommc.modularui.value.sync.BooleanSyncValue;
import com.cleanroommc.modularui.value.sync.IntSyncValue;
import com.cleanroommc.modularui.value.sync.InteractionSyncHandler;
import com.cleanroommc.modularui.value.sync.PanelSyncManager;
import com.cleanroommc.modularui.widgets.ButtonWidget;
import com.cleanroommc.modularui.widgets.layout.Flow;

public class TileEntityVoidGate extends TileEntity implements IGuiHolder<PosGuiData> {

    private static final int MAX_DISTANCE = 64;
    private static final int SPEED = 8;// 每t至多挖8个
    private static final int COMPLETION_SIGNAL_TICKS = 4;
    private int turnOnTime;
    private boolean isTurnedOn = false;
    private boolean isWorking = false;
    private boolean wasTopPowered = false;
    private int completionTimer = 0;
    private boolean xDirection = false;// false为x负方向（西），true为x正方向（东）
    private boolean yDirection = false;// false为y负方向（下），true为y正方向（上）
    private boolean zDirection = false;// false为z负方向（北），true为z正方向（南）
    private int xStart;
    private int yStart;
    private int zStart;
    private int xTarget;
    private int yTarget;
    private int zTarget;
    private int xProcess;
    private int yProcess;
    private int zProcess;

    public boolean getTurnedOn() {
        return this.isTurnedOn;
    }

    public boolean getWorking() {
        return this.isWorking;
    }

    public void setWorking(boolean working) {
        if (this.isWorking == working) return;
        this.isWorking = working;
        this.notifyRedstoneUpdate();
        this.markDirty();
    }

    public void setTurnedOn(boolean turnedOn) {
        if (this.isTurnedOn == turnedOn) return;
        this.isTurnedOn = turnedOn;
        if (!turnedOn) {
            this.setWorking(false);
            if (this.completionTimer != 0) {
                this.completionTimer = 0;
                this.notifyRedstoneUpdate();
            }
        }
        this.turnOnTime = 0;
        this.markDirty();
    }

    public int getCompletionTimer() {
        return this.completionTimer;
    }

    private void notifyRedstoneUpdate() {
        worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, getBlockType());
    }

    @Override
    public ModularScreen createScreen(PosGuiData data, ModularPanel mainPanel) {
        return new ModularScreen(sthyworks.MODID, mainPanel);
    }

    @Override
    public ModularPanel buildUI(PosGuiData guiData, PanelSyncManager syncManager, UISettings settings) {

        BooleanSyncValue isTurnedOnValue = new BooleanSyncValue(
            () -> this.isTurnedOn,
            value -> this.isTurnedOn = value);
        syncManager.syncValue("isTurnedOn", isTurnedOnValue);
        BooleanSyncValue isWorkingValue = new BooleanSyncValue(() -> this.isWorking, value -> this.isWorking = value);
        syncManager.syncValue("isWorking", isWorkingValue);
        BooleanSyncValue xDirectionValue = new BooleanSyncValue(
            () -> this.xDirection,
            value -> this.xDirection = value);
        syncManager.syncValue("xDirection", xDirectionValue);
        BooleanSyncValue yDirectionValue = new BooleanSyncValue(
            () -> this.yDirection,
            value -> this.yDirection = value);
        syncManager.syncValue("yDirection", yDirectionValue);
        BooleanSyncValue zDirectionValue = new BooleanSyncValue(
            () -> this.zDirection,
            value -> this.zDirection = value);
        syncManager.syncValue("zDirection", zDirectionValue);
        IntSyncValue xTargetValue = new IntSyncValue(() -> this.xTarget, value -> this.xTarget = value);
        syncManager.syncValue("xTarget", xTargetValue);
        IntSyncValue yTargetValue = new IntSyncValue(() -> this.yTarget, value -> this.yTarget = value);
        syncManager.syncValue("yTarget", yTargetValue);
        IntSyncValue zTargetValue = new IntSyncValue(() -> this.zTarget, value -> this.zTarget = value);
        syncManager.syncValue("zTarget", zTargetValue);
        IntSyncValue xProcessValue = new IntSyncValue(() -> this.xProcess, value -> this.xProcess = value);
        syncManager.syncValue("xProcess", xProcessValue);
        IntSyncValue yProcessValue = new IntSyncValue(() -> this.yProcess, value -> this.yProcess = value);
        syncManager.syncValue("yProcess", yProcessValue);
        IntSyncValue zProcessValue = new IntSyncValue(() -> this.zProcess, value -> this.zProcess = value);
        syncManager.syncValue("zProcess", zProcessValue);

        return ModularPanel.defaultPanel("void_gate")
            .size(180, 170)
            .child(
                Flow.col()
                    .margin(8)
                    .childPadding(2)

                    .child(
                        IKey.lang("gui.sthyworks.voidGate.title")
                            .asWidget())

                    .child(
                        Flow.row()
                            .childPadding(8)
                            .size(100, 15)
                            .left(0)
                            .crossAxisAlignment(Alignment.CrossAxis.CENTER)

                            .child(
                                IKey.lang("gui.sthyworks.voidGate.workStatus")
                                    .scale(0.7F)
                                    .asWidget())

                            .child(
                                IKey.lang(
                                    () -> isWorkingValue.getBoolValue() ? "gui.sthyworks.voidGate.isWorking.true"
                                        : "gui.sthyworks.voidGate.isWorking.false")
                                    .scale(0.7F)
                                    .asWidget()))

                    .child(
                        Flow.row()
                            .childPadding(8)
                            .size(100, 15)
                            .left(0)
                            .crossAxisAlignment(Alignment.CrossAxis.CENTER)

                            .child(
                                IKey.lang("gui.sthyworks.voidGate.TurnOnStatus")
                                    .scale(0.7F)
                                    .asWidget())

                            .child(
                                IKey.lang(
                                    () -> isTurnedOnValue.getBoolValue() ? "gui.sthyworks.voidGate.isTurnedOn.true"
                                        : "gui.sthyworks.voidGate.isTurnedOn.false")
                                    .scale(0.7F)
                                    .asWidget()))

                    .child(
                        Flow.row()
                            .childPadding(8)
                            .size(100, 15)
                            .left(0)
                            .crossAxisAlignment(Alignment.CrossAxis.CENTER)

                            .child(
                                IKey.lang("gui.sthyworks.voidGate.directionalDescription")
                                    .scale(0.8F)
                                    .asWidget()))

                    .child(
                        Flow.row()
                            .childPadding(8)
                            .size(100, 15)
                            .left(0)
                            .crossAxisAlignment(Alignment.CrossAxis.CENTER)

                            .child(
                                IKey.lang(
                                    () -> xDirectionValue.getBoolValue() ? "gui.sthyworks.voidGate.xDirectionValue.true"
                                        : "gui.sthyworks.voidGate.xDirectionValue.false")
                                    .scale(0.7F)
                                    .asWidget())

                            .child(
                                new ButtonWidget<>().width(20)
                                    .height(12)
                                    .overlay(
                                        IKey.lang("gui.sthyworks.voidGate.toggle")
                                            .scale(0.6F))
                                    .syncHandler(new InteractionSyncHandler().setOnMousePressed(mouse -> {
                                        if (!this.worldObj.isRemote) {
                                            this.xDirection = !xDirection;
                                            this.markDirty();
                                        }
                                    }))))

                    .child(
                        Flow.row()
                            .childPadding(8)
                            .size(100, 15)
                            .left(0)
                            .crossAxisAlignment(Alignment.CrossAxis.CENTER)

                            .child(
                                IKey.lang(
                                    () -> yDirectionValue.getBoolValue() ? "gui.sthyworks.voidGate.yDirectionValue.true"
                                        : "gui.sthyworks.voidGate.yDirectionValue.false")
                                    .scale(0.7F)
                                    .asWidget())

                            .child(
                                new ButtonWidget<>().width(20)
                                    .height(12)
                                    .overlay(
                                        IKey.lang("gui.sthyworks.voidGate.toggle")
                                            .scale(0.6F))
                                    .syncHandler(new InteractionSyncHandler().setOnMousePressed(mouse -> {
                                        if (!this.worldObj.isRemote) {
                                            this.yDirection = !yDirection;
                                            this.markDirty();
                                        }
                                    }))))

                    .child(
                        Flow.row()
                            .childPadding(8)
                            .size(100, 15)
                            .left(0)
                            .crossAxisAlignment(Alignment.CrossAxis.CENTER)

                            .child(
                                IKey.lang(
                                    () -> zDirectionValue.getBoolValue() ? "gui.sthyworks.voidGate.zDirectionValue.true"
                                        : "gui.sthyworks.voidGate.zDirectionValue.false")
                                    .scale(0.7F)
                                    .asWidget())

                            .child(
                                new ButtonWidget<>().width(20)
                                    .height(12)
                                    .overlay(
                                        IKey.lang("gui.sthyworks.voidGate.toggle")
                                            .scale(0.6F))
                                    .syncHandler(new InteractionSyncHandler().setOnMousePressed(mouse -> {
                                        if (!this.worldObj.isRemote) {
                                            this.zDirection = !zDirection;
                                            this.markDirty();
                                        }
                                    })))));
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        this.turnOnTime = compound.getInteger("turnOnTime");
        this.isTurnedOn = compound.getBoolean("isTurnedOn");
        this.isWorking = compound.getBoolean("isWorking");
        this.wasTopPowered = compound.getBoolean("wasTopPowered");
        this.completionTimer = compound.getInteger("completionTimer");

        NBTTagCompound direction = compound.getCompoundTag("direction");
        this.xDirection = direction.getBoolean("xDirection");
        this.yDirection = direction.getBoolean("yDirection");
        this.zDirection = direction.getBoolean("zDirection");

        NBTTagCompound start = compound.getCompoundTag("start");
        this.xStart = start.getInteger("xStart");
        this.yStart = start.getInteger("yStart");
        this.zStart = start.getInteger("zStart");

        NBTTagCompound target = compound.getCompoundTag("target");
        this.xTarget = target.getInteger("xTarget");
        this.yTarget = target.getInteger("yTarget");
        this.zTarget = target.getInteger("zTarget");

        NBTTagCompound process = compound.getCompoundTag("process");
        this.xProcess = process.getInteger("xProcess");
        this.yProcess = process.getInteger("yProcess");
        this.zProcess = process.getInteger("zProcess");
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setInteger("turnOnTime", this.turnOnTime);
        compound.setBoolean("isTurnedOn", this.isTurnedOn);
        compound.setBoolean("isWorking", this.isWorking);
        compound.setBoolean("wasTopPowered", this.wasTopPowered);
        compound.setInteger("completionTimer", this.completionTimer);

        NBTTagCompound direction = new NBTTagCompound();
        direction.setBoolean("xDirection", this.xDirection);
        direction.setBoolean("yDirection", this.yDirection);
        direction.setBoolean("zDirection", this.zDirection);

        NBTTagCompound start = new NBTTagCompound();
        start.setInteger("xStart", this.xStart);
        start.setInteger("yStart", this.yStart);
        start.setInteger("zStart", this.zStart);

        NBTTagCompound target = new NBTTagCompound();
        target.setInteger("xTarget", this.xTarget);
        target.setInteger("yTarget", this.yTarget);
        target.setInteger("zTarget", this.zTarget);

        NBTTagCompound process = new NBTTagCompound();
        process.setInteger("xProcess", this.xProcess);
        process.setInteger("yProcess", this.yProcess);
        process.setInteger("zProcess", this.zProcess);

        compound.setTag("direction", direction);
        compound.setTag("start", start);
        compound.setTag("target", target);
        compound.setTag("process", process);
    }

    public boolean checkValidArea(World world, int x, int y, int z) {

        int xOffset = xDirection ? 1 : -1;
        int yOffset = yDirection ? 1 : -1;
        int zOffset = zDirection ? 1 : -1;

        xStart = xCoord + xOffset;
        yStart = yCoord + yOffset;
        zStart = zCoord + zOffset;

        boolean xValidArea = false;
        boolean yValidArea = false;
        boolean zValidArea = false;
        if (xDirection) {
            for (int i = 2; i < MAX_DISTANCE; i++) {
                Block block = world.getBlock(x + i, y, z);
                if (block == BlockLoader.voidGateLandmark) {
                    xValidArea = true;
                    xTarget = x + i - xOffset;
                    break;
                }
            }
        } else {
            for (int i = 2; i < MAX_DISTANCE; i++) {
                Block block = world.getBlock(x - i, y, z);
                if (block == BlockLoader.voidGateLandmark) {
                    xValidArea = true;
                    xTarget = x - i - xOffset;
                    break;
                }
            }
        }

        if (yDirection) {
            for (int i = 2; i < MAX_DISTANCE; i++) {
                Block block = world.getBlock(x, y + i, z);
                if (block == BlockLoader.voidGateLandmark) {
                    yValidArea = true;
                    yTarget = y + i - yOffset;
                    break;
                }
            }
        } else {
            for (int i = 2; i < MAX_DISTANCE; i++) {
                Block block = world.getBlock(x, y - i, z);
                if (block == BlockLoader.voidGateLandmark) {
                    yValidArea = true;
                    yTarget = y - i - yOffset;
                    break;
                }
            }
        }

        if (zDirection) {
            for (int i = 2; i < MAX_DISTANCE; i++) {
                Block block = world.getBlock(x, y, z + i);
                if (block == BlockLoader.voidGateLandmark) {
                    zValidArea = true;
                    zTarget = z + i - zOffset;
                    break;
                }
            }
        } else {
            for (int i = 2; i < MAX_DISTANCE; i++) {
                Block block = world.getBlock(x, y, z - i);
                if (block == BlockLoader.voidGateLandmark) {
                    zValidArea = true;
                    zTarget = z - i - zOffset;
                    break;
                }
            }
        }

        return xValidArea && yValidArea && zValidArea;
    }

    @Override
    public void updateEntity() {
        if (worldObj.isRemote) return;

        // 完成信号计时器:仅在跨越0的那一刻通知邻居
        if (completionTimer > 0) {
            completionTimer--;
            if (completionTimer == 0) {
                this.notifyRedstoneUpdate();
                this.markDirty();
            }
        }

        // 顶面脉冲:只在上升沿(无电→有电)翻转一次开关机状态
        boolean topPowered = VoidGate.isTopSidePowered(worldObj, xCoord, yCoord, zCoord);
        if (topPowered && !wasTopPowered) {
            this.setTurnedOn(!isTurnedOn);
        }
        wasTopPowered = topPowered;

        if (!isTurnedOn) return;
        turnOnTime++;

        // 开机后每100tick巡检:区域失效则停工;闲置且仍有活则开工
        if (turnOnTime % 100 == 0) {
            if (!checkValidArea(worldObj, xCoord, yCoord, zCoord)) {
                this.setWorking(false);
                return;
            }
            if (!isWorking && checkWork()) {
                this.setWorking(true);
                if (completionTimer != 0) {
                    completionTimer = 0;
                    this.notifyRedstoneUpdate();
                }
                this.resetProcess();
            }
        }
        if (!isWorking) return;
        int digCount = 0;
        boolean finished = false;
        while (digCount < SPEED) {
            Block block = worldObj.getBlock(xProcess, yProcess, zProcess);
            if (block != Blocks.air && block.getBlockHardness(worldObj, xProcess, yProcess, zProcess) >= 0) {
                worldObj.setBlockToAir(xProcess, yProcess, zProcess);
                digCount++;
            }
            if (!advanceProcess()) {
                finished = true;
                break;
            }
        }
        if (finished) {
            if (checkWork()) {
                this.resetProcess();
            } else {
                this.setWorking(false);
                this.setTurnedOn(false);
                completionTimer = COMPLETION_SIGNAL_TICKS;
                this.notifyRedstoneUpdate();
                this.markDirty();
            }
        }
    }

    private void resetProcess() {
        xProcess = xStart;
        yProcess = Math.max(yStart, yTarget);
        zProcess = zStart;
    }

    private boolean advanceProcess() {
        if (xDirection ? xProcess < xTarget : xProcess > xTarget) {
            xProcess += xDirection ? 1 : -1;
            return true;
        }
        xProcess = xStart;
        if (zDirection ? zProcess < zTarget : zProcess > zTarget) {
            zProcess += zDirection ? 1 : -1;
            return true;
        }
        zProcess = zStart;
        if (yProcess != Math.min(yStart, yTarget)) {
            yProcess--;
            return true;
        }
        return false;
    }

    private boolean checkWork() {
        for (int x = Math.min(xStart, xTarget); x <= Math.max(xStart, xTarget); x++) {
            for (int z = Math.min(zStart, zTarget); z <= Math.max(zStart, zTarget); z++) {
                for (int y = Math.min(yStart, yTarget); y <= Math.max(yStart, yTarget); y++) {
                    Block block = worldObj.getBlock(x, y, z);
                    if (block != Blocks.air) {
                        if (block.getBlockHardness(worldObj, x, y, z) >= 0) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
