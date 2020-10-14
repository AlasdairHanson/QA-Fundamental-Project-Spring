package com.qa.wishlist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.wishlist.game.Game;
import com.qa.wishlist.repo.GameRepo;

@Service
public class GameService {
	
	@Autowired
	private GameRepo repo;

	public GameService(GameRepo repo) {
		super();
		this.repo = repo;
	}
		
	public Game createGame(Game game) {
		return this.repo.save(game);
	}

	public List<Game> getGames() {
		return this.repo.findAll();
	}
	
	public Game updateGame(Game game, Long id) {
		Game oldGame = this.repo.findById(id).orElseThrow();
		
		oldGame.setGameTitle(game.getGameTitle());
		oldGame.setGamePrice(game.getGamePrice());
		
		Game saved = this.repo.save(oldGame);
		return saved;
	}

	public boolean deleteGame(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}


}
