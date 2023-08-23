package com.companygroup.company.mapper;

import com.companygroup.company.Dto.CompanyGroupDTO;
import com.companygroup.company.Entity.CompanyGroup;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CompanyGroupMapper  extends BaseMapper<CompanyGroup , CompanyGroupDTO>{

}
