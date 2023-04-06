package bitcamp.backend.community.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import bitcamp.backend.community.service.CommunityService;
import bitcamp.backend.community.vo.Community;
import bitcamp.util.RestResult;
import bitcamp.util.RestStatus;

@RestController
@RequestMapping("/community")
@CrossOrigin("*")
public class CommunityController {

  Logger log = LogManager.getLogger(getClass());

  @Autowired
  private CommunityService communityService;

  //  @GetMapping("/test")
  //  public void test(){
  //    communityService.get(7);
  //    System.out.println(communityService);
  //  }

  @PostMapping
  public Object insert(@RequestBody Community community) {
    communityService.add(community);
    return new RestResult().setStatus(RestStatus.SUCCESS);
  }

  @GetMapping("/list")
  public Object list() {
    return new RestResult().setStatus(RestStatus.SUCCESS).setData(communityService.list());
  }

  @GetMapping("{no}")
  public Object view(@PathVariable int no) {
    return new RestResult().setStatus(RestStatus.SUCCESS).getData();
  }

  @PutMapping("{no}")
  public Object update(
      @PathVariable int no,
      @RequestBody Community community) {

    log.debug(community);

    community.setNo(no);
    communityService.update(community);

    return new RestResult()
        .setStatus(RestStatus.SUCCESS);
  }

  @DeleteMapping("{no}")
  public Object delete(@PathVariable int no) {
    communityService.delete(no);
    return new RestResult()
        .setStatus(RestStatus.SUCCESS);
  }


}
