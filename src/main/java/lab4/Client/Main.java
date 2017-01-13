package lab4.Client;

import java.io.IOException;

/**
 * Created by ihb on 29.12.16.
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                try {
                    Client.runClient(4, 10000000);
                } catch (Exception e) {
                    System.out.println("Some shit error!");
                }
            });
            t.start();
        }

    }
}
