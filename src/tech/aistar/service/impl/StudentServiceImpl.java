package tech.aistar.service.impl;

import tech.aistar.dao.page.IStudentPageDao;
import tech.aistar.dao.page.impl.StudentDaoPageImpl;
import tech.aistar.entity.Student;
import tech.aistar.service.IStudentService;
import tech.aistar.vo.PageList;

import java.util.List;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:业务逻辑层
 * @date 2019/5/5 0005
 */
public class StudentServiceImpl implements IStudentService{

    //业务层调用dao层.
    private IStudentPageDao pageDao = new StudentDaoPageImpl();


    @Override
    public PageList<Student> findAllByPage(Integer pageNow, Integer pageSize) {
        //创建一个分页对象
        PageList<Student> pageList = new PageList<>();
        //封装显示的数据
        pageList.setPageNow(pageNow);
        pageList.setPageSize(pageSize);

        //封装总的条数
        int rows = pageDao.getRows();
        pageList.setRows(rows);

        //求出总的页数
        int pageCounts = rows / pageSize;

        if(rows % pageSize != 0)
            pageCounts++;

        pageList.setPageCounts(pageCounts);

        //获取分页的数据
        List<Student> students = pageDao.findByPage(pageNow,pageSize);

        pageList.setDatas(students);

        return pageList;
    }
}
