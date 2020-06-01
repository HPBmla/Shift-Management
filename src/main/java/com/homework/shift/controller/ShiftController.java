package com.homework.shift.controller;

import com.homework.shift.model.Shift;
import com.homework.shift.model.ShiftGroup;
import com.homework.shift.model.ShiftGrouping;
import com.homework.shift.service.AllShiftGroupService;
import com.homework.shift.service.ShiftGroupService;
import com.homework.shift.service.ShiftService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shift/v1")
@Transactional
@Api(value = "Shift Management", description = "Rest APIs to manage Shifts and shift Groups")
public class ShiftController {

  @Autowired
  ShiftGroupService shiftGroupService;

  @Autowired
  ShiftService shiftService;

  @Autowired
  ShiftGrouping shiftGrouping;

  @Autowired
  AllShiftGroupService allGroupService;

  /**
   * Rest API to add a shift. Shift data will
   * be passed in the request body
   * @param shiftCode
   * @return
   */

  @PostMapping("/add")
  @ApiOperation(value = "API to Add a shift code", response = Boolean.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully added the shift"),
          @ApiResponse(code = 500, message = "An Error occurred in the server side"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
          @ApiResponse(code = 400, message = "Check the request Body passed to the server")})
  public ResponseEntity<Boolean> addShift(@RequestBody Shift shiftCode) {
    boolean shiftFlag = false;
    try {
      if (!shiftCode.equals(null)) {
        shiftFlag = shiftService.saveShift(shiftCode);
        return new ResponseEntity<>(shiftFlag, HttpStatus.ACCEPTED);

      } else {
        return new ResponseEntity<>(shiftFlag, HttpStatus.BAD_REQUEST);
      }
    } catch (Exception ex) {
      return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  /**
   * Rest API to save new Groups.
   * Group data is passed into request body
   * @param shiftGroup
   * @return
   */
  @PostMapping("/shiftGroup/add")
  @ApiOperation(value = "API to Add Shift Group code", response = Boolean.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully added the shift Group"),
          @ApiResponse(code = 500, message = "An Error occurred in the server side"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
          @ApiResponse(code = 400, message = "Check the request Body passed to the server")})
  public ResponseEntity<Boolean> addShiftGroup(@RequestBody ShiftGroup shiftGroup) {
    boolean shiftGroupFlag = false;
    try {
      if (!shiftGroup.equals(null)) {
        shiftGroupFlag = shiftGroupService.shiftGroupSave(shiftGroup);
        return new ResponseEntity<>(shiftGroupFlag, HttpStatus.ACCEPTED);
      } else {
        return new ResponseEntity<>(shiftGroupFlag, HttpStatus.BAD_REQUEST);
      }
    } catch (Exception ex) {
      return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  /**
   * Rest API which save data when a shift is assigned to a group
   * Data will be send in the request body
   * @param allGrouping
   * @return
   */
  @PostMapping("/allShiftGroup/add")
  @ApiOperation(value = "API to assign Group to shift", response = Boolean.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully assigned the group to shift"),
          @ApiResponse(code = 500, message = "An Error occurred in the server side"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
          @ApiResponse(code = 400, message = "Check the request Body passed to the server")})
  public ResponseEntity<Boolean> addAllShiftGroups(@RequestBody ShiftGrouping allGrouping) {
    try {
      if (!allGrouping.getShiftObj().getCode().equals(null) && !allGrouping.getGroupObj().getCode().equals(null)) {
        allGroupService.saveAllShiftData(allGrouping);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
      } else {
        return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
      }
    } catch (Exception ex) {
      return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }

  /**
   * Rest API which check the availability of shift
   * among the assigned groups
   * @param shiftCode
   * @param shiftGroup
   * @return
   */
  @GetMapping("/groupshift/{shiftCode}/{shiftGroup}")
  @ApiOperation(value = "API to check availability grouped shifts", response = Boolean.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Availability check of shift and group "),
          @ApiResponse(code = 500, message = "An Error occurred in the server side"),
          @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
          @ApiResponse(code = 400, message = "Check the parameters passed to the server")})
  public ResponseEntity<Boolean> doesShiftBelongToGroup(@PathVariable("shiftCode") String shiftCode, @PathVariable("shiftGroup") String shiftGroup) {
    boolean checkFlag = false;
    try {
      if (!shiftCode.isEmpty() && !shiftGroup.isEmpty()) {
        checkFlag = allGroupService.checkShiftFrGroup(shiftCode, shiftGroup);
        return new ResponseEntity<>(checkFlag, HttpStatus.ACCEPTED);
      } else {
        return new ResponseEntity<>(checkFlag, HttpStatus.BAD_REQUEST);
      }
    } catch (Exception ex) {
      return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
    }

  }
}
