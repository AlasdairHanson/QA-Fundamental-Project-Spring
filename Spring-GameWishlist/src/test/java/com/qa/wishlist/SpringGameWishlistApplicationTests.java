package com.qa.wishlist;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.wishlist.game.Game;

import nl.jqno.equalsverifier.EqualsVerifier;

@SpringBootTest
class SpringGameWishlistApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void coverage() {
		EqualsVerifier.forClass(Game.class).usingGetClass().verify();
	}

}
