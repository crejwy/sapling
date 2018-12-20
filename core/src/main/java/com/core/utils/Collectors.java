package com.core.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public final class Collectors{
    public static  <T extends TreeNode>  List<T> toTree(List<T>  collection){
        Objects.requireNonNull(collection);
        List<T> tree=new ArrayList<T>();
        if(collection.size()>0){
            tree= catChildrenTree(collection,new RootNode());
        }
        return  tree;


    }

    private  static <T extends TreeNode>  List<T>  catChildrenTree(List<T> collection, TreeNode node){
        List<T> tree=collection.stream().filter(rt->rt.getPid()==node.getId()).collect(java.util.stream.Collectors.toList());
        for(TreeNode n:tree){
            n.setChildren(catChildrenTree(collection,n));
        }
        return  tree;
    }
}
