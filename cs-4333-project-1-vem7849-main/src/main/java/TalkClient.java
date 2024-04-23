package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;

public class TalkClient implements BasicTalkInterface {

    private Socket socket;
    private BufferedReader sockin;
    private PrintWriter sockout;
    private BufferedReader stdin;

    public TalkClient(String hostname, int port) throws UnknownHostException, IOException {
        this.socket = new Socket(hostname, port);
        this.sockin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.sockout = new PrintWriter(socket.getOutputStream(), true);
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
                        System.out.println("[STATUS] Client: " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() +
                                "; Server: " + socket.getLocalAddress().getHostAddress() + ":" + socket.getLocalPort());
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
                System.out.println("[STATUS] Client: " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() +
                        "; Server: " + socket.getLocalAddress().getHostAddress() + ":" + socket.getLocalPort());
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
        return "Client: " + socket.getInetAddress().getHostAddress() + ":" + socket.getPort() +
                "; Server: " + socket.getLocalAddress().getHostAddress() + ":" + socket.getLocalPort();
    }

    public Optional<BasicTalkInterface> autoServer(String hostname, int port) {
        try {
            this.socket.close();
            return Optional.of(new TalkServer(port));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}