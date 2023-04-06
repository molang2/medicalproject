package bitcamp.backend.community.service;

import java.util.List;
import bitcamp.backend.community.vo.CommunityImg;

public interface CommunityImgService {
  void add(CommunityImg comImg);

  List<CommunityImg> list(int B_no);

  CommunityImg get(int no);

  void update(CommunityImg comImg);

  void delete(int no);
}
