package org.zerock.mreview.controller;

import lombok.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Log4j2
public class UploadController {

    @Value("${org.zerock.upload.path}")
    private String uploadPath;
    
    @PostMapping("/uploadAjax")
    public void uploadFile(MultipartFile[] uploadFiles){
        // MultipartFile 배열로 받도록 설계 (배열을 활용하면 동시에 여러 개의 파일 정보를 처리할 수 있다)
        
        for (MultipartFile uploadFile: uploadFiles) {
            // 브라우저에 따라 업로드하는 파일의 이름은 전체 경로일 수도 있고 (IE계열)
            // 단순히 파일의 이름만을 의미할 수도 있다.(크롬 브라우저)
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
            
            log.info("fileName: " + fileName);
        } // end for
    }
}
