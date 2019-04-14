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

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DrBot extends TelegramLongPollingBot {
    static DrKekov drKekov = new DrKekov();

    public static void main(String[] args) throws IOException {



        drKekov.setbDays();
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
//                sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

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
                    sendMsg(message, "Напиши, чей др узнать хочешь ты");
                    break;
                case "тим":
                case "тимофей":
                case "тимоша":
                case "петров":
                case "кэп":
                case "мама":
                    sendMsg(message, "Др Тима: " + drKekov.bDays.get("Тимофей Перов"));
                    break;
                case "оля":
                case "водка":
                case "пронина":
                    sendMsg(message, "Др Оли: " + drKekov.bDays.get("Оля Пронина"));
                    break;
                case "миша ожегов":
                case "ожегов":
                    sendMsg(message, ("Др Миши Ожегова: " + drKekov.bDays.get("Миша Ожегов")));
                    break;
                case "илюша":
                case "илья":
                case "илющенко":
                case "илья илющенко":
                    sendMsg(message, ("Др Илюши: " + drKekov.bDays.get("Илья Илющенко")));
                    break;
                case "суханов миша":
                case "суханов":
                    sendMsg(message, ("Др Миши Суханова: " + drKekov.bDays.get("Миша Суханов")));
                    break;
                case "светикова":
                case "светикова любовь":
                case "любовь светикова":
                    sendMsg(message, ("Др Любови Светиковой: " + drKekov.bDays.get("Светикова Любовь")));
                    break;
                case "сибирева":
                case "сибирёва":
                case "маша сибирева":
                    sendMsg(message, ("Др Маши Сибиревой: " + drKekov.bDays.get("Маша Сибирева")));
                    break;
                case "данила":
                case "долодя":
                case "ванила":
                case "долодя ванилин":
                case "володя данилин":
                case "володя":
                case "данилин":
                case "вова":
                case "вовк":
                case "владимир":
                case "владимир михайлович":
                    sendMsg(message, ("Др Долоди Ванилина: " + drKekov.bDays.get("Данилин Володя")));
                    break;
                case "маруся":
                case "маша федорова":
                case "мария федорова":
                case "федорова":
                case "фёдорова":
                    sendMsg(message, ("Др Маруси: " + drKekov.bDays.get("Мария Федорова")));
                    break;
                case "аня":
                case "анна":
                case "аня вихрева":
                case "анна вихрева":
                    sendMsg(message, ("Др Ани: " + drKekov.bDays.get("Вихрева Аня")));
                    break;
                case "ваняшин":
                case "ваняша":
                case "ваня":
                case "ваня сережин":
                case "сережа ваняшин":
                    sendMsg(message, ("Др Сережи: " + drKekov.bDays.get("Ваняшин Сережа")));
                    break;
                case "леша белов":
                case "белов":
                    sendMsg(message, ("Др Леши Белова: " + drKekov.bDays.get("Белов Леша")));
                    break;
                case "хващевский":
                case "хващевский дима":
                case "дима":
                    sendMsg(message, ("Др Димки: " + drKekov.bDays.get("Хващевский Дима")));
                    break;
                case "вика":
                case "виктория":
                    sendMsg(message, ("Др Вики: " + drKekov.bDays.get("Виктория Афанасьева")));
                    break;
                case "паша":
                case "дед":
                case "старик":
                case "старикан":
                case "динозавр":
                case "trex":
                case "паша рудюк":
                case "павлуша":
                case "павлуха":
                    sendMsg(message, ("Др Павлушки: " + drKekov.bDays.get("Рудюк Паша")));
                    break;
                case "миша яцко":
                case "яцко":
                    sendMsg(message, ("Др Миши Яцко: " + drKekov.bDays.get("Миша Яцко")));
                    break;
                case "любовь патык":
                case "люба патык":
                case "патык":
                case "любовь степановна":
                case "степановна":
                case "патык любовь степановна":
                    sendMsg(message, ("Др Любы Патык: " + drKekov.bDays.get("Любовь Патык")));
                    break;
                case "свирин":
                case "леша свирин":
                    sendMsg(message, ("Др Алеши Свирина: " + drKekov.bDays.get("Алексей Свирин")));
                    break;
                case "пес":
                case "@":
                case "талалайкин":
                case "алекс":
                case "alex":
                case "алешка":
                case "алёшка":
                    sendMsg(message, ("Др Алекса: " + drKekov.bDays.get("Сашка Талалайкин")));
                    break;
                case "натали":
                case "нутелаи":
                case "тоха":
                case "антоха":
                    sendMsg(message, ("Др Натали: " + drKekov.bDays.get("Натали Рубайлова")));
                    break;
                case "настя":
                case "настасья":
                case "кошевая":
                case "персик":
                case "настя кошевая":
                case "анастасия кошевая":
                    sendMsg(message, ("Др Настасьи: " + drKekov.bDays.get("Кошевая Анастасия")));
                case "ксюша":
                case "ксюха":
                case "гурехина":
                case "гзюжа":
                case "гуреева":
                case "ксюша гуреева":
                case "ксения артемовна":
                case "ксения артёмовна":
                    sendMsg(message, ("Др Ксюши Гуреевой: " + drKekov.bDays.get("Гуреева Ксюша")));
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
                case "весь список":
                    for (Map.Entry<String, String> entry : DrKekov.bDays.entrySet()) {
                        String key = entry.getKey();
                        Object value = entry.getValue();
                        System.out.println(key + value);
                    }
                    break;

                case "ближайший др":
//                    Map<String, Date> map = convert(drKekov.setbDays());
//                    for (Date entry : map.values()) {
//                        if (entry - +) {
//
//                        }
//                    }


                default:
                    sendMsg(message, "Не знаю");

            }
        }
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
        keyboardTwoRow.add(new KeyboardButton("Кто в тусовке?"));

        keyboardRowList.add(keyboardFirstRow);
        keyboardRowList.add(keyboardTwoRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public static Map<String, Date> convert(Map<String, String> given) throws ParseException {
        Map<String, Date> ret = new HashMap<>();
        DateFormat format = new SimpleDateFormat("dd.mm", Locale.ENGLISH);
        for (String key : given.keySet()) {
            ret.put(key, format.parse(given.get(key)));
        }
        return ret;
    }


    public String getBotUsername() {
        return "Back_DR_Bot";
    }

    public String getBotToken() {
        return "820777468:AAH5Yf7kD1ykCKhzd-NlHr2ER7ehndB1-2c";
    }
}
