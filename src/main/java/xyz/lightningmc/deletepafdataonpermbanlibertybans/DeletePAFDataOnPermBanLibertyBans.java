package xyz.lightningmc.deletepafdataonpermbanlibertybans;

import de.simonsator.partyandfriends.api.PAFExtension;
import de.simonsator.partyandfriends.api.pafplayers.PAFPlayerManager;
import de.simonsator.partyandfriends.main.Main;
import space.arim.libertybans.api.LibertyBans;
import space.arim.libertybans.api.event.PostPunishEvent;
import space.arim.omnibus.Omnibus;
import space.arim.omnibus.OmnibusProvider;
import space.arim.omnibus.events.EventConsumer;
import space.arim.omnibus.events.ListenerPriorities;

public class DeletePAFDataOnPermBanLibertyBans extends PAFExtension {

    private final Omnibus omnibus;
    private final LibertyBans libertyBans;

    public DeletePAFDataOnPermBanLibertyBans(Omnibus omnibus, LibertyBans libertyBans) {
        this.omnibus = omnibus;
        this.libertyBans = libertyBans;
    }

    public static DeletePAFDataOnPermBanLibertyBans create(){
        Omnibus omnibus = OmnibusProvider.getOmnibus();
        LibertyBans libertyBans = omnibus.getRegistry().getProvider(LibertyBans.class).orElseThrow();
        return new DeletePAFDataOnPermBanLibertyBans(omnibus, libertyBans);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        Main.getInstance().registerExtension(this);
        create();
        listenToPunishEvent();
    }

    public void listenToPunishEvent() {
        EventConsumer<PostPunishEvent> listener = event -> {
                if (event.getPunishment().isPermanent())
                    PAFPlayerManager.getInstance().getPlayer(event.getPunishment().getVictim().getType().name()).deleteAccount();
        };
        omnibus.getEventBus().registerListener(PostPunishEvent.class, ListenerPriorities.LOWEST, listener);
    }

}
