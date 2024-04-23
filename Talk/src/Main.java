//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Main [client|server|auto] [hostname] [port]");
            return;
        }

        String mode = args[0];
        String hostname = "localhost";
        int port = 12987;

        if (mode.equalsIgnoreCase("client")) {
            if (args.length != 3) {
                System.out.println("Usage: java Main client [hostname] [port]");
                return;
            }
            hostname = args[1];
            port = Integer.parseInt(args[2]);

            TalkClient talkClient = new TalkClient(hostname, port);
            talkClient.start();
        } else if (mode.equalsIgnoreCase("server")) {
            if (args.length != 2) {
                System.out.println("Usage: java Main server [port]");
                return;
            }
            port = Integer.parseInt(args[1]);

            TalkServer talkServer = new TalkServer(port);
            talkServer.start();
        } else if (mode.equalsIgnoreCase("auto")) {
            if (args.length != 3) {
                System.out.println("Usage: java Main auto [hostname] [port]");
                return;
            }
            hostname = args[1];
            port = Integer.parseInt(args[2]);

            TalkClient talkClient = new TalkClient(hostname, port);
            talkClient.start();

            if (!talkClient.isConnected()) {
                TalkServer talkServer = new TalkServer(port);
                talkServer.start();
            }
        } else {
            System.out.println("Invalid mode. Use client, server, or auto.");
        }
    }
}