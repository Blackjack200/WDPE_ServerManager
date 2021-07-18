package com.github.blackjack200.wdpe;

import com.github.blackjack200.wdpe.command.AddServerCommand;
import com.github.blackjack200.wdpe.command.RemoveServerCommand;
import dev.waterdog.waterdogpe.plugin.Plugin;

public class ServerManager extends Plugin {
	@Override
	public void onEnable() {
		this.getProxy().getCommandMap().registerCommand(new AddServerCommand());
		this.getProxy().getCommandMap().registerCommand(new RemoveServerCommand());
	}
}
