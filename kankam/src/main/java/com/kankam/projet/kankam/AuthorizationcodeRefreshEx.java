package com.kankam.projet.kankam;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;

public class AuthorizationcodeRefreshEx {
              
	private static final String clientId = "e186dbf45f9d4530b5542eb86a0f938a";
	  private static final String clientSecret = "ec62039e7a8841049938cf9901288c92";
	  private static final String refreshToken = "b0KuPuLw77Z0hQhCsK-GTHoEx_kethtn357V7iqwEpCTIsLgqbBC_vQBTGC6M5rINl0FrqHK-D3cbOsMOlfyVKuQPvpyGcLcxAoLOTpYXc28nVwB7iBq2oKj9G9lHkFOUKn";

	  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
	    .setClientId(clientId)
	    .setClientSecret(clientSecret)
	    .setRefreshToken(refreshToken)
	    .build();
	  private static final AuthorizationCodeRefreshRequest authorizationCodeRefreshRequest = spotifyApi.authorizationCodeRefresh()
	    .build();

	  public static void authorizationCodeRefresh_Sync() throws ParseException, org.apache.hc.core5.http.ParseException {
	    try {
	      final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRefreshRequest.execute();

	      // Set access and refresh token for further "spotifyApi" object usage
	      spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());

	      System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
	    } catch (IOException | SpotifyWebApiException e) {
	      System.out.println("Error: " + e.getMessage());
	    }
	  }

	  public static void authorizationCodeRefresh_Async() {
	    try {
	      final CompletableFuture<AuthorizationCodeCredentials> authorizationCodeCredentialsFuture = authorizationCodeRefreshRequest.executeAsync();

	      // Thread free to do other tasks...

	      // Example Only. Never block in production code.
	      final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeCredentialsFuture.join();

	      // Set access token for further "spotifyApi" object usage
	      spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());

	      System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
	    } catch (CompletionException e) {
	      System.out.println("Error: " + e.getCause().getMessage());
	    } catch (CancellationException e) {
	      System.out.println("Async operation cancelled.");
	    }
	  }

	  public static void main(String[] args) throws org.apache.hc.core5.http.ParseException, ParseException {
	    authorizationCodeRefresh_Sync();
	    authorizationCodeRefresh_Async();
	  }
}
