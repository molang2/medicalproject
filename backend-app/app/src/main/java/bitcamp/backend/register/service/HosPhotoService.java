package bitcamp.backend.register.service;

import java.util.List;
import bitcamp.backend.register.vo.HosPhoto;

public interface HosPhotoService {
  void add(HosPhoto hosPhoto);
  List<HosPhoto> list();
  HosPhoto get(int no);
  HosPhoto get(String id, String password);
  void delete(int no);
}
