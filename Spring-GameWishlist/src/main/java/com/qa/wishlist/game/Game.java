package com.qa.wishlist.game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String gameTitle;
	
	private float gamePrice;
	
	public Game() {
		super();
	}

	public Game(String gameTitle, float gamePrice) {
		super();
		this.gameTitle = gameTitle;
		this.gamePrice = gamePrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGameTitle() {
		return gameTitle;
	}

	public void setGameTitle(String gameTitle) {
		this.gameTitle = gameTitle;
	}

	public float getGamePrice() {
		return gamePrice;
	}

	public void setGamePrice(float gamePrice) {
		this.gamePrice = gamePrice;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(gamePrice);
		result = prime * result + ((gameTitle == null) ? 0 : gameTitle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (Float.floatToIntBits(gamePrice) != Float.floatToIntBits(other.gamePrice))
			return false;
		if (gameTitle == null) {
			if (other.gameTitle != null)
				return false;
		} else if (!gameTitle.equals(other.gameTitle))
			return false;
		return true;
	}

	
	
}
