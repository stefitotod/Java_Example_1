import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        CustomerInfoServer.listOfBranches.add("abc");
        CustomerInfoServer.listOfBranches.add("abcd");
        CustomerInfoServer.listOfBranches.add("abcde");
        CustomerInfoServer.listOfBranches.add("abcef");
        CustomerInfoServer.listOfCustomers.add(new Customer(381222028, "stefi", 1000));
        CustomerInfoServer.listOfCustomers.add(new Customer(381222002, "nikita", 9));
        CustomerInfoServer.listOfCustomers.add(new Customer(381222003, "ioan", 20));
        try (
            ServerSocket server = new ServerSocket(1211);
        ) {
            Socket socket;
            while (true) {
                socket = server.accept();
                Thread thread = new BankThread(socket);
                thread.start();
            }
        }
    }
}
