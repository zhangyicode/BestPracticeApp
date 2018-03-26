package com.liyafeng.practice;

import android.animation.ObjectAnimator;
import android.app.FragmentManager;
import android.content.Context;

public class AndroidFramework {


    //region Android UI绘制

    /**
     * =====================
     * ### Android UI
     * =====================
     * */

    /**
     * 简述事件分发流程
     * 事件分发机制
     */
    public void a1() {
        /*
        * 最一开始ViewRootImpl 接收到触摸事件，然后会传递给DecorView
        * 的dispatchTouchEvent()，然后Decorview会将事件分发给子控件
        * 先判断当前View是否拦截事件，如果拦截就直接调用自己的ontouchevent
        * 如果没有拦截就依次分发给子view，直到最底层的view，在ontouchevent
        * 中返回是否消费，如果有消费下次就直接将事件传递给它，如果没有消费就依次
        * 调用父控件的ontouchevent,直到事件被消费。
        *
        * 整个过程是一个递归调用，是类似于是反向的树的前序遍历
        *
        * */

    }

    /**
     * View的渲染机制
     */
    public void a1_1() {
        /*
        *
        * Android的图形都是在canvas对象中绘制的，一个canvas持有一个bitmap对象
        * 然后用openGl es将多维图形通过gpu来渲染，进行光栅化，就是将矢量图转化为
        * 像素点，然后通过硬件时钟将像素点投影到屏幕上
        *
        * 其中16ms同步一次，帧率就是60fps ，所以我们完成一次绘制要在16ms内
        * 否则就会出现掉帧的情况，因为绘制时间超过16ms，就算绘制完成也不会同步，
        * 只能等待下一次同步，所以这一帧就没有被渲染，我们管他叫掉帧
        *
        * 我们滑动卡顿优化的原理也是根据这个来的
        *
        * */
    }


    /**
     * View的绘制流程
     * http://www.liyafeng.com/c/Android_APIsetContentView流程分析
     */
    public void a1_2() {
        /*
        * 首先会将xml解析成对象，addview添加到decorview中
        * 然后执行requestLayout()，最终在ViewRootImpl中执行doTraversals
        * 进行view树的遍历，最先执行performMeasure()初步确定view的宽高,
        * 然后是performLayout，确定子view在父布局中的位置，left top right bottom 四个参数
        * 最后执行performDraw ,将canvas对象传入，子view根据自己的ondraw方法进行绘制
        */
    }

    /**
     * Android 动画原理 、底层如何给上层信号？
     */
    public void a1_3() {
        /*
        * 分为 1.补间动画（tween 屯，两者之间）2.属性动画(attribute) 3.帧动画 frame
        *
        * 补间动画实际上操作的是canvas的matrix ，属性动画操作view的属性，有get set方法的属性
        * 帧动画就是一帧帧图片播放
        *
        * 他们都原理都是记录动画的执行时间，判断当前时间动画有没有结束，如果没有结束
        * 就调用invalidate方法进行重绘，一次次的重绘，改变位置，就会形成动画效果
        *
        * 给上层信号调用自身的的invalidate方法，里面调用父布局的invalidateChildInParent
        * 这里有一个while循环，会一直取父布局（的引用），直到调用viewrootimpl的invalidateChildInParent
        * 里面会调用scheduleTraversals()执行遍历，遍历调用view树的ondraw，这样就会刷新view的视图
        *
        * */

    }

    /**
     * Activity的加载流程
     * http://www.liyafeng.com/c/Android_APIstartActivity流程分析
     */
    public void a1_4() {
        /*
        * 首先用binder请求到ActivityManagerService ，然后会回调到本进程的
        * ActivityThread，在里面会通过反射方式new 出Activity的对象，然后会
        * 回调Activity的生命周期
        */
    }

