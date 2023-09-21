package org.zerock.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {

    private Long mno;
    private String title;

    @Builder.Default
    private List<MovieImageDTO> imageDTOList = new ArrayList<>();
    // 영화 이미지들도 같이 수집해서 전달해야 하므로 내부적으로 리스트를 이용해서 수집

    private double avg; // 영화의 평균 평점 MovieRepository에서 avg(coalesce(r.grade,0))
    private int reviewCnt; // 리뷰 수 jpa의 count()
    
    private LocalDateTime regDate;
    private LocalDateTime modDate;

}
