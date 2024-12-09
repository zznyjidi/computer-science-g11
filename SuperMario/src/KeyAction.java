
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
        Character character = LevelPanel.character;
        // Move Left
        if (e.getActionCommand().equals(character.getKeyBind()[0]))
            character.moveDirection(-1);
        // Move Right
        else if (e.getActionCommand().equals(character.getKeyBind()[1]))
            character.moveDirection(1);
        // Jump 
        else if (e.getActionCommand().equals(character.getKeyBind()[2])) {
            character.jump();
        }
    }

}
