package ua.com.alevel.Exceptions;

public class ExceptionNix extends Exception{
    public static final String message = "You do something wrong: ";

    public ExceptionNix(String ex){
        super(message + ex);
    }
}
