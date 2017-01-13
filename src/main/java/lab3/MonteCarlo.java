package lab3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ihb on 28.12.16.
 */
public class MonteCarlo {

    private double allPi;

    List<MyThread> threads;

    public double calculatePi(int threadsCount, long dotsCount) {

        threads = new ArrayList<>();
        allPi = 0;

        for(int i=0; i<threadsCount; i++){
            threads.add(new MyThread(this, dotsCount/threadsCount));
        }

        for(Thread tread : threads){
            tread.run();
        }

        for(Thread tread : threads){
            try {
                tread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return allPi/threadsCount;
    }

    void addPi(double pi){
        synchronized (this) {
            allPi += pi;
        }
    }

}
