package com.coder.knight.jetpack.secondsubmission.utils;

import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.GenreEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.MovieEntity;

import java.util.ArrayList;

public class FakeDummyData {

    public static ArrayList<MovieEntity> generateDummyMovie() {
        ArrayList<MovieEntity> movieEntities = new ArrayList<>();

        movieEntities.add(new MovieEntity(429617,
                "Spider-Man: Far from Home",
                (float) 7.7,
                3702,
                "en",
                "393.51",
                "2019-07-02",
                "/lcq8dVxeeOqHvvgcte707K0KVx5.jpg",
                "/dihW2yTsvQlust7mSuAqJDtqW7k.jpg",
                "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                generateGenres()));
        movieEntities.add(new MovieEntity(474350,
                "It Chapter Two",
                (float) 7.1,
                848,
                "en",
                "330.197",
                "2019-09-06",
                "/zfE0R94v1E8cuKAerbskfD3VfUt.jpg",
                "/4W0FnjSGn4x0mKZlBRx8OjFxQUM.jpg",
                "27 years after overcoming the malevolent supernatural entity Pennywise, the former members of the Losers' Club, who have grown up and moved away from Derry, are brought back together by a devastating phone call.",
                generateGenres()));

        movieEntities.add(new MovieEntity(429203,
                "The Old Man & the Gun",
                (float) 6.3,
                530,
                "en",
                "384.25",
                "2018-09-28",
                "/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg",
                "/6X2YjjYcs8XyZRDmJAHNDlls7L4.jpg",
                "The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public. Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, who loves him in spite of his chosen profession.",
                generateGenres()));

        movieEntities.add(new MovieEntity(522938,
                "Rambo: Last Blood",
                (float) 6.3,
                100,
                "en",
                "234.923",
                "2019-09-20",
                "/kTQ3J8oTTKofAVLYnds2cHUz9KO.jpg",
                "/spYx9XQFODuqEVoPpvaJI1ksAVt.jpg",
                "When his housekeeper’s granddaughter is kidnapped, Rambo crosses the U.S.-Mexican border to bring her home but finds himself up against the full might of one of Mexico’s most ruthless cartels.",
                generateGenres()));

        movieEntities.add(new MovieEntity(540901,
                "Hustlers",
                (float) 6.3,
                129,
                "en",
                "195.269",
                "2019-09-13",
                "/zBhv8rsLOfpFW2M5b6wW78Uoojs.jpg",
                "/jTab4cf4X1dJJVS4F8UOGuesvPd.jpg",
                "A crew of savvy former strip club employees band together to turn the tables on their Wall Street clients.",
                generateGenres()));

        return movieEntities;
    }

    private static ArrayList<GenreEntity> generateGenres() {
        ArrayList<GenreEntity> genreEntities = new ArrayList<>();

        genreEntities.add(new GenreEntity(28, "Action"));
        genreEntities.add(new GenreEntity(12, "Adventure"));
        genreEntities.add(new GenreEntity(16, "Animation"));
        genreEntities.add(new GenreEntity(35, "Comedy"));
        genreEntities.add(new GenreEntity(80, "Crime"));
        genreEntities.add(new GenreEntity(99, "Documentary"));
        genreEntities.add(new GenreEntity(18, "Drama"));
        genreEntities.add(new GenreEntity(10751, "Family"));
        genreEntities.add(new GenreEntity(14, "Fantasy"));
        genreEntities.add(new GenreEntity(36, "History"));
        genreEntities.add(new GenreEntity(27, "Horror"));
        genreEntities.add(new GenreEntity(10402, "Music"));
        genreEntities.add(new GenreEntity(9648, "Mystery"));
        genreEntities.add(new GenreEntity(10749, "Romance"));
        genreEntities.add(new GenreEntity(878, "Science Fiction"));
        genreEntities.add(new GenreEntity(10770, "TV Movie"));
        genreEntities.add(new GenreEntity(53, "Thriller"));
        genreEntities.add(new GenreEntity(10752, "War"));
        genreEntities.add(new GenreEntity(37, "Western"));

        return genreEntities;
    }
}
