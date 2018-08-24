package com.data.collect.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="app_users", catalog="quartz", uniqueConstraints={@javax.persistence.UniqueConstraint(columnNames={"UserName"})})
public class AppUsers
  implements Serializable
{
  private Integer id;
  private String userName;
  private Integer salt;
  private String password;
  private String userMail;
  private String userHomepage;
  private String passwordQuestion;
  private String passwordAnswer;
  private Boolean userSex;
  private Integer currency;
  private String userPhoto;
  private String userMobile;
  private String userLastIp;
  private Integer userRegTime;
  private Integer lastLoginTime;
  private Integer lastPostTime;
  private String userInfo;
  private String userIntro;
  private Boolean userRoleId;
  private Boolean userAccountStatus;
  private Date birthday;
  
  public AppUsers() {}
  
  public AppUsers(Integer userRegTime, Integer lastLoginTime, Boolean userRoleId, Boolean userAccountStatus)
  {
    this.userRegTime = userRegTime;
    this.lastLoginTime = lastLoginTime;
    this.userRoleId = userRoleId;
    this.userAccountStatus = userAccountStatus;
  }
  
  public AppUsers(String userName, Integer salt, String password, String userMail, String userHomepage, String passwordQuestion, String passwordAnswer, Boolean userSex, Integer currency, String userPhoto, String userMobile, String userLastIp, Integer userRegTime, Integer lastLoginTime, Integer lastPostTime, String userInfo, String userIntro, Boolean userRoleId, Boolean userAccountStatus, Date birthday)
  {
    this.userName = userName;
    this.salt = salt;
    this.password = password;
    this.userMail = userMail;
    this.userHomepage = userHomepage;
    this.passwordQuestion = passwordQuestion;
    this.passwordAnswer = passwordAnswer;
    this.userSex = userSex;
    this.currency = currency;
    this.userPhoto = userPhoto;
    this.userMobile = userMobile;
    this.userLastIp = userLastIp;
    this.userRegTime = userRegTime;
    this.lastLoginTime = lastLoginTime;
    this.lastPostTime = lastPostTime;
    this.userInfo = userInfo;
    this.userIntro = userIntro;
    this.userRoleId = userRoleId;
    this.userAccountStatus = userAccountStatus;
    this.birthday = birthday;
  }
  
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="ID", unique=true, nullable=false)
  public Integer getId()
  {
    return this.id;
  }
  
  public void setId(Integer id)
  {
    this.id = id;
  }
  
  @Column(name="UserName", unique=true, length=50)
  public String getUserName()
  {
    return this.userName;
  }
  
  public void setUserName(String userName)
  {
    this.userName = userName;
  }
  
  @Column(name="Salt")
  public Integer getSalt()
  {
    return this.salt;
  }
  
  public void setSalt(Integer salt)
  {
    this.salt = salt;
  }
  
  @Column(name="Password", length=32)
  public String getPassword()
  {
    return this.password;
  }
  
  public void setPassword(String password)
  {
    this.password = password;
  }
  
  @Column(name="UserMail")
  public String getUserMail()
  {
    return this.userMail;
  }
  
  public void setUserMail(String userMail)
  {
    this.userMail = userMail;
  }
  
  @Column(name="UserHomepage")
  public String getUserHomepage()
  {
    return this.userHomepage;
  }
  
  public void setUserHomepage(String userHomepage)
  {
    this.userHomepage = userHomepage;
  }
  
  @Column(name="PasswordQuestion")
  public String getPasswordQuestion()
  {
    return this.passwordQuestion;
  }
  
  public void setPasswordQuestion(String passwordQuestion)
  {
    this.passwordQuestion = passwordQuestion;
  }
  
  @Column(name="PasswordAnswer", length=32)
  public String getPasswordAnswer()
  {
    return this.passwordAnswer;
  }
  
  public void setPasswordAnswer(String passwordAnswer)
  {
    this.passwordAnswer = passwordAnswer;
  }
  
  @Column(name="UserSex")
  public Boolean getUserSex()
  {
    return this.userSex;
  }
  
  public void setUserSex(Boolean userSex)
  {
    this.userSex = userSex;
  }
  
  @Column(name="Currency")
  public Integer getCurrency()
  {
    return this.currency;
  }
  
  public void setCurrency(Integer currency)
  {
    this.currency = currency;
  }
  
  @Column(name="UserPhoto")
  public String getUserPhoto()
  {
    return this.userPhoto;
  }
  
  public void setUserPhoto(String userPhoto)
  {
    this.userPhoto = userPhoto;
  }
  
  @Column(name="UserMobile")
  public String getUserMobile()
  {
    return this.userMobile;
  }
  
  public void setUserMobile(String userMobile)
  {
    this.userMobile = userMobile;
  }
  
  @Column(name="UserLastIP", length=50)
  public String getUserLastIp()
  {
    return this.userLastIp;
  }
  
  public void setUserLastIp(String userLastIp)
  {
    this.userLastIp = userLastIp;
  }
  
  @Column(name="UserRegTime", nullable=false)
  public Integer getUserRegTime()
  {
    return this.userRegTime;
  }
  
  public void setUserRegTime(Integer userRegTime)
  {
    this.userRegTime = userRegTime;
  }
  
  @Column(name="LastLoginTime", nullable=false)
  public Integer getLastLoginTime()
  {
    return this.lastLoginTime;
  }
  
  public void setLastLoginTime(Integer lastLoginTime)
  {
    this.lastLoginTime = lastLoginTime;
  }
  
  @Column(name="LastPostTime")
  public Integer getLastPostTime()
  {
    return this.lastPostTime;
  }
  
  public void setLastPostTime(Integer lastPostTime)
  {
    this.lastPostTime = lastPostTime;
  }
  
  @Column(name="UserInfo")
  public String getUserInfo()
  {
    return this.userInfo;
  }
  
  public void setUserInfo(String userInfo)
  {
    this.userInfo = userInfo;
  }
  
  @Column(name="UserIntro")
  public String getUserIntro()
  {
    return this.userIntro;
  }
  
  public void setUserIntro(String userIntro)
  {
    this.userIntro = userIntro;
  }
  
  @Column(name="UserRoleID", nullable=false)
  public Boolean getUserRoleId()
  {
    return this.userRoleId;
  }
  
  public void setUserRoleId(Boolean userRoleId)
  {
    this.userRoleId = userRoleId;
  }
  
  @Column(name="UserAccountStatus", nullable=false)
  public Boolean getUserAccountStatus()
  {
    return this.userAccountStatus;
  }
  
  public void setUserAccountStatus(Boolean userAccountStatus)
  {
    this.userAccountStatus = userAccountStatus;
  }
  
  @Temporal(TemporalType.DATE)
  @Column(name="Birthday", length=10)
  public Date getBirthday()
  {
    return this.birthday;
  }
  
  public void setBirthday(Date birthday)
  {
    this.birthday = birthday;
  }
}
