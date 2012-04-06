package com.tumash.usefy.tag;

import com.googlecode.objectify.annotation.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
public class Tag {

    @Id
    private Long id;

    private String name;
    private List<Long> parents;

    private Tag()   {

    }
    public Tag(String name) {
        this.name = name;
    }

    public Tag(String name, List<Long> parents) {
        this.name = name;
        this.parents = parents;
    }

    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getParents() {
        return parents;
    }

    public void setParents(List<Long> parents) {
        this.parents = parents;
    }
}
