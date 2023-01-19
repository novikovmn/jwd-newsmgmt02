package by.htp.ex.controller.command.provider;

import java.util.HashMap;
import java.util.Map;

import by.htp.ex.controller.command.Command;
import by.htp.ex.controller.command.CommandName;
import by.htp.ex.controller.command.impl.AddNews;
import by.htp.ex.controller.command.impl.DeleteNews;
import by.htp.ex.controller.command.impl.DeleteNewses;
import by.htp.ex.controller.command.impl.DoRegister;
import by.htp.ex.controller.command.impl.DoSignIn;
import by.htp.ex.controller.command.impl.DoSignOut;
import by.htp.ex.controller.command.impl.GoToAddNewsPage;
import by.htp.ex.controller.command.impl.GoToEditPage;
import by.htp.ex.controller.command.impl.GoToBasePage;
import by.htp.ex.controller.command.impl.GoToLoginationPage;
import by.htp.ex.controller.command.impl.GoToNewsList;
import by.htp.ex.controller.command.impl.GoToRegistrationPage;
import by.htp.ex.controller.command.impl.GoToViewNews;
import by.htp.ex.controller.command.impl.SaveEditedNews;
import by.htp.ex.controller.command.impl.SwitchLocale;

public class CommandProvider {
	private Map<CommandName, Command> commands = new HashMap<>();
	
	public CommandProvider() {
		commands.put(CommandName.GO_TO_BASE_PAGE, new GoToBasePage());
		commands.put(CommandName.GO_TO_REGISTRATION_PAGE, new GoToRegistrationPage());
		commands.put(CommandName.DO_SIGN_IN, new DoSignIn());
		commands.put(CommandName.DO_SIGN_OUT, new DoSignOut());
		commands.put(CommandName.GO_TO_NEWS_LIST, new GoToNewsList());
		commands.put(CommandName.GO_TO_VIEW_NEWS, new GoToViewNews());
		commands.put(CommandName.GO_TO_LOGINATION_PAGE, new GoToLoginationPage());		
		commands.put(CommandName.DO_REGISTER, new DoRegister());
		commands.put(CommandName.GO_TO_EDIT_PAGE, new GoToEditPage());
		commands.put(CommandName.SAVE_EDITED_NEWS, new SaveEditedNews());		
		commands.put(CommandName.DELETE_NEWS, new DeleteNews());
		commands.put(CommandName.DELETE_NEWSES, new DeleteNewses());		
		commands.put(CommandName.GO_TO_ADD_NEWS_PAGE, new GoToAddNewsPage());
		commands.put(CommandName.ADD_NEWS, new AddNews());
		commands.put(CommandName.SWITCH_LOCALE, new SwitchLocale());
	}
	
	
	public Command getCommand(String name) {
		CommandName  commandName = CommandName.valueOf(name.toUpperCase());
		Command command = commands.get(commandName);
		return command;
	}

}
