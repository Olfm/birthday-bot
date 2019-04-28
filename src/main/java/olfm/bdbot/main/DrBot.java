package olfm.bdbot.main;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DrBot extends TelegramLongPollingBot {
    public static void main(String[] args) {


        ApiContextInitializer.init();
        TelegramBotsApi telegramBotApi = new TelegramBotsApi();
        try {
            telegramBotApi.registerBot(new DrBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }


    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(text);
        sendMessage.getChatId();


        try {
            setButton(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText().toLowerCase()) {
                case "/start":
                    boolean hello = true;
                    for (Person p : readPersons()) {
                        if (message.getChatId().equals(p.chatId)) {
                            sendMsg(message, "Привет, " + p.firstName + "! \n\nВ отличие от бывших я помню, что у тебя день рождения " + p.birthday + " :)" + "\n\nА еще подскажу, когда у коллег. ");
                            hello = true;
                        }
                    }
                    if (!hello) {
                        sendMsg(message, "Привет, я о тебе ничего не знаю, но о других напомню :)");
                    }
                    break;
                case "весь список":
                    String i = "";
                    for (Person p : readPersons()) {
                        i += p.firstName + " " + p.lastName + " — " + "\t" + p.birthday + "\n";
                    }
                    sendMsg(message, i);
                    break;
                case "миша":
                case "маша":
                case "любовь":
                case "люба":
                case "леша":
                case "алеша":
                case "алексей":
                    sendMsg(message, "Ты мне фамилию — я тебе дату");
                    break;
                default:
                    boolean answer = false;
                    for (Person p : readPersons()) {
                        Set<String> set = new HashSet<>(p.alterNames);
                        if (set.contains(message.getText().toLowerCase())) {
                            sendMsg(message, p.firstName + " будет отмечать др " + p.birthday);
                            answer = true;
                        }
                    }
                    if (!answer) {
                        sendMsg(message, "Я такого не знаю, прости");
                    }

                case "Ближайший ДР":
//                        for (Person p: readPersons()) {
//                            try {
//                                System.out.println(convertDate(p.birthday));
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
                    break;


            }
        }
    }

    @SneakyThrows
    public static List<Person> readPersons() {
        ObjectMapper om = new ObjectMapper();
        String filename = "src/main/resources/persons.json";
        return om.readValue(new FileInputStream(filename), new TypeReference<List<Person>>() {
        });
    }


    private void setButton(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);


        List<KeyboardRow> keyboardRowList = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardFirstRow.add(new KeyboardButton("Весь список"));
        keyboardFirstRow.add(new KeyboardButton("Ближайший ДР"));

        KeyboardRow keyboardTwoRow = new KeyboardRow();
//        keyboardTwoRow.add(new KeyboardButton("Мой WishList")); //это потом допилю, наверное

        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardTwoRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public  void notificationDr(){
        DateCheck dateCheck = new DateCheck();
        dateCheck.controlDate();
        if(dateCheck.timeTrouble){
            sendMsg(null, "Время создавать чать для дня рождения!");
            // доделать так, чтобы было минимум кода тут, основное в классе чек Дата
        }
    }


    public String getBotUsername() {
        return "Back_DR_Bot";
    }

    public String getBotToken() {
        return "820777468:AAH5Yf7kD1ykCKhzd-NlHr2ER7ehndB1-2c";
    }
}
