package com.example.BaiCK.rest.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FruitReq {

    @NotNull
    private long id;

    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private String thumbnail;
}