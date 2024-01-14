import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        try (
            Socket socket = new Socket("127.0.0.1", 1211);
            Scanner scanner = new Scanner(System.in);
            PrintWriter sender = new PrintWriter(socket.getOutputStream(), true);
            Scanner receiver = new Scanner(socket.getInputStream());
        ) {
            while (true) {
                String message = receiver.nextLine();
                if(message.equals("END")) {
                    return;
                }
                System.out.println(message);
                message = scanner.nextLine();
                sender.println(message);
            }
        }
    }
}
