package com.hxht.logprocess.core.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @ClassName CopyBeansUtils
 * @Description: 赋值对象集合
 *
 * 使用方式如下：
 *
 * 1：
 * List<AdminEntity> adminList = ...;
 * List<ArticleVo> articleVoList = CopyBeansUtils.copyListProperties(adminList, AdminVo::new);
 * return articleVoList;
 *
 * 2：如果需要在循环中做处理(回调)，那么可使用lambda表达式：
 *
 * List<Article> adminEntityList = articleMapper.getAllArticle();
 * List<ArticleVo> articleVoList = ColaBeanUtils.copyListProperties(adminEntityList , ArticleVo::new, (articleEntity, articleVo) -> {
 *             // 回调处理
 *  });
 * return articleVoList;
 *
 *
 * @Author hxht-chenhao
 * @Date 2020/5/11
 * @Version V1.0
 **/
public class CopyBeansUtils extends BeanUtils {


    /**
     * Bo、DTO层数据的复制
     * @param sources
     * @param target
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target) {
        return copyListProperties(sources, target, null);
    }

    /**
     * 使用场景：Entity、Bo、Vo、DTO层数据的复制，因为BeanUtils.copyProperties只能给目标对象的属性赋值，却不能在List集合下循环赋值，因此添加该方法
     * 如：List<AdminEntity> 赋值到 List<AdminVo> ，List<AdminVo>中的 AdminVo 属性都会被赋予到值
     * S: 数据源类 ，T: 目标类::new(eg: AdminVo::new)
     */
    public static <S, T> List<T> copyListProperties(List<S> sources, Supplier<T> target, CopyBeanUtilsCallBack<S, T> callBack) {
        List<T> list = new ArrayList<>(sources.size());
        for (S source : sources) {
            T t = target.get();
            copyProperties(source, t);
            list.add(t);
            if (callBack != null) {
                // 回调
                callBack.callBack(source, t);
            }
        }
        return list;
    }




}
