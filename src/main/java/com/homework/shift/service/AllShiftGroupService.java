package com.homework.shift.service;

import com.homework.shift.model.ShiftGrouping;
import com.homework.shift.repository.ShiftGroupRepository;
import com.homework.shift.repository.ShiftGroupingRepository;
import com.homework.shift.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.persistence.QueryHint;

@Service
public class AllShiftGroupService {

  @Autowired
  ShiftGroupingRepository shiftGroupingRepository;

  @Autowired
  EntityManager em;

  @Autowired
  ShiftGroupRepository shiftGroupRepository;

  @Autowired
  ShiftRepository shiftRepository;

  @Autowired
  ShiftGrouping groupingObj;

  /**
   * Service layer function to save all shift and group
   * data to relationship table
   * @param shiftGrouping
   * @return
   * @throws Exception
   */
 @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
  public boolean saveAllShiftData(ShiftGrouping shiftGrouping) throws Exception
  {
    try
    {
      if(!shiftGrouping.equals(null))
      {
        ShiftGrouping shiftGroupingObj = shiftGroupingRepository.save(shiftGrouping);
        if(shiftGroupingObj != null)
          return true;
        else
          return false;
      }
      else
      {
        return false;
      }
    }
    catch(Exception ex)
    {
      throw ex;
    }


  }

  /**
   *Function to check the availability of a shift
   * assigned to a shift Group
   * @param shiftCode
   * @param shiftGroup
   * @return
   * @throws Exception
   */
  @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
  public boolean checkShiftFrGroup(String shiftCode, String shiftGroup) throws Exception {
    try {
      if (!shiftCode.isEmpty() && !shiftGroup.isEmpty()) {
        groupingObj = shiftGroupingRepository.findByShiftObjAndGroupObj(shiftCode, shiftGroup);

        if (groupingObj != null) {
          return true;
        }

      }
      return false;
    } catch (Exception ex) {
     throw ex;
    }
  }


}
