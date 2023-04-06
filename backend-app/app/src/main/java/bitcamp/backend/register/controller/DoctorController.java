package bitcamp.backend.register.controller;

import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import bitcamp.backend.register.service.DoctorService;
import bitcamp.backend.register.vo.Doctor;
import bitcamp.util.RestResult;
import bitcamp.util.RestStatus;

@RestController
@RequestMapping("/doctors")
@CrossOrigin("*")
public class DoctorController {

  Logger log = LogManager.getLogger(getClass());

  {
    log.trace("DoctorController 생성됨!");
  }

  @Autowired private DoctorService doctorService;

  @PostMapping
  public Object insert(@RequestBody Doctor doctor) {
    doctorService.add(doctor);
    return new RestResult()
        .setStatus(RestStatus.SUCCESS);
  }

  @PostMapping("/profileimg")
  public Object insertimg(MultipartHttpServletRequest request) {
    List<MultipartFile> files = request.getFiles("files");
    for(int i =0;i<files.size();i++) {
      System.out.println(files.get(i).getOriginalFilename());
      System.out.println(files.get(i));
      System.out.println(files.get(i).getContentType());
    }

    return null;
  }

  @PostMapping("/check-duplicate/{id}")
  public Object checkDuplicateId(@PathVariable String id) {
    boolean isDuplicate = doctorService.isDuplicateId(id);

    if (isDuplicate) {
      return new RestResult()
          .setStatus(RestStatus.FAILURE)
          .setMessage("이미 사용하고 있는 ID입니다.");
    } else {
      return new RestResult()
          .setStatus(RestStatus.SUCCESS)
          .setMessage("사용 가능한 ID입니다.");
    }
  }

  @GetMapping
  public Object list() {
    return new RestResult()
        .setStatus(RestStatus.SUCCESS)
        .setData(doctorService.list());
  }

  @GetMapping("{no}")
  public Object view(@PathVariable int no) {
    return new RestResult()
        .setStatus(RestStatus.SUCCESS)
        .setData(doctorService.get(no));
  }

  @PutMapping("{no}")
  public Object update(
      @PathVariable int no,
      @RequestBody Doctor doctor) {

    log.debug(doctor);

    doctor.setNo(no);
    doctorService.update(doctor);

    return new RestResult()
        .setStatus(RestStatus.SUCCESS);
  }

  @DeleteMapping("{no}")
  public Object delete(@PathVariable int no) {
    doctorService.delete(no);
    return new RestResult()
        .setStatus(RestStatus.SUCCESS);
  }

}
