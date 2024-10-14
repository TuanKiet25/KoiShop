package com.example.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Student API", version = "1.0", description = "Information"))
@SecurityScheme(name = "api", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
//restAPI
/*
* GET=> lay du lieu
* POST=> tao 1 du lieu
* PUT=> update du lieu
* DELETe=> xoa 1 du lieu nao do
* */
	/*
	* quan ly sinh vien
	* 1. them 1 tk sinh vien moi
	*  => POST: /api/student
	* 2. update 1 tk sinh vien
	*  => PUT: /api/student/:id
	* 3. Lay danh sach sinh vien
	*  => GET: /api/student
	* 4. Lay thong tin cua 1 sinh vien nao do
	*  => GET: /api/student/:id
	* 5. xoa thong tin cua 1 tk sinh vien
	*  => DELETE: /api/student/:id
	*
	* */
}
