package com.team.maker.model;

import java.io.Serializable;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@SuppressWarnings("serial")
@PersistenceCapable
public class YearlyStats implements Serializable
{
	
	public static YearlyStats create(List<String> data) {
		YearlyStats yearlyStats = new YearlyStats();
		yearlyStats.setYear(data.get(0));
		yearlyStats.setAge(parseInteger(data.get(1)));
		if (data.size() == 3) {
			yearlyStats.setDnp(true);
			yearlyStats.setDnpReason(data.get(2));
		} else {
			yearlyStats.setTeam(data.get(2));
			yearlyStats.setLeague(data.get(3));
			yearlyStats.setPosition(data.get(4));
			yearlyStats.setGames(parseInteger(data.get(5)));
			yearlyStats.setGamesStarted(parseInteger(data.get(6)));
			yearlyStats.setMinutesPlayed(parseInteger(data.get(7)));
			yearlyStats.setFieldGoals(parseInteger(data.get(8)));
			yearlyStats.setFieldGoalAttempts(parseInteger(data.get(9)));
			yearlyStats.setFieldGoalPercentage(parseDouble(data.get(10)));
			yearlyStats.setThreePointFieldGoals(parseInteger(data.get(11)));
			yearlyStats.setThreePointFieldGoalAttempts(parseInteger(data.get(12)));
			yearlyStats.setThreePointFieldGoalPercentage(parseDouble(data.get(13)));
			yearlyStats.setTwoPointFieldGoals(parseInteger(data.get(14)));
			yearlyStats.setTwoPointFieldGoalAttempts(parseInteger(data.get(15)));
			yearlyStats.setTwoPointFieldGoalPercentage(parseDouble(data.get(16)));
			yearlyStats.setFt(parseInteger(data.get(17)));
			yearlyStats.setFta(parseInteger(data.get(18)));
			yearlyStats.setFtp(parseDouble(data.get(19)));
			yearlyStats.setOffensiveRebounds(parseInteger(data.get(20)));
			yearlyStats.setDefensiveRebounds(parseInteger(data.get(21)));
			yearlyStats.setTotalRebounds(parseInteger(data.get(22)));
			yearlyStats.setAssists(parseInteger(data.get(23)));
			yearlyStats.setSteals(parseInteger(data.get(24)));
			yearlyStats.setBlocks(parseInteger(data.get(25)));
			yearlyStats.setTurnovers(parseInteger(data.get(26)));
			yearlyStats.setPersonalFouls(parseInteger(data.get(27)));
			yearlyStats.setPoints(parseInteger(data.get(28)));
		}
		return yearlyStats;
	}

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String year;
	@Persistent
	private Integer age;
	@Persistent
	private String team;
	@Persistent
	private String league;
	@Persistent
	private String position;
	@Persistent
	private Integer games;
	@Persistent
	private Integer gamesStarted;
	@Persistent
	private Integer minutesPlayed;
	@Persistent
	private Integer fieldGoals;
	@Persistent
	private Integer fieldGoalAttempts;
	@Persistent
	private Double fieldGoalPercentage;
	@Persistent
	private Integer threePointFieldGoals;
	@Persistent
	private Integer threePointFieldGoalAttempts;
	@Persistent
	private Double threePointFieldGoalPercentage;
	@Persistent
	private Integer twoPointFieldGoals;
	@Persistent
	private Integer twoPointFieldGoalAttempts;
	@Persistent
	private Double twoPointFieldGoalPercentage;
	@Persistent
	private Integer ft;
	@Persistent
	private Integer fta;
	@Persistent
	private Double ftp;
	@Persistent
	private Integer offensiveRebounds;
	@Persistent
	private Integer defensiveRebounds;
	@Persistent
	private Integer totalRebounds;
	@Persistent
	private Integer assists;
	@Persistent
	private Integer steals;
	@Persistent
	private Integer blocks;
	@Persistent
	private Integer turnovers;
	@Persistent
	private Integer personalFouls;
	@Persistent
	private Integer points;
	@Persistent
	private boolean dnp = false;
	@Persistent
	private String dnpReason;
	 
