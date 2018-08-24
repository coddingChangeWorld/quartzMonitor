package com.schedule.task;

import com.common.base.dao.BaseDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("produceScheduleJob")
public class ProduceScheduleJob
  implements Task
{
  @Autowired
  private BaseDaoImpl baseDaoImpl;
  
  public void excute() {}
}
