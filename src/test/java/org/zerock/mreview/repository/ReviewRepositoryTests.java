package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

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
    }
}
