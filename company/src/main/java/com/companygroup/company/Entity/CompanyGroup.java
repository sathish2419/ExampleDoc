package com.companygroup.company.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "company_group", schema = "isys_erp")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Setter
@Getter
public class CompanyGroup {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "no")
    private String no;


    @Column(name = "description")
    private String description;

    @Column(name = "createdBy")
    private String createdBy;

    @Column(name = "updatedBy")
    private String updatedBy ;

    @Column(name = "status")
    private boolean status = true;

    @Column(name = "created_date")
    private Date createdTime = new Date();

    @Column(name = "updated_date")
    private Date updatedTime = new Date();

}
