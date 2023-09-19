package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Review;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertMembers() {

        IntStream.rangeClosed(1,100).forEach(i -> {
            Member member = Member.builder()
                    .email("r"+i+"@zerock.org")
                    .pw("1111")
                    .nickname("reviewer"+i)
                    .build();
            memberRepository.save(member);
        });
    }   // 회원 추가
    @Commit
    @Transactional
    @Test
    public void testDeleteMember() {

        Long mid = 2L;  // Member의 mid

        Member member = Member.builder().mid(mid).build();

        // memberRepository.deleteById(mid);
        // reviewRepository.deleteByMember(member);
        // 1. FK를 가지는 Review 쪽이 먼저 삭제되어야 한다.
        // 2. 트랜젝션 관련 처리가 있어야 한다.

        // 순서 주의
        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);
    }
}
