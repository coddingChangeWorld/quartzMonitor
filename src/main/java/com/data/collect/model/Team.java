package com.data.collect.model;

public class Team
{
  private String abbr;
  private String city;
  private String cityEn;
  private String code;
  private String conference;
  private String displayAbbr;
  private String displayConference;
  private String division;
  private String id;
  private String isAllStarTeam;
  private String isLeagueTeam;
  private String name;
  private String nameEn;
  
  public String getAbbr()
  {
    return this.abbr;
  }
  
  public void setAbbr(String abbr)
  {
    this.abbr = abbr;
  }
  
  public String getCity()
  {
    return this.city;
  }
  
  public void setCity(String city)
  {
    this.city = city;
  }
  
  public String getCityEn()
  {
    return this.cityEn;
  }
  
  public void setCityEn(String cityEn)
  {
    this.cityEn = cityEn;
  }
  
  public String getCode()
  {
    return this.code;
  }
  
  public void setCode(String code)
  {
    this.code = code;
  }
  
  public String getConference()
  {
    return this.conference;
  }
  
  public void setConference(String conference)
  {
    this.conference = conference;
  }
  
  public String getDisplayAbbr()
  {
    return this.displayAbbr;
  }
  
  public void setDisplayAbbr(String displayAbbr)
  {
    this.displayAbbr = displayAbbr;
  }
  
  public String getDisplayConference()
  {
    return this.displayConference;
  }
  
  public void setDisplayConference(String displayConference)
  {
    this.displayConference = displayConference;
  }
  
  public String getDivision()
  {
    return this.division;
  }
  
  public void setDivision(String division)
  {
    this.division = division;
  }
  
  public String getId()
  {
    return this.id;
  }
  
  public void setId(String id)
  {
    this.id = id;
  }
  
  public String getIsAllStarTeam()
  {
    return this.isAllStarTeam;
  }
  
  public void setIsAllStarTeam(String isAllStarTeam)
  {
    this.isAllStarTeam = isAllStarTeam;
  }
  
  public String getIsLeagueTeam()
  {
    return this.isLeagueTeam;
  }
  
  public void setIsLeagueTeam(String isLeagueTeam)
  {
    this.isLeagueTeam = isLeagueTeam;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getNameEn()
  {
    return this.nameEn;
  }
  
  public void setNameEn(String nameEn)
  {
    this.nameEn = nameEn;
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
    Team team = (Team)obj;
    if (!this.id.equals(team.getId())) {
      return false;
    }
    return true;
  }
}
