import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class TalkServer {
    private final int port;
    private ServerSocket serverSocket;
    private final Set<ClientHandler> clientHandlers;

    public TalkServer(int port) {
        this.port = port;
        this.clientHandlers = new HashSet<>();
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandlers.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.err.println("Server unable to listen on specified port");
        }
    }

    private class ClientHandler implements Runnable {
        private final Socket socket;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
out = new PrintWriter(socket.getOutputStream(), true);

                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println("[" + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() + "] " + line);

                    if (line.equalsIgnoreCase("STATUS")) {
                        printStatus();
                    }

                    // Broadcast the message to all connected clients
                    for (ClientHandler clientHandler : clientHandlers) {
                        clientHandler.sendMessage("[remote] " + line);
                    }
                }
            } catch (IOException e) {
                System.err.println("Connection closed");
                clientHandlers.remove(this);
            }
        }

        public void sendMessage(String message) {
            out.println(message);
        }
    }

    private void printStatus() {
        System.out.println("[STATUS] Server: " + serverSocket.getInetAddress().getHostAddress() + ":" + serverSocket.getLocalPort());
    }
}