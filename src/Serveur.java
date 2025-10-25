import java.net.*;
import java.io.*;
public class Serveur {
    static int clientsTotal = 0;
    public static void main(String[] args) throws IOException {
        ServerSocket socketServeur = new ServerSocket(1234);
        System.out.println("Serveur prêt sur le port 1234");
        
        while (true) {
            Socket socketClient = socketServeur.accept();
            clientsTotal++;
            new Thread(new GestionClient(socketClient, clientsTotal)).start();
        }
    }
}
class GestionClient implements Runnable {
    private Socket socket;
    private int numeroClient;
    
    public GestionClient(Socket s, int numero) {
        this.socket = s;
        this.numeroClient = numero;
    }
    public void run() {
        try {
            String ipClient = socket.getInetAddress().getHostAddress();
            System.out.println("Nouveau client " + numeroClient + " depuis " + ipClient);
            
            PrintWriter envoi = new PrintWriter(socket.getOutputStream(), true);
            envoi.println("Bonjour client numero " + numeroClient);
            
            socket.close();
        } catch (IOException e) {
            System.out.println("Probleme avec le client " + numeroClient);
        }
    }
}
