package com.ZenPack.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ZenPack.Dto.FeatureDto;
import com.ZenPack.Dto.ZenPackDto;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ZenPack.Dto.MenuDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "zen_pack")
@JsonInclude(JsonInclude.Include.NON_NULL)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class) 
public class ZenPack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zen_pack_id")
    private Long zenPackId;

    @Column(name = "zen_pack_name")
    private String name;

    @Type(type = "jsonb") // See (2)
    @Column(name = "json_data", columnDefinition = "jsonb")
    private List<MenuDto> menus;

    @Column(name = "feature_ids")
    private String featureIds;

    @Column(name = "created_date")
    private Date updatedTime= new Date();

    @Column(name = "updated_time")
    private Date createdDate =new Date();

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;


}
