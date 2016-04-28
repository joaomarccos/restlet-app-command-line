package br.edu.ifpb.pos.util;

import br.edu.ifpb.pos.entitys.Person;
import br.edu.ifpb.pos.entitys.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author João Marcos <joaomarccos.github.io>
 */
public class JsonConverter {

    public static Person convertToPerson(String json) {
        Gson gson = new GsonBuilder().create();
        Person person = gson.fromJson(json, Person.class);
        return person;
    }

    public static User convertToUser(String json) {
        Gson gson = new GsonBuilder().create();
        User user = gson.fromJson(json, User.class);
        return user;
    }
        
    public static String convertToJson(Object obj) {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(obj);
    }
}
