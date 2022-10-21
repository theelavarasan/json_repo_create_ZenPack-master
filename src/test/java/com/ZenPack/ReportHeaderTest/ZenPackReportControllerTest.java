package com.ZenPack.ReportHeaderTest;

import com.ZenPack.Dto.HeaderInfoDto;
import com.ZenPack.ZenPackProjectApplication;
import com.ZenPack.model.ReportHeader;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ZenPackProjectApplication.class)
@WebAppConfiguration
public abstract class ZenPackReportControllerTest {
//    @MockBean
//    private ReportHeaderRepository repository;
//
//    @MockBean
//    private ZenPackServiceImpl zenPackService;
//
//    @InjectMocks
//    private ZenPackReportController reportController;
//
//    @BeforeEach
//    void init (){
//
//    }
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

    @Test
    void create() throws Exception {
        String uri ="/api/v1/reportHeader/create";
        ReportHeader reportHeader = new ReportHeader();
        List<HeaderInfoDto> list = new ArrayList<>();
        reportHeader.setReportId(1L);
        reportHeader.setReportName("XXXX");
        reportHeader.setHeaderInfo(list);

        String inputJson = this.mapToJson(reportHeader);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Report Header is created successfully");

    }


}
