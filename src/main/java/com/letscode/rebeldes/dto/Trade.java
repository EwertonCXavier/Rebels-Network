package com.letscode.rebeldes.dto;

import com.letscode.rebeldes.model.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Trade {
  Item give;
  Item receive;
}
