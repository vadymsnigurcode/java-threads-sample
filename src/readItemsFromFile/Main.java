package readItemsFromFile;

import java.io.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        int[] AI1 = null;
        int[] AI2 = null;
        int AI3[] = null;
        ReadFromFileThread r1 = new ReadFromFileThread("AI1.txt");
        ReadFromFileThread r2 = new ReadFromFileThread("AI2.txt");
        ReadFromFileThread r3 = new ReadFromFileThread("AI3.txt");
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        Thread t3 = new Thread(r3);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
        AI1 = r1.getNumbers();
        System.out.println(Arrays.toString(AI1));
        AI2 = r2.getNumbers();
        System.out.println(Arrays.toString(AI2));
        AI3 = r3.getNumbers();
        System.out.println(Arrays.toString(AI3));
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());
    }


}

class ReadFromFileThread implements Runnable {
    String fileName;
    int[] numbers;

    public int[] getNumbers() {
        return numbers;
    }

    public ReadFromFileThread(String fileName) {
        this.fileName = fileName;
        this.numbers = numbers;
    }

    @Override
    public void run() {
        try {
//            saveToFile1();
//            saveToFile2();
            readFromFile3();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile3() throws IOException {

        try (BufferedReader br
                     = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            int index = 0;

            while ((line = br.readLine()) != null) {
                if (numbers == null) {
                    int itemsAmount = Integer.parseInt(line);
                    numbers = new int[itemsAmount];
                } else {
                    numbers[index] = Integer.parseInt(line);
                    index++;
                }
//                resultStringBuilder.append(line).append("\n");
            }
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
