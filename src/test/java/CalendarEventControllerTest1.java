import java.io.IOException;
import java.util.Arrays;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.example.RestServiceApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestServiceApplication.class)
@AutoConfigureMockMvc
@Transactional
public class CalendarEventControllerTest1 {

	@Autowired
	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	public void setConverters(HttpMessageConverter<?>[] converters) {
		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
		Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}

	@SuppressWarnings("unchecked")
	protected String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);

		return mockHttpOutputMessage.getBodyAsString();
	}
	
	@Test
	public void should_get_valid_book_with_ok_status() throws Exception {

//	    mockMvc.perform(get("/api/books/978-0321356680").contentType(MediaType.APPLICATION_JSON))
//	        .andExpect(status().isOk())
//	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//	        .andExpect(jsonPath("$.id", is(1)))
//	        .andExpect(jsonPath("$.title", is("Effective Java")))
//	        .andExpect(jsonPath("$.publisher", is("Addison Wesley")))
//	        .andDo(MockMvcResultHandlers.print());
	}
}