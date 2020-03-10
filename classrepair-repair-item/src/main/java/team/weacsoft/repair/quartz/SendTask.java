package team.weacsoft.repair.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author GreenHatHG
 * @since 2020-03-03
 */

@Component
public class SendTask extends QuartzJobBean {

    private PushService pushService;

    @Autowired
    public SendTask(PushService pushService) {
        this.pushService = pushService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        pushService.sendCronMessage();
    }
}
