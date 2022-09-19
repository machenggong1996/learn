#  hashmap

* [Hashmap面试总结](https://blog.csdn.net/androidstarjack/article/details/124507171)

## put

```
// putVal方法中相关部分(put方法调用了putVal())
if (binCount >= TREEIFY_THRESHOLD - 1) {
	// 转换成红黑树,如果hash table的长度不到MIN_TREEIFY_CAPACITY即64,
	// 那么只是做扩容处理,并不会转换为红黑树
    treeifyBin(tab, hash);
}

/ treeifyBin方法中相关部分
// MIN_TREEIFY_CAPACITY = 64
if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY){
	// 扩容,不转换
	resize();
}else if ((e = tab[index = (n - 1) & hash]) != null) {
	// 转换为红黑树节点
    TreeNode<K,V> hd = null, tl = null;
    do {
     // 具体转换逻辑
     ...
    } while ((e = e.next) != null);
    if ((tab[index] = hd) != null){
    	hd.treeify(tab);// 转换为红黑树
    }
}
```

## resize

* [HashMap底层原理+扩容机制](https://blog.csdn.net/qq_40871196/article/details/101855801)
* jdk1.8是要看看原来的hash值新增的那个bit是1还是0好了，如果是0则索引没变，如果是1则索引变成"原索引+oldCap".这是jdk1.8的亮点，设计的确实非常的巧妙，即省去了重新计算hash值得时间，又均匀的把之前的冲突的节点分散到新的数组bucket上
* [HashMap的扩容机制---resize() JDK1.8的新特点](https://blog.csdn.net/runrun117/article/details/80249556)
* [用HashMap存1W条数据，构造时传10000还会触发扩容吗？存1000呢？](https://blog.csdn.net/weixin_39819661/article/details/110639673)

![avatar](pics/hashmap扩容示例图.png)

### hashmap链表长度超过8就一定会变成红黑树吗？

