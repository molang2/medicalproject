package bitcamp.backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import bitcamp.backend.user.service.BoardImgService;
import bitcamp.backend.user.service.BoardService;
import bitcamp.backend.user.service.ObjectStorageService;
import bitcamp.backend.user.vo.Board;
import bitcamp.backend.user.vo.BoardImg;


@CrossOrigin("*")
@SpringBootApplication
@RestController
@ResponseBody
public class App {

  @Autowired
  private BoardService boardService;

  @Autowired
  private ObjectStorageService objectStorageService;

  @Autowired
  private BoardImgService boardImgService;

  private String bucketName = "study-bucket/board-img";



  public static void main(String args[]) {
    SpringApplication.run(App.class, args);
  }

  @GetMapping("/hello")
  public long hello() {



    long ran = (long) ((Math.random() * (999999999999l - 111111111111l)) + 111111111111l);

    System.out.println(ran);

    return ran;
  }

  @PostMapping("/insert")
  @ResponseBody
  public Object sayHello(@RequestBody HashMap<String, Object> param) {
    long ran = 0;
    while (true) {
      ran = (long) ((Math.random() * (999999999999l - 111111111111l)) + 111111111111l);
      List<Board> boards = boardService.list("");
      for (int i = 0; i < boards.size(); i++) {
        // if (Long.parseLong(boards.get(i).getPid()) == ran) {
        // continue;
        // }
      }


      break;
    }
    System.out.println("param : " + param);
    String str = "";

    str += param.get("name") + ",";
    str += param.get("age") + ",";
    str += param.get("gender") + ",";
    str += param.get("tel") + ",";
    str += param.get("addr1") + " " + param.get("addr2") + ",";
    str += param.get("another");

    Board board = new Board((String) param.get("title"), ran + "", (String) param.get("pain"), str);


    boardService.add(board);

    return boardService.get(board.getSerial());
  }


  @PostMapping("/boardSearch")
  public Object bSearch(@RequestBody HashMap<String, Object> param) {
    List<Board> boards = boardService.list((String) param.get("search"));
    return boards;
  }

  @PostMapping("/boardPassword")
  public Object bSearchPwd(@RequestBody HashMap<String, Object> param) {
    System.out.println(param);

    Map<String, Object> result = new HashMap<>();


    if (boardService.get((String) param.get("password")) != null) {
      Board board = boardService.get((String) param.get("password"));

      result.put("status", "success");
      result.put("data", board);
    } else {
      result.put("status", "fail");
    }

    return result;
  }

  @PostMapping("/boardNo")
  public Object bSearchNo(@RequestBody HashMap<String, Object> param) {

    Map<String, Object> result = new HashMap<>();

    if (boardService.get(Integer.parseInt(((String) param.get("no")))) != null) {
      Board board = boardService.get(Integer.parseInt(((String) param.get("no"))));

      result.put("status", "success");
      result.put("data", board);
    } else {
      result.put("status", "fail");
    }

    return result;
  }

  @PostMapping("/files")
  public Object home(MultipartHttpServletRequest request) {


    List<MultipartFile> files = request.getFiles("files");
    List<String> strs = new ArrayList<>();

    System.out.println("보드 번호 : " + request.getParameter("boardNo"));

    for (MultipartFile file : files) {
      System.out.println(file.getOriginalFilename() + ":" + file.getSize());
      strs.add(objectStorageService.uploadFile(bucketName, file));
    }


    return strs;
  }

  @PostMapping("/insertBoardImg")
  public void imgBoard(MultipartHttpServletRequest request) {
    List<MultipartFile> files = request.getFiles("files");
    List<String> strs = new ArrayList<>();
    int b_No = Integer.parseInt(request.getParameter("boardNo"));

    System.out.println("보드 번호 : " + b_No);

    for (MultipartFile file : files) {
      System.out.println(file.getOriginalFilename() + ":" + file.getSize());
      String str = objectStorageService.uploadFile(bucketName, file);

      BoardImg boardImg = new BoardImg();
      boardImg.setB_no(b_No);
      boardImg.setUrl(str);
      boardImg.setName(file.getOriginalFilename());
      boardImg.setMIMETYPE(file.getContentType());

      boardImgService.add(boardImg);
    }
  }

  @PostMapping("/findAllBoardImg")
  public Object findimgBoard(@RequestBody HashMap<String, Object> param) {
    Map<String, Object> result = new HashMap<>();

    List<BoardImg> list = boardImgService.list(Integer.parseInt((String) param.get("bno")));

    if (list != null && list.size() > 0) {
      result.put("status", "success");
      result.put("data", list);
    } else {
      result.put("status", "fail");
    }

    return result;
  }
}
