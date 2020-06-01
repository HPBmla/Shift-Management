package com.homework.shift.service;

import com.homework.shift.model.Shift;
import com.homework.shift.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Service;
import javax.persistence.QueryHint;
import java.util.List;


@Service
public class ShiftService {

  @Autowired
  ShiftRepository shiftrepo;

  @Autowired
  Shift shiftObj;

  /**
   * Service level function to save shift data
   * when a group is created
   * @param shiftCode
   * @return
   * @throws Exception
   */
  @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
  public boolean saveShift(Shift shiftCode) throws Exception {
    try
    {
      shiftObj = shiftrepo.save(shiftCode);
      if (!shiftObj.equals(null)) {
        return true;
      } else {
        return false;
      }
    }
    catch (Exception ex)
    {
      throw ex;
    }
  }

  /**
   * SErvice level function which retrieve all created shift data
   * as a list of shift objects
   * @return
   * @throws Exception
   */
  @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
  public List<Shift> getAllShifts() throws Exception
  {
    try
    {
      return shiftrepo.findAll();
    }
    catch(Exception ex)
    {
      throw ex;
    }

  }

  /**
   * Function to get shift object when a shift code is passed
   * @param shiftCode
   * @return
   * @throws Exception
   */
  @QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
  public Shift findShiftObj(String shiftCode) throws Exception
  {
    try
    {
      return shiftrepo.findByCode(shiftCode);
    }
    catch(Exception ex)
    {
      throw ex;
    }

  }

}