    /**
     * invalidate和postInvalidate的区别及使用?
     * */
    public void a1_5(){
        /*
        * 他们都是用来发出信号来刷新UI的
        * 区别是后者可以在字线程中调用
        * 原理是调用了ViewRootImpl中的ViewRootHandler.post方法
        */
    }

    /**
     * Activity-Window-View三者的差别?
     * */
    public void a1_6(){
        /*
        * Activity中持有Window对象，他的实现类是PhoneWindow
        * PhoneWindow中持有DecorView,DecorView是FrameLayout的子类
        * 是真正显示视图的
        */
    }

    //endregion

    //region Android 内存/虚拟机

    /**
     * =====================
     * ### Android 内存
     * =====================
     * */

    /**
     * 说说什么是内存泄漏，说一个典型的例子，怎么避免？
     */
    public void a2_1() {
        /*
        * 本该被回收的对象因为存在对他的强引用而没有被回收
        * Android中最典型的就是Activity对象的泄漏，比如用Handler发延时消息
        * 在Activity销毁后消息还存在队列中，但是此时Handler对象持有Activity的引用
        * 从而使Activity没有被回收，导致内存泄漏，解决方法就是用静态Handler或者在
        * onDestroy()中移除消息
        * -------
        * 还有一个例子是在Activity中使用AsyncTask，当Activity销毁时任务没有执行完
        * 因为AsyncTask持有Activity的引用，也会导致泄漏，解决方法是在onDestroy调用
        * 他的cancel方法来中断线程
        *
        */
    }

    /**
     * Android进程如何保活？系统杀掉后如何重启？为什么要保活？
     * http://blog.csdn.net/andrexpert/article/details/75045678
     */
    public void a2_2() {
        /*
        * 我们APP要及时接收到通知，那么就需要通知服务一直在后台运行
        * Android的进程回收机制是用Low Memory Killer
        *
        * 1.监听系统广播唤醒app
        * 2.启动前台service，在通知栏发个消息
        * 3.减少内存消耗，防止被杀死
        * 4.一像素保活（动态监听屏幕锁屏解锁广播，在锁屏时开启一个像素的Activity）
        *   在黑屏状态下保活
        * 5.循环播放一段无声的音频，用一键清理也保活
        * 6.双进程相互唤起
        *
        * linux会为每个进程分配一个优先级，叫oom_adj，数值越低优先级越高，越不容易被杀死
        * 普通app的值一般是大于0，系统进程一般是小于0
        * 用adb shell进入手机命令行模式，然后用 ps|grep com.xxx 来查看包下的所有进程
        * 然后用 cat /proc/进程id/oom_adj 来查看进程的优先级数值
        *
        *
        */
    }

    /**
     * Android Dalvik虚拟机和JVM的区别？
     */
    public void a2_3() {
        /*
        * 1.Android Dalvik 运行的是.dex 即Dalvik Executable,
        * 他是.class文件的压缩，这样占用的内存更少
        * 2.dvm是基于寄存器的，而jvm是基于栈的
        * http://rednaxelafx.iteye.com/blog/492667
        */
    }

    //endregion

    //region Android 四大组件基本知识


