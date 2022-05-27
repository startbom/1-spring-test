package com.bomstart.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

// RunWith가 ExtendWith로 변경되었음
// SpringBootTest로 RuntWith, ExtendWith를 생략할 수 있음
@SpringBootTest
// WebMvcTest 대체
@AutoConfigureMockMvc
class TestApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void hello_string_return() throws Exception {
		String hello = "hello";

		mvc.perform(get("/hello"))
				.andExpect(status().isOk())
				.andExpect(content().string(hello));
	}

	@Test
	public void hello_object_return() throws Exception {
		String name = "hello";
		int amount = 1000;

		mvc.perform(
			get("/hello/dto")
				.param("name", name)
				.param("amount", String.valueOf(amount)))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.name", org.hamcrest.Matchers.is(name)))
					.andExpect(jsonPath("$.amount", org.hamcrest.Matchers.is(amount)));
	}
}
