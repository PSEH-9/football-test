package com.football;

public interface MainServiceAPI {
	
	public String getCountriesListings();
	
	public String getLeagueId(String countryId);
	
	public String getStandings(String leagueId);
	
}
