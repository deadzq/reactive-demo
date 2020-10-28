package com.example.reactivedemo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: zhangQi
 * @Date: 2020-10-28 17:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private long id;
    private Date when;
}
