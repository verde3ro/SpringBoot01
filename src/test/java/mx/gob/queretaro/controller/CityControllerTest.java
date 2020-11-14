package mx.gob.queretaro.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = IndexController.class)
public class CityControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void index_basico() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/index"))
		.andExpect(status().isOk())
		.andExpect(content().contentType("text/html;charset=UTF-8"))
		.andExpect(view().name("index"));
	}

}
