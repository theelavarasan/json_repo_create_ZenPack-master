package com.ZenPack;

import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.model.ZenPack;
import com.ZenPack.repository.ZenPackRepository;
import com.ZenPack.service.Impl.ZenPackServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ZenPackServiceTest {

    @Mock
    private ZenPackRepository repository;

    @InjectMocks
    private ZenPackServiceImpl service;

    @Autowired
    private ModelMapper modelMapper;

    private ZenPackDto zenPackDto;

    @BeforeEach
    public void setUp(){
        ZenPackDto zenPackDto = new ZenPackDto();
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        ZenPack zenPack=mapper.map(zenPackDto,ZenPack.class);
        zenPack= ZenPack.builder()
                .zenPackId(1L)
                .name("ZenPack")
                .createdDate(new Date())
                .createdBy("XXXX")
                .updatedBy("YYYY")
                .updatedTime(new Date())
                .build();
    }


    @Test
    @DisplayName("Junit Test for Save ZenPack")
    void saveZenPack() {
        ZenPackDto zenPackDto = new ZenPackDto();
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        ZenPack zenPack = mapper.map(zenPackDto, ZenPack.class);
        zenPack = ZenPack.builder()
                .zenPackId(1L)
                .name("ZenPack")
                .createdDate(new Date())
                .createdBy("XXXX")
                .updatedBy("YYYY")
                .updatedTime(new Date())
                .build();
        given(repository.findByZenPackId(zenPack.getZenPackId()))
                .willReturn(Optional.empty());

        given(repository.save(zenPack)).willReturn(ZenPack.builder().build());

        System.out.println(repository);
        System.out.println(service);

        ZenPack ZenPack = null;
        ZenPack savedList = service.saveZenPack(ZenPack).getBody();

        System.out.println(savedList);
        assertThat(savedList).isNotNull();
    }

    @DisplayName("JUnit test for getAllZenPack method")
    @Test
    public void givenZenPack_whenZenPack_thenReturnZenPack() throws JsonProcessingException {

        ZenPack zenPack = ZenPack.builder()
                .zenPackId(1L)
                .name("ZenPack")
                .createdDate(new Date())
                .createdBy("XXXX")
                .updatedBy("YYYY")
                .updatedTime(new Date())
                .build();

        ZenPack zenPack1 = ZenPack.builder()
                .zenPackId(2L)
                .name("ZenPack")
                .createdDate(new Date())
                .createdBy("XXXX")
                .updatedBy("YYYY")
                .updatedTime(new Date())
                .build();

        given(repository.findAll()).willReturn(Arrays.asList(zenPack,zenPack1));

        List<ZenPackDto> list = service.getAllZenPack();

        assertThat(list).isNotNull();
    }

}