package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.events.Event;

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

    private ArrayList<String> readArrayList() throws IOException {
        return gsonManager.readArray(inData.readUTF());
    }

    private String writeArrayList(ArrayList<String> list) {
        return gsonManager.writeArray(list);
    }

    private void sendArray(ArrayList<String> array) throws IOException {
        String arrayString = writeArrayList(array);
        outData.writeUTF(arrayString);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        System.out.println("comand actual: " + command);
        if (command.equals("backToEvents")) {
            showEvents();
        } else if (command.contains("masInfo")) {
            getEventByCommand(e);
            showEventInfo();
        } else if (command.contains("shopEvent")) {
            getEventByCommand(e);
            showSectionsFromEvent();
        } else if (command.contains("click")) {
            getEventByCommandSelectSection(e);
            getSectionByCommand(e);
            showSeatsFromSectionSelected();
            // } else if (command.contains("shopSeat")) {
            // Event eventSelected = getEventByCommandSectionPanel(e);
            // Section sectionSelected = getSectionByCommandSectionPanel(eventSelected, e);
            // buyTickets(eventSelected, sectionSelected);
        } else if (command.equals("logIn"))

        {
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

    public void showSeatsFromSectionSelected() {
        try {
            ArrayList<String> infoSeats = readArrayList();
            ArrayList<String> dataEvent = readArrayList();
            view.setInfoSeats(infoSeats, dataEvent, this);
            view.showSection(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void getSectionByCommand(ActionEvent e) {
        String nameButton = e.getActionCommand();
        String[] names = nameButton.split("///");
        String nameSection = names[1];
        writeMessage(nameSection);
    }

    private void getEventByCommandSelectSection(ActionEvent e) {
        String nameButton = e.getActionCommand();
        writeMessage(nameButton);
        String[] names = nameButton.split("///");
        String nameEvent = names[2];
        writeMessage(nameEvent);
    }

    public void showLogIn() {
        view.showLogIn(this);
    }

    public void showSectionsFromEvent() {
        try {
            String nullString = readMessage();
            if (nullString.equals("null")) {
                showLogIn();
            }
            String eventName = readMessage();
            ArrayList<String> sectionsString = readArrayList();
            ArrayList<String> sectionsAvailablesString = readArrayList();
            System.out.println("secciones disponibles: " + sectionsAvailablesString.toString());
            view.setInfoSections(eventName, sectionsString, sectionsAvailablesString, this);
            view.showSections(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showEventInfo() {
        try {
            System.out.println("entra show event info");
            ArrayList<String> falso = readArrayList();
            ArrayList<String> eventData = readArrayList();
            System.out.println(falso.toString());
            String classUser = readMessage();
            String nameEvent = readMessage();
            view.setInfoEvent(eventData, classUser, this);
            view.setNameEvent(nameEvent);
            view.showEventInfo(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getEventByCommand(ActionEvent e) {
        String nameButton = e.getActionCommand();
        System.out.println("Envía opción: " + nameButton);
        writeMessage(nameButton);
        String[] names = nameButton.split("///");
        String nameEvent = names[1];
        System.out.println("Envía opción: " + nameEvent);
        writeMessage(nameEvent);
        System.out.println("comando: " + names[0]);
        System.out.println("nombre seleccionado: " + nameEvent);
    }

    public void showEvents() {
        // writeMessage("backToEvents");
        System.out.println("entra aqui por backToEvents");
        try {
            ArrayList<String> infoEvents = readArrayList();
            String nickname = readMessage();
            String userType = readMessage();
            view.setInfoEvents(infoEvents, nickname, userType, this);
            view.showEvents(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean logIn() throws IOException {
        writeMessage("logIn");
        ArrayList<String> dataLogIn = view.getDataLogIn();
        System.out.println(dataLogIn.toString());
        sendArray(dataLogIn);
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
