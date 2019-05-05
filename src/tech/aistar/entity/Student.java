package tech.aistar.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:学生实体类
 * @date 2019/4/28 0028
 */
public class Student implements Serializable{
    private Integer id;

    private String sno;//学生编号

    private String sname;//学生姓名

    private Date birthday;//学生生日

    private Gender gender;//本质上可以直接使用int类型.

    public Student() {
    }

    public Student(String sno, String sname, Date birthday, Gender gender) {
        this.sno = sno;
        this.sname = sname;
        this.birthday = birthday;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("id=").append(id);
        sb.append(", sno='").append(sno).append('\'');
        sb.append(", sname='").append(sname).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append(", gender=").append(gender);
        sb.append('}');
        return sb.toString();
    }
}
