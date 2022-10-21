package com.ZenPack.ReportHeaderTest;

import com.ZenPack.repository.ReportHeaderRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ZenPackRepoTest {
    @Autowired
    private ReportHeaderRepository repository;


//    @Test
//    public void create(){
//        ReportHeader reportHeader = new ReportHeader();
//        reportHeader.setReportId(1L);
//        reportHeader.setReportName("ZenPack");
//
//        HeaderInfoDto headerInfoDto = new HeaderInfoDto();
//        headerInfoDto.setActualName("name");
//        headerInfoDto.setHide(false);
//        headerInfoDto.setDisplayName("ZenPack Name");
//        headerInfoDto.setDataType("String");
//
//        List<HeaderInfoDto> headerInfoDtoList = new ArrayList<HeaderInfoDto>();
//        headerInfoDtoList.add(headerInfoDto);
//        reportHeader.setHeaderInfo(headerInfoDtoList);
//
//        repository.save(reportHeader);
//
//        assertEquals(reportHeader,reportHeader);
//        assertThat(reportHeader).isNotNull();
//
//        System.out.println(reportHeader.toString());
//
//    }
}
