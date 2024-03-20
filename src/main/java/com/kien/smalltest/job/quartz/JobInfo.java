package com.kien.smalltest.job.quartz;

import lombok.*;

/**
 * @author kienvt
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobInfo {

    private String name;
    private String cronExpression;
    private String jobClass;

}
