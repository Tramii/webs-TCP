package mundoClient;

import java.io.File;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) throws Exception {
        Socket socket = null;
        String host = "127.0.0.1";

        socket = new Socket(host, 4444);

        File file = new File("M:\\test.xml");
        // Get the size of the file

    }
}
