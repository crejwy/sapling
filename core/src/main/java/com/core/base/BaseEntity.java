package com.core.base;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;
@MappedSuperclass
public class BaseEntity  {
    /**
     * 由谁创建
     */
    @Column(name = "Creator",updatable = false)
    private String creator;
    /**
     * 创建时间
     */
    @Column(name = "CreateDT",updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdt;

    /**
     * 最后修改者
     */
    @Column(name = "Modifier",insertable =false )
    private String modifier;


    /**
     * 最后修改时间
     */
    @Column(name = "ModifyDT",insertable =false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date modifydt;
    /**
     * 是否逻辑删除
     */
    @Column(name = "IsDeleted")
    private Boolean isdeleted;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatedt() {
        return createdt;
    }

    public void setCreatedt(Date createdt) {
        this.createdt = createdt;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifydt() {
        return modifydt;
    }

    public void setModifydt(Date modifydt) {
        this.modifydt = modifydt;
    }

    public Boolean getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }
}
