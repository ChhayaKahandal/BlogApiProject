package com.chhaya.blog.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="post")
public class Post 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer postId;
	
	@Column(name="post_title",length=100,nullable=false)
	private String title ;
	
	@Column(length=1000)
	private String content;
	
	private String imageName;
	
	private Date addedDate;
	
	//now we have to make relationship between tables
	//one category have multiple post(means many post have one category)
	@ManyToOne
	@JoinColumn(name="category_id")//here we changing this column name
	private Category category;
	//one user have multiple post(means many post have one user)
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	

}
