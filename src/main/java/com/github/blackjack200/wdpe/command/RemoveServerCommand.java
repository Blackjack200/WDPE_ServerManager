package com.github.blackjack200.wdpe.command;

import dev.waterdog.waterdogpe.ProxyServer;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import dev.waterdog.waterdogpe.command.ConsoleCommandSender;
import dev.waterdog.waterdogpe.network.ServerInfo;

public class RemoveServerCommand extends Command {
	public RemoveServerCommand() {
		super("wdrmserver",
				new CommandSettings.Builder()
						.setUsageMessage("/wdrmserver <name>")
						.setDescription("remove wd server")
						.build()
		);
	}

	@Override
	public boolean onExecute(CommandSender sender, String label, String[] args) {
		if (!(sender instanceof ConsoleCommandSender)) {
			return true;
		}
		if (args.length < 1) {
			return false;
		}
		ServerInfo info = ProxyServer.getInstance().removeServerInfo(args[0]);
		if (info != null) {
			sender.sendMessage("Success!");
		} else {
			sender.sendMessage("Failed!");
		}
		return true;
	}
}