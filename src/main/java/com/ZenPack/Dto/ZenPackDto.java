package com.ZenPack.Dto;


import com.ZenPack.model.FeaturedList;
import lombok.Getter;
import lombok.Setter;


import java.util.*;

@Getter
@Setter
public class ZenPackDto {
    private Long zenPackId;
    private String name;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedTime;
    private List<MenuDto> menus;
    private String featureId;
    private List<FeaturedList> features;
}