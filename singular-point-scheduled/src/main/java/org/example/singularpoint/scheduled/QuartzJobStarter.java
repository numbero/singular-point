package org.example.singularpoint.scheduled;

import org.quartz.*;
import org.springframework.context.annotation.Bean;

public class QuartzJobStarter {

    @Bean
    public Trigger myTrigger() {
        JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class)
                .withIdentity("myJobDetail", "myJobDetailGroup")
                .storeDurably()
                .build();
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("myJobTrigger", "myJobTriggerGroup")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? "))
                .build();
    }
}
