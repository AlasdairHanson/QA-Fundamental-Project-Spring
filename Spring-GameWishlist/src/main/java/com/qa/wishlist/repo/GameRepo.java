package com.qa.wishlist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.wishlist.game.Game;

@Repository
public interface GameRepo extends JpaRepository<Game, Long> {

}
