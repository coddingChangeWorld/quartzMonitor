package com.data.collect.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="app_profile", catalog="quartz")
public class AppProfile
  implements Serializable
{
  private Integer id;
  private String arenaName;
  private String awayTeamId;
  private String awayName;
  private String gameId;
  private String homeTeamId;
  private String homeName;
  private Integer awayScore;
  private Integer homeScore;
  private Timestamp gameTime;
  private Timestamp updateTime;
  private String status;  // 未开始 1  进行中 2 已结束 3
  
  public AppProfile() {}
  
  public AppProfile(String arenaName, String awayTeamId, String awayName, String gameId, String homeTeamId, String homeName, Integer awayScore, Integer homeScore, Timestamp gameTime, Timestamp updateTime, String status)
  {
    this.arenaName = arenaName;
    this.awayTeamId = awayTeamId;
    this.awayName = awayName;
    this.gameId = gameId;
    this.homeTeamId = homeTeamId;
    this.homeName = homeName;
    this.awayScore = awayScore;
    this.homeScore = homeScore;
    this.gameTime = gameTime;
    this.updateTime = updateTime;
    this.status = status;
  }
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="id", unique=true, nullable=false)
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  @Column(name="arenaName")
  public String getArenaName()
  {
    return this.arenaName;
  }
  
  public void setArenaName(String arenaName)
  {
    this.arenaName = arenaName;
  }
  
  @Column(name="awayTeamId")
  public String getAwayTeamId()
  {
    return this.awayTeamId;
  }
  
  public void setAwayTeamId(String awayTeamId)
  {
    this.awayTeamId = awayTeamId;
  }
  
  @Column(name="awayName")
  public String getAwayName()
  {
    return this.awayName;
  }
  
  public void setAwayName(String awayName)
  {
    this.awayName = awayName;
  }
  
  @Column(name="gameId")
  public String getGameId()
  {
    return this.gameId;
  }
  
  public void setGameId(String gameId)
  {
    this.gameId = gameId;
  }
  
  @Column(name="homeTeamId")
  public String getHomeTeamId()
  {
    return this.homeTeamId;
  }
  
  public void setHomeTeamId(String homeTeamId)
  {
    this.homeTeamId = homeTeamId;
  }
  
  @Column(name="homeName")
  public String getHomeName()
  {
    return this.homeName;
  }
  
  public void setHomeName(String homeName)
  {
    this.homeName = homeName;
  }
  
  @Column(name="awayScore")
  public Integer getAwayScore()
  {
    return this.awayScore;
  }
  
  public void setAwayScore(Integer awayScore)
  {
    this.awayScore = awayScore;
  }
  
  @Column(name="homeScore")
  public Integer getHomeScore()
  {
    return this.homeScore;
  }
  
  public void setHomeScore(Integer homeScore)
  {
    this.homeScore = homeScore;
  }
  
  @Column(name="gameTime", length=19)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
  public Timestamp getGameTime()
  {
    return this.gameTime;
  }
  
  public void setGameTime(Timestamp gameTime)
  {
    this.gameTime = gameTime;
  }
  
  @Column(name="updateTime", length=19)
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  public Timestamp getUpdateTime()
  {
    return this.updateTime;
  }
  
  public void setUpdateTime(Timestamp updateTime)
  {
    this.updateTime = updateTime;
  }
  
  @Column(name="status", length=10)
  public String getStatus()
  {
    return this.status;
  }
  
  public void setStatus(String status)
  {
    this.status = status;
  }
}