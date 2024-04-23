import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TalkClient {
    private final String host;
    private final int port;
    private Socket socket;
    private PrintWriter out;
    private boolean isConnected;

    public TalkClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(host, port), 5000);
            isConnected = true;

            out = new PrintWriter(socket.getOutputStream(), true);

            Thread inputThread = new Thread(() -> {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println("[" + host + ":" + port + "] " + line);
                    }
                } catch (IOException e) {
                    System.err.println("Connection closed");
                    isConnected = false;
                }
            });

            inputThread.start();

            Scanner scanner = new Scanner(System.in);
            String input;
            while (true) {
                input = scanner.nextLine();

                if (input.equalsIgnoreCase("STATUS")) {
                    printStatus();
                } else {
                    out.println(input);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Client unable to communicate with server");
        } catch (IOException e) {
            System.err.println("Client unable to communicate with server");
        }
    }

    public boolean isConnected() {
        return isConnected;
    }

    private void printStatus() {
        System.out.println("[STATUS] Client: " + host + ":" + port);
    }
}