package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public abstract class Talk {

    public static void main(String[] args) throws IOException {
        // Parse command-line arguments
        String hostname = "localhost";
        int port = 12987;
        boolean serverMode = false;
        boolean autoMode = false;

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-h":
                    hostname = args[++i];
                    break;
                case "-p":
                    port = Integer.parseInt(args[++i]);
                    break;
                case "-s":
                    serverMode = true;
                    break;
                case "-a":
                    autoMode = true;
                    break;
                case "-help":
                    printHelp();
                    return;
                default:
                    printHelp();
                    return;
            }
        }

        // Determine the mode and create the appropriate instance
        BasicTalkInterface talkInterface;
        if (serverMode) {
            talkInterface = new TalkServer(port);
        } else if (autoMode) {
            talkInterface = new TalkClient(hostname, port);
            Optional<BasicTalkInterface> serverOptional = ((TalkClient) talkInterface).autoServer(hostname, port);
            if (serverOptional.isPresent()) {
                talkInterface = serverOptional.get();
            }
        } else {
            talkInterface = new TalkClient(hostname, port);
        }

        // Call the start method on the created instance
        talkInterface.start();
    }

    private static void printHelp() {
        System.out.println("Talk -h [hostname | IPaddress] [–p portnumber]");
        System.out.println("The program behaves as a client connecting to [hostname | IPaddress] on port portnumber.");
        System.out.println("If a server is not available your program should exit with the message \"Client unable to communicate with server\".");
        System.out.println("Note: portnumber in this case refers to the server and not to the client.");
        System.out.println();
        System.out.println("Talk –s [–p portnumber]");
        System.out.println("The program behaves as a server listening for connections on port portnumber.");
        System.out.println("If the port is not available for use, your program should exit with the message \"Server unable to listen on specified port\".");
        System.out.println();
        System.out.println("Talk –a [hostname | IPaddress] [–p portnumber]");
        System.out.println("The program enters auto mode.");
        System.out.println("When in auto mode, your program should start as a client attempting to communicate with [hostname | IPaddress] on port portnumber.");
        System.out.println("If a server is not found, your program should detect this condition and start behaving as a server listening for connections on port portnumber.");
        System.out.println();
        System.out.println("Talk –help");
        System.out.println("The program prints your name and instructions on how to use your program.");
    }

  public abstract boolean clientMode(String hostname, int portnumber);

  public Boolean start(String[] strings) {
      return null;
  }

    public abstract boolean autoMode(String hostname, int portnumber);

    public abstract boolean serverMode(int portnumber);
}