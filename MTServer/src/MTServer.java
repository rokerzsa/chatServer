import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


class QuoteService{
    Map<String,String> Products = new HashMap<>();
    public QuoteService(){
        Products.put("a","100");
        Products.put("b", "200");
        Products.put("c", "250");
    }
    public String getQuote(String product){
        return Products.get(product);
    }
}

class ServiceThread extends Thread{
    Socket sock;

    QuoteService QS = new QuoteService();

    public ServiceThread(Socket sock){
        this.sock = sock;
    }

    public void run(){
        try {
                InputStream in = sock.getInputStream();
                OutputStream out = sock.getOutputStream();

                byte[] request =  new byte[100];

                in.read(request);
                String productName = new String(request).trim();
                System.out.println("Recieved product name is: "+productName);

                String price = QS.getQuote(productName);
                if(price==null){
                    System.out.println("Invalid product");
                    out.write("".getBytes());
                }else{
                    System.out.println("Sending Price Information");
                    out.write(price.getBytes());
                    System.out.println("Response sent");
                }
                sock.close();

        }catch (Exception e){}
    }

}

public class MTServer {
    public static void main(String[] args) throws IOException {

        int PORT = 9999;
        ServerSocket serSocket = new ServerSocket(PORT);
        System.out.println("Starting server at port: "+ PORT);

        while(true){
            System.out.println("Waiting for connection");

            Socket sock = serSocket.accept();

            System.out.println("Connected to Client with Multi TThreading");
            System.out.println("Waiting for Product Information");

            new ServiceThread(sock).start();


        }

    }
}