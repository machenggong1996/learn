# java ClassLoader

* [一看你就懂，超详细java中的ClassLoader详解](https://blog.csdn.net/briblue/article/details/54973413)
* [深入理解Java类加载器(ClassLoader)](https://www.cnblogs.com/cl-rr/p/9081817.html)

## 1. 加载流程

* Bootstrap ClassLoader 最顶层的加载类，主要加载核心类库，%JRE_HOME%\lib下的rt.jar、resources.jar、charsets.jar和class等。另外需要注意的是可以通过启动jvm时指定-Xbootclasspath和路径来改变Bootstrap ClassLoader的加载目录。比如java -Xbootclasspath/a:path被指定的文件追加到默认的bootstrap路径中。我们可以打开我的电脑，在上面的目录下查看，看看这些jar包是不是存在于这个目录。
* Extention ClassLoader 扩展的类加载器，加载目录%JRE_HOME%\lib\ext目录下的jar包和class文件。还可以加载-D java.ext.dirs选项指定的目录。
* Appclass Loader也称为SystemAppClass 加载当前应用的classpath的所有类

### 1.1 类加载器间的关系

1. 启动类加载器，由C++实现，没有父类。
2. 拓展类加载器(ExtClassLoader)，由Java语言实现，父类加载器为null
3. 系统类加载器(AppClassLoader)，由Java语言实现，父类加载器为ExtClassLoader
4. 自定义类加载器，父类加载器肯定为AppClassLoader。

### 1.2 类与类加载器

在JVM中表示两个class对象是否为同一个类对象存在两个必要条件
* 类的完整类名必须一致，包括包名。
* 加载这个类的ClassLoader(指ClassLoader实例对象)必须相同。

## 2. 加载顺序 查找类缓存顺序

### 2.1 双亲委托

* 一个AppClassLoader查找资源时，先看看缓存是否有，缓存有从缓存中获取，否则委托给父加载器。
* 递归，重复第1部的操作。
* 如果ExtClassLoader也没有加载过，则由Bootstrap ClassLoader出面，它首先查找缓存，如果没有找到的话，就去找自己的规定的路径下，也就是sun.mic.boot.class下面的路径。找到就返回，没有找到，让子加载器自己去找。
* Bootstrap ClassLoader如果没有查找成功，则ExtClassLoader自己在java.ext.dirs路径中去查找，查找成功就返回，查找不成功，再向下让子加载器找。
* ExtClassLoader查找不成功，AppClassLoader就自己查找，在java.class.path路径下查找。找到就返回。如果没有找到就让子类找，如果没有子类会怎么样？抛出各种异常。

#### 类加载和类缓存

* 每次向上委托前都会先查找缓存，缓存中存在则返回，不存在继续向上委托，直到到Bootstrap ClassLoader 方向由下往上
* Bootstrap ClassLoader查找能不能加载这个类能的话加载，不能的话向下查找能加载这个类的类加载器    方向由上往下

### 2.2 双亲委派模式优势

* 采用双亲委派模式的是好处是Java类随着它的类加载器一起具备了一种带有优先级的层次关系，通过这种层级关可以避免类的重复加载，当父亲已经加载了该类时，就没有必要子ClassLoader再加载一次。
* 其次是考虑到安全因素，java核心api中定义类型不会被随意替换，假设通过网络传递一个名为java.lang.Integer的类，通过双亲委托模式传递到启动类加载器，
  而启动类加载器在核心Java API发现这个名字的类，发现该类已被加载，并不会重新加载网络传递的过来的java.lang.Integer，而直接返回已加载过的Integer.class，这样便可以防止核心API库被随意篡改。
* 可能你会想，如果我们在classpath路径下自定义一个名为java.lang.SingleInterge类(该类是胡编的)呢？该类并不存在java.lang中，
  经过双亲委托模式，传递到启动类加载器中，由于父类加载器路径下并没有该类，所以不会加载，将反向委托给子类加载器加载，最终会通过系统类加载器加载该类。
  但是这样做是不允许，因为java.lang是核心API包，需要访问权限，强制加载将会报出如下异常
```java_exception
java.lang.SecurityException: Prohibited package name: java.lang
```

## 3. ClassLoader重要方法

### 3.1 loadClass()

* 执行findLoadedClass(String)去检测这个class是不是已经加载过了。
* 执行父加载器的loadClass方法。如果父加载器为null，则jvm内置的加载器去替代，也就是Bootstrap ClassLoader。这也解释了ExtClassLoader的parent为null,但仍然说Bootstrap ClassLoader是它的父加载器。
* 如果向上委托父加载器没有加载成功，则通过findClass(String)查找

```java_method
protected Class<?> loadClass(String name, boolean resolve)
        throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // 首先，检测是否已经加载
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    if (parent != null) {
                    	//父加载器不为空则调用父加载器的loadClass
                        c = parent.loadClass(name, false);
                    } else {
                    	//父加载器为空则调用Bootstrap Classloader
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    //父加载器没有找到，则调用findclass
                    c = findClass(name);

                    // this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
            	//调用resolveClass()
                resolveClass(c);
            }
            return c;
        }
    }
```

## 4. 自定义ClassLoader

* 编写一个类继承自ClassLoader抽象类。
* 复写它的findClass()方法。 
* 在findClass()方法中调用defineClass()。

