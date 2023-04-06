package bitcamp.backend.community.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import bitcamp.backend.community.vo.CommunityImg;

@Mapper
public interface CommunityImgDao {
  public void insert(CommunityImg communityImg);

  public List<CommunityImg> findByBno(int c_no);

  public CommunityImg findByNo(int no);

}
