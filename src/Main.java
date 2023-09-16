package src;

public class Main {

    public static void main(String[] args) {
        // Creating a new thread to start running the window
        Window window = new Window();
        Thread t1 = new Thread(window);
        t1.start();

    }
}
