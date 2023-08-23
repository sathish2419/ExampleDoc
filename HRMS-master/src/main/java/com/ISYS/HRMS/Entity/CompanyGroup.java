package com.ISYS.HRMS.Entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "company_group", schema = "isys_erp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyGroup {
    //Entity
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "no", nullable = false)
    private String no;
    @Column(name = "description")
    private String description;
    @Column(name = "status", nullable = false)
    private boolean status = true;
    @Column(name = "created_by", nullable = false)
    private String createdBy;
    @Column(name = "updated_by")
    private String updatedBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", nullable = false, updatable = false)
    private Date createdDate = new Date();
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_date")
    private Date updatedDate = new Date();
}
