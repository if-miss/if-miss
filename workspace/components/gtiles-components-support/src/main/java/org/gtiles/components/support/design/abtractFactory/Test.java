package org.gtiles.components.support.design.abtractFactory;

import org.gtiles.components.support.design.abtractFactory.impl.BlackAnimalFactory;
import org.gtiles.components.support.design.abtractFactory.impl.WhiteAnimalFactory;

public class Test {

	public static void main(String[] args) {

		IAnimalFactory black = new BlackAnimalFactory();
		black.createCat().eat();
		black.createDog().eat();

		IAnimalFactory white = new WhiteAnimalFactory();
		white.createCat().eat();
		white.createDog().eat();

	}

}
