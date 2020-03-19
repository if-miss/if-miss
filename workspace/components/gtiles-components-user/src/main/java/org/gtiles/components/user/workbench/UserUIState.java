package org.gtiles.components.user.workbench;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.gtiles.components.securityworkbench.service.IUIBootstrapState;
import org.gtiles.components.securityworkbench.service.SWBConstants;
import org.gtiles.components.securityworkbench.service.UIAbstractState;
import org.gtiles.components.securityworkbench.service.UIModule;
import org.gtiles.components.securityworkbench.service.UIState;
import org.springframework.stereotype.Component;

@Component("org.gtiles.components.user.workbench.UserUIState")
public class UserUIState implements IUIBootstrapState {

	@Override
	public void getUIAbstractStateList(Map<String, UIAbstractState> uiAbstractStateMap) {

		UIState userState = new UIState();
		userState.setComcode("user");
		userState.setComtype("components");
		userState.setCtrlname("userctrl");
		userState.setMenucode("userlist");
		userState.setMenupage("user/list.html");
		userState.setMenuurl("/userlist");
		userState.setUserdata("pageTitle:''");
		List<UIModule> userModuleList = new ArrayList<UIModule>();
		UIModule userModule = new UIModule();
		userModule.setCtrlname("Module.user");
		userModule.setFilelist("user/Userservice.js,user/Userctrl.js");
		userModuleList.add(userModule);

		/**
		 * 用戶菜單
		 */
		UIAbstractState uabs = uiAbstractStateMap.get(SWBConstants.MENUGROUPCODE_ADMIN);
		userState.setModulelist(userModuleList);

		uabs.addUIState(userState);
	}

}
