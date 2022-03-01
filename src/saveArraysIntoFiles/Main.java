package saveArraysIntoFiles;

import java.io.*;
import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        int[] AI1 = {2, 4, 3, 8, 9, 11, 7};
        int[] AI2 = {1, 8, 7, 6, 3};
        int AI3[] = {7, 7, 9, 9, 4, 2};

        Thread t1 = new Thread(new SaveAsThread(AI1, "AI1.txt"));
        Thread t2 = new Thread(new SaveAsThread(AI2, "AI2.txt"));
        Thread t3 = new Thread(new SaveAsThread(AI3, "AI3.txt"));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }


}

class SaveAsThread implements Runnable {
    String fileName;
    int[] numbers;

    public SaveAsThread(int[] numbers, String fileName) {
        this.fileName = fileName;
        this.numbers = numbers;
    }

    @Override
    public void run() {
        try {
//            saveToFile1();
//            saveToFile2();
            saveToFile3();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile3() throws IOException {
        File fout = new File(fileName);
        FileOutputStream fos = new FileOutputStream(fout);

        OutputStreamWriter osw = new OutputStreamWriter(fos);
        try {
            osw.write(numbers.length + "\n");
            for (int item : numbers) {
                osw.write(item + "\n");
            }
            osw.close();
            System.out.println("file: " + fileName + " updated");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile2() throws IOException {
        FileWriter fw = new FileWriter(fileName);
        try {
            fw.write(String.valueOf(numbers.length));

            for (int item : numbers) {
                fw.write(String.valueOf(item));
            }
            fw.close();
            System.out.println("file: " + fileName + "updated");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile1() {
        File fout = new File(fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(fout);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(String.valueOf(numbers.length));
            bw.newLine();
            for (int item : numbers) {
                bw.write(String.valueOf(item));
                bw.newLine();
            }
            bw.close();
            System.out.println("file: " + fileName + "updated");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
