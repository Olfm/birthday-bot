import java.util.Date;
import java.util.List;

public class Person {
    public String name;
    public Date birthday;
    public int chatId;
    public List<String> alterNames;

    public String getName(){
        return name;
    }
    public Date getBirthday(){
        return birthday;
    }

    public int getChatId(){
        return chatId;
    }

    public List<String> getAlterNames(){
        return alterNames;
    }



}
