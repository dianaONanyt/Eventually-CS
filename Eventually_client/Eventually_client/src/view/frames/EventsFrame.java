package view.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import controller.ControllerClient;
import view.elements.Header;
import view.elements.Palette;
import view.panels.AddCard;
import view.panels.AddEventPanel;
import view.panels.CreateProfilePanel;
import view.panels.Events;
import view.panels.InfoEventPanel;
import view.panels.LogInPanel;
import view.panels.ProfilePanel;
import view.panels.SectionPanel;
import view.panels.SelectSectionPanel;

public class EventsFrame extends BigBaseFrame {
    private JPanel background;
    private Header header;
    private Events events;
    private SelectSectionPanel sections;
    private CreateProfilePanel signIn;
    private ProfilePanel profile;
    private LogInPanel logIn;
    private SectionPanel section;
    private AddEventPanel addEvent;
    private InfoEventPanel infoEvent;
    private AddCard addCard;

    public EventsFrame(ActionListener actionListener) {
        super(actionListener);
        this.background = new JPanel();
        background.setLayout(new BorderLayout());
        background.setBackground(Palette.DARK_BG);

        this.header = new Header(actionListener);
        header.setPreferredSize(new Dimension(35, 85));

        this.infoEvent = new InfoEventPanel(actionListener);
        this.events = new Events(actionListener);
        this.signIn = new CreateProfilePanel(actionListener);
        this.sections = new SelectSectionPanel(actionListener);
        this.sections = new SelectSectionPanel(actionListener);
        this.section = new SectionPanel(actionListener, 14, 8);
        this.profile = new ProfilePanel(actionListener);
        this.addEvent = new AddEventPanel(actionListener);
        this.logIn = new LogInPanel(actionListener);
        this.addCard = new AddCard(actionListener);

        background.add(header, BorderLayout.NORTH);
        background.add(logIn, BorderLayout.CENTER);

        background.setVisible(true);
        this.add(background);

    }

    public void showEventInfo(ActionListener controller) {
        background.remove(events);
        background.remove(sections);
        background.add(infoEvent, BorderLayout.CENTER);
        infoEvent.setVisible(true);
        this.repaint();
        this.revalidate();

    }

    public void showSections(ControllerClient controller) {
        background.remove(infoEvent);
        background.add(sections, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }

    public void showEvents(ActionListener controller) {
        background.remove(logIn);
        background.remove(infoEvent);
        background.remove(profile);
        background.remove(section);
        background.remove(addEvent);
        background.remove(sections);
        header.getUserIconButton().setEnabled(true);
        background.add(events, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }

    public void showCreateAccount(ActionListener controller) {
        background.remove(logIn);
        background.add(signIn, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }

    public void showProfile(ActionListener controller) {
        background.remove(events);
        background.remove(sections);
        background.remove(infoEvent);
        background.remove(addEvent);
        background.remove(section);
        background.add(profile, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }

    public void showAddEvent(ControllerClient controller) {
        background.remove(events);
        background.remove(sections);
        background.remove(infoEvent);
        background.add(addEvent, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }

    public void showSection(ControllerClient controller) {
        background.remove(sections);
        section.repaint();
        section.revalidate();
        background.add(section, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }

    public void setEvents(ArrayList<String> eventsInfo, String nickName, String userType, ControllerClient controller) {
        events.setEvents(eventsInfo, userType, controller);
        header.setNameUser(nickName, controller);

    }

    public void setEvent(ArrayList<String> eventInfo, String typeUser, ControllerClient controller) {
        infoEvent.setEventInfo(eventInfo, typeUser, controller);
    }

    public void setNameEvent(String nameEvent) {
        infoEvent.setEventName(nameEvent);
    }

    public void setInfoSections(String nameEvent, ArrayList<String> sectionsAvailables, ArrayList<String> dataSections,
            ControllerClient controller) {
        sections.setInfoSections(nameEvent, dataSections, sectionsAvailables, controller);
    }

    public void setSeatsInfo(ArrayList<String> dataSeats, ArrayList<String> dataEvent, ControllerClient controller) {
        section.setSeatsInfo(dataSeats, dataEvent, controller);
    }

    public void setProfileInfo(ArrayList<String> profileInfo, ArrayList<String> cardsInfo, ActionListener controller) {
        profile.setProfileInfo(profileInfo, cardsInfo, controller);
    }

    public List<String> getSelectedSeats() {
        return section.getSelectedSeats();
    }

    public void cleanSelectionsSeats() {
        section.cleanSelectionsSeats();
    }

    public void showLogIn(ControllerClient controller) {
        logIn.cleanFields();
        header.removePanel();
        background.remove(events);
        background.remove(sections);
        background.remove(infoEvent);
        background.remove(addEvent);
        background.remove(signIn);
        background.remove(profile);
        background.add(logIn, BorderLayout.CENTER);
        this.repaint();
        this.revalidate();
    }

    public ArrayList<String> getDataLogIn() {
        return logIn.getDataLogIn();
    }

    public ArrayList<String> getDataNewUser() {
        return signIn.getDataNewUser();
    }

    public void showCardInfo(ControllerClient controller) {
        addCard.setVisible(true);
    }

    public void hideCardInfo() {
        addCard.setVisible(false);
        this.repaint();
        this.revalidate();
    }

    public ArrayList<String> getDataNewCard() {
        return addCard.getDataNewCard();
    }

    public ArrayList<String> getDataNewEvent() {
        return addEvent.getDataNewEvent();
    }

}
