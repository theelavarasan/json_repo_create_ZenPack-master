package com.ZenPack.service.Services;

import com.ZenPack.Dto.SpecificationDto;
import com.ZenPack.Dto.ZenPackDto;
import com.ZenPack.Specification.ZenPackSpecification;
import com.ZenPack.model.ZenPack;
import com.ZenPack.repository.ZenPackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpecificationService {
    @Autowired
    private ZenPackRepository repository;

    public ResponseEntity<Page<ZenPack>> getBySpecification(SpecificationDto specificationDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        Sort.Direction sort = Sort.Direction.DESC;
        ZenPackSpecification spec = new ZenPackSpecification(specificationDto);
        Page<ZenPack> zenPacks=repository.findAll(spec, Pageable.unpaged());
        return new ResponseEntity<>(zenPacks,HttpStatus.OK);
    }
}
