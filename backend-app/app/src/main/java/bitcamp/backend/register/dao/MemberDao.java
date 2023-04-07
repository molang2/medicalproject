package bitcamp.backend.register.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import bitcamp.backend.register.vo.Member;

@Mapper
public interface MemberDao {

  void insert(Member m);

  Member findById(String id);

  List<Member> findAll();

  Member findByNo(int no);

  int update(Member m);

  int delete(int no);

}






