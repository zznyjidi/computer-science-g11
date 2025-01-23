package interfaces.list;

import javax.swing.JPanel;

public interface ListActionListener {
    public void selectChanged(ListPane<?> source,int index, JPanel newEntry);
}
