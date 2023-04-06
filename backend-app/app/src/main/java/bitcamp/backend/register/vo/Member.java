package bitcamp.backend.register.vo;

import java.sql.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Data;

@Data
public class Member {
  private int no;
  private String id;
  private String password;
  private String name;
  private String email;
  private String addr;
  private String gender;
  private String tel;
  private boolean auth;
  private String phoUrl;
  private String phoName;
  private String phoType;

  @JsonFormat(
      shape = Shape.STRING,
      pattern = "yyyy-MM-dd")
  private Date birth;
}
