package com.kankam.projet.kankam;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.apache.hc.core5.http.ParseException;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

public class ClientCredentialsExample {

	  private static final String clientId = "e186dbf45f9d4530b5542eb86a0f938a";
	  private static final String clientSecret = "ec62039e7a8841049938cf9901288c92";

	  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
	    .setClientId(clientId)
	    .setClientSecret(clientSecret)
	    .build();
	  private static final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials()
	    .build();

	  public static void clientCredentials_Sync() throws ParseException, SpotifyWebApiException, IOException  {
	    try {
	      final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

	      // Set access token for further "spotifyApi" object usage
	      spotifyApi.setAccessToken(clientCredentials.getAccessToken());

	      System.out.println("Expires in: " + clientCredentials.getExpiresIn());
	    } catch (StringIndexOutOfBoundsException e) {
	      System.out.println("Error: " );
	    }
	  }

	  public static void clientCredentials_Async() {
	    try {
	      final CompletableFuture<ClientCredentials> clientCredentialsFuture = clientCredentialsRequest.executeAsync();

	      // Thread free to do other tasks...

	      // Example Only. Never block in production code.
	      final ClientCredentials clientCredentials = clientCredentialsFuture.join();

	      // Set access token for further "spotifyApi" object usage
	      spotifyApi.setAccessToken(clientCredentials.getAccessToken());

	      System.out.println("Expires in: " + clientCredentials.getExpiresIn());
	    } catch (CompletionException e) {
	      System.out.println("Error: " + e.getCause().getMessage());
	    } catch (CancellationException e) {
	      System.out.println("Async operation cancelled.");
	    }
	  }

	  public static void main(String[] args) throws ParseException, SpotifyWebApiException, IOException {
	    clientCredentials_Sync();
	    clientCredentials_Async();
	  }

}
