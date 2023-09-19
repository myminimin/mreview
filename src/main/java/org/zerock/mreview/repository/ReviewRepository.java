package org.zerock.mreview.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);
    // 특정한 영화 번호를 이용해서 해당 영화를 리뷰한 정보를 찾기
    // @EntityGraph를 적용해서 Member도 같이 로딩할 수 있도록 변경

    @Modifying  // update/delete를 이용하기 위해선 필수적인 어노테이션
    @Query("DELETE FROM Review mr WHERE mr.member = :member")
    // @Query를 이용해서 where절을 지정해주면 리뷰가 여러 개일 때 한 번에 묶어서 삭제 처리할 수 있다.
    void deleteByMember(Member member);
    // 특정한 회원을 삭제하는 경우 해당 회원이 등록한 모든 영화 리뷰가 삭제되어야 한다.
    // m_member 테이블에서 특정 회원을 삭제하려면 우선은 review 테이블에서 먼저 삭제하고,
    // m_member 테이블에서 삭제해야 합니다. 이 2개의 작업은 하나의 트랜젝션으로 관리될 필요가 있다.
}