    //region Activity/Fragment
    /**
     * 1.Activity 生命周期的理解？
     * 2.横竖屏切换时的生命周期？如何配置?
     * 3.显示dialog时 Activity的生命周期?
     * 4.Activity上有Dialog的时候按Home键（前后台切换）时的生命周期？
     * 5.跳转时的生命周期
     * 6.锁屏和解锁后生命周期
     * 7.永久性质的数据，应该在哪个生命周期方法中保存?
     * https://developer.android.google.cn/guide/components/activities.html
     * https://developer.android.google.cn/guide/components/activities/activity-lifecycle.html#java
     * */
    public void a3_1(){
        /*
        * =============1==========================
        * onCreate() 设置布局，初始化变量，接收Bundle来恢复Activity
        * onStart() 这个方法不建议做耗时操作，可以进行注册广播接受者的操作
        * 这个时候Activity还不可见，
        *
        * onRestoreInstanceState 在onStart后调用， 在onPostCreate前调用
        * 一般我们在onCreate中就恢复了原有状态，但是在这调用时为了有些时候
        * 我们需要等所有资源初始化完毕后再调用。（这个只有在系统回收后，我们
        * 再次启动的时候调用,还有在屏蔽旋转销毁后）所以这里的Bundle一定不为null
        *
        *
        * onResume()后，Activity变得可见，一般在这个方法中恢复在onPause
        * 中释放的资源，或者初始化动画？这个时候Activity获取到焦点
        *
        * onPause()当Activity被遮罩的时候，失去焦点，用户不能再与之交互
        * 比如半透明的activity（作为dialog的Activity），这个Activity部分可见，就会
        * 失去焦点，调用onPause();注意Dialog出现不会调用任何方法
        *
        * onSaveInstanceState 永远在onPause后和onStop前调用，为什么这么设计？
        * 是因为我们如果onStop后调用，那么有可能被系统回收而得不到回调。
        * onPause之前调用又会效率太低。（用户按back键或者finish时这个方法不被调用
        * ，因为用户已经明确不需要这个Activity了，只有在Activity进入后台，有可能被
        * 回收的时候，才会调用（比如home键，锁屏，打开新的Activity））
        *
        *
        * onStop当Activity完全不可见的时候进入这个方法。这个方法中我们释放
        * 一些用户不用的资源，比如我们在onStart中注册的广播，可以在
        * onStop中取消注册。还可以释放一些资源以免内存泄漏，因为这个Activity
        * 有可能被系统终止而不调用onDestroy ,如果Activity重新回到前台，
        * 会调用onReStart()-OnStart
        *
        * onDestroy 释放所有资源
        *
        *
        *
        *
        * =====================2==================
        * https://blog.csdn.net/xiaoli100861/article/details/50855152
        *  方向切换的时候我们会销毁后重建，当然onSaveInstanceState
        *  onRestoreInstanceState 可以用来恢复数据
        *  onPause ,onStop onDestroy onCreate onStart onResume
        * 我们在清单文件中配置不销毁
        * android:configChanges="orientation|keyboardHidden|screenSize"
        * （这个和操作系统(4.0)和targetApi(12)有关，但是最新的一般都是这样配置）
        * ================3========================
        * Dialog的出现不会调用Activity的任何生命周期，
        * 调用生命周期是ActivityManager，而dialog是通过WindowManager来管理的
        * （但是好像系统的Dialog会造成onPause???）
        *====================4=======================
        * 有无dialog，Activity的进入后台，切回前台生命周期都是这样的
        *   onPause:
        *   onSaveInstanceState:
        *   onStop:
        *   onRestart:
        *   onStart:
        *   onResume:
        *
        * ==================5=========================
        * A开启B, A-onPause B-onCreate\onStart\onResume A-onStop
        * B关闭，B-onPause A-onRestart\onStart\onResume  B-onStop\onDestroy
        * ================6================
        * 锁屏和前后台切换的生命周期相同
        * ===============7================
        * 永久性数据应该在onStop中存储，因为在后台有可能被系统kill掉不调用onDestroy
        * onPause中太频繁
        *
        */
    }


    /**
     * Activity与Fragment之间生命周期比较
     * https://developer.android.google.cn/guide/components/fragments.html
     * @link com.liyafeng.view.fragment.Main}
     * */
    public void a3_2(Context context){
        /*
        * Fragment生命周期是FragmentManager来控制的
        * 他本质上还是inflate了布局，然后addView的方式加入到Activity的布局中
        * 他也有回退栈管理，按返回键和Activity效果一样
        *
        */
        context.getResources().getDrawable(R.drawable.fragment_lifecycle);
        context.getResources().getDrawable(R.drawable.activity_fragment_lifecycle);

    }

