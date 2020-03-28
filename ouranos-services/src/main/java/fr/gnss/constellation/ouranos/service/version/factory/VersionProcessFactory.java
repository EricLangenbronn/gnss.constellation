package fr.gnss.constellation.ouranos.service.version.factory;

import fr.gnss.constellation.ouranos.service.version.strategy.IVersionProcessStrategy;
import fr.gnss.constellation.ouranos.service.version.strategy.VersionProcessPrefixStrategyImpl;

public class VersionProcessFactory {
	
	// -------------------- Propriétés de la classe --------------------

	private final IVersionProcessStrategy prefixVersionStrategy;
	
	// -------------------- Constructeurs --------------------
	
	public VersionProcessFactory() {
		this.prefixVersionStrategy = new VersionProcessPrefixStrategyImpl();
	}

	public IVersionProcessStrategy getVersionProcessStrategy(VersionType versionType) {

		IVersionProcessStrategy strategieChoise;

		switch (versionType) {
		default:
			strategieChoise = this.prefixVersionStrategy;
		}

		return strategieChoise;
	}
}
