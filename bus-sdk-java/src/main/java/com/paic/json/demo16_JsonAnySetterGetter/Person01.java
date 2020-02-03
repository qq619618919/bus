package com.paic.json.demo16_JsonAnySetterGetter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Auther: lwm_6
 * @Date: 2020/1/30
 * @Description: com.paic.demo16
 * @version: 1.0
 */
public class Person01 {

    private List<String> names;

    @JsonCreator
    public Person01(@JsonProperty("names")List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
