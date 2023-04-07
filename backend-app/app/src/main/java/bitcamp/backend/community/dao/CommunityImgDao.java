package bitcamp.backend.community.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import bitcamp.backend.community.vo.CommunityImg;

@Mapper
public interface CommunityImgDao {
  void insert(CommunityImg communityImg);
  List<CommunityImg> findAll();
  CommunityImg findByComNo(int no);
  int update(CommunityImg communityImg);
  int delete(int no);
  List<CommunityImg> findByCno(int no);

}
