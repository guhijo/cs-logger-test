package com.cs.logger.repository;

import com.cs.logger.domain.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.cs.logger.domain.Status.FINISHED;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(args={"C:\\dev\\logfile.txt"})
public class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Test
    public void shouldFindEventById () {
        Event event = new Event();
        event.setId("abcd");
        event.setTimeStamp(1L);
        event.setState(FINISHED);
        event.setDuration(4);
        event.setType("4");
        event.setHost("4");
        event.setAlert(true);
        eventRepository.save(event);
        assertThat(eventRepository.findById("abcd")).isInstanceOf(Optional.class);
    }
}
