package com.game1.inventory;

public class Wallet {

	private int coins;
	private int gems;

	public Wallet() {
		this.coins = 0;
		this.gems = 0;
	}

	public void addCoins(int amount) {
		this.coins += amount;
		System.out.println("+ " + amount + " coins");
	}

	public boolean removeCoins(int amount) {
		if (amount > coins)
			return false;

		this.coins -= amount;
		return true;
	}

	public void addGems(int amount) {
		this.gems += amount;
	}

	public boolean removeGems(int amount) {
		if (amount > gems)
			return false;

		this.gems -= amount;
		return true;
	}

	public void reset() {
		this.coins = 0;
		this.gems = 0;
	}

	/* GETTERS AND SETTERS */

	public int getCoins() {
		return coins;
	}

	public int getGems() {
		return gems;
	}
}
