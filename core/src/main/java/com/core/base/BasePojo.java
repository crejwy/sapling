package com.core.base;

import java.util.Date;

public class BasePojo {
    private String creator;
    /**
     * 创建时间
     */

    private Date createdt;

    /**
     * 最后修改者
     */

    private String modifier;


    /**
     * 最后修改时间
     */

    private Date modifydt;
    /**
     * 是否逻辑删除
     */

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
