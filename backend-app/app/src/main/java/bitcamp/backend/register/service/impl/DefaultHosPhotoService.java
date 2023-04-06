package bitcamp.backend.register.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bitcamp.backend.register.dao.HosPhotoDao;
import bitcamp.backend.register.service.HosPhotoService;
import bitcamp.backend.register.vo.HosPhoto;

@Service
public class DefaultHosPhotoService implements HosPhotoService  {

  @Autowired private HosPhotoDao hosPhotoDao;

  @Transactional
  @Override
  public void add(HosPhoto hosPhoto) {
    hosPhotoDao.insert(hosPhoto);
  }

  @Override
  public List<HosPhoto> list() {
    return hosPhotoDao.findAll();
  }

  @Override
  public HosPhoto get(int no) {
    return hosPhotoDao.findByNo(no);
  }

  @Override
  public HosPhoto get(String id, String password) {
    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("id", id);
    paramMap.put("password", password);

    return hosPhotoDao.findByIdAndPassword(paramMap);
  }
  @Transactional
  @Override
  public void delete(int no) {
    if (hosPhotoDao.delete(no) == 0) {
    } else {
      throw new RuntimeException("사진이 존재하지 않습니다.");
    }
  }
}
