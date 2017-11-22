package com.exia.controller;


import org.apache.log4j.Logger;

import com.exia.modele.FridgeModel;

public class FridgeControllerImpl implements FridgeController {

	private static Logger logger = Logger.getLogger(FridgeControllerImpl.class);
	
	private FridgeModel model;
	
	public FridgeControllerImpl(FridgeModel model) {
		super();
		this.model = model;
	}

	@Override
	public void augmenterConsigne() {
		logger.debug("controller demande au model de faire consigne +1");
		model.setConsigne(model.getConsigne() + 1);
	}

	@Override
	public void diminuerConsigne() {
		logger.debug("controller demande au model de faire consigne -1");
		model.setConsigne(model.getConsigne() -1);
	}
}
