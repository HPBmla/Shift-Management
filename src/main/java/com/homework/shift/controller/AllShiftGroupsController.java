package com.homework.shift.controller;

import com.homework.shift.model.AllShiftGroups;
import com.homework.shift.model.Shift;
import com.homework.shift.model.ShiftGroup;
import com.homework.shift.model.ShiftGrouping;
import com.homework.shift.service.AllShiftGroupService;
import com.homework.shift.service.ShiftGroupService;
import com.homework.shift.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@Controller
@ApiIgnore
public class AllShiftGroupsController {
  @Autowired
  AllShiftGroupService allGroupService;
  @Autowired
  ShiftService shiftService;
  @Autowired
  ShiftGroupService shiftGroupService;

  /**
   * Controller function to navigate to
   * checkShift request mapping
   * @param model
   * @return
   */
  @GetMapping("/check")
  public String checkShift(Model model) {
    model.addAttribute("result", "");
    return "checkShift";
  }

  /**
   * Controller function to redirect to
   * shift code mapping. Initilizing the model attribute
   * for the results value is also done
   * @param model
   * @return
   */
  @GetMapping("/shift")
  public String shiftAdd(Model model) {
    model.addAttribute("result", "");
    return "shiftCode";
  }

  /**
   * Controller function to redirect to shiftGroup
   * mapping. Initializing model attribute for
   * result value is done
   * @param model
   * @return
   */
  @GetMapping("/group")
  public String shiftGroup(Model model) {
    model.addAttribute("result", "");
    return "shiftGroup";
  }

  /**
   * Controller function navigating to allShiftGroup mapping
   * which assign shifts to group. Initializing model attribute
   * for result value also done
   * @param model
   * @return
   */
  @GetMapping("/shiftGroupPage")
  public String shiftGroupPage(Model model) {
    try {
      model.addAttribute("shiftList", shiftService.getAllShifts());
      model.addAttribute("groupList", shiftGroupService.getAllShiftGroups());
    } catch (Exception ex) {
      model.addAttribute("result", ex.getMessage());
    }

    return "allShiftGroup";
  }


  /**
   * Controller function to navigate to checkShift mapping
   * which check the availability of assigned shifts to
   * groups. Initializing of model attribute for result
   * value also done
   * @param model
   * @return
   */
  @GetMapping("/checkShift")
  public String checkGroupPage(Model model) {
    try {
      model.addAttribute("shiftList", shiftService.getAllShifts());
      model.addAttribute("groupList", shiftGroupService.getAllShiftGroups());
    } catch (Exception ex) {
      model.addAttribute("result", ex.getMessage());
    }

    return "checkShift";
  }

  /**
   * Controller function which check if the selected  shift
   * is already assigned to the selected group
   * @param allShiftGroups
   * @param results
   * @param model
   * @return
   */
  @PostMapping("/checkShift")
  public String doesShiftBelongToGroup(@Valid AllShiftGroups allShiftGroups, BindingResult results, Model model) {
    String resultValue = "";
    try {
      model.addAttribute("result", resultValue);
      model.addAttribute("shiftList", shiftService.getAllShifts());
      model.addAttribute("groupList", shiftGroupService.getAllShiftGroups());
      boolean flagValue = false;
      if (results.hasErrors()) {
        resultValue = "error";
        model.addAttribute("result", resultValue);
      } else {
        flagValue = allGroupService.checkShiftFrGroup(allShiftGroups.getShiftCode(), allShiftGroups.getShiftGroupCode());
        if (flagValue == true) {
          resultValue = "success";
          model.addAttribute("result", resultValue);
        } else {
          resultValue = "unavailable";
          model.addAttribute("result", resultValue);

        }
      }
    } catch (Exception ex) {
      model.addAttribute("result", ex.getMessage());
    }

    return "checkShift";
  }

  /**
   * Controller function which save created shift
   * Display the result either its successfull or not
   * @param shiftVal
   * @param result
   * @param model
   * @return
   */
  @PostMapping("/shiftCode")
  public String addShiftCode(@Valid Shift shiftVal, BindingResult result, Model model) {

    boolean addFlag = false;
    try {
      if (result.hasErrors()) {
        model.addAttribute("result", "error");
      } else {
        addFlag = shiftService.saveShift(shiftVal);

        if (addFlag == true) {
          model.addAttribute("result", "success");
        } else {
          model.addAttribute("result", "unavailable");
        }
      }
    } catch (Exception ex) {
      model.addAttribute("result", ex.getMessage());
    }

    return "shiftCode";
  }

  /**
   * Controller function which save group data
   * Display the result either its successfull or not
   * @param shiftGroupObj
   * @param result
   * @param model
   * @return
   */
  @PostMapping("/shiftGroup")
  public String addShiftGroup(@Valid ShiftGroup shiftGroupObj, BindingResult result, Model model) {
    boolean addFlag = false;
    try {
      if (result.hasErrors()) {
        model.addAttribute("result", "error");
      } else {
        addFlag = shiftGroupService.shiftGroupSave(shiftGroupObj);

        if (addFlag == true) {
          model.addAttribute("result", "success");
        } else {
          model.addAttribute("result", "unavailable");
        }
      }
    } catch (Exception ex) {
      model.addAttribute("result", ex.getMessage());
    }

    return "shiftGroup";
  }

  /**
   * Controller function which assign a shift to a group
   * Display the result either its successfull or not
   * @param groupObj
   * @param result
   * @param model
   * @return
   */
  @PostMapping("/addShiftToGroup")
  public String addShiftToGroup(@Valid AllShiftGroups groupObj, BindingResult result, Model model) {
    boolean addFlag = false;
    try {
      model.addAttribute("shiftList", shiftService.getAllShifts());
      model.addAttribute("groupList", shiftGroupService.getAllShiftGroups());
      ShiftGrouping grouping = new ShiftGrouping();
      if (result.hasErrors()) {
        model.addAttribute("result", "error");
      } else {

        Shift shiftObj = shiftService.findShiftObj(groupObj.getShiftCode());
        ShiftGroup groupObjVal = shiftGroupService.findGroupObj(groupObj.getShiftGroupCode());
        if (!shiftObj.equals(null) && !groupObjVal.equals(null)) {
          grouping.setGroupObj(groupObjVal);
          grouping.setShiftObj(shiftObj);
          addFlag = allGroupService.saveAllShiftData(grouping);
          if (addFlag == true) {
            model.addAttribute("result", "success");
          } else {
            model.addAttribute("result", "unavailable");
          }
        } else {
          model.addAttribute("result", "error");
        }
      }
    } catch (Exception ex) {
      model.addAttribute("result", ex.getMessage());
    }

    return "allShiftGroup";
  }
}
