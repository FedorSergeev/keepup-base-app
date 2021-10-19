package io.keepup.cms.projects.model;

import io.keepup.cms.core.service.ContentId;
import io.keepup.cms.core.service.ContentMapping;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestLoadEntity implements Serializable {
    @ContentId
    private Long id;

    @ContentMapping("string_value")
    private String stringValue;

    @ContentMapping("list_value")
    private List<String> listValue;

    public TestLoadEntity() {
        this.listValue = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public List<String> getListValue() {
        return listValue;
    }

    public void setListValue(List<String> listValue) {
        this.listValue = listValue;
    }
}
