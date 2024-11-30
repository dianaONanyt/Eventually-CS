package net;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GSONManager {

    private Gson gson;

    public GSONManager() {
        gson = new Gson();
    }

    public String writeMessage(String message) {
        return gson.toJson(new Message(message));
    }

    public Message readMessage(String jsonMessage) {
        return gson.fromJson(jsonMessage, Message.class);
    }

    public String writeArray(ArrayList<String> jsonArray) {
        return gson.toJson(jsonArray);
    }

    public ArrayList<String> readArray(String jsonArray) {
        Type info = new TypeToken<ArrayList<String>>() {
        }.getType();
        return gson.fromJson(jsonArray, info);
    }

    public String writeList(List<String> list) {
        return gson.toJson(list);
    }

}
