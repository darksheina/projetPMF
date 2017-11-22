package com.exia.modele;

public interface FridgeModel {

	long getValueForLed();

	void setValueForLed(long value);

	long getValueForSensorTempInt();
	
	long getValueForSensorTempExt();
	
	int getConsigne();

	void setConsigne(int consigne);

	void setTemperatureInt(long temperatureInt);

	void setTemperatureExt(long temperatureExt);
}
