package Java2.lesson_3;

import java.util.*;

public class HomeWork3 {
    public static void main(String[] args) {

        String s = "ночь улица фонарь аптека бессмысленный тусклый свет живи еще хоть четверть века всё будет так исхода нет и повернется всё как встарь ночь ледяная рябь канала аптека улица фонарь";
        String[] arr = s.split(" ");

        HashMap<String, Integer> uniqWords = veryUniqueWords(arr);

        for (Map.Entry<String, Integer> entry: uniqWords.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        PhoneBookNew book = new PhoneBookNew();
        book.addContact("Иванов", "+79833011168", "lunat1k@ngs.ru");
        book.addContact("Иванов", "+79235933010", "wondkind@ngs.ru");
        book.addContact("Петрова", "+79963778300", "superpuper89@mail.ru");

        for (String str:
             book.getEmail("Иванов")) {

            System.out.println(str);

        }

    }

    //возвращает map, где ключ - слово, а значение - число слов в массиве
    public static HashMap<String, Integer> veryUniqueWords(String[] arr){

        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++){

            if (map.containsKey(arr[i])){
                map.put(arr[i], map.get(arr[i])+1);
            } else {
                map.put(arr[i], 1);
            }

        }

        return map;

    }
}
