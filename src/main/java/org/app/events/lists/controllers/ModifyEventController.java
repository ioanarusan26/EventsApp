package org.app.events.lists.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.app.events.event.services.EventService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ModifyEventController
{
    @FXML
    private TextField name;
    @FXML
    private DatePicker date;
    @FXML
    private TextArea description;
    @FXML
    private Text modifyMessage;

    public void modifyEvent()
    {
        if(!(name.getText().equals("")))
        {
            EventService.events.get(AdminToAllEvents.indexATE).setName(name.getText());
            EventService.persistEvents();
        }
        String pattern = "d-MMM-yyyy";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);
        if(date.getValue()!=null)
        {
        LocalDate dp = date.getValue();
        System.out.println(dateFormatter.format(dp));

        EventService.events.get(AdminToAllEvents.indexATE).setDate(dateFormatter.format(dp));
        EventService.persistEvents();
        }
        if(!(description.getText().equals("")))
        {
            EventService.events.get(AdminToAllEvents.indexATE).setDescription(description.getText());
            EventService.persistEvents();
        }
    }
}
