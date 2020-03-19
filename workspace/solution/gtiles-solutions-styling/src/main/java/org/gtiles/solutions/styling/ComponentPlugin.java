package org.gtiles.solutions.styling;

import org.gtiles.core.module.LocalModuleAdapter;
import org.gtiles.core.module.infofield.Developer;
import org.gtiles.core.module.infofield.Version;

public class ComponentPlugin extends LocalModuleAdapter {
	/**
	 * @Fields serialVersionUID: (用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Developer[] developers() {
		return new Developer[] { new Developer("IF阳光请疯狂", "alongsmile@126.com") };
	}

	@Override
	public boolean checkInstall() {
		return super.tableExist("life_style");
	}

	@Override
	public String name() {
		return "学习成就梦想";
	}

	@Override
	public Version version() {
		return new Version(1, 0, 0);
	}

	@Override
	public boolean checkStatus() {
		return true;
	}
}
