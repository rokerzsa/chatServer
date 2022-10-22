import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket sock = new Socket("127.0.0.1", 9999);

        InputStream in = sock.getInputStream();
        OutputStream out = sock.getOutputStream();

        out.write("Hello Server, How are you?".getBytes());

        byte[] buffer = new byte[1024];

        in.read(buffer);
        System.out.println("And the Server Says:'" + new String(buffer).trim()+"'");
    }
}
