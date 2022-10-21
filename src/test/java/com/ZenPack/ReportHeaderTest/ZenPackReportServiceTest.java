package com.ZenPack.ReportHeaderTest;

import com.ZenPack.Dto.HeaderInfoDto;
import com.ZenPack.model.ReportHeader;
import com.ZenPack.repository.ReportHeaderRepository;
import com.ZenPack.service.Impl.ZenPackServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ZenPackReportServiceTest {
    @Autowired
    private ZenPackServiceImpl zenPackService;

    @Mock
    private ReportHeaderRepository repository;

    private ReportHeader reportHeader;

    private HeaderInfoDto headerInfoDto;

    @BeforeEach
    public void setUp(){
        reportHeader = ReportHeader.builder()
                .reportId(1L)
                .reportName("ZENpack")
                .build();
        headerInfoDto = HeaderInfoDto.builder()
                .actualName("Zenpack_Name")
                .hide(false)
                .displayName("ZenPack Name")
                .dataType("String")
                .build();
    }

    @Test
    @DisplayName("Junit for save Report")
    public void saveReport(){
        given(repository.findById(reportHeader.getReportId()))
                .willReturn(Optional.empty());
        given(repository.save(reportHeader)).willReturn(reportHeader);

        System.out.println(repository);
        System.out.println(zenPackService);

        ReportHeader list =zenPackService.createReportHeader(reportHeader);

        System.out.println(list);
        Assertions.assertThat(list).isNotNull();
    }

    @Test
    public void getReports(){
        ReportHeader reportHeader1 = new ReportHeader();
        reportHeader1 = ReportHeader.builder()
                .reportId(2L)
                .reportName("ZENpack")
                .build();
        HeaderInfoDto headerInfoDto1 = new HeaderInfoDto();
        headerInfoDto1 = HeaderInfoDto.builder()
                .actualName("Zenpack_Name")
                .hide(false)
                .displayName("ZenPack Name")
                .dataType("String")
                .build();
        given(repository.findAll()).willReturn(Arrays.asList(reportHeader1,reportHeader));

        List<ReportHeader> list= zenPackService.getAllReportHeader();

        Assertions.assertThat(list).isNotNull();
        Assertions.assertThat(list.size()).isEqualTo(2);
    }

}
