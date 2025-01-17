import global.Database;
import interfaces.MainFrame;
import online.Account;
import replay.ReplayRecorder;

public class Application {

    public static void main(String[] args) {
        // Init Online Service
        //Settings.scoreServerAddr = new String[] { "http", "127.0.0.1:8080" };
        Database.account = new Account("test_user");
        int uid = Database.account.login("uU55c1Y340XpLgJJHp74");
        if (uid > 0) {
            System.out.println("Logged in as: UID" + uid);
        }
        Database.replayRecorder = new ReplayRecorder();

        // Start MainFrame
        Database.mainFrame = new MainFrame();
    }
}
