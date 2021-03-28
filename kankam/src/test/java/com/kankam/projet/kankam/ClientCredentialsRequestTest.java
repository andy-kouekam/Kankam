/*package com.kankam.projet.kankam;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

@SpringBootTest
public class ClientCredentialsRequestTest {
	
	
	 SpotifyApi SPOTIFY_API = new SpotifyApi.Builder()
			    .setClientId("e186dbf45f9d4530b5542eb86a0f938a")
			    .setClientSecret("ec62039e7a8841049938cf9901288c92")
			    .setRedirectUri(SpotifyHttpManager.makeUri("https://localhost:8080"))
			    .setAccessToken("AQBtlbK5NAznvXYZWMGOd2Iyq9N_1r_11tt6WG1SFY8HAbPBqbKXW6QMOAGTCfPt6K4MJV3bV0-rzWEN39-y84E6oa7xWRYlYWeCLX-STYOURFObga-PEc0jwDIwSAK-JhmH5HWU4RYy3i06GO1YhkCteAHNAhoXEg")
			    .setRefreshToken("b0KuPuLw77Z0hQhCsK-GTHoEx_kethtn357V7iqwEpCTIsLgqbBC_vQBTGC6M5rINl0FrqHK-D3cbOsMOlfyVKuQPvpyGcLcxAoLOTpYXc28nVwB7iBq2oKj9G9lHkFOUKn")
			    .build();
	 
	 
		  private final  ClientCredentialsRequest defaultRequest = SPOTIFY_API.clientCredentials()
		    .setHttpManager(TestUtil.MockedHttpManager.returningJson(
		      "requests/authorization/client_credentials/ClientCredentials.json"))
		    .grant_type("client_credentials")
		    .build();

		  public ClientCredentialsRequestTest() throws Exception {
		  }

		 

		  @Test
		  public void shouldReturnDefault_sync() throws IOException, SpotifyWebApiException, ParseException, org.apache.hc.core5.http.ParseException {
		    shouldReturnDefault(defaultRequest.execute());
		  }

		  @Test
		  public void shouldReturnDefault_async() throws ExecutionException, InterruptedException {
		    shouldReturnDefault(defaultRequest.executeAsync().get());
		  }

		  public void shouldReturnDefault(final ClientCredentials clientCredentials) {
		    assertEquals(
		      "AQBtlbK5NAznvXYZWMGOd2Iyq9N_1r_11tt6WG1SFY8HAbPBqbKXW6QMOAGTCfPt6K4MJV3bV0-rzWEN39-y84E6oa7xWRYlYWeCLX-STYOURFObga-PEc0jwDIwSAK-JhmH5HWU4RYy3i06GO1YhkCteAHNAhoXEg",
		      clientCredentials.getAccessToken());
		    
		    assertEquals(
		      3600,
		      (int) clientCredentials.getExpiresIn());
		  }
		}
*/
