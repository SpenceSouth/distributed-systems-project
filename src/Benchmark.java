import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.io.FileWriter;

/**
 * Created by spencesouthard on 2/15/16.
 */
public class Benchmark {

    private static ArrayList<Float> executionTime = new ArrayList<Float>();
    private static ArrayList<Float> bindingTime = new ArrayList<Float>();

    public static void main(String args[]){

        ExecutorService executorService;
        int maxClients = 0;

        for(int run = 0; run < 5; run++) {
            for (int scale = 0; scale < 6; scale++) {

                if (scale == 0) {
                    maxClients = 1;
                }
                if (scale == 1) {
                    maxClients = 5;
                }
                if (scale == 2) {
                    maxClients = 10;
                }
                if (scale == 3) {
                    maxClients = 20;
                }
                if (scale == 4) {
                    maxClients = 50;
                }
                if (scale == 5) {
                    maxClients = 100;
                }

                for (int option = 1; option < 4; option++) {

                    executorService = Executors.newFixedThreadPool(100);

                    for (int clients = 0; clients < maxClients; clients++) {
                        executorService.execute(new ClientThread(option));
                    }

                    executorService.shutdown();

                    try {
                        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

                        String optionString = "";

                        if (option == 1) {
                            optionString = "read";
                        } else if (option == 2) {
                            optionString = "update";
                        } else {
                            optionString = "list";
                        }

                        FileWriter writer = new FileWriter("data/rmi_run-" + run + "_action-" + optionString + "_clients-" + maxClients + ".txt");
                        for (float f : executionTime) {
                            writer.write(Float.toString(f) + "\n");
                        }
                        writer.close();

                        FileWriter w = new FileWriter("data/bindingtime_run-" + run + "_action-" + optionString + "_clients-" + maxClients + ".txt");
                        for (float f : bindingTime) {
                            w.write(Float.toString(f) + "\n");
                        }
                        w.close();



                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        executionTime.clear();
                        bindingTime.clear();
                    }
                }
            }
        }


        // Data test
        try {
            UserMenu userMenu = new UserMenu();
            long startTime = System.currentTimeMillis();
            long estimatedTime = System.currentTimeMillis() - startTime;
            ArrayList<Float> one = new ArrayList<Float>();
            ArrayList<Float> ten = new ArrayList<Float>();
            ArrayList<Float> twenty = new ArrayList<Float>();
            ArrayList<Float> thirty = new ArrayList<Float>();
            ArrayList<Float> fifty = new ArrayList<Float>();
            ArrayList<Float> onehundred = new ArrayList<Float>();



            for(int run = 0; run < 5; run++) {

                startTime = System.currentTimeMillis();
                userMenu.list(true);
                estimatedTime = System.currentTimeMillis() - startTime;
                one.add((float)estimatedTime);

                startTime = System.currentTimeMillis();
                for (int i = 0; i < 10; i++) {
                    userMenu.list(true);
                }
                estimatedTime = System.currentTimeMillis() - startTime;
                ten.add((float)estimatedTime);

                startTime = System.currentTimeMillis();
                for (int i = 0; i < 20; i++) {
                    userMenu.list(true);
                }
                estimatedTime = System.currentTimeMillis() - startTime;
                twenty.add((float)estimatedTime);

                startTime = System.currentTimeMillis();
                for (int i = 0; i < 30; i++) {
                    userMenu.list(true);
                }
                estimatedTime = System.currentTimeMillis() - startTime;
                thirty.add((float)estimatedTime);

                startTime = System.currentTimeMillis();
                for (int i = 0; i < 50; i++) {
                    userMenu.list(true);
                }
                estimatedTime = System.currentTimeMillis() - startTime;
                fifty.add((float)estimatedTime);

                startTime = System.currentTimeMillis();
                for (int i = 0; i < 100; i++) {
                    userMenu.list(true);
                }
                estimatedTime = System.currentTimeMillis() - startTime;
                onehundred.add((float)estimatedTime);

            }

            FileWriter w = new FileWriter("data/onemb.txt");
            for (float f : one) {
                w.write(Float.toString(f) + "\n");
            }
            w.close();

            w = new FileWriter("data/tenmb.txt");
            for (float f : ten) {
                w.write(Float.toString(f) + "\n");
            }
            w.close();

            w = new FileWriter("data/twentymb.txt");
            for (float f : twenty) {
                w.write(Float.toString(f) + "\n");
            }
            w.close();

            w = new FileWriter("data/thirtymb.txt");
            for (float f : thirty) {
                w.write(Float.toString(f) + "\n");
            }
            w.close();

            w = new FileWriter("data/fiftymb.txt");
            for (float f : fifty) {
                w.write(Float.toString(f) + "\n");
            }
            w.close();

            w = new FileWriter("data/onehundredmb.txt");
            for (float f : onehundred) {
                w.write(Float.toString(f) + "\n");
            }
            w.close();

        }
        catch(Exception ex){
            ex.printStackTrace();
        }

    }

    public static synchronized void addExecutionTime(float ms){
        executionTime.add(ms);
    }

    public static synchronized void addBindingTime(float ms){
        bindingTime.add(ms);
    }

}

class ClientThread implements Runnable{

    int option;

    public ClientThread(int option){
        this.option = option;
    }

    @Override
    public void run() {
        try {
            long startTime = System.currentTimeMillis();
            UserMenu userMenu = new UserMenu();
            long estimatedTime = System.currentTimeMillis() - startTime;
            Benchmark.addBindingTime(estimatedTime);

            switch (option){
                case(1):
                    Random random = new Random();
                    int id = random.nextInt(10 - 1 + 1) + 1;
                    startTime = System.currentTimeMillis();
                    userMenu.read(id);
                    estimatedTime = System.currentTimeMillis() - startTime;
                    break;
                case(2):
                    startTime = System.currentTimeMillis();
                    userMenu.update(1, "Spence", "12656 SE 53ct Belleview, FL 34420");
                    estimatedTime = System.currentTimeMillis() - startTime;
                    break;
                case(3):
                    startTime = System.currentTimeMillis();
                    userMenu.list(true);
                    estimatedTime = System.currentTimeMillis() - startTime;
                    break;
                default:
                    break;
            }

            //Add benchmark
            Benchmark.addExecutionTime(estimatedTime);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
