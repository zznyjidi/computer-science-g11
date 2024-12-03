
import java.awt.event.ActionEvent;
import javax.swing.text.TextAction;


public class KeyAction extends TextAction{

    private String key;

    public KeyAction(String key) {
        super(key);
        this.key = key;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Character character = LevelFrame.character;
        // Move Left
        if (e.getActionCommand().equals(character.getKeyBind()[0]) && 
            LevelFrame.gameBoard[character.getPosition()[1]][character.getPosition()[0] - 1].getIcon() != Icon.WALL) {
            character.moveDirection(-1, 0);
        } 
        // Move Right
        else if (e.getActionCommand().equals(character.getKeyBind()[1]) && 
            LevelFrame.gameBoard[character.getPosition()[1]][character.getPosition()[0] + 1].getIcon() != Icon.WALL) {
            character.moveDirection(1, 0);
        }
        // Jump 
        else if (e.getActionCommand().equals(character.getKeyBind()[2]) && 
            LevelFrame.gameBoard[character.getPosition()[1] - 1][character.getPosition()[0]].getIcon() != Icon.WALL) {
            if (!character.isJumping()) {
                character.jump();
            }
        }
    }

}
