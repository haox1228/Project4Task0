package ds.cmu.project4;

import com.google.gson.Gson;
import ds.cmu.project4.models.SearchData;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MovieServlet {
    public static void main(String[] args) {
        //zip code
        String search_movie = "Inception 2010";
        String encoded_movie=search_movie.replaceAll(" ","%20");
        String url = "https://imdb-api.com/en/API/SearchMovie/k_46acfnj9/";
        String apiKey = "k_46acfnj9";
        // create the request with the API end point and parameters
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url+encoded_movie))
                .header("content-type", "application/json")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;

        // use the client to send the request
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // print the response to the console
        System.out.println(response.body());
        String info = response.body();
        Gson gson = new Gson();
        SearchData searchData = gson.fromJson(info, SearchData.class);
        searchData.results.forEach(movie->System.out.println(movie.id+"\t"+movie.image));

    }
}
