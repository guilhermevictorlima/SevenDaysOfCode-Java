package com.github.zsguil.http;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpService {
    private final HttpClient client = defaultClient();

    public HttpResponse<String> get(String url) throws IOException, InterruptedException {
        return client.send(
            defaultRequest(url)
                .GET()
                .build(),
            HttpResponse.BodyHandlers.ofString()
        );
    }

    public JSONObject parseJson(HttpResponse<String> request) throws ParseException {
        return (JSONObject) new JSONParser().parse(request.body());
    }

    private HttpRequest.Builder defaultRequest(String url) {
        return HttpRequest
            .newBuilder(URI.create(url + System.getenv("APIKEY")))
            .timeout(Duration.ofSeconds(5));
    }

    private HttpClient defaultClient() {
        return HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();
    }

}
