package com.companygroup.company.Repository;


import com.companygroup.company.Entity.CompanyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyGroupRepository extends JpaRepository<CompanyGroup, Long> {


}
