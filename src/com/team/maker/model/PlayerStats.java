package com.team.maker.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class PlayerStats implements Serializable
{
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	 
    @Persistent
	private Integer playerId;
	@Persistent
	private String	name;
	@Persistent
	private List<YearlyStats> yearlyStats = Collections.emptyList();
	
	public PlayerStats() {}
	
	public Integer getPlayerId() {
		return playerId;
	}
	public void setPlayerId(Integer playerId) {
		this.playerId = playerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<YearlyStats> getYearlyStats() {
		return yearlyStats;
	}
	public void setYearlyStats(List<YearlyStats> yearlyStats) {
		this.yearlyStats = yearlyStats;
	}

	@Override
	public String toString() {
		return "PlayerStats [playerId=" + playerId + ", name=" + name + ", yearlyStats=" + yearlyStats + "]";
	}
	
	
	

}
