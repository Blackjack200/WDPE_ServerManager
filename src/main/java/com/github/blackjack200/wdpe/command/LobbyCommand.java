package com.github.blackjack200.wdpe.command;

import dev.waterdog.waterdogpe.ProxyServer;
import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.command.CommandSettings;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;

public class LobbyCommand extends Command {
    private ProxyServer server;

    public LobbyCommand(ProxyServer server) {
        super("lobby", CommandSettings.builder()
                .setDescription("Go to the lobby server")
                .setUsageMessage("lobby")
                .setAliases(new String[]{
                        "l", "lob"
                }).build());
        this.server = server;
    }

    @Override
    public boolean onExecute(CommandSender sender, String alias, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            player.connect(this.server.getJoinHandler().determineServer(player));
        }
        return true;
    }
}
