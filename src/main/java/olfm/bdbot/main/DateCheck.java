package olfm.bdbot.main;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TreeMap;

import static olfm.bdbot.main.DrBot.readPersons;

public class DateCheck {

    public String lastNameToPush;

    TreeMap<java.util.Date, String> people = new TreeMap<>();
    java.util.Date dateNow = new java.util.Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM");


    public void controlDate() {
        for (Person p : readPersons()) {
            java.util.Date date = simpleDateFormat.parse(p.birthday, new ParsePosition(0));
            String format = simpleDateFormat.format(date);
            date.setYear(dateNow.getYear());
            people.put(date, p.lastName);
        }

        for (Map.Entry<java.util.Date, String> entry : people.entrySet()) {
            java.util.Date key = entry.getKey();

            if ((int)((dateNow.getTime() - key.getTime()) / (24 * 60 * 60 * 1000)) == 14) {
                // добавить пробежку по массиву, чтобы понимать, у кого др и в чей чат ИД не отправлять рассылку
                lastNameToPush = entry.getValue();
                DrBot.scenario = "PushMe";


            }
        }
//        return timeTrouble;
    }
}
