package tech.aistar.tx;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/5/5 0005
 */
public interface IAccountDao {
    void transfer(String srcno,String targetno,double money);
}
