package com.ISYS.HRMS.Service.Impl;
import com.ISYS.HRMS.Entity.CompanyGroup;
import com.ISYS.HRMS.Repository.CompanyGroupRepository;
import com.ISYS.HRMS.Service.CompanyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class CompanyGroupServiceImpl implements CompanyGroupService {
    @Autowired
    private CompanyGroupRepository companyGroupRepository;
    @Override
    public List<CompanyGroup> getAllCompanyGroups() {
        return companyGroupRepository.findAll();
    }
    @Override
    public Optional<CompanyGroup> getCompanyGroupById(Long id) {
        return companyGroupRepository.findById(id);
    }
    @Override
    public CompanyGroup saveCompanyGroup(CompanyGroup companyGroup) {
        return companyGroupRepository.save(companyGroup);
    }
    @Override
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
        return null;
    }
    @Override
    public void deleteCompanyGroup(Long id) {
        companyGroupRepository.deleteById(id);
    }
}
