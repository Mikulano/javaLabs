package lab4.Server;

import lab3.MonteCarlo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by ihb on 29.12.16.
 */
public class Server {

    public void runServer() throws IOException {
        // Selector: multiplexor of SelectableChannel objects
        Selector selector = Selector.open(); // selector is open here

        // ServerSocketChannel: selectable channel for stream-oriented listening sockets
        ServerSocketChannel socket = ServerSocketChannel.open();
        InetSocketAddress addr = new InetSocketAddress("localhost", 1111);

        // Binds the channel's socket to a local address and configures the socket to listen for connections
        socket.bind(addr);

        // Adjusts this channel's blocking mode.
        socket.configureBlocking(false);

        int ops = socket.validOps();
        SelectionKey selectKy = socket.register(selector, ops, null);

        // Infinite loop..
        // Keep server running
        while (true) {

            log("i'm a server and i'm waiting for new connection and buffer select...");
            // Selects a set of keys whose corresponding channels are ready for I/O operations
            selector.select();

            // token representing the registration of a SelectableChannel with a Selector
            Set<SelectionKey> crunchifyKeys = selector.selectedKeys();
            Iterator<SelectionKey> crunchifyIterator = crunchifyKeys.iterator();

            while (crunchifyIterator.hasNext()) {
                SelectionKey myKey = crunchifyIterator.next();

                // Tests whether this key's channel is ready to accept a new socket connection
                if (myKey.isAcceptable()) {
                    SocketChannel client = socket.accept();

                    // Adjusts this channel's blocking mode to false
                    client.configureBlocking(false);

                    // Operation-set bit for read operations
                    client.register(selector, SelectionKey.OP_READ);
                    log("Connection Accepted: " + client.getLocalAddress() + "\n");

                    // Tests whether this key's channel is ready for reading
                } else if (myKey.isReadable()) {

                    SocketChannel client = (SocketChannel) myKey.channel();

                    ByteBuffer buffer = ByteBuffer.allocate(256);
                    client.read(buffer);
                    String req = new String(buffer.array()).trim();

                    log("get " + req);

                    if ("disconnect".equals(req)) {
                        myKey.channel().close();
                    } else {
                        String[] parameters = req.split(" ");
                        int threadsCount = Integer.valueOf(parameters[0]);
                        long dotsCount = Long.valueOf(parameters[1]);

                        new Thread(() -> {
                            long start = System.currentTimeMillis();
                            MonteCarlo method = new MonteCarlo();
                            String response = String.valueOf(method.calculatePi(threadsCount, dotsCount));
                            long end = System.currentTimeMillis() - start;

                            ByteBuffer bufferResponse = ByteBuffer.wrap((response + " " + String.valueOf(end)).getBytes());
                            try {
                                client.write(bufferResponse);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).run();
                    }
                }
                crunchifyIterator.remove();
            }
        }
    }

    private static void log(String str) {
        System.out.println(str);
    }
}
