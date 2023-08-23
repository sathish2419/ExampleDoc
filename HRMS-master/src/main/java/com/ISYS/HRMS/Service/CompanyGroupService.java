package com.ISYS.HRMS.Service;
import com.ISYS.HRMS.Entity.CompanyGroup;
import java.util.List;
import java.util.Optional;
public interface CompanyGroupService {
    List<CompanyGroup> getAllCompanyGroups();
    Optional<CompanyGroup> getCompanyGroupById(Long id);
    CompanyGroup saveCompanyGroup(CompanyGroup companyGroup);
    CompanyGroup updateCompanyGroup(Long id, CompanyGroup updatedGroup);
    void deleteCompanyGroup(Long id);
}
