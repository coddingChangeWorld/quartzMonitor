package com.service.sport.bet.json;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ruleJson
{
  private String strategy;
  private int aas_score;     //主队让分
  private Double win_odds;   //主队赢的赔率
  private Double lose_odds;  //主队输的赔率 
  private int base_score;    //预设总分数
  private Double big_odds;   //打分赔率
  private Double small_odds;  //小分赔率
  
  public String getStrategy()
  {
    return this.strategy;
  }
  
  public void setStrategy(String strategy)
  {
    this.strategy = strategy;
  }
  
  public int getAas_score()
  {
    return this.aas_score;
  }
  
  public void setAas_score(int aas_score)
  {
    this.aas_score = aas_score;
  }
  
  public Double getWin_odds()
  {
    return this.win_odds;
  }
  
  public void setWin_odds(Double win_odds)
  {
    this.win_odds = win_odds;
  }
  
  public Double getLose_odds()
  {
    return this.lose_odds;
  }
  
  public void setLose_odds(Double lose_odds)
  {
    this.lose_odds = lose_odds;
  }
  
  public int getBase_score()
  {
    return this.base_score;
  }
  
  public void setBase_score(int base_score)
  {
    this.base_score = base_score;
  }
  
  public Double getBig_odds()
  {
    return this.big_odds;
  }
  
  public void setBig_odds(Double big_odds)
  {
    this.big_odds = big_odds;
  }
  
  public Double getSmall_odds()
  {
    return this.small_odds;
  }
  
  public void setSmall_odds(Double small_odds)
  {
    this.small_odds = small_odds;
  }
}
