package xyz.lightningmc.deletepafdataonpermbanlibertybans;

import de.simonsator.partyandfriends.velocity.api.PAFExtension;
import de.simonsator.partyandfriends.velocity.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.velocity.main.Main;
import space.arim.libertybans.api.LibertyBans;
import space.arim.libertybans.api.event.PostPunishEvent;
import space.arim.omnibus.Omnibus;
import space.arim.omnibus.events.EventConsumer;
import space.arim.omnibus.events.ListenerPriorities;

import java.nio.file.Path;

public class DeletePAFDataOnPermBanLibertyBans extends PAFExtension {

    private final Omnibus omnibus;
    private final LibertyBans libertyBans;

    public DeletePAFDataOnPermBanLibertyBans(Omnibus omnibus, LibertyBans libertyBans, Path folder) {
        super(folder);
        this.omnibus = omnibus;
        this.libertyBans = libertyBans;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Main.getInstance().registerExtension(this);
        listenToPunishEvent();
    }

    @Override
    public String getName() {
        return "DeletePAFDataOnPermBanLibertyBans";
    }

    public void listenToPunishEvent() {
        EventConsumer<PostPunishEvent> listener = event -> {
                if (event.getPunishment().isPermanent())
                    PAFPlayerManager.getInstance().getPlayer(event.getPunishment().getVictim().getType().name()).deleteAccount();
        };
        omnibus.getEventBus().registerListener(PostPunishEvent.class, ListenerPriorities.LOWEST, listener);
    }

}
