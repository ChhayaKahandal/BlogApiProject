package com.chhaya.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.chhaya.blog.repositories.UserRepo;

@SpringBootTest
class BIogAppApiApplicationTests
{
	
	//1:test case for find the UserRep interface's class and package name.
	@Autowired
	private UserRepo userrepo;
	@Test
	public void repoTesr()
	{
		String classname=this.userrepo.getClass().getName();
		String packageName=this.userrepo.getClass().getPackageName();
		System.out.println("this is implementation class of UserReo interface: "+classname);
		System.out.println("this is the package name of thar implementation class of UserReo interface: "+classname);
	}

}
