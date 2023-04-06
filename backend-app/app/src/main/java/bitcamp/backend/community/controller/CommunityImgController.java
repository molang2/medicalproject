package bitcamp.backend.community.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import bitcamp.backend.community.service.CommunityImgService;
import bitcamp.backend.community.vo.CommunityImg;
import bitcamp.backend.user.service.ObjectStorageService;

@RestController
@RequestMapping("/communityimg")
@CrossOrigin("*")
@SpringBootApplication
public class CommunityImgController {

  private String bucketName = "study-bucket/community-img";

  @Autowired
  private ObjectStorageService objectStorageService;

  @Autowired
  private CommunityImgService communityImgService;


  //  @PostMapping("/communityNo")
  //  public Object cSearchNo(@RequestBody HashMap<String, Object> param) {
  //
  //    Map<String, Object> result = new HashMap<>();
  //
  //    if (CommunityService.get(Integer.parseInt((String) (param.get("no"))) != null) {
  //      Community community = communityService.get(Integer.parseInt((String) (param.get("no")));
  //
  //      result.put("status", "success");
  //      result.put("data", community);
  //    } else {
  //      result.put("status", "fail");
  //    }
  //
  //    return result;
  //  }

  @CrossOrigin("*")
  @PostMapping("/files")
  public Object home(MultipartHttpServletRequest request) {


    List<MultipartFile> files = request.getFiles("files");
    List<String> strs = new ArrayList<>();

    System.out.println("커뮤 번호 : " + request.getParameter("communityNo"));

    for (MultipartFile file : files) {
      System.out.println(file.getOriginalFilename() + ":" + file.getSize());
      strs.add(objectStorageService.uploadFile(bucketName, file));
    }


    return strs;
  }

  @CrossOrigin("*")
  @PostMapping("/insertComImg")
  public void imgCommunity(MultipartHttpServletRequest request) {
    List<MultipartFile> files = request.getFiles("files");
    List<String> strs = new ArrayList<>();
    int c_No = Integer.parseInt(request.getParameter("comNo"));

    System.out.println("커뮤 사진번호 : " + c_No);

    for (MultipartFile file : files) {
      System.out.println(file.getOriginalFilename() + ":" + file.getSize());
      String str = objectStorageService.uploadFile(bucketName, file);

      CommunityImg communityImg = new CommunityImg();
      communityImg.setC_no(c_No);
      communityImg.setUrl(str);
      communityImg.setName(file.getOriginalFilename());
      communityImg.setMIMETYPE(file.getContentType());

      communityImgService.add(communityImg);
    }
  }

  @CrossOrigin("*")
  @PostMapping("/findAllComImg")
  public Object findimgCommunity(@RequestBody HashMap<String, Object> param) {
    Map<String, Object> result = new HashMap<>();

    List<CommunityImg> list = communityImgService.list(Integer.parseInt((String) param.get("cno")));

    if (list != null && list.size() > 0) {
      result.put("status", "success");
      result.put("data", list);
    } else {
      result.put("status", "fail");
    }

    return result;
  }
}
