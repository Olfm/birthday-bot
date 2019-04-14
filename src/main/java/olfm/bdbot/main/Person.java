package olfm.bdbot.main;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Person {
    public String name;
    public String birthday;
    public int chatId;
    public List<String> alterNames;
}
