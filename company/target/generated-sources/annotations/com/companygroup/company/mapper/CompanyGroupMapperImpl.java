package com.companygroup.company.mapper;

import com.companygroup.company.Dto.CompanyGroupDTO;
import com.companygroup.company.Entity.CompanyGroup;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-22T13:50:57+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class CompanyGroupMapperImpl implements CompanyGroupMapper {

    @Override
    public CompanyGroupDTO toModel(CompanyGroup entity) {
        if ( entity == null ) {
            return null;
        }

        CompanyGroupDTO companyGroupDTO = new CompanyGroupDTO();

        companyGroupDTO.setNo( entity.getNo() );
        companyGroupDTO.setDescription( entity.getDescription() );
        companyGroupDTO.setCreatedBy( entity.getCreatedBy() );
        companyGroupDTO.setUpdatedBy( entity.getUpdatedBy() );

        return companyGroupDTO;
    }

    @Override
    public CompanyGroup toEntity(CompanyGroupDTO model) {
        if ( model == null ) {
            return null;
        }

        CompanyGroup companyGroup = new CompanyGroup();

        companyGroup.setNo( model.getNo() );
        companyGroup.setDescription( model.getDescription() );
        companyGroup.setCreatedBy( model.getCreatedBy() );
        companyGroup.setUpdatedBy( model.getUpdatedBy() );

        return companyGroup;
    }

    @Override
    public List<CompanyGroupDTO> toModel(List<CompanyGroup> modelList) {
        if ( modelList == null ) {
            return null;
        }

        List<CompanyGroupDTO> list = new ArrayList<CompanyGroupDTO>( modelList.size() );
        for ( CompanyGroup companyGroup : modelList ) {
            list.add( toModel( companyGroup ) );
        }

        return list;
    }

    @Override
    public List<CompanyGroup> toEntity(List<CompanyGroupDTO> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CompanyGroup> list = new ArrayList<CompanyGroup>( entityList.size() );
        for ( CompanyGroupDTO companyGroupDTO : entityList ) {
            list.add( toEntity( companyGroupDTO ) );
        }

        return list;
    }
}
