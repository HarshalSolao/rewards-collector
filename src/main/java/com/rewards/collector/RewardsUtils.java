package com.rewards.collector;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@Component
public class RewardsUtils {
    Map<String, String> accounts = new HashMap<String, String>() {{
        put("<email>", "<password>");
    }};
    Faker faker = new Faker();

    public  Map<String, String> getAccounts(){
        return accounts;
    }

    public String getFullName(){
        return faker.name().fullName();
    }
    public String getFirstName(){
        return faker.name().firstName();
    }

    public String getLastName(){
        return faker.name().lastName();
    }
}
