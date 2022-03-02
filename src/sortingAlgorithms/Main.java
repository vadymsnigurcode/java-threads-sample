package sortingAlgorithms;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class Main {


    static int[] AS = null;

    public static void main(String[] args) {
        Utils utils = new Utils();
        AS = utils.generate();

        String[] AS1 = {"abc", "def", "bcd", "dsd", "aff", "jkl", "ffg"};
        String[] AS2 = {"abc", "def", "bcd", "dsd", "aff", "jkl", "ffg"};
        String[] AS3 = {"abc", "def", "bcd", "dsd", "aff", "jkl", "ffg"};

        // 2. Оголосити екземпляри класів-потоків
        SelectionSortThread t1 = new SelectionSortThread(AS1, "t1:SelectionSort");
        InsertionSortThread t2 = new InsertionSortThread(AS2, "t2:InsertionSort");
        BubbleSortThread t3 = new BubbleSortThread(AS3, "t3:BubbleSort");

        // 3. Запустити потоки t1, t2, t3 паралельно
        t1.start();
        t2.start();
        t3.start();

        // 4. Дочекатись завершення потоків t1, t2, t3, щоб отримати коректний результат
        try {
            t1.getThread().join();
            t2.getThread().join();
            t3.getThread().join();
        } catch (InterruptedException e) {
            // Якщо помилка, то вивести повідомлення
            System.out.println("Error: " + e.getMessage());
            return;
        }

        // 5. Вивести посортовані масиви на екран
        t1.Print();
        t2.Print();
        t3.Print();
    }
}

class Utils {
    Random random = new Random();
    int max = 100;
    int min = 10;

    public int[] generate() {
        int itemsCount = random.nextInt(max - min) + min;
        int[] randomArray = new int[itemsCount];
        for (int k = 0; k < itemsCount; k++) {
            randomArray[k] = random.nextInt(max - min) + min;
        }
        return randomArray;
    }
}

class SelectionSortThread extends Thread {
    private String[] AS; // масив рядків
    private Thread t; // посилання на поточний потік

    public SelectionSortThread(String[] AS, String threadName) {
        // Зберегти посилання на зовнішній масив
        this.AS = AS;

        // Створити потік
        t = new Thread(this, threadName);
    }

    // Метод, що запускає потік
    public void start() {
        t.start(); // виклик методу класу Thread
    }

    // Метод, що виконується в потоці
    public void run() {
        Instant start = Instant.now();
        // Посортувати рядки методом сортування вибором

        // 1. Сповістити про початок сортування
        System.out.println("Begin => " + t.getName());

        // 2. Оголосити додаткові внутрішні змінні
        int i, j, k;
        String s;

        // 3. Цикл сортування вибором в порядку спадання рядків
        for (i = 0; i < AS.length; i++) {
            // i - поточний крок
            k = i;

            // Пошук найбільшого (максимального) елементу
            s = AS[i];

            for (j = i + 1; j < AS.length; j++)
                if (AS[j].compareTo(s) > 0) {
                    k = j; // індекс максимального елементу
                    s = AS[j];
                }

            // Обміняти місцями максимальний елемент з AS[i]
            AS[k] = AS[i];
            AS[i] = s;
        }

        // 4. Сповістити про закінчення потоку
        System.out.println("End => " + t.getName());

        Instant end = Instant.now();
        System.out.println("SelectionSort " + Duration.between(start, end).toMillis() + " ms.");
    }

    // Метод доступу до масиву AS
    public String[] get() {
        return AS;
    }

    // Метод доступу до потоку t
    public Thread getThread() {
        return t;
    }

    // Метод, що виводить масив AS на екран
    public void Print() {
        System.out.print(t.getName() + " = [ ");
        for (String s : AS)
            System.out.print(s + " ");
        System.out.println(" ]");
    }
}

