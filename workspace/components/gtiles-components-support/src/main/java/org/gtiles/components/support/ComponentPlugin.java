package org.gtiles.components.support;

import org.gtiles.core.module.LocalModuleAdapter;
import org.gtiles.core.module.infofield.Developer;
import org.gtiles.core.module.infofield.Version;

public class ComponentPlugin extends LocalModuleAdapter {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Developer[] developers() {
		return new Developer[] { new Developer("huguangjun", "alongsmile@126.com") };
	}

	@Override
	public boolean checkInstall() {
		return super.tableExist("gt_tableName");
	}

	@Override
	public String name() {
		return "support组件";
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
