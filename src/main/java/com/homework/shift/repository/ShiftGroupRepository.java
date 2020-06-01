package com.homework.shift.repository;

import com.homework.shift.model.Shift;
import com.homework.shift.model.ShiftGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;

@Repository
public interface ShiftGroupRepository extends JpaRepository<ShiftGroup,Integer> {
  @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
  public ShiftGroup findByCode(String shiftcode);
}
