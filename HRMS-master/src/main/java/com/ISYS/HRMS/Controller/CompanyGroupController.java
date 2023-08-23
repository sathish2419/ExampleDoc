package com.ISYS.HRMS.Controller;
import com.ISYS.HRMS.Entity.CompanyGroup;
import com.ISYS.HRMS.Service.Impl.CompanyGroupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/company-group")
public class CompanyGroupController {
    private final CompanyGroupServiceImpl companyGroupService;
    @Autowired
    public CompanyGroupController(CompanyGroupServiceImpl companyGroupService) {
        this.companyGroupService = companyGroupService;
    }
    @GetMapping
    public ResponseEntity<List<CompanyGroup>> getAllCompanyGroups() {
        List<CompanyGroup> companyGroups = companyGroupService.getAllCompanyGroups();
        return new ResponseEntity<>(companyGroups, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CompanyGroup> getCompanyGroupById(@PathVariable Long id) {
        Optional<CompanyGroup> companyGroup = companyGroupService.getCompanyGroupById(id);
        return companyGroup.map(group -> new ResponseEntity<>(group, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public ResponseEntity<CompanyGroup> createCompanyGroup(@RequestBody CompanyGroup companyGroup) {
        CompanyGroup savedGroup = companyGroupService.saveCompanyGroup(companyGroup);
        return new ResponseEntity<>(savedGroup, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CompanyGroup> updateCompanyGroup(@PathVariable Long id, @RequestBody CompanyGroup updatedGroup) {
        CompanyGroup updatedCompanyGroup = companyGroupService.updateCompanyGroup(id, updatedGroup);
        if (updatedCompanyGroup != null) {
            return new ResponseEntity<>(updatedCompanyGroup, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompanyGroup(@PathVariable Long id) {
        Optional<CompanyGroup> existingGroup = companyGroupService.getCompanyGroupById(id);

        if (existingGroup.isPresent()) {
            companyGroupService.deleteCompanyGroup(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
/*
Copy and paste Swagger URL in Browser
        http://localhost:8080/swagger-ui/index.html

        or

        http://localhost:8080/swagger-ui.html

 */