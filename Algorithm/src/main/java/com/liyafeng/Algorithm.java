package com.liyafeng;

/**
 * Created by liyafeng on 16/11/2017.
 * 这里主要是问答形式的算法题，
 * 如果是写代码形式的算法题，见com.liyafeng.algorithm
 */

public class Algorithm {


    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i=0;
                    while (i++<5) {
                        System.out.println("进入了1");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
        });
        thread.start();


        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("执行结束了");
    }
    //region 排序

    /**
     * 几种排序比较？
     */
    void a1() {

    }

    /**
     * 快速排序 quick 的原理？
     * ==========
     * 快速排序，思想是切分（partition），取第一个元素k，两个指针，i ,j 指向第二个元素和尾元素
     * i找到第一个比k大的，j找到第一个比k小的，交换，直到j < i ，最后交换k 和 j
     * <p>
     * 如此反复
     * ==========
     * 改进，在小数组中改用插入排序，小数组插入排序比快速排序要快，因为快排的递归需要调用自己
     */
    void a1_1() {

    }


    /**
     * 插入排序 insertion 和希尔排序 shell 区别？
     * ======
     * 区别：
     * 希尔排序是改进版的插入排序
     * 插入排序每次选取剩余数组的第一个元素，插入到排序好的数组中，但是如果数组很大
     * 假如最后一个元素是最小的，那么他将要和左边排序好的所有元素比较后才能移动到最左边
     * 希尔排序则可以将数组大体排序好（h有序的），这样最后一次完全的插入排序时
     * 数组中元素的比较次数减少了。
     * <p>
     * 时间复杂度insertion O(n^2) shell大约为O(n^3/2)
     * ==============
     * 希尔原理：
     * h   遍历顺序h/3...13 ,4 ,1     h取值范围 3h+1...1 (3h+1<n)
     * <p>
     * 从第h个元素开始遍历剩下所以的元素，每隔h个数字组成一个数组进行插入排序
     * <p>
     * 当前的j和前j-h（j>h）的数字比较，小的插入前面
     * <p>
     * <p>
     * <p>
     * ===============
     * 插入排序是从剩余数组中选第一个元素，插入前面到已经排序好的数组中
     * <p>
     * <p>
     * ============
     * insertion会比selection比较次数要少一些
     */
    void a1_2() {

    }

    /**
     * 选择排序selection 和 冒泡排序bubble 区别？
     * ========
     * 选择排序是每次遍历剩余数组，找出最小的元素放在剩余数组的最前面
     * 冒泡排序是每次剩余数组中的元素两两比较，最小的元素会冒到剩余数组的最前面
     * <p>
     * 两者时间复杂度都是O(n^2)，但是selection要比bubble快一些，因为选择排序的交换次数少
     */
    void a1_3() {

    }


    /**
     * 归并排序 merge
     * =========
     * 分治思想的应用
     * 两个有序的小数组合并为一个有序的大数组
     * 优点是只需要O(NlogN)时间复杂度，缺点是需要一个辅助空间，和N成正比
     * <p>
     * ===================
     * 重点是归并方法merge
     * a[] lo mid hi
     * lo - mid  mid -hi 的两段都是有序的
     * <p>
     * =================
     * 改进，可以对小数组排序的时候改为插入排序，这样比纯归并快10%
     */
    void a1_4() {

    }

    //endregion


    //region 查找

    /**
     * 几种查找算法比较？
     * ===========
     * 二分法，红黑树，散列表（拉链法，线性探测法）
     */
    void a2() {

    }
    //endregion

    //region 字符串

    /**
     * 了解字符串操作？
     * ==============
     * 字符串匹配，字符串排序（低位优先的字符串排序，高位优先），（单词查找树）
     */
    void a3() {

    }
    //endregion

    //region 图


    /**
     * 什么是图？
     */
    public void a4() {
        /*
        * 定义：由一组定点和一组能够将两个顶点相连的边组成
        */
    }

    /**
     * 图的算法？
     * ===========
     * 最短路径，最小生成树，拓扑排序
     */
    public void a4_1() {

    }

    //endregion

    //region 树

    /**
     * 什么是树？
     */
    public void a5_1() {
        /*
        *  树(Tree)是n(n≥0)个结点的有限集T，T为空时称为空树，否则它满足如下两个条件：
        *   有且仅有一个特定的称为根(Root)的结点；
        *   其余的结点可分为m(m≥0)个互不相交的子集Tl，T2，…，Tm，其中每个子集本身又是一棵树，并称其为根的子树(Subree)。
        *
        *   
        */
    }

    /**
     * 说说几种常用的树
     */
    public void a5_2() {
        /*
        * 见res/drawable/tree.png
        */
    }

    /**
     * 什么是B树？什么是B+树？有什么区别？
     * ----------------------
     * （B树等于 B-树） B-Tree
     * http://blog.csdn.net/v_JULY_v/article/details/6530142
     */
    public void a5_3() {
        /*
        * B树，是Balance树的简称，意为多路平衡查找树，我们知道红黑树也是一种平衡树，
        * 但是他的每个节点只能有2-3个子节点。而一个m阶的B树最多有m个子节点。
        * 我们先要理解磁盘的结构，和计算机如何查找到磁盘上的数据并读取的。
        * 磁盘查找要依次查找 柱面好，盘面好，磁道号，其中最花费时间的就是读写臂
        * 移动到指定的柱面，所以我们树越深，读取的节点越多，那么IO耗费的时间越多1
        * 所以我们试图在一个节点中存储多个数据，来减少树的高度。这样来节省
        * IO时间。所以B树更适合磁盘查找。
        * =================B+Tree=================
        * B树的实际值都存在各个结点中，而B+树的实际值都存在叶子结点中
        * 而非叶子结点中存的都是他指向的叶子结点的最小值。
        * 结构见drawable/Btree.jpg ，
        * ----------------优点---------------------
        * B+-tree比B 树更适合实际应用中操作系统的文件索引和数据库索引
        * 1.占用内存更少，因为查找所加载的结点中没有实际值，所以占用内存更少
        * 2.B树虽然提高了磁盘的IO性能，但没有解决遍历元素效率低下的问题
        *    而B+树所有数据都在叶子结点，所以我们把叶子结点串联形成一个链表，
        *    可以更高效的遍历。
        * B树只能中序遍历树。
        * 比如数据库经常用到范围查找，我们可以找到min，和max，然后遍历叶子结点
        * 形成的链表即可。这是B+树的优点。
        *
        *
        */
    }
    //endregion

    //region 压缩/加密

    /**
     * 数据编码（压缩）？
     */
    void a6() {

    }
    //endregion
}
