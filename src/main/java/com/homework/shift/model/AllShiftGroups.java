package com.homework.shift.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllShiftGroups {

  @NotNull
  private String shiftCode;
  @NotNull
  private String shiftGroupCode;

}
