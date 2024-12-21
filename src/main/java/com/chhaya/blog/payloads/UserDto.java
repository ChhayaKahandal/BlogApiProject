package com.chhaya.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//Dto:Data transfer object
/*Why did we create Dto class?
 Ans:I dont want to expose the entity(User) class directly in Services or controllers thats why we create Dto
     Suppose we have some important info in Entity class and we dont want to expose that information so we use Dto.
 */

//this class is used to transfer the user data.means we are not sending the user data direct;y from service class.we send user data to serviceclass using this class.
//here we use lombok for creating constructor,getter,setter
@NoArgsConstructor
@Getter
@Setter
public class UserDto 
{
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}
