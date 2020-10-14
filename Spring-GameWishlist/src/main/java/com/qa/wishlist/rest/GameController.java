package com.qa.wishlist.rest;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.qa.wishlist.game.Game;
import com.qa.wishlist.service.GameService;

@CrossOrigin
@RestController
public class GameController {
	
	private GameService service;

	public GameController(GameService service) {
		super();
		this.service = service;
	}
	
	@PostMapping("/createGame")
	public ResponseEntity<Game> createGame(@RequestBody Game game) {
		return new ResponseEntity<Game>(this.service.createGame(game), HttpStatus.CREATED);	
	}
	
	@GetMapping("/getGames")
	public ResponseEntity<List<Game>> getGames() {
		return ResponseEntity.ok(this.service.getGames());
	}
	
	@PutMapping("/update")
	public ResponseEntity<Game> updateGame(@RequestBody Game game, @PathParam("id") Long id) {
		return new ResponseEntity<Game>(this.service.updateGame(game, id), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteGame/{id}")
	public ResponseEntity<Object> deleteWood(@PathVariable Long id) {
		if (this.service.deleteGame(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
