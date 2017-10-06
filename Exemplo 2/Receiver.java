
import java.io.IOException;
import java.net.*;

/**
 *
 * @author LEANDRO LOFFI
 */
public class Receiver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        byte[] buffer = "1".getBytes();
        InetAddress IPAddress = InetAddress.getByName("192.168.1.105");

        while (true) {
            receber(buffer, IPAddress, 57);
        }
    }

    public static void envio(byte[] buffer, InetAddress IPAddress, int port) throws SocketException, IOException {
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, IPAddress, 57);
        DatagramSocket datagramSocket = new DatagramSocket();
        datagramSocket.send(packet);
        System.out.println("RECEIVER: Enviou 1 ");
        datagramSocket.close();
        System.out.println("RECEIVER: Fechou Conexão Envio");
    }

    public static void receber(byte[] buffer, InetAddress IPAddress, int port) throws InterruptedException {
        try {
            DatagramSocket serverSocket = new DatagramSocket(port);
            byte[] receiveData = new byte[8];

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData(), 0,
                    receivePacket.getLength());
            if (sentence.equals("2")) {
                System.out.println("RECEIVER: Recebido " + sentence);
                serverSocket.close();
                System.out.println("RECEIVER: Fechou Conexão Recebimento");
                Thread.sleep(1); // TEMPO PARA A OUTRA APLICAÇÃO FICAR EM ESTADO DE RECEBIMENTO
                envio(buffer, IPAddress, port); // ENVIO
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
