import java.io.IOException;
import java.net.*;

/**
 *
 * @author hp
 */
public class send {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, SocketException, IOException  {
        // TODO code application logic here
        byte[] buffer = "12345678".getBytes();
        InetAddress IPAddress = InetAddress.getByName("192.168.1.105");
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, IPAddress, 57);
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(packet);
        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }
}