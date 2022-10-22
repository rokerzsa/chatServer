import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        String HOST = "127.0.0.1";
        int PORT = 9999;
        Socket sock = new Socket(HOST, PORT);
        System.out.println("Connected to client");

        InputStream in =  sock.getInputStream();
        OutputStream out = sock.getOutputStream();

        System.out.println("Enter Product Name:");
        Scanner scan = new Scanner(System.in);
        String productName = scan.nextLine();

        out.write(productName.getBytes());
        System.out.println("Sent product name: "+productName);

        byte[] response =  new byte[100];
        in.read(response);
        String price = new String(response).trim();
        if(price!=""){
            System.out.println("Price of the product is: "+price);
        }else{
            System.out.println("Invalid Product Name");
        }


        sock.close();

    }
}
