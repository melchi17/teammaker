package com.team.maker.data;

import javax.jdo.PersistenceManager;

import com.team.maker.model.PlayerStats;

public class PlayerStatRepo {

	public PlayerStats getPlayerStats(Integer playerId) {
		if (playerId == null) {
			throw new NullPointerException();
		} else {
			
			PlayerStats playerStats = null;
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				playerStats = pm.getObjectById(PlayerStats.class, playerId);
			} finally {
				pm.close();
			}
			return playerStats;
		}
	}
	
}
