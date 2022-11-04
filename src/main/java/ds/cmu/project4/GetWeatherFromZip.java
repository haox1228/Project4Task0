package ds.cmu.project4;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
public class GetWeatherFromZip {
    public static void main(String[] args) {
        //zip code
        String movie = "Inception 2010";
        String url = "https://imdb-api.com/en/API/SearchMovie/k_46acfnj9/inception%202010";
        String apiKey = "k_46acfnj9";
        // create the request with the API end point and parameters
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("content-type", "application/json")
                .header("x-api-key", apiKey)
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
        ObjectMapper mapper = new ObjectMapper();
        try {

            // convert JSON string to Map
            Map<String, String> map = mapper.readValue(info, Map.class);

            // it works
            //Map<String, String> map = mapper.readValue(json, new TypeReference<Map<String, String>>() {});

            System.out.println(map);
           System.out.println(map.get("City"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
