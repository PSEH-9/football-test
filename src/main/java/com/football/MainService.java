package com.football;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

public class MainService implements MainServiceAPI {

	private static String BASE_URL = "https://apifootball.com/api/?action=";
	
	private static String API_KEY = "&APIkey=9bb66184e0c8145384fd2cc0f7b914ada57b4e8fd2e4d6d586adcc27c257a978";
	
	private static String GET_COUNTRIES = BASE_URL + "get_countries"+API_KEY;
	
	private static String GET_LEAGUE = BASE_URL + "get_leagues&country_id=";
	
	private static String GET_STANDINGS = BASE_URL + "get_standings&league_id=";

	
	@Override
	public String getCountriesListings() {
		return getResultsFromAPI(GET_COUNTRIES);
	}

	@Override
	public String getLeagueId(String countryId) {
		String countries = getCountriesListings();
		JSONArray jsonArray = new JSONArray(countries);
		JSONObject countryObj = jsonArray.getJSONObject(1);
		String countryId1 = countryObj.getString("country_id");
		String GET_LEAGUE_INFO = GET_LEAGUE + countryId1 + API_KEY;
		String leagueInformation =  getResultsFromAPI(GET_LEAGUE_INFO);
		JSONArray leagueArray = new JSONArray(leagueInformation);
		JSONObject leagueObj = leagueArray.getJSONObject(0);
		String leagueId = leagueObj.getString("league_id");
		return leagueId;
	}

	@Override
	public String getStandings(String leagueId) {
		String leagueID = this.getLeagueId("173");
		String GET_STANDINGS_INFO = GET_STANDINGS + leagueID + API_KEY;
		return getResultsFromAPI(GET_STANDINGS_INFO);
	}
	
	public String getResultsFromAPI(String URL) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(URL);
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				return handler.handleResponse(response);
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
