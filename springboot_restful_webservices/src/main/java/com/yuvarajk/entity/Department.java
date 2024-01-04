package com.yuvarajk.entity;

import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
@Table
public class Department {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dept_id;
    private int fees;
//    @OneToOne(mappedBy = "department")
//    private User user;


}
