import java.net.*;
import java.io.*;
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1234);
        BufferedReader lecture = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String message = lecture.readLine();
        System.out.println(message);
        socket.close();
    }
}
