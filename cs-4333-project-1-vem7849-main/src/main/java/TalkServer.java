package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;

public class TalkServer implements BasicTalkInterface {

    private Socket client;
    private ServerSocket server;
    private BufferedReader sockin;
    private PrintWriter sockout;
    private BufferedReader stdin;

    public TalkServer(int port) throws IOException {
        this.server = new ServerSocket(port);
        this.client = server.accept();
        this.sockin = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.sockout = new PrintWriter(client.getOutputStream(), true);
        this.stdin = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() throws IOException {
        Thread inputThread = new Thread(() -> {
            try {
                String line;
                while ((line = sockin.readLine()) != null) {
                    if (!line.equalsIgnoreCase("STATUS")) {
                        System.out.println("[remote] " + line);
                    } else {
                        System.out.println("[STATUS] Client: " + client.getInetAddress().getHostAddress() + ":" + client.getPort() +
                                "; Server: " + server.getLocalSocketAddress());
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading from socket: " + e.getMessage());
            }
        });
        inputThread.start();

        String line;
        while ((line = stdin.readLine()) != null) {
            if (!line.equalsIgnoreCase("STATUS")) {
                sockout.println(line);
} else {
                System.out.println("[STATUS] Client: " + client.getInetAddress().getHostAddress() + ":" + client.getPort() +
                        "; Server: " + server.getLocalSocketAddress());
            }
        }
    }

    public void asyncIO() throws IOException {
        // Implement asynchronous I/O using polling
    }

  @Override
  public void close() throws IOException {

  }

  public void syncIO() throws IOException {
        // Implement synchronous I/O by blocking on input
    }

    public String status() {
        // Return the status of the current socket connection as a String
        return "Client: " + client.getInetAddress().getHostAddress() + ":" + client.getPort() +
                "; Server: " + server.getLocalSocketAddress();
    }

    public Optional<BasicTalkInterface> autoServer(String hostname, int port) {
        return Optional.empty();
    }
}