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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class DrBot extends TelegramLongPollingBot {

    public static void main(String[] args) throws IOException {



            ApiContextInitializer.init();
            TelegramBotsApi telegramBotApi = new TelegramBotsApi();

            try {
                telegramBotApi.registerBot(new DrBot());
            } catch (TelegramApiRequestException e) {
                e.printStackTrace();
            }

        }




    void sendMsg(Message message, String text) {
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



    String i = "";




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
                        for (Person p : readPersons()) {
                            i += p.firstName + " " + p.lastName + " — " + "\t" + p.birthday + "\n";
                        }
                        sendMsg(message, i);
                        i = "";
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
                        break;

                }
            }
    }

    @SneakyThrows
    private static List<Person> readPersons() {
        ObjectMapper om = new ObjectMapper();
        String filename = "src/main/resources/persons.json";
        return om.readValue(new FileInputStream(filename), new TypeReference<List<Person>>() {
        });
    }


    @SuppressWarnings("src/main/resources/persons.json")
    public static void writeValue(Person person) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("src/main/resources/persons.json"), person);
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
        keyboardTwoRow.add(new KeyboardButton("Мой WishList"));

        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardTwoRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }



    public String getBotUsername() {
        return "Back_DR_Bot";
    }

    public String getBotToken() {
        return "820777468:AAH5Yf7kD1ykCKhzd-NlHr2ER7ehndB1-2c";
    }


}
