package com.oscar.ovewatchdashboard.data;

import javax.persistence.EntityManager;

import com.oscar.ovewatchdashboard.model.Team;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;


@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final EntityManager em;

    @Autowired
    public JobCompletionNotificationListener(EntityManager em) {
        this.em = em;
    }
/**
 * Waiting for job to be completed 
 * Which is using a jbctemplete which is injected using autowired 
 */
@Override
@Transactional // srping boot knows this whole method will be the trasnsation that it needs
public void afterJob(JobExecution jobExecution) {
  if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
    log.info("!!! JOB FINISHED! Time to verify the results");
    
    Map<String, Team> teamData = new HashMap<>();
          // are we steaming this all at once? Does this mean that we will get the 
          // e -> the map object, return type,  to be able to be called 
          // answer is yes, we dont need to map anymore we have already done this in the ffirst query 
          
    em.createQuery("select m.attacker, count(*) from Match m group by m.attacker", Object[].class)
      .getResultList()
      .stream()
      .map(e -> new Team((String) e[0], (long) e[1]))// map creates the team object 
      .forEach(e -> teamData.put(e.getTeamName(), e)); // each team object mapped is now adding to 
  
      em.createQuery("select m.defender, count(*) from Match m group by m.defender", Object[].class)
      .getResultList()
      .stream()
      .forEach(e -> {
          Team team = teamData.get((String) e[0]);
          System.out.println("heelo" + teamData.get((String) e[0]) );
          System.out.println("Before total matches"+ team.getTotalMatches());
          team.setTotalMatches(team.getTotalMatches() + (long) e[1]);
          System.out.println("After total matches"+ team.getTotalMatches());

      });

      em.createQuery("select m.mapWinner, count(*) from Match m group by m.mapWinner", Object[].class)
      .getResultList()
      .stream()
      .forEach(e -> {
          Team team = teamData.get((String) e[0]);
          if (team != null) team.setTotalWins((long) e[1]);
      });

      teamData.values().forEach(team -> em.persist(team));
      teamData.values().forEach(team -> System.out.println(team));
  }
}
}
