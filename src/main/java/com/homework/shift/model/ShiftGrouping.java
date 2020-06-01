package com.homework.shift.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity(name = "shift_grouping")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Cacheable
public class ShiftGrouping {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
 /* private String shift_group_code;
  private String shift_code;*/

  @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
  @ManyToOne
          @JoinColumn(name = "shift_code",
                      referencedColumnName = "code")
  Shift shiftObj;
  @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
  @ManyToOne
//          @MapsId("code")
          @JoinColumn(name = "shift_group_code",
                  referencedColumnName = "code")
  ShiftGroup groupObj;


}
