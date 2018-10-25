package com.bbpl.server1.model;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CalcInput implements Serializable {

    @NotNull
    private Operation operation;
    @NotNull
    private List<Integer> data;
    private Integer result;
}
