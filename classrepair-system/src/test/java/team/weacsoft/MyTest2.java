package team.weacsoft;

import java.time.LocalDate;
import java.util.Date;

/**
 * @Description
 * @Author 魔法はまだ解けない
 * @Date 2020/12/23
 */
public class MyTest2 {

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(new Date());
        LocalDate now = LocalDate.now();

        System.out.println(now);
        LocalDate of = LocalDate.of(now.getDayOfYear(), now.getMonth(), now.getDayOfMonth());

    }
}
