
import java.io.IOException;
import java.net.*;

/**
 *
 * @author LEANDRO LOFFI
 */
public class send {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        // TODO code application logic here
        byte[] buffer = "2".getBytes();
        InetAddress IPAddress = InetAddress.getByName("192.168.1.105");

        envio(buffer, IPAddress, 57);
        while (true) {
            receber(buffer, IPAddress, 57);
        }
    }

    public static void envio(byte[] buffer, InetAddress IPAddress, int port) throws SocketException, IOException {
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, InetAddress.getLocalHost(), 57);
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(packet);
        System.out.println("SEND: Enviou 2 ");
        datagramSocket.close();
        System.out.println("SEND: Fechou Conexão Envio");
    }

    public static void receber(byte[] buffer, InetAddress IPAddress, int port) throws InterruptedException {
        try {
            DatagramSocket serverSocket = new DatagramSocket(port);
            byte[] receiveData = new byte[8];

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData(), 0,
                    receivePacket.getLength());
            if (sentence.equals("1")) {
                System.out.println("SEND: Recebido " + sentence);
                serverSocket.close();
                System.out.println("SEND: Fechou Conexão Recebimento");
                Thread.sleep(1); // TEMPO DE ESPERA PARA A OUTRA APLICAÇÃO RETOMAR AO ESTADO DE ESCUTA
                envio(buffer, IPAddress, port); // ENVIO COM 2
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
