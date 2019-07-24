package Java2.lesson_2;

public class ArraySizeException extends Exception {

    private String errorMessage;

    ArraySizeException(String s){
        super(s);
        errorMessage = s;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}
