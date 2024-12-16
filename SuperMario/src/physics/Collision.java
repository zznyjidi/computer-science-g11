package physics;

import javax.swing.JLabel;

import global.Settings;
import level.Icon;
import sound.Sound;
import sound.SoundPlayer;

public class Collision implements PhysicsProcessor {

    JLabel[][] gameBoard;

    public Collision(JLabel[][] gameBoard) {
        this.gameBoard = gameBoard;
    }

    public boolean getCollisionX(int[] position, int deltaX) {
        int indexPosRow = position[1] / Settings.BLOCK_SIZE;
        int indexPosCol = position[0] / Settings.BLOCK_SIZE;
        int indexRowOffset = position[1] % Settings.BLOCK_SIZE;
        int indexColOffset = position[0] % Settings.BLOCK_SIZE;
        boolean inMiddleRow = indexColOffset != 0;

        // Move Right
        if (deltaX > 0) {
            // At Block Edge & Bottom Block triggered Collision
            if (gameBoard[indexPosRow][indexPosCol + 1].getIcon() == Icon.WALL)
                return true;
            // At Block Edge & In the Air & Top Block triggered Collision
            if (inMiddleRow && gameBoard[indexPosRow - 1][indexPosCol + 1].getIcon() == Icon.WALL)
                return true;
        }
        // Move Left
        if (deltaX < 0) {
            // At Block Edge & Bottom Block triggered Collision
            if (gameBoard[indexPosRow][indexPosCol].getIcon() == Icon.WALL)
                return true;
            // At Block Edge & In the Air & Top Block triggered Collision
            if (inMiddleRow && gameBoard[indexPosRow - 1][indexPosCol].getIcon() == Icon.WALL)
                return true;
            // At Next Block & next Frame Bottom Block trigger Collision
            if (indexRowOffset < deltaX && gameBoard[indexPosRow][indexPosCol - 1].getIcon() == Icon.WALL)
                return true;
            // At Next Block & next Frame Top Block trigger Collision
            if (indexRowOffset < deltaX && inMiddleRow && gameBoard[indexPosRow - 1][indexPosCol - 1].getIcon() == Icon.WALL)
                return true;
        }
        return false;
    }

    public boolean getCollisionY(int[] position, int deltaY) {
        int indexPosRow = position[1] / Settings.BLOCK_SIZE;
        int indexPosCol = position[0] / Settings.BLOCK_SIZE;
        int indexRowOffset = position[1] % Settings.BLOCK_SIZE;
        int indexColOffset = position[0] % Settings.BLOCK_SIZE;
        boolean inMiddleCol = indexRowOffset != 0;

        // Move Up
        if (deltaY > 0) {
            // At Block Edge & Left Block triggered Collision
            if (gameBoard[indexPosRow][indexPosCol].getIcon() == Icon.WALL)
                return true;
            // At Block Edge & Right Block triggered Collision
            if (inMiddleCol && gameBoard[indexPosRow][indexPosCol + 1].getIcon() == Icon.WALL)
                return true;
            // At Next Block & next Frame Left Block trigger Collision
            if (indexColOffset < deltaY && gameBoard[indexPosRow - 1][indexPosCol].getIcon() == Icon.WALL)
                return true;
            // At Next Block & next Frame Right Block trigger Collision
            if (indexColOffset < deltaY && inMiddleCol && gameBoard[indexPosRow - 1][indexPosCol + 1].getIcon() == Icon.WALL)
                return true;
        }
        // Move Down
        if (deltaY < 0) {
            // At Block Edge & Left Block triggered Collision
            if (gameBoard[indexPosRow + 1][indexPosCol].getIcon() == Icon.WALL)
                return true;
            // At Block Edge & Right Block triggered Collision
            if (inMiddleCol && gameBoard[indexPosRow + 1][indexPosCol + 1].getIcon() == Icon.WALL)
                return true;
        }
        return false;
    }

    @Override
    public PhysicsStatus process(PhysicsStatus currentStatus) {
        if (getCollisionX(currentStatus.getPosition(), currentStatus.getDeltaX())) {
            currentStatus.setDeltaX(0);
        }
        if (getCollisionY(currentStatus.getPosition(), currentStatus.getDeltaY())) {
            currentStatus.setDeltaY(0);
            SoundPlayer.play(Sound.hitGround);
        }
        return currentStatus;
    }

}
