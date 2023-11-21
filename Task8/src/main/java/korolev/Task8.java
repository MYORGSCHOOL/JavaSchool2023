package korolev;

import java.util.concurrent.locks.ReentrantLock;

/**
 * A class for displaying all thread states.
 * @author Nikita Korolev
 * @version 1.0
 */
public class Task8 {
    /**
     * Basic static mathod for creating and
     * running threads, state {@code NEW} and {@code RUNNABLE}.
     * @param args
     */
    public static void main(String[] args) {
        Store store = new Store();
        ReentrantLock locker = new ReentrantLock();
        Producer producer = new Producer(store);
        Consummer consummer = new Consummer(store, locker);
        new Thread(producer).start();
        new Thread(consummer).start();
    }
}

/**
 * Class {@code Store}, for storing 
 * products and running methods {@code Producer} for the supplier, 
 * and {@code Consummer} for the client.
 */
class Store {
    private int groceries = 0;

    /**
     * Method for adding resources to the store, 
     * implements states {@code WAITING_TIME}.
     */
    public synchronized void get() {
        while (groceries < 1) {
            try {
                wait(3000);
            } catch (Exception e) {
            }
        }

        groceries--;
        System.out.println("Bought 1 product");
        System.out.println("Products in stock:" + groceries);
        notify();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
    }

    /**
     * A method for a customer to purchase products,
     * implements states {@code WAITING} and {@code WAITING_TIME}.
     */
    public synchronized void put() {
        while (groceries >= 3) {
            try {
                wait();
            } catch (Exception e) {
            }
        }
        
        groceries++;
        System.out.println("Added 1 product to stock!");
        System.out.println("Products in stock:" + groceries);
        notify();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }
}

/**
 * Provider class, runs the method {@code put()}
 */
class Producer implements Runnable {
    Store store;

    public Producer(Store s) {
        this.store = s;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            store.put();
        }
        Thread.interrupted();
    }
}

/**
 * Client class, runs a method {@code get()}
 * and implements thread states {@code BLOCKED}
 */
class Consummer implements Runnable {
    Store store;
    ReentrantLock locker;
    
    public Consummer(Store s, ReentrantLock l) {
        this.store = s;
        this.locker = l;
    }

    @Override
    public void run() {
        for (int i = 1; i < 6; i++) {
            locker.lock();
            store.get();
            locker.unlock();
        }
        Thread.interrupted();
    }
    
}