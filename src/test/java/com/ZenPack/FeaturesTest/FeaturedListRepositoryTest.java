package com.ZenPack.FeaturesTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.ZenPack.model.FeaturedList;
import com.ZenPack.repository.FeaturedListRepository;

//@DataJpaTest
@AutoConfigureMockMvc
@SpringBootTest
public class FeaturedListRepositoryTest {
    @Autowired
    private FeaturedListRepository featuredListRepository;

    @Autowired
    private MockMvc mockMvc;

    private FeaturedList list;
    private FeaturedList list1;


    @Test
    @DisplayName("It Should save the list to database")
    void save(){
        FeaturedList list=new FeaturedList();
        list.setId(1);
        list.setFeatureId("#");
        list.setText("Project Management");
        list.setFeatureUrl(null);
        list.setIcon("#");
        list.setIsSettingMenu(true);
        FeaturedList newList = featuredListRepository.save(list);
        assertNotNull(newList);
        assertThat(newList.getId()).isNotEqualTo(null);
    }

    @Test
    @DisplayName("It should return List from database")
    void getAllList(){
        List<FeaturedList> featuredLists = featuredListRepository.findAll();

        assertNotNull(featuredLists);
        assertThat(featuredLists).isNotNull();
        assertNotEquals(0,featuredLists.size());
    }
//
    //@Test
    @DisplayName("It should update the list name with PROJECT")
    void updateList() {
        FeaturedList list=new FeaturedList();
        list.setId(15);
        list.setFeatureId("#");
        list.setText("Project Management");
        list.setFeatureUrl(null);
        list.setIcon("#");
        list.setIsSettingMenu(true);

        featuredListRepository.save(list);

        FeaturedList existingMovie = featuredListRepository.findById(list.getId()).get();
        existingMovie.setText("Project Management");
        FeaturedList updatedList = featuredListRepository.save(existingMovie);

        assertEquals("Project Management", updatedList.getText());
    }
//
    @Test
    @DisplayName("It should delete the existing list")
    void deleteList() {

        FeaturedList list=new FeaturedList();
        list.setId(17);
        list.setFeatureId("#");
        list.setText("Project Management");
        list.setFeatureUrl(null);
        list.setIcon("#");
        list.setIsSettingMenu(true);

        featuredListRepository.save(list);
        Integer id = list.getId();

        //featuredListRepository.save(list1);

        featuredListRepository.delete(list);

        List<FeaturedList> list1 = featuredListRepository.findAll();

        Optional<FeaturedList> exitingMovie = featuredListRepository.findById(id);

//        assertEquals(5, list1.size());
        assertThat(exitingMovie).isEmpty();

    }
}

