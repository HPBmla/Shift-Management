package com.homework.shift.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "shift_group")
@javax.persistence.Cacheable
public class ShiftGroup implements Serializable {

  @NaturalId
  private String code;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private  Integer shiftGroupId;

 /* @ManyToMany
  private Collection<Shift> shiftData;*/
 @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
 @OneToMany(mappedBy = "groupObj")
 private Set<ShiftGrouping> groupingList;
}
