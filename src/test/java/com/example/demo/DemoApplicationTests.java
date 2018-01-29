package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.domain.Multiplication;
import com.itextpdf.text.log.SysoCounter;
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
		Multiplication m = new Multiplication(12, 21);
		m.getFactorA()
		System.out.println(m);
	}

}
