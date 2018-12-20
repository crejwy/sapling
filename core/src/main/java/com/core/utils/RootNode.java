package com.core.utils;

import java.util.Collection;
import java.util.List;

public class RootNode<T,ID> implements TreeNode<T,ID> {
    private Collection<T> children;

    @Override
    public ID getId() {
        return null;
    }

    @Override
    public ID getPid() {
        return null;
    }

    @Override
    public Collection<T> getChildren() {
        return children;
    }

    @Override
    public void setChildren(Collection<T> children) {
        this.children=children;
    }
}
