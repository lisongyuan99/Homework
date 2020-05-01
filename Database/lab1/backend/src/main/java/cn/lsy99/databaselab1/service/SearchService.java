package cn.lsy99.databaselab1.service;

import cn.lsy99.databaselab1.dao.StudentMapper;
import cn.lsy99.databaselab1.entity.Input;
import cn.lsy99.databaselab1.entity.Student;
import cn.lsy99.databaselab1.entity.StudentExample;
import cn.lsy99.databaselab1.entity.StudentExample.Criteria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

  @Autowired
  private StudentMapper studentMapper;

  public List<Student> getStudents(Input input) {
    StudentExample example = new StudentExample();
    Criteria criteria = example.createCriteria();
    if (input.getId() != null) {
      criteria.andIdLike("%" + input.getId() + "%");
    }
    if (input.getName() != null) {
      criteria.andNameLike("%" + input.getName() + "%");
    }
    if (input.getAge() != null) {
      criteria.andAgeBetween(input.getAge()[1], input.getAge()[2]);
    }
    if (input.getGender() != null) {
      criteria.andGenderLike("%" + input.getGender() + "%");
    }
    if (input.getClassName() != null) {
      criteria.andIdLike("%" + input.getClassName() + "%");
    }
    if (input.getDept() != null) {
      criteria.andIdLike("%" + input.getDept() + "%");
    }
    if (input.getAddr() != null) {
      criteria.andAddrLike("%" + input.getAddr() + "%");
    }
    return studentMapper.selectByExample(example);
  }

  public String generateSql(Input input) {
    // 生成sql语句
    String sqlLine = "SELECT * FROM student ";
    String where = "";
    where = where + stringHelper("id", input.getId());
    where = where + stringHelper("name", input.getName());
    where = where + stringHelper("gender", input.getGender());
    where = where + stringHelper("class_name", input.getClassName());
    where = where + stringHelper("dept", input.getDept());
    where = where + stringHelper("addr", input.getAddr());
    if (input.getAge() != null) {
      where = where + " AND age >= " + input.getAge()[0] + " AND age <= " + input.getAge()[1];
    }
    if (where.length() > 5) {
      where = where.substring(5);
      sqlLine = sqlLine + "WHERE " + where;
    }
    return sqlLine;
  }

  public String stringHelper(String colName, String content) {
    String result;
    if (content == null) {
      result = "";
    } else if (content.contains("%") || content.contains("_") || content.contains("[")) {
      result = " AND " + colName + " LIKE '" + content + "'";
    } else {
      result = " AND " + colName + " = '" + content + "'";
    }
    return result;
  }
}
