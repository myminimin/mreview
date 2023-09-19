package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTests {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMovieReviews() {

        IntStream.rangeClosed(1,200).forEach(i -> {
            // 200개의 리뷰를 등록

            Long mno = (long)(Math.random()*100) + 1;
            // 임의의 값으로 영화 번호 생성 처리

            Long mid = ((long)(Math.random()*100) +1);
            // 임의의 값으로 리뷰어 번호 생성 처리
            Member member = Member.builder().mid(mid).build();

            Review movieReview = Review.builder()
                    .member(member)
                    .movie(Movie.builder().mno(mno).build())
                    .grade((int)(Math.random()*5) +1)
                    .text("이 영화에 대한 느낌..."+i)
                    .build();

            reviewRepository.save(movieReview);
        });
    }   // 리뷰 등록

    @Test
    public void testGetMovieReviews() {

        Movie movie = Movie.builder().mno(91L).build();

        List<Review> result = reviewRepository.findByMovie(movie);
        // 91번 movie 객체에 대한 리뷰를 찾아서 해당 리뷰들을 Review 객체의 List로 반환

        result.forEach(movieReview -> {

            System.out.println(movieReview.getReviewnum());
            System.out.println("\t"+movieReview.getGrade());
            System.out.println("\t"+movieReview.getText());
            System.out.println("\t"+movieReview.getMember().getEmail());
            System.out.println("==================================");
        });
    }
}
