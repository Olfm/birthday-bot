package olfm.bdbot.main;

import java.util.HashMap;
import java.util.Map;

public class DrKekov {

    static HashMap<String, String> bDays = new HashMap<>();

    public void setbDays() {
        bDays.put("Тимофей Перов", "08.03");
        bDays.put("Оля Пронина", "20.07");
        bDays.put("Миша Ожегов", "11.01");
        bDays.put("Илья Илющенко", "13.01");
        bDays.put("Миша Суханов", "29.01");
        bDays.put("Светикова Любовь", "31.01");
        bDays.put("Маша Сибирева", "25.02");
        bDays.put("Данилин Володя", "16.04");
        bDays.put("Мария Федорова", "18.04");
        bDays.put("Вихрева Аня", "07.09");
        bDays.put("Ваняшин Сережа", "11.09");
        bDays.put("Белов Леша", "03.10");
        bDays.put("Хващевский Дима", "10.10");
        bDays.put("Виктория Афанасьева", "20.10");
        bDays.put("Рудюк Паша", "22.11");
        bDays.put("Миша Яцко", "29.11");
        bDays.put("Любовь Патык", "30.11");
        bDays.put("Алексей Свирин", "06.12");
        bDays.put("Сашка Талалайкин", "22.01");
        bDays.put("Натали Рубайлова", "14.08");
        bDays.put("Кошевая Анастасия", "20.04");
        bDays.put("Гуреева Ксюша", "27.06");

        bDays.entrySet().stream().sorted(Map.Entry.<String, String>comparingByValue().reversed());
    }
}