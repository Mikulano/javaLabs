package lab3;

/**
 * Created by ihb on 29.12.16.
 */
public class Main {
    public static void main(String[] args) {
        MonteCarlo method = new MonteCarlo();

        long start = System.currentTimeMillis();
        System.out.println(method.calculatePi(1, 100000000L));
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        System.out.println(method.calculatePi(2, 100000000L));
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        System.out.println(method.calculatePi(3, 100000000L));
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        System.out.println(method.calculatePi(4, 100000000L));
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        System.out.println(method.calculatePi(1000, 100000000L));
        System.out.println(System.currentTimeMillis() - start);
    }
}
