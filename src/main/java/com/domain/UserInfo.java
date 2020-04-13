package com.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity(label="userInfo_nd")
public class UserInfo extends BaseNode {
	
	@Id
	@GeneratedValue
//	@GraphId  //这是springboot2.x之前的版本使用的注解
    private Long id; //必须是Long类型

	@Property(name="name") //如果名称与数据库一致那么就不需要这个注解
    private String name;

    private Integer age;

    private String sex;

    //不知道哪个版本开始必须要构造函数
    public UserInfo() {
		super();
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
    
    
}