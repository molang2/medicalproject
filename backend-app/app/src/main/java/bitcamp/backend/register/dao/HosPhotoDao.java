package bitcamp.backend.register.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import bitcamp.backend.register.vo.HosPhoto;
@Mapper
public interface HosPhotoDao {
  void insert(HosPhoto hosPhoto);
  List<HosPhoto> findAll();
  HosPhoto findByNo(int no);
  HosPhoto findByIdAndPassword(Map<String, Object> params);
  int update(HosPhoto hosPhoto);
  int delete(int no);
}
