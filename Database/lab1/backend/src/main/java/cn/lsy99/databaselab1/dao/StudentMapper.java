package cn.lsy99.databaselab1.dao;

import cn.lsy99.databaselab1.entity.Student;
import cn.lsy99.databaselab1.entity.StudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface StudentMapper {
    long countByExample(StudentExample example);

    List<Student> selectByExample(StudentExample example);

    Student selectByPrimaryKey(String id);
}