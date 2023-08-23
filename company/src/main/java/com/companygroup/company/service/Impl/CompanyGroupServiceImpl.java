package com.companygroup.company.service.Impl;

import com.companygroup.company.Dto.CompanyGroupDTO;
import com.companygroup.company.Entity.CompanyGroup;
import com.companygroup.company.Repository.CompanyGroupRepository;
import com.companygroup.company.mapper.CompanyGroupMapper;
import com.companygroup.company.service.Service.CompanyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyGroupServiceImpl implements CompanyGroupService {

    @Autowired
    private CompanyGroupMapper companyGroupMapper;

    @Autowired
    private CompanyGroupRepository companyGroupRepository;


    @Override
    public ResponseEntity<CompanyGroupDTO> createCompanyGroup(CompanyGroupDTO companyGroupDTO) {
        CompanyGroup companyGroup= companyGroupMapper.toEntity(companyGroupDTO);
        CompanyGroup saveCompanyGroup =companyGroupRepository.save(companyGroup);
        CompanyGroupDTO saveCompanyGroupDto = companyGroupMapper.toModel(saveCompanyGroup);
        return new ResponseEntity<>(saveCompanyGroupDto, HttpStatus.CREATED);



    }

    @Override
    public CompanyGroupDTO updateCompanyGroup(Long id, CompanyGroupDTO companyGroupDTO) {
        CompanyGroup existingCompanyGroup = companyGroupRepository.findById(id).orElse(null);
        if (existingCompanyGroup != null) {
            CompanyGroup updatedCompanyGroup = companyGroupMapper.toEntity(companyGroupDTO);
            updatedCompanyGroup.setId(existingCompanyGroup.getId()); // Set the ID to the existing ID
            updatedCompanyGroup.setCreatedTime(existingCompanyGroup.getCreatedTime()); // Retain the original created time
            // Set any other fields you want to update, and then save the entity
            CompanyGroup saveCompanyGroup = companyGroupRepository.save(updatedCompanyGroup);
            return companyGroupMapper.toModel(saveCompanyGroup);
        } else {
            return null;
        }
    }

    @Override
    public CompanyGroupDTO GetByUserId(Long id) {
        CompanyGroup companyGroup = companyGroupRepository.findById(id).orElse(null);
        if (companyGroup != null) {
            return companyGroupMapper.toModel(companyGroup);
        } else {
            return null;
        }
    }

    @Override
    public List<CompanyGroupDTO> GetAllDetails() {
        List<CompanyGroup> companyGroups = companyGroupRepository.findAll();
        return companyGroupMapper.toModel(companyGroups);
    }

    @Override
    public void deleteCompanyById(Long id) {
        companyGroupRepository.deleteById(id);
    }


}
