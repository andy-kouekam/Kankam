package com.kankam.projet.kankam;


import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.apache.hc.core5.http.ParseException;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.special.FeaturedPlaylists;
import com.wrapper.spotify.requests.data.browse.GetListOfFeaturedPlaylistsRequest;

public class GetListOfFeaturedPlaylistsExample {
	public static String accessToken = ClientCredentialsExample.clientCredentials_Async();
  private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
    .setAccessToken(accessToken)
    .build();
  private static final GetListOfFeaturedPlaylistsRequest getListOfFeaturedPlaylistsRequest = spotifyApi
    .getListOfFeaturedPlaylists()
//          .country(CountryCode.SE)
            .limit(10)
//          .offset(0)
//          .timestamp(new Date(1414054800000L))
    .build();

  public static FeaturedPlaylists getListOfFeaturedPlaylists_Sync() {
    try {
      return getListOfFeaturedPlaylistsRequest.execute();

    } catch (IOException | SpotifyWebApiException | ParseException e) {
      System.out.println("Error: " + e.getMessage());
    }
    return null;
  }

  public static void getListOfFeaturedPlaylists_Async() {
    try {
      final CompletableFuture<FeaturedPlaylists> featuredPlaylistsFuture = getListOfFeaturedPlaylistsRequest.executeAsync();

      // Thread free to do other tasks...

      // Example Only. Never block in production code.
      final FeaturedPlaylists featuredPlaylists = featuredPlaylistsFuture.join();

      System.out.println("Message: " + featuredPlaylists.getMessage());
    } catch (CompletionException e) {
      System.out.println("Error: " + e.getCause().getMessage());
    } catch (CancellationException e) {
      System.out.println("Async operation cancelled.");
    }
  }

  public static void main(String[] args) {
    getListOfFeaturedPlaylists_Sync();
    getListOfFeaturedPlaylists_Async();
  }
}
