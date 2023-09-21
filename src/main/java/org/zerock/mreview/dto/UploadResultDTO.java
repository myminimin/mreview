package org.zerock.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@AllArgsConstructor
public class UploadResultDTO implements Serializable {
    // 실제 파일과 관련된 모든 정보를 가짐
    
    private String fileName;    // 업로드된 파일의 원래 이름
    private String uuid;        // 파일의 UUID 값
    private String folderPath;  // 업로드된 파일의 저장 경로


    public String getImageURL() {
        try {
            return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }   // 전체 경로가 필요한 경우

    public String getThumbnailURL(){
        try {
            return URLEncoder.encode(folderPath+"/s_"+uuid+"_"+fileName,"UTF-8");
            // getImageURL()과 거의 동일한데 "/s_" 가 다르다
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }   // 섬네일 링크를 처리하는 메서드
}
