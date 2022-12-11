package xyz.lightningmc.deletepafdataonpermbanlibertybans;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import de.simonsator.partyandfriends.velocity.VelocityExtensionLoadingInfo;
import de.simonsator.partyandfriends.velocity.main.PAFPlugin;
import space.arim.libertybans.api.LibertyBans;
import space.arim.omnibus.Omnibus;
import space.arim.omnibus.OmnibusProvider;

import java.nio.file.Path;

@Plugin(id = "delete-paf-data-on-permban-libertybans", name = "Delete-PAF-Data-On-PermBan-LibertyBans", version = "1.0-RELEASE",
        url = "https://web.lightning-mc.xyz/",
        description = "An add-on for party and friends extended to add a list all friend requests",
        authors = {"JT122406"}, dependencies = {@Dependency(id = "partyandfriends"), @Dependency(id = "libertybans")})
public class Loader {

    private final Path folder;

    @Inject
    public Loader(@DataDirectory final Path folder) {
        this.folder = folder;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        Omnibus omnibus = OmnibusProvider.getOmnibus();
        LibertyBans libertyBans = omnibus.getRegistry().getProvider(LibertyBans.class).orElseThrow();
        PAFPlugin.loadExtension(new VelocityExtensionLoadingInfo(new DeletePAFDataOnPermBanLibertyBans(omnibus, libertyBans, folder),
                "delete-paf-data-on-permban-libertybans",
                "Delete-PAF-Data-On-PermBan-LibertyBans",
                "1.0-RELEASE", "JT122406"));
    }


}
