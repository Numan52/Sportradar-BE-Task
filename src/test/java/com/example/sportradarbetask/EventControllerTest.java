package com.example.sportradarbetask;

import com.example.sportradarbetask.controllers.EventController;
import com.example.sportradarbetask.models.Dtos.EventDto;
import com.example.sportradarbetask.models.Dtos.TeamDto;
import com.example.sportradarbetask.models.Dtos.VenueDto;
import com.example.sportradarbetask.models.Event;
import com.example.sportradarbetask.models.Sport;
import com.example.sportradarbetask.models.Team;
import com.example.sportradarbetask.models.Venue;
import com.example.sportradarbetask.service.EventService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(EventController.class)
class EventControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EventService eventService;

	@Test
	public void testGetAllEvents() throws Exception {
		VenueDto venueDto = new VenueDto("Stadium A", "123 Main St", "City A", "Country A", 50000);
		List<TeamDto> teamDtos = Arrays.asList(
				new TeamDto(1L, "Real Madrid", "Madrid", 1900),
				new TeamDto(2L, "FC Barcelona", "Madrid", 1920)
		);
		EventDto eventDto = new EventDto(1L, LocalDate.now(), "Real Madrid vs FC Barcelona", 30, venueDto,
				teamDtos, LocalTime.of(18, 0), "Football");

		// mock getAllEvents() and toDto() functions of EventService dependency
		BDDMockito.given(eventService.getAllEvents()).willReturn(List.of(new Event()));
		BDDMockito.given(eventService.toDto(BDDMockito.any(Event.class))).willReturn(eventDto);
		System.out.println("all events:" + eventService.toDto(new Event()));

		mockMvc.perform(MockMvcRequestBuilders.get("/api/events/getEvents"))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("Real Madrid vs FC Barcelona"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].venue.name").value("Stadium A"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].sport").value("Football"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].teams[0].teamName").value("Real Madrid"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].teams[1].teamName").value("FC Barcelona"));
				// other property checks are omitted for brevity
	}


	@Test
	void testGetFilteredEventsBySportAndDate() throws Exception {
		// Mock Dtos
		VenueDto venueDto = new VenueDto("Stadium A", "123 Main St", "City A", "Country A", 50000);
		List<TeamDto> teamDtos = Arrays.asList(
				new TeamDto(1L, "Team A", "City A", 1900),
				new TeamDto(2L, "Team B", "City B", 1920)
		);
		EventDto eventDto = new EventDto(1L, LocalDate.of(2024, 11, 2), "Team A vs Team B", 20, venueDto, teamDtos, LocalTime.of(18, 0), "Football");

		// Mock Service Behavior
		Event event = new Event();
		String sport = "Football";
		LocalDate date = LocalDate.of(2024, 11, 2);

		BDDMockito.given(eventService.findBySportAndDate(sport, date)).willReturn(List.of(event));
		BDDMockito.given(eventService.toDto(BDDMockito.any(Event.class))).willReturn(eventDto);

		// Perform the request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/events/filter")
						.param("sport", sport)
						.param("date", date.toString()))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].description").value("Team A vs Team B"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].sport").value("Football"));
				// other property checks are omitted for brevity
	}


}
