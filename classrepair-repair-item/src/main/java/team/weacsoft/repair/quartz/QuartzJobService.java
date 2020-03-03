package team.weacsoft.repair.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GreenHatHG
 * @since 2020-03-03
 */

@Service
public class QuartzJobService {

    @Autowired
    private Scheduler scheduler;

    public void addJob(String cron) throws SchedulerException {
        //创建一个任务
        //指明job的名称，以及绑定job类
        JobDetail jobDetail = JobBuilder.newJob(SendTask.class)
                .withIdentity("send message")
                .build();

        //创建一个触发器，指定任务在什么时间执行
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("send message")
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 更新job cron表达式
     */
    public void updateJobCron(String cron) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey("send message");
        //之前没有创建过任务就创建，否则删除重新添加
        if(scheduler.checkExists(triggerKey)){
            scheduler.pauseTrigger(triggerKey);
            scheduler.unscheduleJob(triggerKey);
        }
        //如果不是"-"的话，就添加任务，否则当作删除
        if(!"-".equals(cron)){
            addJob(cron);
        }
    }
}
