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
        String zip = "15203";
        String url = "https://api.interzoid.com/getweatherzipcode?zip=";
        String apiKey = "928439cb99c101461df3c06b1c05f911";
        // create the request with the API end point and parameters
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url+zip))
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
