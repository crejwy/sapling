package com.core.utils;

import java.util.Collection;

public interface TreeNode<T,ID> {
    ID getId();
    ID getPid();

    Collection<T> getChildren();
    void setChildren(Collection<T> children);
}
