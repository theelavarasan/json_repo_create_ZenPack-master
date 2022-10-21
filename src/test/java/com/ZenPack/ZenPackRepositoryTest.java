package com.ZenPack;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.model.ZenPack;
import com.ZenPack.repository.ZenPackRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class ZenPackRepositoryTest {
    @Autowired
    private ZenPackRepository zenPackRepository;

    @Autowired
    private MockMvc mockMvc;

    private ZenPackDto zenPackDto;
    private ZenPack zenPack;


    @Test
    @DisplayName("It Should save the list to database")
    void save(){
        ZenPackDto zenPackDto = new ZenPackDto();
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        ZenPack zenPack=mapper.map(zenPackDto,ZenPack.class);
        zenPack.setZenPackId(1L);
        zenPack.setName("");
        zenPack.setCreatedDate(new Date());
        zenPack.setCreatedBy("");
        zenPack.setUpdatedTime(new Date());
        zenPack.setUpdatedBy("");
        ZenPack newList = zenPackRepository.save(zenPack);
        assertNotNull(newList);
        assertThat(newList.getZenPackId()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("It should return List from database")
    void getAllList(){
        List<ZenPack> list = zenPackRepository.findAll();

        assertNotNull(list);
        assertThat(list).isNotNull();
        assertNotEquals(0,list.size());
    }
    //
    //@Test
    @DisplayName("It should update the list name with ZenPackNew")
    void updateList() {
        ZenPackDto zenPackDto = new ZenPackDto();
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        ZenPack zenPack=mapper.map(zenPackDto,ZenPack.class);
        zenPack.setZenPackId(1L);
        zenPack.setName("ZenPack");
        zenPack.setCreatedDate(new Date());
        zenPack.setCreatedBy("XXX");
        zenPack.setUpdatedTime(new Date());
        zenPack.setUpdatedBy("YYY");

        zenPackRepository.save(zenPack);

        ZenPack existingZenPack = zenPackRepository.findByZenPackId(zenPack.getZenPackId()).get();
        existingZenPack.setName("ZenPackNew");
        ZenPack updatedList = zenPackRepository.save(existingZenPack);

        assertEquals("ZenPack", updatedList.getName());
    }
    //
    @Test
    @DisplayName("It should delete the existing zenpack")
    void deleteList() {

        ZenPackDto zenPackDto = new ZenPackDto();
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setAmbiguityIgnored(true);
        ZenPack zenPack=mapper.map(zenPackDto,ZenPack.class);
        zenPack.setZenPackId(1L);
        zenPack.setName("ZenPack");
        zenPack.setCreatedDate(new Date());
        zenPack.setCreatedBy("XXX");
        zenPack.setUpdatedTime(new Date());
        zenPack.setUpdatedBy("YYY");

        zenPackRepository.save(zenPack);
        Long id = zenPack.getZenPackId();

        zenPackRepository.delete(zenPack);

        List<ZenPack> list1 = zenPackRepository.findAll();

        Optional<ZenPack> existingZenPack = zenPackRepository.findByZenPackId(id);

        assertThat(existingZenPack).isEmpty();

    }
}