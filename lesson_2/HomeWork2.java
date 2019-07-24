package Java2.lesson_2;

public class HomeWork2 {
    public static void main(String[] args) {

        String[][] arr;

        String str = "k 3 1 2\n2 3 2 2\n5 6 7 1\n3 3 1 0";

        try {
            arr = StringToArr(str);
            for (int i = 0; i < arr.length; i++){
                for (int k = 0; k < arr[i].length; k++){
                    System.out.print(arr[i][k] + " ");
                }
                System.out.println();
            }
        } catch (ArraySizeException e){
            System.out.println(e.getErrorMessage());
        } catch (NotIntException e){
            System.out.println(e.getErrorMessage());
        }


    }

    public static String[][] StringToArr(String s) throws ArraySizeException, NotIntException{

        int length;
        String[] transitArr = s.split("\n");
        String[][] retArr = new String[transitArr.length][];

        for (int i = 0; i < transitArr.length; i++){

            retArr[i] = transitArr[i].split(" ");
            length = retArr[i].length;

            if (length != 4){
                throw new ArraySizeException("Размер массива не равен 4х4!");
            }

            for (int k = 0; k < length; k++){
                try {
                    Integer.parseInt(retArr[i][k]);
                } catch (NumberFormatException e)
                {
                    throw new NotIntException("В массиве присутствуют не только числа!");
                }

            }

        }

        return retArr;

    }
}
