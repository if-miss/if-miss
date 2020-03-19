package org.gtiles.components.user;

import org.gtiles.core.module.LocalModuleAdapter;
import org.gtiles.core.module.infofield.DependencyModule;
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
		return super.tableExist("life_user");
	}

	@Override
	public String name() {
		return "用户组件";
	}

	@Override
	public Version version() {
		return new Version(1, 0, 0);
	}

	@Override
	public boolean checkStatus() {
		return true;
	}

	/**
	 * 组件中的依赖
	 */
	@Override
	public DependencyModule[] dependencyModules() {
		DependencyModule[] dependencyModule = new DependencyModule[1];
		dependencyModule[0] = new DependencyModule("org.gtiles.components.securityworkbench", new Version(1, 0, 0));
		return dependencyModule;
	}
}
