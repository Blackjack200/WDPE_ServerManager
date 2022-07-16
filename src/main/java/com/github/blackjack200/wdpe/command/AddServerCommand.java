package com.github.blackjack200.wdpe.command;

import dev.waterdog.waterdogpe.ProxyServer;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import dev.waterdog.waterdogpe.command.ConsoleCommandSender;
import dev.waterdog.waterdogpe.network.serverinfo.BedrockServerInfo;
import dev.waterdog.waterdogpe.network.serverinfo.ServerInfo;

import java.net.InetSocketAddress;

public class AddServerCommand extends Command {
	public AddServerCommand() {
		super("wdaddserver",
				new CommandSettings.Builder()
						.setUsageMessage("/wdaddserver <name> <local> <remote>")
						.setDescription("add wd server")
						.build()
		);
	}

	private static InetSocketAddress addr(String str) {
		int end = str.lastIndexOf(":");
		String host = str.substring(0, end);
		String port = str.substring(end + 1);
		return new InetSocketAddress(host, Integer.parseInt(port));
	}

	@Override
	public boolean onExecute(CommandSender sender, String label, String[] args) {
		if (!(sender instanceof ConsoleCommandSender)) {
			return true;
		}
		if (args.length < 3) {
			return false;
		}
		ServerInfo info = new BedrockServerInfo(args[0], addr(args[1]), addr(args[1]));
		ProxyServer.getInstance().registerServerInfo(info);
		sender.sendMessage("Success!");
		return true;
	}
}
