package com.cs.logger.service;

import com.cs.logger.domain.Event;
import com.cs.logger.domain.Status;
import com.cs.logger.repository.EventRepository;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EventService {

    private final EventRepository eventRepository;
    private final static int EXPECTED_THRESHOLD = 4;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void initLogReading (String path){
        measureEventsDuration(parseLogFile(path));
    }

    private List<Event> parseLogFile (String path) {
        List<Event> eventList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            Gson gson = new Gson();
            while (line != null) {
                eventList.add(gson.fromJson(line, Event.class));
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.getMessage();
            log.error("Unable to read provided file.", e);
        }
        return eventList;
    }
    private void measureEventsDuration (List<Event> eventList){
        for (final Event item: eventList) {
            String logId = item.getId();
            Status logState = item.getState();
            if(logState == Status.STARTED){
                long duration = 0;
                for (final Event subItem: eventList) {
                    if(subItem.getId().equalsIgnoreCase(logId) && subItem.getState() == Status.FINISHED){
                        duration = subItem.getTimeStamp() - item.getTimeStamp();
                        subItem.setDuration(duration);
                        subItem.setAlert(duration > EXPECTED_THRESHOLD ? true : false);
                        this.save(subItem);
                    }
                }
            }
        }
    }

    private void save(Event event){
        eventRepository.save(event);
        log.info("Saved event {} with alert set to {}.", event.getId(), event.getAlert());
    }
}
