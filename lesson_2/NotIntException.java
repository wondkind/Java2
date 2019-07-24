package Java2.lesson_2;

public class NotIntException extends Exception {

    private String errorMessage;

    NotIntException(String s){
        super(s);
        errorMessage = s;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
