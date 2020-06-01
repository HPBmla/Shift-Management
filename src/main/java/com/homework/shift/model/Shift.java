package com.homework.shift.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "shift")
@javax.persistence.Cacheable
public class Shift implements Serializable {

  @NaturalId
  private String code;


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer shiftId;

 /* @ManyToMany
  @JoinTable(name = "shift_grouping", joinColumns = @JoinColumn(name="shift"), inverseJoinColumns = @JoinColumn(name = "shiftCode"))
  private List<ShiftGroup> shiftGroupingList;*/
 @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
 @OneToMany(mappedBy = "shiftObj")
  private Set<ShiftGrouping> groupingList;


}
