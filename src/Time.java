package src;

public class Time {
    //Return the starter time in nanoseconds
    public static double timeStarted = System.nanoTime();
    public static double getTime(){ return (System.nanoTime()-timeStarted)*1E-9; };
}
