package olfm.bdbot.main;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Person {
    public String firstName;
    public String lastName;
    public String birthday;
    public Long chatId;
    public List<String> alterNames;
    public List<String> wishList;
    public boolean admin;

}


