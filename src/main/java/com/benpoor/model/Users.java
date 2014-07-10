package com.benpoor.model;


import java.io.Serializable;

public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String password;
    private Integer status;
    private Integer permission;
    private String email;
    private String mobile;
    private Integer sex;
    private String nickName;
    private String imgUrl;
    private String remark;
    private String city;
    private String industry;
    private String profession;
    private java.util.Date  createdAt;
    private java.util.Date  updatedAt;

    public Long getId(){
		return id;
		
    }
    public void setId(Long id){
		this.id=id;
    }
    public String getName(){
		if(name==null){
			return "";
		}else{
			return name;
		}	
		
    }
    public void setName(String name){
		this.name=name;
    }
    public String getPassword(){
		if(password==null){
			return "";
		}else{
			return password;
		}	
		
    }
    public void setPassword(String password){
		this.password=password;
    }
    public Integer getStatus(){
		if(status==null){
			return 0;
		}else{
			return status;
		}	
		
    }
    public void setStatus(Integer status){
		this.status=status;
    }
    public Integer getPermission(){
		if(permission==null){
			return 0;
		}else{
			return permission;
		}	
		
    }
    public void setPermission(Integer permission){
		this.permission=permission;
    }
    public String getEmail(){
		if(email==null){
			return "";
		}else{
			return email;
		}	
		
    }
    public void setEmail(String email){
		this.email=email;
    }
    public String getMobile(){
		if(mobile==null){
			return "";
		}else{
			return mobile;
		}	
		
    }
    public void setMobile(String mobile){
		this.mobile=mobile;
    }
    public Integer getSex(){
		if(sex==null){
			return 0;
		}else{
			return sex;
		}	
		
    }
    public void setSex(Integer sex){
		this.sex=sex;
    }
    public String getNickName(){
		if(nickName==null){
			return "";
		}else{
			return nickName;
		}	
		
    }
    public void setNickName(String nickName){
		this.nickName=nickName;
    }
    public String getImgUrl(){
		if(imgUrl==null){
			return "";
		}else{
			return imgUrl;
		}	
		
    }
    public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
    }
    public String getRemark(){
		if(remark==null){
			return "";
		}else{
			return remark;
		}	
		
    }
    public void setRemark(String remark){
		this.remark=remark;
    }
    public String getCity(){
		if(city==null){
			return "";
		}else{
			return city;
		}	
		
    }
    public void setCity(String city){
		this.city=city;
    }
    public String getIndustry(){
		if(industry==null){
			return "";
		}else{
			return industry;
		}	
		
    }
    public void setIndustry(String industry){
		this.industry=industry;
    }
    public String getProfession(){
		if(profession==null){
			return "";
		}else{
			return profession;
		}	
		
    }
    public void setProfession(String profession){
		this.profession=profession;
    }
    public java.util.Date  getCreatedAt(){
		return createdAt;
		
    }
    public void setCreatedAt(java.util.Date  createdAt){
		this.createdAt=createdAt;
    }
    public java.util.Date  getUpdatedAt(){
		return updatedAt;
		
    }
    public void setUpdatedAt(java.util.Date  updatedAt){
		this.updatedAt=updatedAt;
    }
}
