package Java2.lesson_3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PhoneBookNew {

    private HashMap<String, ArrayList<HashMap<String, String>>> phoneBook;
    private String typePhone = "Phone";
    private String typeEmail = "Email";

    PhoneBookNew(){
        phoneBook = new HashMap<>();
    }

    public void addContact(String lastName, String phone, String email){

        ArrayList<HashMap<String, String>> arrList;
        HashMap<String, String> map = new HashMap<>();
        if (phoneBook.containsKey(lastName)){
            arrList = phoneBook.get(lastName);
        } else {
            arrList = new ArrayList<>();
        }
        map.put(typePhone, phone);
        map.put(typeEmail, email);
        arrList.add(map);
        phoneBook.put(lastName, arrList);

    }

    public ArrayList<String> getEmail(String lastName){

       return getContact(lastName, typeEmail);

    }

    public ArrayList<String> getPhone(String lastName){

        return getContact(lastName, typePhone);

    }

    private ArrayList<String> getContact(String lastName, String typeOfContract){

        ArrayList<String> retArrList = new ArrayList<>();
        if (phoneBook.containsKey(lastName)){

            ArrayList<HashMap<String, String>> arr;
            arr = phoneBook.get(lastName);
            Iterator<HashMap<String, String>> iterator = arr.iterator();
            HashMap<String, String> itMap;

            while (iterator.hasNext()){
                itMap = iterator.next();
                if (itMap.containsKey(typeOfContract)){
                    retArrList.add(itMap.get(typeOfContract));
                }

            }

        }
        return retArrList;

    }

}
