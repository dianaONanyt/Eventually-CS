package view;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import controller.ControllerClient;
import view.frames.EventsFrame;

public class View {
    private EventsFrame events;

    public View(ActionListener actionListener) {
        events = new EventsFrame(actionListener);
        events.setVisible(true);
    }

    public void showSections(ControllerClient controller) {
        events.showSections(controller);
    }

    public void showEvents(ControllerClient test) {
        events.showEvents(test);
    }

    public void showProfile(ControllerClient controller) {
        events.showProfile(controller);
    }

    public void showBack(ControllerClient controller) {
        events.showEvents(controller);
    }

    public void showSection(ControllerClient controller) {
        events.showSection(controller);
    }

    public void showLogIn(ControllerClient controller) {
        events.showLogIn(controller);
    }

    public ArrayList<String> getDataLogIn() {
        return events.getDataLogIn();
    }

    public void showCreateAccount(ControllerClient controller) {
        events.showCreateAccount(controller);
    }

    public ArrayList<String> getDataNewUser() {
        return events.getDataNewUser();
    }

    public void showMainPage() {

    }

    public void setInfoEvents(ArrayList<String> dataEvents, String nickName, String userType,
            ControllerClient controller) {
        events.setEvents(dataEvents, nickName, userType, controller);
    }

    public void showEventInfo(ControllerClient controller) {
        events.showEventInfo(controller);
    }

    public void setInfoEvent(ArrayList<String> dataEvent, String typeUser, ControllerClient controller) {
        events.setEvent(dataEvent, typeUser, controller);
    }

    public void setNameEvent(String nameEvent) {
        events.setNameEvent(nameEvent);
    }

    public void showSelectSection() {

    }

    public void setInfoSections(String nameEvent, ArrayList<String> sectionsAvailables, ArrayList<String> dataSections,
            ControllerClient controller) {
        events.setInfoSections(nameEvent, dataSections, sectionsAvailables, controller);

    }

    public void cleanSelectionsSeats() {
        events.cleanSelectionsSeats();
    }

    public void repaintSelectedSeats() {

    }

    public void setInfoSeats(ArrayList<String> dataSeats, ArrayList<String> dataEvent, ControllerClient controller) {
        events.setSeatsInfo(dataSeats, dataEvent, controller);
    }

    public void setProfileInfo(ArrayList<String> profileInfo, ArrayList<String> cardsInfo, ActionListener controller) {
        events.setProfileInfo(profileInfo, cardsInfo, controller);
    }

    public List<String> getSelectedSeats() {
        return events.getSelectedSeats();
    }

    public void showAddEvent(ControllerClient controller) {
        events.showAddEvent(controller);
    }

    public void showPriceSections() {

    }

    public void setInfoSectionsToAdd(ArrayList<String> dataSections) {

    }

    public void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Advertencia", 2);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "", 1);
    }

    public void showCardInfo(ControllerClient controller) {
        events.showCardInfo(controller);
    }

    public void hideCardInfo() {
        events.hideCardInfo();
    }

    public ArrayList<String> getDataNewCard() {
        return events.getDataNewCard();
    }

    public ArrayList<String> getDataNewEvent() {
        return events.getDataNewEvent();
    }

}
