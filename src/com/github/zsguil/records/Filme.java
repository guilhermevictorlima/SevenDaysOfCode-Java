package com.github.zsguil.records;

import org.json.simple.JSONObject;

public record Filme(String titulo,
                    String tituloCompleto,
                    String imagemUrl,
                    String equipe,
                    Integer anoLancamento,
                    Integer avaliacoes,
                    Integer rankImdb,
                    Float notaImdb) {

    public static Filme from(JSONObject filmeJson) {
        return new Filme(
            (String) filmeJson.get("title"),
            (String) filmeJson.get("fullTitle"),
            (String) filmeJson.get("image"),
            (String) filmeJson.get("crew"),
            Integer.parseInt((String) filmeJson.get("year")),
            Integer.parseInt((String) filmeJson.get("imDbRatingCount")),
            Integer.parseInt((String) filmeJson.get("rank")),
            Float.parseFloat((String) filmeJson.get("imDbRating"))
        );
    }
}
