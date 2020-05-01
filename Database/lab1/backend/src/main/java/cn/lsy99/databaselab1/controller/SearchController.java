package cn.lsy99.databaselab1.controller;

import cn.lsy99.databaselab1.entity.Input;
import cn.lsy99.databaselab1.entity.Output;
import cn.lsy99.databaselab1.entity.Student;
import cn.lsy99.databaselab1.service.SearchService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/database/lab1")
public class SearchController {

  @Autowired
  private SearchService searchService;

  @PostMapping("/search")
  public Output isSuccess(@RequestBody Input input) {
    List<Student> students = searchService.getStudents(input);
    String sql = searchService.generateSql(input);
    return new Output(sql, students);
  }
}
