package org.gtiles.solutions.study;

import org.gtiles.core.module.LocalModuleAdapter;
import org.gtiles.core.module.infofield.Developer;
import org.gtiles.core.module.infofield.Version;
public class ComponentPlugin extends LocalModuleAdapter
{
	/**
	 * @Fields serialVersionUID: (用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Developer[] developers() {
		return new Developer[] {new Developer("Gtiles","Gtiles@gtiles.com")};
	}

	@Override
	public boolean checkInstall() {
		return super.tableExist("gt_study");
	}

	@Override
	public String name() {
		return "测试组件";
	}

	@Override
	public Version version() {
		return new Version(1,0,0);
	}

	@Override
	public boolean checkStatus() {
		return true;
	}
}
