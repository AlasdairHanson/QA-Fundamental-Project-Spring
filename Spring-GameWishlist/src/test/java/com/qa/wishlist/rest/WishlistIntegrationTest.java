package com.qa.wishlist.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.wishlist.game.Game;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:game-schema.sql",
"classpath:game-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class WishlistIntegrationTest {

	@Autowired
	private MockMvc mockMVC;
	
	@Autowired 
	private ObjectMapper mapper;
	
	@Test
	void testCreate() throws Exception {
		Game newGame = new Game("Battlefield 4", 19.99f); // new game
		String requestBody = this.mapper.writeValueAsString(newGame); 
		RequestBuilder request = post("/createGame").contentType(MediaType.APPLICATION_JSON).content(requestBody);
		
		ResultMatcher checkStatus = status().is(201);// checks if created
		
		Game savedGame = new Game("Battlefield 4", 19.99f);
		savedGame.setId(2L);
		
		String resultBody = this.mapper.writeValueAsString(savedGame);
		ResultMatcher checkBody = content().json(resultBody);
		
		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody); // send request, check if created 
		
		MvcResult result = this.mockMVC.perform(request).andExpect(checkStatus).andReturn();

		String reqBody = result.getResponse().getContentAsString();

		Game gameResult = this.mapper.readValue(reqBody, Game.class);
	}
	
	@Test
	void testUpdate() throws Exception {
		Game newGame = new Game("Battlefield 4", 19.99f);
		String requestBody = this.mapper.writeValueAsString(newGame);
		RequestBuilder request = put("/updateGame/1").contentType(MediaType.APPLICATION_JSON).content(requestBody);
		
		ResultMatcher checkStatus = status().isAccepted();
		
		Game savedGame = new Game("Battlefield 4", 19.99f);
		savedGame.setId(1L);
		
		String resultBody = this.mapper.writeValueAsString(savedGame);
		ResultMatcher checkBody = content().json(resultBody);

		this.mockMVC.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testDelete() throws Exception {
		RequestBuilder request = delete("/deleteGame/1");

		ResultMatcher checkStatus = status().is(200);

		this.mockMVC.perform(request).andExpect(checkStatus);
	}
	
	@Test
	void testRead() throws Exception {
		Game game = new Game("Cyberpunk 2077", 49.99f);
		game.setId(1L); 
		List<Game> games = new ArrayList<>();
		games.add(game);
		String responseBody = this.mapper.writeValueAsString(games);

		this.mockMVC.perform(get("/getGames")).andExpect(status().isOk()).andExpect(content().json(responseBody));
	}	
	

}
