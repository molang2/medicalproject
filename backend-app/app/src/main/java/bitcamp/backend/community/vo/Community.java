package bitcamp.backend.community.vo;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Data;

@Data
public class Community {

  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
  private Date createdDate;

  private int no;
  private int doctorNo;
  private String doctorName;
  private String title;
  private String content;
  private int category;
  //private String comWriter;  // 작성자(의사) 아이디
  private int viewCnt;
  private int filter;
  private int area;  // 게시글 공개 범위

}