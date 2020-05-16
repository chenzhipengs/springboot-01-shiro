package com.example.pojo;

import lombok.*;

/**
 * Created by chenzhipeng on 2020/5/15 10:01
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private int id;
    private String name;
    private String pwd;
    private String prams;

}
