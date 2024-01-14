import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class BankThread extends Thread {
    
    Socket socket;

    public BankThread(Socket socket) {
        this.socket = socket;
    }


    @Override 
    public void run() {
        try (
            PrintWriter sender = new PrintWriter(socket.getOutputStream(), true);
            Scanner receiver = new Scanner(socket.getInputStream());
        ) {
            sender.println("Enter your bank id: ");
            String id = receiver.nextLine();
            String branchID = null;
            for(String number : CustomerInfoServer.listOfBranches) {
                if(id.equals(number)) {
                    branchID = id;
                }
            }
            if(branchID == null) {
                sender.println("Invalid code"); 
                return;
            }
            sender.println("Enter client number: ");
            int clientId = receiver.nextInt();
            for(Customer customer : CustomerInfoServer.listOfCustomers) {
                if(clientId == customer.customerID) {
                    sender.println(customer.customerID + "*" + customer.customerName + "*" + customer.amount);
                    sender.println("END");
                }
            } 
            sender.println("No such customer");
            return;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
}