    /**
     * Activity的四种启动模式对比? 回退栈有什么用？
     * https://developer.android.google.cn/guide/components/activities/tasks-and-back-stack.html
     * https://blog.csdn.net/u012203641/article/details/77408342
     * */
    public void a3_6(){
        /*
        * https://blog.csdn.net/u012203641/article/details/77408342
        */
    }

    /**
     * 进程的四种状态？内存低的时候Android系统是如何管理进程的？
     * https://developer.android.google.cn/guide/components/activities/process-lifecycle.html
     * */
    public void a3_7(){
        /*
        * 1.前台进程，
        * 这个进程中有一个resume的Activity，
        * 或者一个在执行onReceive()的BroadCastReceiver ,
        * 或者有一个在执行onCreate onStart onDestroy 的Service
        *
        * 2. 可见进程，
        * 进程中有一个没有焦点的Activity，但是它可见，比如一个半透明的Activity 盖住了他
        * 有个前台的Service，通过Service.startForeground方法来显示一个Notification
        * 有系统用的独特服务，比如动态壁纸，输入法服务
        *
        * 3.服务进程
        * 进程中有startService 方式打开的Service，当前两种进程内存不够用时，将回收这个进程
        * 连续运行30分钟以上有可能会被降级，因为这有可能发生了内存泄漏而占用太多内存
        *
        * 4.缓存进程
        * 一般这种进程中有1个或者多个onStop的Activity，这个时候当内存不足时会优先回收
        * 一般优先回收的是最久没有用过的进程。
        */
    }
    
    /**
     * Fragment状态保存startActivityForResult是哪个类的方法，在什么情况下使用？
     * */
    public void a3_8(){
        /*
        * 有个回调是在FragmentActivity中调用的，
        */
    }
    
    /**
     * 如何实现Fragment的滑动？
     * */
    public void a3_9(){
        /*
        * 用ViewPager
        */
    }
    
    /**
     * fragment之间传递数据的方式？
     * */
    public void a3_10(){
        /*
        * 可以通过Activity来传递，也可以用EventBus的实现方式
        */
    }


    //endregion

    //region BroadCastReceiver

    /**
     * 广播有几种注册方式？各有什么优点？
     * https://developer.android.google.cn/guide/components/broadcasts.html#receiving_broadcasts
     */
    public void a4() {
        /*
        * AndroidManifest中静态注册，代码中动态注册
        * 优点，代码注册的优先级比较高，而且有些隐式广播只能代码中注册
        * 缺点，注册广播的Activity的页面关闭后，广播就失效了
        * 静态的优点，是随时都能接收到广播
        *
        */
    }

    /**
     * 有哪些常见的系统广播
     * */
    public void a4_1(){
        /*
        * 系统开机
        * 飞行模式
        * 网络状态改变
        *
        */
    }
    
    /**
     * 本地广播和全局广播的差别?
     * */
    public void a4_2(){
        /*
        * 本地广播只能在本应用内传播，使用LocalBroadcastManager来注册和发送
        */
    }
    //endregion

    //region Service

    /**
     * 请描述一下Service 的生命周期?
     * */
    public void a5_1(){
        /*
        * start方式，onCreate ,onStartCommand ,onDestroy
        * bind方式，onCreate,onBind  onUnBind ,
        * 多次start 会调用多次startCommand()
        * bind只能调用一次，否则抛异常
        * bind后调用stop无效果
        * start和bind可以不分顺序的调用
        */
    }
    //endregion

    //region ContentProvider

    /**
     * 谈谈你对ContentProvider的理解?
     * https://developer.android.google.cn/guide/topics/providers/content-providers.html
     * */
    public void a6_1(){
        /*
        * 我们可以用这个组件来提供自己app的数据（CRUD操作），比如系统提供的音频，视频
        * 相片，联系人，日历
        *
        */

        // Queries the user dictionary and returns results
//        mCursor = getContentResolver().query(
//                UserDictionary.Words.CONTENT_URI,   // The content URI of the words table
//                mProjection,                        // The columns to return for each row
//                mSelectionClause                    // Selection criteria
//                mSelectionArgs,                     // Selection criteria
//                mSortOrder);                        // The sort order for the returned rows
    }
    //endregion


