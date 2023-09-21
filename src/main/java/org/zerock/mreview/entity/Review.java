package org.zerock.mreview.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"movie","member"}) // toString() 호출 시에 다른 엔티티를 사용하지 않도록 exclude 지정
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewnum;

    private int grade;   // 평점
    private String text; // 리뷰 내용

    /* Review 클래스는 Movie와 Member를 @ManyToOne을 이용해서 양쪽을 참조하는 구조이다. */
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    /* 리뷰 수정 기능 */
    public void changeGrade(int grade){
        this.grade = grade;
    }

    public void changeText(String text){
        this.text = text;
    }

}
