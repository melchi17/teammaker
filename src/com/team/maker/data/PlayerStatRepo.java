package com.team.maker.data;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.team.maker.model.PlayerStats;

public class PlayerStatRepo {

	public PlayerStats getPlayerStats(Integer playerId) {
		if (playerId == null) {
			throw new NullPointerException();
		} else {
			
			PlayerStats playerStats = null;
			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				Query query = pm.newQuery(PlayerStats.class);
				
				query.setFilter("playerId == playerIdParam");
				query.declareParameters("Integer playerIdParam");
				List<PlayerStats> allPlayerStats = (List<PlayerStats>)query.execute(playerId);
				if (allPlayerStats != null && allPlayerStats.size() > 0) {
					playerStats = allPlayerStats.get(0);
					playerStats.getYearlyStats();
				}
			} finally {
				pm.close();
			}
			return playerStats;
		}
	}
	
}
