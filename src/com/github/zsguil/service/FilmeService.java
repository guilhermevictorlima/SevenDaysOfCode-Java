package com.github.zsguil.service;

import com.github.zsguil.http.HttpService;
import com.github.zsguil.records.Filme;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class FilmeService {
    private final HttpService httpService = new HttpService();

    public List<Filme> getTopFilmesImdb() throws IOException, InterruptedException, ParseException {
        return listar("https://imdb-api.com/pt-BR/API/Top250Movies/", "items");
    }

    public List<Filme> getTopSeriesImdb() throws IOException, InterruptedException, ParseException {
        return listar("https://imdb-api.com/pt-BR/API/Top250TVs/", "items");
    }

    private List<Filme> listar(String url, String arrayResultado) throws IOException, InterruptedException, ParseException {
        JSONArray json = (JSONArray) httpService.parseJson(httpService.get(url))
            .get(arrayResultado);

        return parseFilmes(json);
    }

    private List<Filme> parseFilmes(JSONArray json) {
        return json
            .stream()
            .map(item -> Filme.from((JSONObject) item))
            .toList();
    }


}
