package net;

import java.util.ArrayList;

import com.google.gson.Gson;

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

    public String writeUser(ArrayList<String> dataUser) {
        return gson.toJson(dataUser);
    }

}
