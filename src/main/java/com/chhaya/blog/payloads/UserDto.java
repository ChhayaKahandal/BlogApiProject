package com.chhaya.blog.payloads;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
  //we are applying all validations here ,because in controller class we taking the data into the userdto object.and in controller method we applied @Valid anotation to enable this all validations.
    @NotEmpty //it checks null value as well as empty value. 
    @Size(min=4,message="Username must be min of 4 character")//name he minimum 4 char pahije.
    private String name;
    @Email(message="Email address is not valid..!!")
    private String email;
    @NotEmpty
    @Size(min=3,max=10,message="Password must be min of 3 chars and max of 10 chars")
    //@Pattern(regexp=)
    private String password;
    @NotEmpty
    private String about;
}
