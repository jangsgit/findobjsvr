package com.actas.cmob.DTO.Hanyangs;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class HanyangsModel {private int id;
    @NonNull @Builder.Default private String name = "NULL NAME";
    @NonNull @Builder.Default private String email = "NULL EMAIL"; 
}
