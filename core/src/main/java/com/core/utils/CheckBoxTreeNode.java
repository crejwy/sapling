package com.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public  class CheckBoxTreeNode<T extends CheckBoxTreeNode ,ID> implements TreeNode<T,ID>{
    private ID id;//主键.
    private ID parentId; //父编号
    private Collection<T> children;
    private Boolean checked;//是否选中

    public void setId(ID id) {
        this.id = id;
    }

    public ID getParentId() {
        return parentId;
    }

    public void setParentId(ID parentId) {
        this.parentId = parentId;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    @Override
    public ID getId() {
        return this.id;
    }

    @Override
    public ID getPid() {
        return this.parentId;
    }

    @Override
    public Collection<T> getChildren() {
        return this.children;
    }

    @Override
    public void setChildren(Collection<T> children) {
        this.children=children;
    }
    public List<ID> getChildrenChecked() {
        List<ID> c=new ArrayList<>();
        for(T router:getChildren()){
            c.addAll(router.getChildrenChecked());
        }
        if(getChecked()){c.add(getId());}
        return c;
    }
}
