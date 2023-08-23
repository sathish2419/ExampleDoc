package com.companygroup.company.Controller;

import com.companygroup.company.Dto.CompanyGroupDTO;
import com.companygroup.company.Entity.CompanyGroup;
import com.companygroup.company.service.Service.CompanyGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/company-group")
public class CompanyGroupController {


    @Autowired
    private CompanyGroupService companyGroupService;

    @PostMapping("/create")
    public ResponseEntity<CompanyGroupDTO>createCompanyGroup(@RequestBody CompanyGroupDTO companyGroupDTO){
       return companyGroupService.createCompanyGroup(companyGroupDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCompanyGroup(@PathVariable Long id, @RequestBody CompanyGroupDTO companyGroupDTO) {
        CompanyGroupDTO updatedCompanyGroupDTO = companyGroupService.updateCompanyGroup(id, companyGroupDTO);
        if (updatedCompanyGroupDTO != null) {
            return  ResponseEntity.ok("Updated successfully");
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public  ResponseEntity<CompanyGroupDTO> GetByUserId (@PathVariable Long id){
        CompanyGroupDTO companyGroupDTO = companyGroupService.GetByUserId(id);
        return new ResponseEntity<>(companyGroupDTO, HttpStatus.OK);
    }

    @GetMapping("/All")
    public ResponseEntity<List<CompanyGroupDTO>>GetAllDetails(){
        List<CompanyGroupDTO> companyGroupDTOS =companyGroupService.GetAllDetails();
        return new ResponseEntity<>(companyGroupDTOS,HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById (@PathVariable Long id){
        companyGroupService.deleteCompanyById(id);
        return ResponseEntity.ok("Deleted successfully");
}

}
