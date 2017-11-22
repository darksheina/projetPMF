package com.exia.controller;


import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.exia.modele.FridgeModel;

public class FridgeControllerImpl extends TimerTask implements FridgeController {

	private static Logger logger = Logger.getLogger(FridgeControllerImpl.class);
	
	private FridgeModel model;
	
	public FridgeControllerImpl(FridgeModel model) {
		super();
		this.model = model;
		
		Timer timer = new Timer();
		timer.schedule(this, 0, 2000);
		
//		Runnable runnable = () -> {
//		    try {
//		        String name = Thread.currentThread().getName();
//		        logger.info("début tache");
//		        TimeUnit.SECONDS.sleep(1);
//		        logger.info("fin tache");
//		        TimeUnit.SECONDS.sleep(1);
//		    }
//		    catch (InterruptedException e) {
//		    	logger.error(e);
//		    }
//		};
//
//		Thread thread = new Thread(runnable);
//		thread.start();
		
		
	}

	@Override
	public long Allumer_eteindre() throws IllegalStateException, IOException {
		long ledValue = model.getValueForLed();
		if (ledValue == 0) {
			model.setValueForLed(1);
		} else if (ledValue == 1) {
			model.setValueForLed(0);
		}
		return model.getValueForLed();
	}

	// température consigne = nombreConsigne (coté vue)

	@Override
	public void calcul1() {
		// get valeur avec pin dans Utils
		// calcul
		// affichage dans la vue en appellant cette méthode
	}

	@Override
	public void augmenterConsigne() {
		logger.info("controller demande au model de faire consigne +1");
		model.setConsigne(model.getConsigne() + 1);
	}

	@Override
	public void diminuerConsigne() {
		logger.info("controller demande au model de faire consigne -1");
		model.setConsigne(model.getConsigne() -1);
	}

	@Override
	public int getConsigne() {
		return model.getConsigne();
	}
	
	@Override
	public void run() {
        logger.info("début tache");
        long calculTemperatureInt = (model.getValueForSensorTempInt() * 5) /15;
        model.setTemperatureInt(calculTemperatureInt);
        long calculTemperatureExt = (model.getValueForSensorTempExt() +1);
        model.setTemperatureExt(calculTemperatureExt);
        logger.info("fin tache");
	}

}