    //endregion
    


    //region Android 操作系统
    /**
     * =====================
     * ### Android 操作系统
     * =====================
     * */

    /**
     * Android  6.0的权限机制？6.0之前的权限机制？权限机制的原理是什么？
     */
    public void a8() {
        /*
         * 权限请求
         * https://developer.android.google.cn/training/permissions/requesting.html?hl=zh-cn
         * -----------------------
         * 6.0后要动态申请权限，6.0之前可以在xml中申请权限，用户在安装的时候同意所有
         * 原理是调用系统api的时候回去判断这个应用有没有被授权，如果有则执行，没有就
         * 返回null或者异常。
         */
    }

    //endregion

    //region Android 架构模式
    /**
     * =====================
     * ### Android 架构模式
     * =====================
     * */

    /**
     * 说说Android最新架构 Architecture Component
     * <p>
     * https://developer.android.google.cn/topic/libraries/architecture/guide.html#recommended_app_architecture
     * 源码地址
     * https://github.com/googlesamples/android-architecture-components
     */
    public void a9() {
        /*
        * 为了更好的管理生命周期，比如横竖屏切换，数据要重新加载的问题
        * 数据加载完成后 Activity 已经销毁导致内存泄漏的问题
        * 可以不让UI Controller （Activity Fragment）不那么臃肿，这样代码可维护
        * LifecycleOwner 持有Activity或者Fragment的生命周期
        * LiveData 负责当ViewModel获取了数据后，通知UI
        * ViewModel 为指定的UI提供数据
        * Repository 真正获取数据的仓库，里面获取MutableLiveData;
        * -------------
        * 在LiveData，setValue的时候，就会通知Activity中的观察者，然后更新UI
        * ---------
        * 其实也是MVVM的一种实现，但是加入了生命周期的管理，基于观察者模式的mvvm
        *
        */
    }


    /**
     * 说说MVC MVP MVVM 和Clean架构各自优点和区别？
     */
    public void a10() {
        /*
        *
        */
    }

    //endregion

    //region Android动画
    /**
     * 估值器和差值器的区别
     * https://blog.csdn.net/u012203641/article/details/77823949
     * */
    public void a11(){
        /*
        * 一个动画 过程是从0-1（100%） 匀速完成的，这个进度定义为 fraction（百分比）
        * 差值器是重新计算这个fraction，
        * 而估值器是计算当前百分比时，动画的属性值是多少
        *
        */
    }
    /**
     * Android动画框架实现原理?
     * https://blog.csdn.net/u012203641/article/details/77823949
     * */
    public void a11_1(){
        /*
        *
        * 视图动画
        * 原理就是调用invalidate（）方法，然后在View.draw的时候getAnimation
        * 判断是否为null，如果不为null，则获取View的Transformation对象(转化对象)，
        * 这个对象持有View画布的Matrix对象，最终在Animation中的applyTransformation()
        * 方法中完成矩阵的转化，不同子类调用不同的Matrix方法。比如Rotate调用的是
        * Matrix的旋转方法，这个方法是native的。然后如果动画没完成，会接着触发invalidate
        * 就在draw中，有个more标记，如果为true，那么继续调用invalidate
        *
        * 属性动画，
        * 从start()后，里面 Choreographer.getInstance();
        * Choreographer = Coordinates the timing of animations, input and drawing.
        * 我们将属性动画的回调加入到Choreographer中，然后post一个消息，
        * 然后先调用我们属性动画的回调，调用View的setXXX来设置View的属性
        * 然后Choreographer中还添加了draw的回调，所以就会刷新UI。
        * 依然是判断时间有没有执行完，没有执行完就循环调用
        *
        */

//        ObjectAnimator.ofFloat().start();
    }
    //endregion

}
