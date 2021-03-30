package com.kankam.projet.kankam;

import java.io.IOException;
import java.text.ParseException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.browse.GetListOfNewReleasesRequest;

public class GetListOfNewReleases {
       
	 public static String accessToken = ClientCredentialsExample.clientCredentials_Async();
	 

	    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
	            .setAccessToken(accessToken)
	            .build();
	    private static final GetListOfNewReleasesRequest getListOfNewReleasesRequest = spotifyApi.getListOfNewReleases()
	    	 .country(CountryCode.FR)
	          .limit(10)
//	          .offset(3)
	            .build();

	    public static Paging<AlbumSimplified> getListOfNewReleases_Sync() throws ParseException, org.apache.hc.core5.http.ParseException {
	        try {
	                return   getListOfNewReleasesRequest.execute();
	        } catch (IOException | SpotifyWebApiException e) {
	            System.out.println("Error: " + e.getMessage());
	        }
	        return null;
	       
	    }

	    public static void getListOfNewReleases_Async() {
	        try {
	            final CompletableFuture<Paging<AlbumSimplified>> pagingFuture = getListOfNewReleasesRequest.executeAsync();

	            // Thread free to do other tasks...

	            // Example Only. Never block in production code.
	            final Paging<AlbumSimplified> albumSimplifiedPaging = pagingFuture.join();		
                
	            System.out.println("Total: " + albumSimplifiedPaging.getTotal());
	        } catch (CompletionException e) {
	            System.out.println("Error: " + e.getCause().getMessage());
	        } catch (CancellationException e) {

	            System.out.println("Async operation cancelled.");
	        }
	    }

	    public static void main(String[] args) throws org.apache.hc.core5.http.ParseException, ParseException {
	    	getListOfNewReleases_Sync();
	        getListOfNewReleases_Async();
	    }
}
