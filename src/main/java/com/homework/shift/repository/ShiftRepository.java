package com.homework.shift.repository;

import com.homework.shift.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Integer>
{
  //SELECT u FROM OAuthUser as u JOIN u.roles as r WHERE r.id = ?1 AND u.isEnabled = true
//  @Query("select s from shift as s join s.shiftGroupingList as r where r.shiftCode = ?1 and s.shift = ?2")
  @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
  public Shift findByCode(String shift);
}