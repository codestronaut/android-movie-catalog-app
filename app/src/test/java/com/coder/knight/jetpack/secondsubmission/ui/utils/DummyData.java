package com.coder.knight.jetpack.secondsubmission.ui.utils;

import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.GenreEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.MovieEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.ReviewEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.TrailerEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.local.entity.TvShowEntity;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.MovieResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.ReviewResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.TrailerResponse;
import com.coder.knight.jetpack.secondsubmission.data.source.remote.response.TvShowResponse;

import java.util.ArrayList;

public class DummyData {

    public static MovieResponse generateDummyMovie() {
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

        return new MovieResponse(movieEntities);
    }

    public static TvShowResponse generateDummyTvShow() {
        ArrayList<TvShowEntity> tvShowEntities = new ArrayList<>();

        tvShowEntities.add(new TvShowEntity(62286,
                "Fear the Walking Dead",
                "263.904",
                1036,
                (float) 6.2,
                "2015-08-23",
                "/lZMb3R3e5vqukPbeDMeyYGf2ZNG.jpg",
                "/nUXzdD2Jo3wV9Q7mIZjK46Yyty4.jpg",
                "en",
                "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                generateGenres()));

        tvShowEntities.add(new TvShowEntity(1434,
                "Family Guy",
                "203.071",
                1641,
                (float) 6.5,
                "1999-01-31",
                "/gBGUL1UTUNmdRQT8gA1LUV4yg39.jpg",
                "/3OFrs1ets87VmRvG78Zg5eJTZeq.jpg",
                "en",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                generateGenres()));

        tvShowEntities.add(new TvShowEntity(1413,
                "American Horror Story",
                "158.392",
                1140,
                (float) 7,
                "2011-10-05",
                "/uR2K0Ui9UsOqnzb5IrV6tLUcUHl.jpg",
                "/1gGRY9bnIc0Jaohgc6jNFidjgLK.jpg",
                "en",
                "An anthology horror drama series centering on different characters and locations, including a house with a murderous past, an asylum, a witch coven, a freak show, a hotel, a farmhouse in Roanoke and a cult.",
                generateGenres()));

        tvShowEntities.add(new TvShowEntity(456,
                "The Simpsons",
                "207.937",
                2144,
                (float) 7.1,
                "1989-12-17",
                "/yTZQkSsxUFJZJe67IenRM0AEklc.jpg",
                "/1pP0gg0iNGX06wSs19EQOvzfZIF.jpg",
                "en",
                "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                generateGenres()));

        tvShowEntities.add(new TvShowEntity(75450,
                "Titans",
                "119.771",
                246,
                (float) 7.4,
                "2018-10-12",
                "/eeHI5iBSCOxj4HGSOmFM6azBmwb.jpg",
                "/7DE9KC9GyY2TUJMnSPgYJG3rafw.jpg",
                "en",
                "A team of young superheroes led by Nightwing (formerly Batman's first Robin) form to combat evil and other perils.",
                generateGenres()));

        return new TvShowResponse(tvShowEntities);
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

    public static MovieEntity generateMovieById(int movieId) {
        return new MovieEntity(movieId,
                "Spider-Man: Far from Home",
                (float) 7.7,
                3702,
                "en",
                "393.51",
                "2019-07-02",
                "/lcq8dVxeeOqHvvgcte707K0KVx5.jpg",
                "/dihW2yTsvQlust7mSuAqJDtqW7k.jpg",
                "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
                generateGenres());
    }

    public static TvShowEntity generateTvShowById(int tvId) {
        return new TvShowEntity(tvId,
                "Fear the Walking Dead",
                "263.904",
                1036,
                (float) 6.2,
                "2015-08-23",
                "/lZMb3R3e5vqukPbeDMeyYGf2ZNG.jpg",
                "/nUXzdD2Jo3wV9Q7mIZjK46Yyty4.jpg",
                "en",
                "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
                generateGenres());
    }

    public static TrailerResponse generateTrailer() {
        ArrayList<TrailerEntity> trailerEntities = new ArrayList<>();
        trailerEntities.add(new TrailerEntity("DYYtuKyMtY8"));
        trailerEntities.add(new TrailerEntity("V38cLTYYXNw"));
        trailerEntities.add(new TrailerEntity("LFoz8ZJWmPs"));

        return new TrailerResponse(trailerEntities);
    }

    public static ReviewResponse generateReviews() {
        ArrayList<ReviewEntity> reviewEntities = new ArrayList<>();
        reviewEntities.add(new ReviewEntity("SWITCH.",
                "The whole film is a relentless flurry of action and adventure from the get-go, with the man himself sporting no less than four different spider suits (for all the mega fans out there). Maria Hill (Cobie Smulders) gets a look-in once again after her very disappointing absence from 99.99% of ‘Endgame’, but she’s wasted on screen, serving little to no purpose. And while this film finally gives us a Peter/MJ love story, it’s all too familiar territory - not because of the characters involved but because we saw Peter pursue a crush in ‘Homecoming’. We also saw him try to balance the student/hero sides of his life then too. This is THIS Peter Parker’s fifth time on the rollercoaster. We’ve seen him and fell in love with him as the sweet, innocent kid who had greatness thrust upon him and his thirst to be a superhero. He had his shot in the ring - several times in fact - and now we need to see him grow, but they’ve just given us much of the same. We need to know where this is going, not just watch a kid play dress-ups time and time again.\\r\\n- Jess Fenton\\r\\n\\r\\nRead Jess' full article...\\r\\nhttps://www.maketheswitch.com.au/article/review-spider-man-far-from-home-in-a-post-iron-man-world-spideys-still-the-same-insecure-kid"));
        return new ReviewResponse(reviewEntities);
    }
}
