package com.kankam.projet.kankam;

import java.io.IOException;
import java.net.URI;
import java.text.ParseException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;

public class AuthorizationCodeEx {
      
	 private static final String clientId = "e186dbf45f9d4530b5542eb86a0f938a";
	  private static final String clientSecret = "ec62039e7a8841049938cf9901288c92";
	  private static final URI redirectUri = SpotifyHttpManager.makeUri("https://developer.spotify.com/dashboard/applications/e186dbf45f9d4530b5542eb86a0f938a");
	  private static final String code = "";

	  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
	    .setClientId(clientId)
	    .setClientSecret(clientSecret)
	    .setRedirectUri(redirectUri)
	    .build();
	  private static final AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
	    .build();

	  public static void authorizationCode_Sync() throws org.apache.hc.core5.http.ParseException, ParseException {
	    try {
	      final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

	      // Set access and refresh token for further "spotifyApi" object usage
	      spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
	      spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

	      System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
	    } catch (IOException | SpotifyWebApiException e) {
	      System.out.println("Error: " + e.getMessage());
	    }
	  }

	  public static void authorizationCode_Async() {
	    try {
	      final CompletableFuture<AuthorizationCodeCredentials> authorizationCodeCredentialsFuture = authorizationCodeRequest.executeAsync();

	      // Thread free to do other tasks...

	      // Example Only. Never block in production code.
	      final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeCredentialsFuture.join();

	      // Set access and refresh token for further "spotifyApi" object usage
	      spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
	      spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

	      System.out.println("Expires in: " + authorizationCodeCredentials.getExpiresIn());
	    } catch (CompletionException e) {
	      System.out.println("Error: " + e.getCause().getMessage());
	    } catch (CancellationException e) {
	      System.out.println("Async operation cancelled.");
	    }
	  }

	  public static void main(String[] args) throws org.apache.hc.core5.http.ParseException, ParseException {
	    authorizationCode_Sync();
	    authorizationCode_Async();
	  }
}
