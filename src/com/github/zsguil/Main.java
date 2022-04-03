package com.github.zsguil;

import com.github.zsguil.service.FilmeService;

import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParseException, InterruptedException {
        new FilmeService().getTopFilmesImdb().forEach(System.out::println);
    }

}
