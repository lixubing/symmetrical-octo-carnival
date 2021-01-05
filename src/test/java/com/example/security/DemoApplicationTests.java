package com.example.security;

import com.example.security.service.BookDo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		System.out.println("1");
	}
	@Test
	public void testDo(){
		BookDo bookDo = new BookDo();
		bookDo.setId("1");
		bookDo.getOne();
	}

}
