package com.kankam.projet.kankam;


import java.net.URI;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

public class AuthorizationCodeUriEx {

		  private static final String clientId = "e186dbf45f9d4530b5542eb86a0f938a";
		  private static final String clientSecret = "ec62039e7a8841049938cf9901288c92";
		  private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080");

		  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
		    .setClientId(clientId)
		    .setClientSecret(clientSecret)
		    .setRedirectUri(redirectUri)
		    .build();
		  private static final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
//		          .state("x4xkmn9pu3j6ukrs8n")
//		          .scope("user-read-birthdate,user-read-email")
//		          .show_dialog(true)
		    .build();

		  public static void authorizationCodeUri_Sync() {
		    final URI uri = authorizationCodeUriRequest.execute();

		    System.out.println("URI: " + uri.toString());
		  }

		  public static void authorizationCodeUri_Async() {
		    try {
		      final CompletableFuture<URI> uriFuture = authorizationCodeUriRequest.executeAsync();

		      // Thread free to do other tasks...

		      // Example Only. Never block in production code.
		      final URI uri = uriFuture.join();

		      System.out.println("URI: " + uri.toString());
		    } catch (CompletionException e) {
		      System.out.println("Error: " + e.getCause().getMessage());
		    } catch (CancellationException e) {
		      System.out.println("Async operation cancelled.");
		    }
		  }

		  public static void main(String[] args) {
			 System.out.println("Hello world !!!!");
		    authorizationCodeUri_Sync();
		    authorizationCodeUri_Async();
		  }
}
