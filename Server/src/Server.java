import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serSocket =  new ServerSocket(9999);
        Socket sock = serSocket.accept();

        InputStream in = sock.getInputStream();
        OutputStream out = sock.getOutputStream();

        byte buffer[] = new byte[1024];

        in.read(buffer);

        System.out.println("The Client asked me '" + new String(buffer).trim() + "'");

        out.write("I am fine Server".getBytes());

        sock.close();
        serSocket.close();

    }
}