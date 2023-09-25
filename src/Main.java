package src;

public class Main {

    public static  int state=0;
    public static Thread mainThread ;
    public static MainMenu menu;
    public static Window window;
    public static void main(String[] args) {
        // Creating a new thread to start running the window
         menu = new MainMenu();

//
        mainThread = new Thread(menu);
        mainThread.start();

    }
    public  static  void changeState(int newState){
        if(newState==1 && state==0){
            menu.stop();
            window = new Window();
            mainThread= new Thread(window);
            mainThread.start();
        }else if(newState==0 && state==1){
            window.stop();
        }
    }
}
