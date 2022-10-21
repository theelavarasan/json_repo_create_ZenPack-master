package com.ZenPack;
import com.ZenPack.Dto.FeatureDto;
import com.ZenPack.Dto.MenuDto;
import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.controller.ZenPackController;
import com.ZenPack.model.ZenPack;
import com.ZenPack.repository.ZenPackRepository;
import com.ZenPack.service.Impl.ZenPackServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*@RunWith(SpringRunner.class)*/
@WebMvcTest(ZenPackController.class)
public class ZenPackControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZenPackServiceImpl service;

    @Autowired
    private ZenPackController controller;

    @MockBean
    private ZenPackRepository repository;

    @Autowired
    private WebApplicationContext context;


    @Test
    public void testZenPackController() throws Exception {

        FeatureDto featureDto=new FeatureDto();
        featureDto.setFeatureId("1");
        featureDto.setText("Murugan");
        featureDto.setId(1);
        featureDto.setIsSettingMenu(true);
        featureDto.setIcon("#");
        featureDto.setFeatureUrl("www.google.com");

        FeatureDto featureDto1=new FeatureDto();
        featureDto1.setFeatureId("1");
        featureDto1.setText("Murugan");
        featureDto1.setId(2);
        featureDto1.setIsSettingMenu(true);
        featureDto1.setIcon("#");
        featureDto1.setFeatureUrl("www.google.com");

        MenuDto menuDto=new MenuDto();
        menuDto.setMenuName("MuruganMenu");
        menuDto.setParentMenuId(1);
        menuDto.setCreatedTime(new Date());
        menuDto.setCreatedBy("Murugan");
        menuDto.setFeatures(Arrays.asList(featureDto,featureDto1));

        ZenPackDto zenPackDto=new ZenPackDto();
        zenPackDto.setZenPackId(1L);
        zenPackDto.setName("MuruganZenPack");
        zenPackDto.setCreatedBy("Murugan");
        zenPackDto.setUpdatedBy("Ashok");
        zenPackDto.setCreatedDate(new Date());
        zenPackDto.setUpdatedTime(new Date());
        zenPackDto.setMenus(Arrays.asList(menuDto));

       /* ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        ZenPack zenPack=mapper.map(zenPackDto,ZenPack.class);*/

        String inputJson=this.mapToJson(zenPackDto);
        String URI="/api/v1";
        when(service.createZenPack(any(ZenPackDto.class))).thenReturn(ResponseEntity.ok(zenPackDto));
        RequestBuilder requestBuilder= MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response= mvcResult.getResponse();
        String outPutInJson= response.getContentAsString();
        assertThat(outPutInJson).isEqualTo(inputJson);
    }

    @Test
    public void getZenPackByIdTest() throws Exception {
        FeatureDto featureDto=new FeatureDto();
        featureDto.setFeatureId("1");
        featureDto.setText("Murugan");
        featureDto.setId(1);
        featureDto.setIsSettingMenu(true);
        featureDto.setIcon("#");
        featureDto.setFeatureUrl("www.google.com");

        FeatureDto featureDto1=new FeatureDto();
        featureDto1.setFeatureId("1");
        featureDto1.setText("Murugan");
        featureDto1.setId(1);
        featureDto1.setIsSettingMenu(true);
        featureDto1.setIcon("#");
        featureDto1.setFeatureUrl("www.google.com");

        MenuDto menuDto=new MenuDto();
        menuDto.setMenuName("MuruganMenu");
        menuDto.setParentMenuId(1);
        menuDto.setCreatedTime(new Date());
        menuDto.setCreatedBy("Murugan");
        menuDto.setFeatures(Arrays.asList(featureDto,featureDto1));

        ZenPackDto zenPackDto=new ZenPackDto();
        zenPackDto.setZenPackId(1L);
        zenPackDto.setName("MuruganZenPack");
        zenPackDto.setCreatedBy("Murugan");
        zenPackDto.setUpdatedBy("Ashok");
        zenPackDto.setCreatedDate(new Date());
        zenPackDto.setUpdatedTime(new Date());
        zenPackDto.setMenus(Arrays.asList(menuDto));

        when(service.getByZenPackId(anyLong())).thenReturn(zenPackDto);
        mockMvc.perform(MockMvcRequestBuilders.get("api/v1/getZenPackById/1"))
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test
    public void getAllZenPack() throws Exception {
        FeatureDto featureDto=new FeatureDto();
        featureDto.setFeatureId("1");
        featureDto.setText("Murugan");
        featureDto.setId(1);
        featureDto.setIsSettingMenu(true);
        featureDto.setIcon("#");
        featureDto.setFeatureUrl("www.google.com");

        FeatureDto featureDto1=new FeatureDto();
        featureDto1.setFeatureId("1");
        featureDto1.setText("Murugan");
        featureDto1.setId(1);
        featureDto1.setIsSettingMenu(true);
        featureDto1.setIcon("#");
        featureDto1.setFeatureUrl("www.google.com");

        MenuDto menuDto=new MenuDto();
        menuDto.setMenuName("MuruganMenu");
        menuDto.setParentMenuId(1);
        menuDto.setCreatedTime(new Date());
        menuDto.setCreatedBy("Murugan");
        menuDto.setFeatures(Arrays.asList(featureDto,featureDto1));

        ZenPackDto zenPackDto=new ZenPackDto();
        zenPackDto.setZenPackId(1L);
        zenPackDto.setName("MuruganZenPack");
        zenPackDto.setCreatedBy("Murugan");
        zenPackDto.setUpdatedBy("Ashok");
        zenPackDto.setCreatedDate(new Date());
        zenPackDto.setUpdatedTime(new Date());
        zenPackDto.setMenus(Arrays.asList(menuDto));
        List<ZenPackDto> zenPackDtos=new ArrayList<>();
        zenPackDtos.set(1,zenPackDto);
        when(service.getAllZenPack()).thenReturn(zenPackDtos);
        mockMvc.perform(MockMvcRequestBuilders.get("api/v1/get_all"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void shouldDeleteById() throws Exception {
        FeatureDto featureDto=new FeatureDto();
        featureDto.setFeatureId("1");
        featureDto.setText("Murugan");
        featureDto.setId(1);
        featureDto.setIsSettingMenu(true);
        featureDto.setIcon("#");
        featureDto.setFeatureUrl("www.google.com");

        FeatureDto featureDto1=new FeatureDto();
        featureDto1.setFeatureId("1");
        featureDto1.setText("Murugan");
        featureDto1.setId(1);
        featureDto1.setIsSettingMenu(true);
        featureDto1.setIcon("#");
        featureDto1.setFeatureUrl("www.google.com");

        MenuDto menuDto=new MenuDto();
        menuDto.setMenuName("MuruganMenu");
        menuDto.setParentMenuId(1);
        menuDto.setCreatedTime(new Date());
        menuDto.setCreatedBy("Murugan");
        menuDto.setFeatures(Arrays.asList(featureDto,featureDto1));

        ZenPackDto zenPackDto=new ZenPackDto();
        zenPackDto.setZenPackId(1L);
        zenPackDto.setName("MuruganZenPack");
        zenPackDto.setCreatedBy("Murugan");
        zenPackDto.setUpdatedBy("Ashok");
        zenPackDto.setCreatedDate(new Date());
        zenPackDto.setUpdatedTime(new Date());
        zenPackDto.setMenus(Arrays.asList(menuDto));
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        Optional<ZenPack> zenPackDto1=repository.findByZenPackId(zenPackDto.getZenPackId());

        when(service.deleteByzenPackId(zenPackDto.getZenPackId())).thenReturn(String.valueOf(zenPackDto1));
        mockMvc.perform(MockMvcRequestBuilders.get("api/v1/get_all"))
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test
    public void shouldUpdateWhenGivenId() throws Exception {

        FeatureDto featureDto=new FeatureDto();
        featureDto.setFeatureId("1");
        featureDto.setText("Murugan");
        featureDto.setId(1);
        featureDto.setIsSettingMenu(true);
        featureDto.setIcon("#");
        featureDto.setFeatureUrl("www.google.com");

        FeatureDto featureDto1=new FeatureDto();
        featureDto1.setFeatureId("1");
        featureDto1.setText("Murugan");
        featureDto1.setId(2);
        featureDto1.setIsSettingMenu(true);
        featureDto1.setIcon("#");
        featureDto1.setFeatureUrl("www.google.com");

        MenuDto menuDto=new MenuDto();
        menuDto.setMenuName("MuruganMenu");
        menuDto.setParentMenuId(1);
        menuDto.setCreatedTime(new Date());
        menuDto.setCreatedBy("Murugan");
        menuDto.setFeatures(Arrays.asList(featureDto,featureDto1));

        ZenPackDto zenPackDto=new ZenPackDto();
        zenPackDto.setZenPackId(1L);
        zenPackDto.setName("MuruganZenPack");
        zenPackDto.setCreatedBy("Murugan");
        zenPackDto.setUpdatedBy("Ashok");
        zenPackDto.setCreatedDate(new Date());
        zenPackDto.setUpdatedTime(new Date());
        zenPackDto.setMenus(Arrays.asList(menuDto));

        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        ZenPack zenPack=mapper.map(zenPackDto,ZenPack.class);

        String inputJson=this.mapToJson(zenPack);
        String URI="/api/v1/create";
        when(service.createZenPack(any(ZenPackDto.class))).thenReturn(ResponseEntity.ok(zenPackDto));
        RequestBuilder requestBuilder= MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response= mvcResult.getResponse();
        String outPutInJson= response.getContentAsString();
        assertThat(outPutInJson).isEqualTo(inputJson);
    }


    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
