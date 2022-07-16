package com.github.blackjack200.wdpe;

import com.github.blackjack200.wdpe.command.AddServerCommand;
import com.github.blackjack200.wdpe.command.KickCommand;
import com.github.blackjack200.wdpe.command.LobbyCommand;
import com.github.blackjack200.wdpe.command.RemoveServerCommand;
import com.nukkitx.protocol.bedrock.data.entity.EntityFlag;
import com.nukkitx.protocol.bedrock.data.entity.EntityFlags;
import com.nukkitx.protocol.bedrock.packet.MobEffectPacket;
import com.nukkitx.protocol.bedrock.packet.SetEntityDataPacket;
import dev.waterdog.waterdogpe.event.defaults.ClientBindEvent;
import dev.waterdog.waterdogpe.event.defaults.PreTransferEvent;
import dev.waterdog.waterdogpe.event.defaults.TransferCompleteEvent;
import dev.waterdog.waterdogpe.plugin.Plugin;

public class ServerManager extends Plugin {
	@Override
	public void onEnable() {
		this.getProxy().getCommandMap().registerCommand(new AddServerCommand());
		this.getProxy().getCommandMap().registerCommand(new RemoveServerCommand());
		this.getProxy().getCommandMap().registerCommand(new KickCommand());
		this.getProxy().getCommandMap().registerCommand(new LobbyCommand(this.getProxy()));
		this.getProxy().getEventManager().subscribe(PreTransferEvent.class, (ev) -> {
			ev.getPlayer().sendMessage("§aTransferring to another server");
		});
		this.getProxy().getEventManager().subscribe(ClientBindEvent.class, (ev) -> {
			SetEntityDataPacket pk = new SetEntityDataPacket();
			EntityFlags f = new EntityFlags();
			f.setFlag(EntityFlag.NO_AI, true);
			pk.setRuntimeEntityId(ev.getPlayer().getRewriteData().getEntityId());
			pk.getMetadata().putFlags(f);
			ev.getPlayer().sendPacket(pk);

			MobEffectPacket pk2 = new MobEffectPacket();
			pk2.setRuntimeEntityId(ev.getPlayer().getRewriteData().getEntityId());
			pk2.setAmplifier(255);
			//blindness
			pk2.setEffectId(15);
			pk2.setEvent(MobEffectPacket.Event.ADD);
			pk2.setParticles(false);
			pk2.setDuration(Integer.MAX_VALUE);
			ev.getPlayer().sendPacket(pk2);
		});
		this.getProxy().getEventManager().subscribe(TransferCompleteEvent.class, (ev) -> {

			SetEntityDataPacket pk = new SetEntityDataPacket();
			EntityFlags f = new EntityFlags();
			f.setFlag(EntityFlag.NO_AI, false);
			pk.setRuntimeEntityId(ev.getPlayer().getRewriteData().getEntityId());
			pk.getMetadata().putFlags(f);
			ev.getPlayer().sendPacket(pk);

			MobEffectPacket pk2 = new MobEffectPacket();
			pk2.setRuntimeEntityId(ev.getPlayer().getRewriteData().getEntityId());
			pk2.setAmplifier(255);
			//blindness
			pk2.setEffectId(15);
			pk2.setEvent(MobEffectPacket.Event.REMOVE);
			pk2.setParticles(false);
			pk2.setDuration(Integer.MAX_VALUE);
			ev.getPlayer().sendPacket(pk2);
		});
	}
}
