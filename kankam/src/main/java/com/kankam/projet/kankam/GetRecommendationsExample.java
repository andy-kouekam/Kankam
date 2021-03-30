package com.kankam.projet.kankam;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Recommendations;
import com.wrapper.spotify.requests.data.browse.GetRecommendationsRequest;
import org.apache.hc.core5.http.ParseException;

import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class GetRecommendationsExample {
    private static final String accessToken = ClientCredentialsExample.clientCredentials_Async();

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setAccessToken(accessToken)
            .build();
    private static final GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations()
// .limit(10)
 .market(CountryCode.FR)
// .max_popularity(50)
// .min_popularity(10)
// .seed_artists("0LcJLqbBmaGUft1e9Mm8HV")
 .seed_genres("electro")
// .seed_tracks("01iyCAUm8EvOFqVWYJ3dVX")
// .target_popularity(20)
            .build();

    public static Recommendations getRecommendations_Sync() {
        try {
            return getRecommendationsRequest.execute();

        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public static void getRecommendations_Async() {
        try {
            final CompletableFuture<Recommendations> recommendationsFuture = getRecommendationsRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final Recommendations recommendations = recommendationsFuture.join();

            System.out.println("Length: " + recommendations.getTracks().length);
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }

    public static void main(String[] args) {
        //getRecommendations_Sync();
        getRecommendations_Async();
    }
}