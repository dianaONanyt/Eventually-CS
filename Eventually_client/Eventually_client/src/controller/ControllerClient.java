package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.events.Event;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.GSONManager;
import view.View;

public class ControllerClient implements ActionListener {

    private final int PORT = 12345;
    private final String HOST = "localhost";
    private Socket socket;
    private DataOutputStream outData;
    private DataInputStream inData;
    private View view;
    private GSONManager gsonManager;

    public ControllerClient() {
        try {
            socket = new Socket(HOST, PORT);
            outData = new DataOutputStream(socket.getOutputStream());
            inData = new DataInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.view = new View(this);
        this.gsonManager = new GSONManager();
    }

    public static void main(String[] args) {
        ControllerClient client = new ControllerClient();
        client.run();
    }

    public void run() {

    }

    private void writeMessage(String message) {
        try {
            outData.writeUTF(gsonManager.writeMessage(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readMessage() throws IOException {
        return gsonManager.readMessage(inData.readUTF()).getText();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println("comand actual: " + command);
        if (command.equals("backToEvents")) {
            showEvents();
            // } else if (command.contains("masInfo")) {
            // Event eventSelected = getEventByCommand(e);
            // showEventInfo(eventSelected);
            // } else if (command.contains("shopEvent")) {
            // Event eventSelected = getEventByCommand(e);
            // showSectionsFromEvent(eventSelected);
            // } else if (command.contains("click")) {
            // Event eventSelected = getEventByCommandSelectSection(e);
            // Section sectionSelected = getSectionByCommand(eventSelected, e);
            // showSeatsFromSectionSelected(sectionSelected, eventSelected);
            // } else if (command.contains("shopSeat")) {
            // Event eventSelected = getEventByCommandSectionPanel(e);
            // Section sectionSelected = getSectionByCommandSectionPanel(eventSelected, e);
            // buyTickets(eventSelected, sectionSelected);
        } else if (command.equals("logIn")) {
            try {
                if (logIn()) {
                    showEvents();
                } else {
                    view.showWarningMessage("No se puede iniciar sesion");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        // } else if (command.equals("createAccount")) {
        // showCreateAccount();
        // } else if (command.equals("createNewAccount")) {
        // if (createAccount()) {
        // showLogIn();
        // } else {
        // view.showWarningMessage("No se pudo crear la cuenta");
        // }
        // } else if (command.equals("showProfile")) {
        // showProfile();
        // } else if (command.equals("logOut")) {
        // logOut();
        // } else if (command.equals("backFromProfile")) {
        // showLogIn();
        // } else if (command.equals("addCard")) {
        // showCardInfo();
        // } else if (command.equals("addNewCard")) {
        // hideCardInfo();
        // addCard();
        // } else if (command.equals("addEvent")) {
        // showAddEvent();
        // } else if (command.equals("addNewEvent")) {
        // addEvent();
        // } else if (command.contains("changeSectionsAvailables")) {
        // Event event = getEventByCommandChangeSections(e);
        // view.showMessage("cambio de secciones a evento " + event.getName());
        // } else if (command.contains("deleteEvent")) {
        // Event event = getEventByCommandChangeSections(e);
        // deleteEvent(event);
        // }
    }

    public void showEvents() {
        System.out.println("entra aqui por backToEvents");
        writeMessage("");

        // view.setInfoEvents(infoEvents, user.getNickname(), userType, this);
        view.showEvents(this);
    }

    public boolean logIn() throws IOException {
        writeMessage("logIn");
        ArrayList<String> dataLogIn = view.getDataLogIn();
        outData.writeUTF(gsonManager.writeUser(dataLogIn));
        boolean validation = inData.readBoolean();
        return validation;

        // String nickname = dataLogIn.get(0);
        // System.out.println(nickname);
        // String password = dataLogIn.get(1);
        // System.out.println(password);

        // boolean validation = pSystem.logIn(nickname, password);
        // if (validation) {
        // User actual = pSystem.getUser(nickname);
        // pSystem.setActualUser(actual);
        // return true;
        // }
        // return false;
    }

}
