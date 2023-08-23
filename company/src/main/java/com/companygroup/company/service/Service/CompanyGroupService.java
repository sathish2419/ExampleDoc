package com.companygroup.company.service.Service;

import com.companygroup.company.Dto.CompanyGroupDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyGroupService {

    ResponseEntity<CompanyGroupDTO> createCompanyGroup(CompanyGroupDTO companyGroupDTO);

    CompanyGroupDTO updateCompanyGroup(Long id, CompanyGroupDTO companyGroupDTO);


    CompanyGroupDTO GetByUserId (Long id);

    List<CompanyGroupDTO> GetAllDetails();

    void deleteCompanyById (Long id);
}
