package TRASHvid1.OOP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(8000);

            while (true){
                Socket socket = serverSocket.accept();
                handleConnection(socket);
            }

        } catch (IOException e) {
            System.out.println("Could not bind port");
        }


    }

    private static void handleConnection(Socket socket) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

        //TODO: Not done

        } catch (IOException e) {
            System.out.println("Unable to read from client");
        }
    }
}
