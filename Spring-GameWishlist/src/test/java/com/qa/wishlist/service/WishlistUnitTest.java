package com.qa.wishlist.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.wishlist.game.Game;
import com.qa.wishlist.repo.GameRepo;

@SpringBootTest
@ActiveProfiles(profiles = "test")
public class WishlistUnitTest {
	
	@Autowired
	private GameService service;

	@MockBean
	private GameRepo repo;

	// GIVEN - WHEN - THEN
	@Test
	void testCreate() {
		// GIVEN
		Long id = 1L;
		Game newGame = new Game("Battlefield 4", 19.99f);
		Game savedGame = new Game("Battlefield 4", 19.99f);
		savedGame.setId(id);

		// WHEN
		Mockito.when(this.repo.save(newGame)).thenReturn(savedGame);

		// THEN
		assertThat(this.service.createGame(newGame)).isEqualTo(savedGame);

		Mockito.verify(this.repo, Mockito.times(1)).save(newGame);
	}

	@Test
	void testUpdate() {
		// GIVEN

		Long id = 1L;

		Game newGame = new Game("Battlefield 4", 19.99f);

		Game oldGame = new Game("Fall Guys", 12.99f);
		oldGame.setId(id);

		Game updatedGame = new Game("Battlefield 4", 19.99f);
		updatedGame.setId(id);

		// WHEN
		Mockito.when(this.repo.findById(id)).thenReturn(Optional.of(oldGame));
		Mockito.when(this.repo.save(updatedGame)).thenReturn(updatedGame);

		// THEN
		assertThat(this.service.updateGame(newGame, id)).isEqualTo(updatedGame);

		Mockito.verify(this.repo, Mockito.times(2)).findById(id);
		Mockito.verify(this.repo, Mockito.times(1)).save(updatedGame);
	}

	@Test
	void testGet() {
		// GIVEN
		Game game = new Game("Cyberpunk 2077", 49.99f);
		game.setId(1L); // wood object to match the one in wood-data.sql
		List<Game> games = new ArrayList<>();
		games.add(game);

		// WHEN
		Mockito.when(this.repo.findAll()).thenReturn(games);

		// THEN
		assertThat(this.service.getGames()).isEqualTo(games);

		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}

	@Test
	void testDelete() {
		// GIVEN
		Long id = 1L;
		boolean found = false;

		// WHEN
		Mockito.when(this.repo.existsById(id)).thenReturn(found);

		// THEN
		assertThat(this.service.deleteGame(id)).isEqualTo(!found);

		Mockito.verify(this.repo, Mockito.times(1)).existsById(id);
	}

}
