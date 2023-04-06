package bitcamp.backend.register.controller;

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
import bitcamp.backend.register.service.HospitalService;
import bitcamp.backend.register.vo.Hospital;
import bitcamp.util.RestResult;
import bitcamp.util.RestStatus;

@RestController
@RequestMapping("/hospitals")
@CrossOrigin("*")
public class HospitalController {

  Logger log = LogManager.getLogger(getClass());

  {
    log.trace("HospitalController 생성됨!");
  }

  @Autowired private HospitalService hospitalService;

  @PostMapping
  public Object insert(@RequestBody Hospital hospital) {
    hospitalService.add(hospital);
    return new RestResult()
        .setStatus(RestStatus.SUCCESS);
  }

  @GetMapping
  public Object list() {
    return new RestResult()
        .setStatus(RestStatus.SUCCESS)
        .setData(hospitalService.list());
  }

  @GetMapping("{no}")
  public Object view(@PathVariable int no) {
    return new RestResult()
        .setStatus(RestStatus.SUCCESS)
        .setData(hospitalService.get(no));
  }

  @PutMapping("{no}")
  public Object update(
      @PathVariable int no,
      @RequestBody Hospital hospital) {

    log.debug(hospital);

    hospital.setHospitalNo(no);//vo에 나온대로 setter이름 적용하였음
    hospitalService.update(hospital);

    return new RestResult()
        .setStatus(RestStatus.SUCCESS);
  }

  @DeleteMapping("{no}")
  public Object delete(@PathVariable int no) {
    hospitalService.delete(no);
    return new RestResult()
        .setStatus(RestStatus.SUCCESS);
  }

}
