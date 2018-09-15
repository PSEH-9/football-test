package com.football;

public class MainController {

	MainService mainService = new MainService();

	public String getCountries() {
		return mainService.getCountriesListings();
	}

	public String getLeagueInformation() {
		return mainService.getLeagueId("173");
	}

	public String getStandings() {
		return mainService.getStandings("123");
	}
}
