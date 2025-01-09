package physics;

import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import global.Settings;

public class BlockTrigger implements PhysicsProcessor {

    // Components
    JLabel[][] gameBoard;
    ImageIcon targetBlock;

    // Action for Trigger
    TriggerAction action;

    /**
     * Trigger Function When touching Block
     * @param gameBoard game board from level panel
     * @param targetBlock target block for trigger the function
     * @param action function to run when block is touched
     */
    public BlockTrigger(JLabel[][] gameBoard, ImageIcon targetBlock, TriggerAction action) {
        this.gameBoard = gameBoard;
        this.targetBlock = targetBlock;
        this.action = action;
    }

    /**
     * Get Block Position when touching
     * @param position current position
     * @param block target block
     * @return position of touched target block ((-1, -1)when not touching)
     */
    public int[] getTouchedBlockPos(int[] position, ImageIcon block) {
        int indexPosRow = position[1] / Settings.BLOCK_SIZE;
        int indexPosCol = position[0] / Settings.BLOCK_SIZE;
        int indexRowOffset = position[1] % Settings.BLOCK_SIZE;
        int indexColOffset = position[0] % Settings.BLOCK_SIZE;
        boolean inMiddleRow = indexColOffset != 0;
        boolean inMiddleCol = indexRowOffset != 0;

        // At Target Block
        if (gameBoard[indexPosRow][indexPosCol].getIcon() == block)
            return new int[] { indexPosRow, indexPosCol };
        // Reaching from Top
        else if (inMiddleRow && gameBoard[indexPosRow + 1][indexPosCol].getIcon() == block)
            return new int[] { indexPosRow + 1, indexPosCol };
        // Reaching from Left
        else if (inMiddleCol && gameBoard[indexPosRow][indexPosCol + 1].getIcon() == block)
            return new int[] { indexPosRow, indexPosCol + 1 };
        // Reaching from Top Left
        else if (inMiddleRow && inMiddleCol && gameBoard[indexPosRow + 1][indexPosCol + 1].getIcon() == block)
            return new int[] { indexPosRow + 1, indexPosCol + 1 };
        return new int[] { -1, -1 };
    }

    @Override
    public PhysicsStatus process(PhysicsStatus currentStatus) {
        int[] blockPos = getTouchedBlockPos(currentStatus.getPosition(), this.targetBlock);
        if (blockPos[0] != -1 && blockPos[1] != -1) {
            this.action.action(blockPos, currentStatus);
        }
        return currentStatus;
    }

    // Generated hashCode and equals for map and list
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(gameBoard);
        result = prime * result + ((targetBlock == null) ? 0 : targetBlock.hashCode());
        result = prime * result + ((action == null) ? 0 : action.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BlockTrigger other = (BlockTrigger) obj;
        if (!Arrays.deepEquals(gameBoard, other.gameBoard))
            return false;
        if (targetBlock == null) {
            if (other.targetBlock != null)
                return false;
        } else if (!targetBlock.equals(other.targetBlock))
            return false;
        if (action == null) {
            if (other.action != null)
                return false;
        } else if (!action.equals(other.action))
            return false;
        return true;
    }
}
