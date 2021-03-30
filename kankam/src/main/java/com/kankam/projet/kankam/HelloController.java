package com.kankam.projet.kankam;

import com.wrapper.spotify.model_objects.special.FeaturedPlaylists;
import com.wrapper.spotify.model_objects.specification.Recommendations;
import org.apache.hc.core5.http.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;




@RestController
@RequestMapping
public class HelloController {
    
    @GetMapping(value = "/new-releases")
    public Paging<AlbumSimplified> getInfos() throws ParseException, java.text.ParseException{
    	
        ClientCredentialsExample.clientCredentials_Async();
           
                return GetListOfNewReleases.getListOfNewReleases_Sync();
        
    }

    @GetMapping(value = "/playlist")
    public FeaturedPlaylists getPlaylist(){
        ClientCredentialsExample.clientCredentials_Async();
        return GetListOfFeaturedPlaylistsExample.getListOfFeaturedPlaylists_Sync();
    }

    @GetMapping(value = "/recommandations")
    public Recommendations getRecommandations(){
        ClientCredentialsExample.clientCredentials_Async();
        return GetRecommendationsExample.getRecommendations_Sync();
    }
}

