package io.nqa.menetlus.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.nqa.menetlus.entity.Menetlus;
import io.nqa.menetlus.model.CustomResponse;
import io.nqa.menetlus.repository.MenetlusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
public class MenetlusControllerTest {
    @MockBean
    MenetlusRepository repository;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        List<Menetlus> allMenetlusList = new ArrayList<>();
        allMenetlusList.add(Menetlus.builder()
                .id(1L)
                .name("Kiisu Miisu")
                .personalCode(42907233725L)
                .email("kiisu@mii.su")
                .reason("Ajab karvu").build());
        allMenetlusList.add(Menetlus.builder()
                .id(2L)
                .name("Karl Karu")
                .personalCode(38903283729L)
                .email("karl@ka.ru")
                .reason("Kardab karu").build());
        allMenetlusList.add(Menetlus.builder()
                .id(3L)
                .name("Malle Maal")
                .personalCode(45903044268L)
                .email("malle@ma.al")
                .reason("Maakas").build());
        allMenetlusList.add(Menetlus.builder()
                .id(4L)
                .name("Kalle Palle")
                .personalCode(37512167073L)
                .email("kalle@pal.le")
                .reason("Ei oska ujuda").build());

        given(this.repository.findById(1L))
                .willReturn(Optional.of(Menetlus.builder()
                        .id(1L)
                        .name("Kiisu Miisu")
                        .personalCode(42907233725L)
                        .email("kiisu@mii.su")
                        .reason("Ajab karvu").build()));
        given(this.repository.findById(2L))
                .willReturn(Optional.empty());
        given(this.repository.findAll())
                .willReturn(allMenetlusList);
    }

    @Test
    public void getAllTest() throws Exception {
        MvcResult result = this.mvc.perform(get("/api/menetlus"))
                .andDo(print()).andExpect(status().isOk()).andReturn();
        List<Menetlus> expectedData = new ArrayList<>();
        expectedData.add(Menetlus.builder()
                .id(1L)
                .name("Kiisu Miisu")
                .personalCode(42907233725L)
                .email("kiisu@mii.su")
                .reason("Ajab karvu").build());
        expectedData.add(Menetlus.builder()
                .id(2L)
                .name("Karl Karu")
                .personalCode(38903283729L)
                .email("karl@ka.ru")
                .reason("Kardab karu").build());
        expectedData.add(Menetlus.builder()
                .id(3L)
                .name("Malle Maal")
                .personalCode(45903044268L)
                .email("malle@ma.al")
                .reason("Maakas").build());
        expectedData.add(Menetlus.builder()
                .id(4L)
                .name("Kalle Palle")
                .personalCode(37512167073L)
                .email("kalle@pal.le")
                .reason("Ei oska ujuda").build());
        CustomResponse expectedResult = new CustomResponse(true, expectedData);
        assertEquals(mapper.writeValueAsString(expectedResult), result.getResponse().getContentAsString());
    }

    @Test
    public void getByIdTest() throws Exception {
        this.mvc.perform(get("/api/menetlus/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("Kiisu Miisu"));
    }
}
