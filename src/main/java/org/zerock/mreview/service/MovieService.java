package org.zerock.mreview.service;

import org.zerock.mreview.dto.MovieDTO;
import org.zerock.mreview.dto.MovieImageDTO;
import org.zerock.mreview.dto.PageRequestDTO;
import org.zerock.mreview.dto.PageResultDTO;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.MovieImage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {

    Long register(MovieDTO movieDTO);
    
    MovieDTO getMovie(Long mno); // 영화 번호를 이용해서 MovieDTO를 반환

    PageResultDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO); //목록 처리

    default MovieDTO entitiesToDTO(Movie movie, List<MovieImage> movieImages, Double avg, Long reviewCnt){
        // 매개값 4개 : MovieRepository에서 'SELECT m, mi, avg(coalesce(r.grade,0)), count(distinct r)'

        MovieDTO movieDTO = MovieDTO.builder()
                .mno(movie.getMno())
                .title(movie.getTitle())
                .regDate(movie.getRegDate())
                .modDate(movie.getModDate())
                .build();
        // 첫 번째 movie에서 가져온 값을 movieDTO에 넣음

        List<MovieImageDTO> movieImageDTOList = movieImages.stream().map(movieImage -> {
            return MovieImageDTO.builder().imgName(movieImage.getImgName())
                    .path(movieImage.getPath())
                    .uuid(movieImage.getUuid())
                    .build();
        }).collect(Collectors.toList());
        // 두 번째 movieImages 리스트의 각 요소를 MovieImageDTO 객체로 변환하여 새로운 리스트로 수집함


        movieDTO.setImageDTOList(movieImageDTOList);
        movieDTO.setAvg(avg);
        movieDTO.setReviewCnt(reviewCnt.intValue());
        // 첫 번째 movie의 값이 들어있는 movieDTO에 나머지 3개를 다 넣음

        return movieDTO;
    }

    // 한 번에 두가지 종류의 객체를 반환해야 하므로 Map 타입을 이용한다
    default Map<String, Object> dtoToEntity(MovieDTO movieDTO){

        Map<String, Object> entityMap = new HashMap<>();

        Movie movie = Movie.builder()
                .mno(movieDTO.getMno())
                .title(movieDTO.getTitle())
                .build();

        entityMap.put("movie", movie);
        // 1. MovieDTO -> Movie 변환 후 entityMap에 movie라는 이름으로 넣는다.

        List<MovieImageDTO> imageDTOList = movieDTO.getImageDTOList();
        // MovieDTO에서 Image는 내부적으로 List를 이용해서 수집함

        //MovieImageDTO 처리
        if(imageDTOList != null && imageDTOList.size() > 0 ) {

            List<MovieImage> movieImageList = imageDTOList.stream().map(movieImageDTO ->{

                MovieImage movieImage = MovieImage.builder()
                        .path(movieImageDTO.getPath())
                        .imgName(movieImageDTO.getImgName())
                        .uuid(movieImageDTO.getUuid())
                        .movie(movie)
                        .build();
                return movieImage;

            }).collect(Collectors.toList());

            entityMap.put("imgList", movieImageList);
            // 2. MovieDTO -> ImageList 변환 후 entityMap에 movie라는 이름으로 넣는다.
        }

        return entityMap;
    }
}
