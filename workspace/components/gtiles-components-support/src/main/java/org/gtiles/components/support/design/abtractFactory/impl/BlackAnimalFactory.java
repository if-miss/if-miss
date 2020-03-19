package org.gtiles.components.support.design.abtractFactory.impl;

import org.gtiles.components.support.design.abtractFactory.IAnimalFactory;
import org.gtiles.components.support.design.abtractFactory.ICat;
import org.gtiles.components.support.design.abtractFactory.IDog;

public class BlackAnimalFactory implements IAnimalFactory {

	@Override
	public ICat createCat() {
		return new BlackCat();
	}

	@Override
	public IDog createDog() {
		return new BlackDog();
	}

}
