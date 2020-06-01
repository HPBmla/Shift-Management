package com.homework.shift.service;

import com.homework.shift.model.ShiftGroup;
import com.homework.shift.repository.ShiftGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Service;

import javax.persistence.QueryHint;
import java.util.List;

@Service
public class ShiftGroupService {

  @Autowired
  ShiftGroup shiftGroupObj;

  @Autowired
  ShiftGroupRepository groupRepo;

  /**
   * Service level function to save group data
   * when a group is created
   * @param group
   * @return
   * @throws Exception
   */
  @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
  public boolean shiftGroupSave(ShiftGroup group) throws Exception
  {
    try
    {
      if(!group.equals(null))
      {
        shiftGroupObj = groupRepo.save(group);
        return true;
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
   * Service layer function to retrieve all
   * shift groups created in the form of a List of SgiftGroups
   * @return
   * @throws Exception
   */
  @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
  public List<ShiftGroup> getAllShiftGroups() throws Exception
  {
    try
    {
      return groupRepo.findAll();
    }
    catch(Exception ex)
    {
      throw ex;
    }

  }

  /**
   * Function to get Group object when a group value
   * is passed
   * @param groupVal
   * @return
   * @throws Exception
   */
  @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
  public ShiftGroup  findGroupObj(String groupVal) throws Exception
  {
    try
    {
      return groupRepo.findByCode(groupVal);
    }
    catch(Exception ex)
    {
      throw ex;
    }

  }
}
