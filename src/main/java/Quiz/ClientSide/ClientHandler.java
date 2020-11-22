package Quiz.ClientSide;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Thread thread;
    private final Socket playerSocket;
    ClientProtocol protocol;
    private String player1Name;
    private String player2Name;


    public ClientHandler(Socket player1, ClientProtocol protocol) {
        this.thread = new Thread(this);
        this.playerSocket = player1;
        this.protocol = protocol;
        this.thread.start();
    }

    @Override
    public void run() {

        System.out.println("Two players connected!");

        try (
                ObjectOutputStream out = new ObjectOutputStream(playerSocket.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
        ) {

            out.writeObject(this.protocol.ProcessInput("init"));

            String fromClient;
            while ((fromClient = in.readLine()) != null) {
                out.writeObject(this.protocol.ProcessInput(fromClient));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
