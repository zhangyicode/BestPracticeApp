package com.liyafeng.view.fragment;

import android.content.Context;

/**
 * Created by liyafeng on 2017/12/29.
 */

public class Main {

    /**
     * @param args
     * https://developer.android.google.cn/guide/components/fragments.html
     * 我们要用support-v4包下的FragmentManager 和Fragment
     * 然后 开启事务
     * add ,是将一个 片段 加入到布局中
     * 而replace是将布局中存在的一个Fragment替换为这个Fragment，如果没有，则无效果
     *
     * Fragment也可以后退，像Activity一样也有回退栈
     * -----------------------
     * Fragment本身就是一个View，只不过用FragmentActivity，和FragmentManager来赋予它生命周期
     * 这样就是包装了一层，使得我们可以复用代码
     *
     * --------------------------------
     * add后，只是将这个Fragment  addView到布局中，会盖在下面的Fragment上
     * detach相当于removeView，但是没有销毁，下次可以attach
     * replace方法相当于把现在container中的Fragment全都remove，然后将新的Fragment add进去
     *
     * --------------------------
     * 一个app主体可以用Fragment+viewpager+RadioButton，但是viewpager要加懒加载
     * 也可以Fragment+radioButton，这样适合没有滑动效果的主页框架
     *
     */
    public static void main(String[] args) {
    }
}
