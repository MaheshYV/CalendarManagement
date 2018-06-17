import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.controller.CalendarEventController;
import com.example.domain.Calendar;
import com.example.domain.CalendarEvent;
import com.example.service.CalendarEventService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CalendarEventController.class)
@AutoConfigureMockMvc
//@WebMvcTest(value = CalendarEventController.class, secure = false)
public class CalendarEventControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CalendarEventService calendarEventService;

	
	@Test
	public void retrieveCalendarEvents() throws Exception {

		CalendarEvent calendarEvent = new CalendarEvent();
		Calendar calendar = new Calendar();
		calendar.setId(1l);
		calendar.setName("Ron");
		calendar.setUser("Calendar 1");
		
		calendarEvent.setCalendar(calendar);
		
		calendarEvent.setId(1l);
		calendarEvent.setTitle("Team retro");
		calendarEvent.setEventDate(LocalDate.of(2018, 06,  10));
		calendarEvent.setEventTime(LocalTime.of(10, 30));
		calendarEvent.setCalendar(calendar);
		
		List<CalendarEvent> calendarEventList = Arrays.asList(calendarEvent);
		
		String calenderEventListJson = 	"[{\"id\": 1, \"title\": \"Team retro\", \"eventDate\": \"2018-06-10\", \"eventTime\": \"10:30:00\", \"calendar\": 1}]";
		
		Mockito.when(calendarEventService.getAllEvents()).thenReturn(calendarEventList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("http://localhost:8081/calendar/events/");

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		// String expected = "{id:Course1,name:Spring,description:10 Steps}";
		String expected = calenderEventListJson;
		
		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples
		// and 10K Students","steps":["Learn Maven","Import Project","First
		// Example","Second Example"]}

	    JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

}
