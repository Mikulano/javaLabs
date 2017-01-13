package lab3;

import java.util.*;

/**
 * Created by ihb on 28.12.16.
 */
public class MyThread extends Thread {
    private final MonteCarlo owner;
    private final long dotsCount;

    MyThread(MonteCarlo owner, long dotsCount) {
        this.owner = owner;
        this.dotsCount = dotsCount;
    }

    @Override
    public void run() {

        Random rand = new Random();

        double x, y, pi;
        double a = 100;//сторона квадарата
        int i = 0;//Счетчик
        double dotsInCircle = 0;//Количество точек, попавших в круг

        while (i < dotsCount) {
            x = (rand.nextInt() % (a * 1000)) / 1000;  //Рандомный Х с 3 знаками после запятой
            y = (rand.nextInt() % (a * 1000)) / 1000;  //Рандомный Y с 3 знаками после запятой
            if (y * y <= circle(x, (a / 2)))  //Условие принадлежности точки к кругу
            {
                dotsInCircle++;
            }
            i++;
        }

        pi = (dotsInCircle / dotsCount) * 16;

        owner.addPi(pi);
    }

    private double circle(double x, double radius) {
        return radius * radius - x * x;
    }
}

