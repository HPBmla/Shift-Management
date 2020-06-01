package com.homework.shift;

import com.homework.shift.model.Shift;
import com.homework.shift.model.ShiftGroup;
import com.homework.shift.model.ShiftGrouping;
import com.homework.shift.service.AllShiftGroupService;
import com.homework.shift.service.ShiftGroupService;
import com.homework.shift.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class commandLineQueryClass implements ApplicationRunner {

  @Autowired
  ShiftService shiftServiceObj;

  @Autowired
  ShiftGroupService groupServiceObj;

  @Autowired
  AllShiftGroupService allShiftGroupServiceObj;
  @Override
  public void run(ApplicationArguments args) throws Exception {

    for(int i=1; i <= 10; i++)
    {
      Shift shiftObj = new Shift();
      ShiftGroup groupObj = new ShiftGroup();
      ShiftGrouping groupingObj = new ShiftGrouping();
      shiftObj.setCode("code0"+i);
      groupObj.setCode("group0"+i);
      groupingObj.setShiftObj(shiftObj);
      groupingObj.setGroupObj(groupObj);
      shiftServiceObj.saveShift(shiftObj);
      groupServiceObj.shiftGroupSave(groupObj);
      allShiftGroupServiceObj.saveAllShiftData(groupingObj);

    }

  }
}
