package com.homework.shift.repository;

import com.homework.shift.model.Shift;
import com.homework.shift.model.ShiftGroup;
import com.homework.shift.model.ShiftGrouping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;


public interface ShiftGroupingRepository extends JpaRepository<ShiftGrouping, Integer> {

@QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
  @Query("select s from shift_grouping as s where s.shiftObj.code = ?1 and s.groupObj.code = ?2")
  public ShiftGrouping findByShiftObjAndGroupObj(String shiftObj, String groupObj);


}
