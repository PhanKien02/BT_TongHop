package com.example.BaiCK.rest.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FruitRes {

    private long id;

    private String title;

    private String description;

    private String thumbnail;
}
