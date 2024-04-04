import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class testCoffee {

    public static void main(String args[])
            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        foo();
    }

    private static boolean dedicatedAcceptThread = false;
    private String myName = "";
    private static Runnable runnable;
    private Thread selectorThread;
    private Long selectorThreadId;

    static void foo() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println("Runnable running");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
//		cryptoError();
        createThread(runnable);
        Thread rt = returnThread(runnable);
        ExecutorService ex = createExec();
        ExecutorService fixedExec = createExec1(runnable);
    }

    static void cryptoError() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("AES");
        KeyGenerator keygen = KeyGenerator.getInstance("AES");

        // CogniCryt_SAST reports an error in the next line saying that the key size is
        // chosen inappropriately.
        keygen.init(46);
        SecretKey key = keygen.generateKey();
        cipher.init(Cipher.ENCRYPT_MODE, key);
        keygen.init(126);
        SecretKey key1 = keygen.generateKey();
        cipher.init(Cipher.ENCRYPT_MODE, key1);
    }

    static void createThread(Runnable runnable) {
        ThreadGroup group = new ThreadGroup("tgd");
        Thread thread2 = new Thread(group, runnable);

        Thread thread3 = Thread.ofVirtual().name("gh").unstarted(runnable);
        thread3.start();
        thread2.setName("th");
        thread2.start();
        ExecutorService exec = Executors.newFixedThreadPool(10);

    }

    static Thread returnThread(Runnable runnable) {
        if (!dedicatedAcceptThread) {
            Thread thread2;
            if (true) {
                ThreadGroup group = new ThreadGroup("tgd");
                thread2 = new Thread(group, runnable);
            }
            return thread2;
        } else {
            return new Thread(runnable, "rtye");
        }
    }


    static ExecutorService createExec() {
        ExecutorService exec = Executors.newFixedThreadPool(0);
        return Executors.newCachedThreadPool();
    }


    static ExecutorService createExec1(Runnable runnable) {


        Thread thread2;
        ThreadGroup group = new ThreadGroup("tgd");
        StringBuffer writer = new StringBuffer("SIP Writer ");

        thread2 = new Thread(group, runnable, writer.toString());

        return Executors.newCachedThreadPool();
    }


    void createThread() {
        if (!dedicatedAcceptThread) {
            System.out.println("");
        }
        this.selectorThreadId = selectorThread.getId();

        updateThread();
    }

    void updateThread() {
        LibertyThread run1 = new LibertyThread();
        this.selectorThread = new Thread(run1);
        this.selectorThreadId = selectorThread.getId();
    }
    // kkhg kdhjwhukeyw ewuewue{ ewwew}W ewe

    static class LibertyThread implements Runnable {

        LibertyThread() {

        }

        @Override
        public void run() {
            System.out.println("Runnable running");
        }
    }

}
