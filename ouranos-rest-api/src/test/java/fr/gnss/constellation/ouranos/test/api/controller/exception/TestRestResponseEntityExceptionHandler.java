package fr.gnss.constellation.ouranos.test.api.controller.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import fr.gnss.constellation.ouranos.api.SpringBootRestApplication;

/*
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringBootRestApplication.class, properties = { "repertoire.sp3=classpath:\\Sp3File\\" })
@AutoConfigureMockMvc
public class TestRestResponseEntityExceptionHandler {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void whenMethodArgumentMismatch_thenBadRequest() throws Exception {

		MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<String, String>();
		requestParams.add("lat", "38.889139");
		requestParams.add("longi", "-77.049");
		requestParams.add("alt", "130.049");
		requestParams.add("tsStart", "1387666800");
		requestParams.add("tsEnd", "1387753199");
		requestParams.add("elevmask", "15.0");

		mockMvc.perform(get("/api/visibleSat/v01/bySatellite")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
				.params(requestParams))
				.andExpect(status().isBadRequest())
				.andDo(MockMvcResultHandlers.print());
	}

}
*/