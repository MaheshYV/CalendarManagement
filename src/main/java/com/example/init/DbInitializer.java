package com.example.init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public abstract class DbInitializer implements CommandLineRunner {

//    private MovieRepository movieRepository;
//    private ReviewRepository reviewRepository;
//
//    @Autowired
//    public DbInitializer(MovieRepository movieRepository, ReviewRepository reviewRepository) {
//        this.movieRepository = movieRepository;
//        this.reviewRepository = reviewRepository;
//    }
//
//    @Override
//    public void run(String... strings) {
//
//        Movie movie1 = new Movie("Game Plan, The", 1992);
//        Movie movie2 = new Movie("Illusion Of Blood", 1984);
//        Movie movie3 = new Movie("American Gangster", 2006);
//
//        Review review1 = new Review
//                ("Now is the time for all good men to come to the aid of their party.", movie1);
//        Review review2 = new Review
//                ("The quick brown fox jumps over the lazy dog.", movie1);
//        Review review3 = new Review
//                ("Nullam sodales efficitur mauris non semper.", movie1);
//        Review review4 = new Review
//                ("Lorem ipsum dolor sit amet, consectetur adipiscing elit.", movie2);
//        Review review5 = new Review
//                ("Li lingues differe solmen in li grammatica, li pronunciation e li plu commun vocabules.", movie2);
//
//
//        this.movieRepository.save(movie1);
//        this.reviewRepository.save(review1);
//        this.reviewRepository.save(review2);
//        this.reviewRepository.save(review3);
//
//        this.movieRepository.save(movie2);
//        this.reviewRepository.save(review4);
//        this.reviewRepository.save(review5);
//
//        this.movieRepository.save(movie3);

//    }
}