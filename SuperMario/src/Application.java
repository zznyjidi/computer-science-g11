import global.Database;
import global.Settings;
import interfaces.MainFrame;
import online.Account;
import replay.ReplayRecorder;

public class Application {

    public static void main(String[] args) {
        // Init Online Service
        Settings.scoreServerAddr = new String[] { "http", "127.0.0.1:8080" };
        Database.account = new Account("test_user");
        System.out.println("Logged in as: UID" + Database.account.login("uU55c1Y340XpLgJJHp74"));
        Database.replayRecorder = new ReplayRecorder();

        // Start MainFrame
        Database.mainFrame = new MainFrame();
    }
}
