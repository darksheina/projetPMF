package com.exia.controller;

import java.io.IOException;

public interface FridgeController {

	public long Allumer_eteindre() throws IllegalStateException, IOException;

	public int getConsigne();
	
	/**
	 * Augmente la consigne de 1
	 */
	public void augmenterConsigne();

	/**
	 * Diminue la consigne de 1
	 */
	public void diminuerConsigne();
	
	public void calcul1();
}
