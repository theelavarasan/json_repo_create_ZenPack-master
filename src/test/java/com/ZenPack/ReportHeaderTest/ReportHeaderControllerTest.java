package com.ZenPack.ReportHeaderTest;

import com.ZenPack.Dto.HeaderInfoDto;
import com.ZenPack.controller.ZenPackReportController;
import com.ZenPack.model.ReportHeader;
import com.ZenPack.service.Impl.ZenPackServiceImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPla)
public class ReportHeaderControllerTest {
    @InjectMocks
    private ZenPackReportController zenPackReportController;

    @Mock
    private ZenPackServiceImpl service;

    @Autowired
    private MockMvc mockMvc;


    @Test
    void create(){

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        ReportHeader reportHeader = new ReportHeader();
        List<HeaderInfoDto> list = new ArrayList<>();
        reportHeader.setReportId(1L);
        reportHeader.setReportName("XXXX");
        reportHeader.setHeaderInfo(list);
        when(service.createReportHeader(any(ReportHeader.class))).thenReturn(reportHeader);

        ResponseEntity<ReportHeader> responseEntity = zenPackReportController.createReportHeader(reportHeader);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
//        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/api/v1/reportHeader/create");
    }

    @Test
    void findAll() throws Exception {
        ReportHeader reportHeader1 = new ReportHeader();
        List<HeaderInfoDto> list = new ArrayList<>();
        reportHeader1.setReportId(1L);
        reportHeader1.setReportName("XXXX");
        reportHeader1.setHeaderInfo(list);

        ReportHeader reportHeader2 = new ReportHeader();
        List<HeaderInfoDto> list1 = new ArrayList<>();
        reportHeader2.setReportId(2L);
        reportHeader2.setReportName("YYYY");
        reportHeader2.setHeaderInfo(list1);

        List<ReportHeader> list2 = new ArrayList<>();
        list2.add(reportHeader1);
        list2.add(reportHeader2);

        when(service.getAllReportHeader()).thenReturn(list2);

        this.mockMvc.perform(get("/reportHeader/getAll"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(list2.size())));
    }

    @Test
    void shouldDeleteList() throws Exception {

        doNothing().when(service).deleteReportHeaderById(anyLong());

        this.mockMvc.perform(delete("/api/v1/delete/{reportId}", 1))
                .andExpect(status().isNoContent());

    }


}
