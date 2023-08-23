package com.ISYS.HRMS.Service;

import com.ISYS.HRMS.Entity.CompanyGroup;
import com.ISYS.HRMS.Repository.CompanyGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class CompanyGroupService {
    @Autowired
    private CompanyGroupRepository companyGroupRepository;

    public List<CompanyGroup> getAllCompanyGroups() {
        return companyGroupRepository.findAll();
    }

    public Optional<CompanyGroup> getCompanyGroupById(Long id) {
        return companyGroupRepository.findById(id);
    }

    public CompanyGroup saveCompanyGroup(CompanyGroup companyGroup) {
        return companyGroupRepository.save(companyGroup);
    }

    public void deleteCompanyGroup(Long id) {
        companyGroupRepository.deleteById(id);
    }

    public CompanyGroup updateCompanyGroup(Long id, CompanyGroup updatedGroup) {
        Optional<CompanyGroup> existingGroupOptional = companyGroupRepository.findById(id);

        if (existingGroupOptional.isPresent()) {
            CompanyGroup existingGroup = existingGroupOptional.get();
            existingGroup.setNo(updatedGroup.getNo());
            existingGroup.setDescription(updatedGroup.getDescription());
            existingGroup.setStatus(updatedGroup.isStatus());
            existingGroup.setUpdatedBy(updatedGroup.getUpdatedBy());
            existingGroup.setUpdatedDate(new Date());

            return companyGroupRepository.save(existingGroup);
        }

        return null; // or throw an exception indicating not found
    }
}