	public YearlyStats() {}
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public Integer getAge() {
		return age;
	}
	public Double getTwoPointFieldGoalPercentage() {
		return twoPointFieldGoalPercentage;
	}
	public void setTwoPointFieldGoalPercentage(Double twoPointFieldGoalPercentage) {
		this.twoPointFieldGoalPercentage = twoPointFieldGoalPercentage;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getLeague() {
		return league;
	}
	public void setLeague(String league) {
		this.league = league;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getGames() {
		return games;
	}
	public void setGames(Integer games) {
		this.games = games;
	}
	public Integer getGamesStarted() {
		return gamesStarted;
	}
	public void setGamesStarted(Integer gamesStarted) {
		this.gamesStarted = gamesStarted;
	}
	public Integer getMinutesPlayed() {
		return minutesPlayed;
	}
	public void setMinutesPlayed(Integer minutesPlayed) {
		this.minutesPlayed = minutesPlayed;
	}
	public Integer getFieldGoals() {
		return fieldGoals;
	}
	public void setFieldGoals(Integer fieldGoals) {
		this.fieldGoals = fieldGoals;
	}
	public Integer getFieldGoalAttempts() {
		return fieldGoalAttempts;
	}
	public void setFieldGoalAttempts(Integer fieldGoalAttempts) {
		this.fieldGoalAttempts = fieldGoalAttempts;
	}
	public Double getFieldGoalPercentage() {
		return fieldGoalPercentage;
	}
	public void setFieldGoalPercentage(Double fieldGoalPercentage) {
		this.fieldGoalPercentage = fieldGoalPercentage;
	}
	public Integer getThreePointFieldGoals() {
		return threePointFieldGoals;
	}
	public void setThreePointFieldGoals(Integer threePointFieldGoals) {
		this.threePointFieldGoals = threePointFieldGoals;
	}
	public Integer getThreePointFieldGoalAttempts() {
		return threePointFieldGoalAttempts;
	}
	public void setThreePointFieldGoalAttempts(Integer threePointFieldGoalAttempts) {
		this.threePointFieldGoalAttempts = threePointFieldGoalAttempts;
	}
	public Double getThreePointFieldGoalPercentage() {
		return threePointFieldGoalPercentage;
	}
	public void setThreePointFieldGoalPercentage(Double threePointFieldGoalPercentage) {
		this.threePointFieldGoalPercentage = threePointFieldGoalPercentage;
	}
	public Integer getTwoPointFieldGoals() {
		return twoPointFieldGoals;
	}
	public void setTwoPointFieldGoals(Integer twoPointFieldGoals) {
		this.twoPointFieldGoals = twoPointFieldGoals;
	}
	public Integer getTwoPointFieldGoalAttempts() {
		return twoPointFieldGoalAttempts;
	}
	public void setTwoPointFieldGoalAttempts(Integer twoPointFieldGoalAttempts) {
		this.twoPointFieldGoalAttempts = twoPointFieldGoalAttempts;
	}
	public Integer getFt() {
		return ft;
	}
	public void setFt(Integer ft) {
		this.ft = ft;
	}
	public Integer getFta() {
		return fta;
	}
	public void setFta(Integer fta) {
		this.fta = fta;
	}
	public Double getFtp() {
		return ftp;
	}
	public void setFtp(Double ftp) {
		this.ftp = ftp;
	}
	public Integer getOffensiveRebounds() {
		return offensiveRebounds;
	}
	public void setOffensiveRebounds(Integer offensiveRebounds) {
		this.offensiveRebounds = offensiveRebounds;
	}
	public Integer getDefensiveRebounds() {
		return defensiveRebounds;
	}
	public void setDefensiveRebounds(Integer defensiveRebounds) {
		this.defensiveRebounds = defensiveRebounds;
	}
	public Integer getTotalRebounds() {
		return totalRebounds;
	}
	public void setTotalRebounds(Integer totalRebounds) {
		this.totalRebounds = totalRebounds;
	}
	public Integer getAssists() {
		return assists;
	}
	public void setAssists(Integer assists) {
		this.assists = assists;
	}
	public Integer getSteals() {
		return steals;
	}
	public void setSteals(Integer steals) {
		this.steals = steals;
	}
	public Integer getBlocks() {
		return blocks;
	}
	public void setBlocks(Integer blocks) {
		this.blocks = blocks;
	}
	public Integer getTurnovers() {
		return turnovers;
	}
	public void setTurnovers(Integer turnovers) {
		this.turnovers = turnovers;
	}
	public Integer getPersonalFouls() {
		return personalFouls;
	}
	public void setPersonalFouls(Integer personalFouls) {
		this.personalFouls = personalFouls;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	
	
	public boolean isDnp() {
		return dnp;
	}
	public void setDnp(boolean dnp) {
		this.dnp = dnp;
	}
	public String getDnpReason() {
		return dnpReason;
	}
	public void setDnpReason(String dnpReason) {
		this.dnpReason = dnpReason;
	}
	private static Integer parseInteger(String data) {
		return "".equals(data) ? null : Integer.parseInt(data);
	}
	private static Double parseDouble(String data) {
		return "".equals(data) ? null : Double.parseDouble(data);
	}
	@Override
	public String toString() {
		return "YearlyStats [year=" + year + ", age=" + age + ", team=" + team + ", league=" + league + ", position=" + position + ", games=" + games + ", gamesStarted=" + gamesStarted + ", minutesPlayed=" + minutesPlayed + ", fieldGoals=" + fieldGoals + ", fieldGoalAttempts=" + fieldGoalAttempts + ", fieldGoalPercentage=" + fieldGoalPercentage + ", threePointFieldGoals=" + threePointFieldGoals + ", threePointFieldGoalAttempts=" + threePointFieldGoalAttempts + ", threePointFieldGoalPercentage=" + threePointFieldGoalPercentage + ", twoPointFieldGoals=" + twoPointFieldGoals + ", twoPointFieldGoalAttempts=" + twoPointFieldGoalAttempts + ", ft=" + ft + ", fta=" + fta + ", ftp=" + ftp + ", offensiveRebounds=" + offensiveRebounds + ", defensiveRebounds=" + defensiveRebounds + ", totalRebounds=" + totalRebounds + ", assists=" + assists + ", steals=" + steals + ", blocks=" + blocks + ", turnovers=" + turnovers + ", personalFouls=" + personalFouls + ", points=" + points + "]";
	}
	
	
}
