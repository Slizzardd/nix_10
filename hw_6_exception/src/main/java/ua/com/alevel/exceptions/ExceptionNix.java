package ua.com.alevel.exceptions;

public class ExceptionNix extends Exception{

    public static final String message = "You do something wrong: ";

    public ExceptionNix(String ex){
        super(message + ex);
    }
}