// Клас, що інкапсулює потік, в якому сортується масив
// рядків методом вставок. Потік створюється шляхом успадкування класу Thread.
// Сортування відбувається у порядку спадання елементів.
class InsertionSortThread extends Thread {
    // Внутрішні поля класу
    private String[] AS; // масив рядків
    private Thread t; // поточний потік

    public InsertionSortThread(String[] AS, String threadName) {
        // Зберегти посилання на зовнішній масив
        this.AS = AS;

        // Створити потік
        t = new Thread(this, threadName);
    }

    // Метод, що запускає потік з клієнтського коду
    public void start() {
        t.start(); // неявний виклик методу run()
    }

    // Метод, що запускає потік з клієнтського коду
    public void run() {
        Instant start = Instant.now();
        // Тут потрібно реалізувати сортування методом вставки
        // 1. Сповістити про початок сортування
        System.out.println("Begin => " + t.getName());

        // 2. Оголосити внутрішні змінні
        int i, j;
        String s;

        // 3. Цикл сортування вставками
        for (i = 0; i < AS.length; i++) {
            // i - номер проходу
            s = AS[i];

            // Пошук місця елементу в послідовності
            for (j = i - 1; j >= 0 && AS[j].compareTo(s) < 0; j--) {
                // зсувати елемент вправо наскільки можливо
                AS[j + 1] = AS[j];
            }

            AS[j + 1] = s;
        }

        // 4. Сповістити про закінчення сортування
        System.out.println("End => " + t.getName());

        Instant end = Instant.now();
        System.out.println("InsertionSort " + Duration.between(start, end).toMillis() + " ms.");

    }

    // Метод доступу до масиву AS
    public String[] get() {
        return AS;
    }

    // Метод доступ до потоку t
    public Thread getThread() {
        return t;
    }

    // Метод, що виводить масив AS на екран
    public void Print() {
        System.out.print(t.getName() + " = [ ");
        for (String s : AS)
            System.out.print(s + " ");
        System.out.println(" ]");
    }
}

// Клас, що інкапсулює потік, в якому відбувається сортування бульбашкою.
// Сортування рядків відбувається у спадному порядку.
// Клас реалізує інтерфейс Runnable
class BubbleSortThread extends Thread {

    // Внутрішні змінні класу
    private String[] AS; // масив рядків
    private Thread t; // посилання на поточний потік

    // Конструктор, отримує 2 параметри:
    // - масив рядків;
    // - ім'я поточного потоку.
    public BubbleSortThread(String[] AS, String threadName) {
        // Зберегти зовнішній масив
        this.AS = AS;

        // Створити новий потік
        t = new Thread(this, threadName);
    }

    // Метод, що запускає потік на виконання
    public void start() {
        t.start();
    }

    // Метод, в якому виконується потік
    public void run() {
        Instant start = Instant.now();
        // Сортування методом бульбашки
        // 1. Сповістити про початок сортування
        System.out.println("Begin => " + t.getName());

        // 2. Оголосити внутрішні змінні
        int i, j;
        String s;

        // 3. Цикл сортування
        for (i = 0; i < AS.length; i++) {
            // i - номер проходу

            for (j = AS.length - 1; j > i; j--) {
                // внутрішній цикл проходу
                // сортування за спаданням
                if (AS[j - 1].compareTo(AS[j]) < 0) {
                    s = AS[j];
                    AS[j] = AS[j - 1];
                    AS[j - 1] = s;
                }
            }
        }

        // 4. Сповістити про кінець сортування
        System.out.println("End => " + t.getName());

        Instant end = Instant.now();
        System.out.println("BubbleSort " + Duration.between(start, end).toMillis() + " ms.");

    }

    // Метод доступу до масиву AS
    public String[] get() {
        return AS;
    }

    // Метод доступ до потоку t
    public Thread getThread() {
        return t;
    }

    // Метод, що виводить масив AS на екран
    public void Print() {
        System.out.print(t.getName() + " = [ ");
        for (String s : AS)
            System.out.print(s + " ");
        System.out.println(" ]");
    }
}
