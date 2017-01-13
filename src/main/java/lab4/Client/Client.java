package lab4.Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * Created by ihb on 29.12.16.
 */
public class Client {

    public static void runClient(int n, long num) throws IOException, InterruptedException {
        InetSocketAddress addr = new InetSocketAddress("localhost", 1111);
        SocketChannel client = SocketChannel.open(addr);

        log("Connecting to Server on port 1111...");

        ArrayList<String> requests = new ArrayList<>();


        // create a ArrayList with companyName list
        requests.add(n + " " + num);
        requests.add("disconnect");

        for (String req : requests) {

            byte[] message = req.getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            client.write(buffer);

            log("sending: " + req);
            buffer.clear();

            ByteBuffer bufferResponse = ByteBuffer.allocate(256);
            client.read(bufferResponse);
            String pi =  new String(bufferResponse.array()).trim();

            log(pi);

            // wait for 2 seconds before sending next message
            Thread.sleep(2000);
        }
        client.close();
    }

    private static void log(String str) {
        System.out.println(str);
    }
}
