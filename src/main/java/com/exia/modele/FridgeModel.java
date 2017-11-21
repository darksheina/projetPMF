package com.exia.modele;

public interface FridgeModel {

	long getValueForLed();

	void setValueForLed(long value);

	long getValueForSensorTemp();

	int getConsigne();

	void setConsigne(int consigne);

	void setTemperatureInt(long temperatureInt);
}
