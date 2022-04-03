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
        JSONArray jsonFilmes = (JSONArray) httpService.parseJson(httpService.get("https://imdb-api.com/pt-BR/API/Top250Movies/"))
            .get("items");

        return jsonFilmes
            .stream()
            .map(item -> Filme.from((JSONObject) item))
            .toList();
    }


}
