package com.chhaya.blog.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="categories")
@NoArgsConstructor
@Getter
@Setter
public class Category
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer categoryId;
	
	@Column(name="title" ,length=100,nullable=false)
	private String categoryTitle;
	
	@Column(name="description")
	private String categorydescription;
	
	//here making the one to many relationship between category and post(one category has multiple post)
	@OneToMany(mappedBy = "category",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	//mappedBy = "category"  ->In post table on which column we have to mapped."category" is column in post tabel.
	//fetch=FetchType.LAZY  means parent delet kiya to child delet nhi hoga.
	//cascade=CascadeType.ALL means when we delet parent then automaticall child will delet.and when we add parent then child will automatially get saved.
	private List<Post> posts=new ArrayList<>();
	

}
