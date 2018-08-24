/*  1:   */ package com.common.dto;
/*  2:   */ 
/*  3:   */ import com.fasterxml.jackson.annotation.JsonInclude;
/*  4:   */ import com.fasterxml.jackson.annotation.JsonInclude.Include;
/*  5:   */ import java.io.Serializable;
/*  6:   */ 
/*  7:   */ @JsonInclude(JsonInclude.Include.NON_NULL)
/*  8:   */ public class message
/*  9:   */   implements Serializable
/* 10:   */ {
/* 11:   */   private static final long serialVersionUID = 1L;
/* 12:   */   private int code;
/* 13:   */   private String msg;
/* 14:   */   private String error;
/* 15:   */   
/* 16:   */   public message()
/* 17:   */   {
/* 18:20 */     this.code = Code.SCUESS.getIndex();
/* 19:   */   }
/* 20:   */   
/* 21:   */   public message(Code code, String msg)
/* 22:   */   {
/* 23:24 */     this.code = code.getIndex();
/* 24:25 */     this.msg = msg;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public int getCode()
/* 28:   */   {
/* 29:29 */     return this.code;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void setCode(int code)
/* 33:   */   {
/* 34:33 */     this.code = code;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public String getMsg()
/* 38:   */   {
/* 39:37 */     return this.msg;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void setMsg(String msg)
/* 43:   */   {
/* 44:41 */     this.msg = msg;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public String getError()
/* 48:   */   {
/* 49:45 */     return this.error;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public void setError(String error)
/* 53:   */   {
/* 54:49 */     this.error = error;
/* 55:   */   }
/* 56:   */ }


/* Location:           F:\apache-tomcat-7.0.67\webapps\quartzMonitor\WEB-INF\classes\
 * Qualified Name:     com.common.dto.message
 * JD-Core Version:    0.7.0.1
 */