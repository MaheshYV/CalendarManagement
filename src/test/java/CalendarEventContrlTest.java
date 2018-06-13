import static java.util.Collections.singletonList;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.controller.CalendarEventController;
import com.example.domain.CalendarEvent;
import static java.util.Collections.singletonList;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import static org.hamcrest.core.Is.is;

import static org.mockito.BDDMockito.given;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CalendarEventController.class)
@AutoConfigureMockMvc
// @WebMvcTest(CalendarEventController.class)
public class CalendarEventContrlTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CalendarEventController calendarController;

	@Test
	public void getAllEvents() throws Exception {

		CalendarEvent event = new CalendarEvent();

		event.setTitle("Team conference");

		List<CalendarEvent> allEvents = singletonList(event);

		given(calendarController.getAllEvents()).willReturn(allEvents);

		mvc.perform(get("/calendar/events/day/2018-06-10"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].title", is(event.getTitle())));
	}
}