package org.zerock.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    // Review 엔티티 클래스가 따로 존재하므로 ReviewDTO도 필요하다
    // Review가 Movie와 Member를 참조하는 구성으로 되어 있으므로 ReviewDTO는
    // 엔티티 클래스와 달리 단순 문자열이나 영화 번호르 참조하는 형태로 변경된다.

    // ReviewDTO는 화면에 필요한 모든 내용을 가지고 있어야하기 때문에
    // 회원 아이디/닉네임/이메일도 처리할 수 있도록 설계해야한다.

    // review
    private Long reviewnum;

    // movie
    private Long mno;

    // member
    private Long mid;
    private String nickname;
    private String email;

    private int grade;
    private String text;
    private LocalDateTime regDate, modDate;


}
