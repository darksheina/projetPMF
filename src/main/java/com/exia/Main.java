package com.exia;

import java.io.IOException;

import com.exia.controller.FridgeController;
import com.exia.controller.FridgeControllerImpl;
import com.exia.modele.FridgeModelImpl;
import com.exia.view.View;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		FridgeModelImpl model = new FridgeModelImpl();
		FridgeController controller = new FridgeControllerImpl(model);
		View view = new View(controller);
		model.addObserver(view);
		view.run();
	}

}
