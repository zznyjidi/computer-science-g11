package physics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import global.Settings;

public class BlockTrigger implements PhysicsProcessor {

    JLabel[][] gameBoard;
    ImageIcon targetBlock;

    TriggerAction action;

    public BlockTrigger(JLabel[][] gameBoard, ImageIcon targetBlock, TriggerAction action) {
        this.gameBoard = gameBoard;
        this.targetBlock = targetBlock;
        this.action = action;
    }

    public int[] getTouchedBlockPos(int[] position, ImageIcon block) {
        int indexPosRow = position[1] / Settings.BLOCK_SIZE;
        int indexPosCol = position[0] / Settings.BLOCK_SIZE;
        int indexRowOffset = position[1] % Settings.BLOCK_SIZE;
        int indexColOffset = position[0] % Settings.BLOCK_SIZE;
        boolean inMiddleRow = indexColOffset != 0;
        boolean inMiddleCol = indexRowOffset != 0;

        // At Coin Block
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
            this.action.action(blockPos);
        }
        return currentStatus;
    }
}
