package com.data.collect.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="app_team", catalog="quartz")
public class AppTeam
  implements Serializable
{
  private String id;
  private String division;
  private String displayConference;
  private String abbr;
  private String city;
  private String cityEn;
  private String code;
  private String conference;
  private String displayAbbr;
  private String isAllStarTeam;
  private String isLeagueTeam;
  private String name;
  private String nameEn;
  private String pic;
  
  public AppTeam() {}
  
  public AppTeam(String id)
  {
    this.id = id;
  }
  
  public AppTeam(String id, String division, String displayConference, String abbr, String city, String cityEn, String code, String conference, String displayAbbr, String isAllStarTeam, String isLeagueTeam, String name, String nameEn, String pic)
  {
    this.id = id;
    this.division = division;
    this.displayConference = displayConference;
    this.abbr = abbr;
    this.city = city;
    this.cityEn = cityEn;
    this.code = code;
    this.conference = conference;
    this.displayAbbr = displayAbbr;
    this.isAllStarTeam = isAllStarTeam;
    this.isLeagueTeam = isLeagueTeam;
    this.name = name;
    this.nameEn = nameEn;
    this.pic = pic;
  }
  
  @Id
  @Column(name="id", unique=true, nullable=false, length=10)
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  @Column(name="division")
  public String getDivision()
  {
    return this.division;
  }
  
  public void setDivision(String division)
  {
    this.division = division;
  }
  
  @Column(name="displayConference")
  public String getDisplayConference()
  {
    return this.displayConference;
  }
  
  public void setDisplayConference(String displayConference)
  {
    this.displayConference = displayConference;
  }
  
  @Column(name="abbr")
  public String getAbbr()
  {
    return this.abbr;
  }
  
  public void setAbbr(String abbr)
  {
    this.abbr = abbr;
  }
  
  @Column(name="city")
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  @Column(name="cityEn")
  public String getCityEn()
  {
    return this.cityEn;
  }
  
  public void setCityEn(String cityEn)
  {
    this.cityEn = cityEn;
  }
  
  @Column(name="code")
  public String getCode()
  {
    return this.code;
  }
  
  public void setCode(String code)
  {
    this.code = code;
  }
  
  @Column(name="conference")
  public String getConference()
  {
    return this.conference;
  }
  
  public void setConference(String conference)
  {
    this.conference = conference;
  }
  
  @Column(name="displayAbbr")
  public String getDisplayAbbr()
  {
    return this.displayAbbr;
  }
  
  public void setDisplayAbbr(String displayAbbr)
  {
    this.displayAbbr = displayAbbr;
  }
  
  @Column(name="isAllStarTeam")
  public String getIsAllStarTeam()
  {
    return this.isAllStarTeam;
  }
  
  public void setIsAllStarTeam(String isAllStarTeam)
  {
    this.isAllStarTeam = isAllStarTeam;
  }
  
  @Column(name="isLeagueTeam")
  public String getIsLeagueTeam()
  {
    return this.isLeagueTeam;
  }
  
  public void setIsLeagueTeam(String isLeagueTeam)
  {
    this.isLeagueTeam = isLeagueTeam;
  }
  
  @Column(name="name")
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  @Column(name="nameEn")
  public String getNameEn()
  {
    return this.nameEn;
  }
  
  public void setNameEn(String nameEn)
  {
    this.nameEn = nameEn;
  }
  
  @Column(name="pic")
  public String getPic()
  {
    return this.pic;
  }
  
  public void setPic(String pic)
  {
    this.pic = pic;
  }
  
  public boolean equals(Object obj)
  {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    AppTeam team = (AppTeam)obj;
    if (!this.id.equals(team.getId())) {
      return false;
    }
    return true;
  }
}
