# 三种实现TabLayout滑动布局方式总结 #

----------
## 自定义HorizontalScrollView实现TabLayout效果 ##
**先放效果：**
![这里写图片描述](http://img.blog.csdn.net/20150922144432482)
**使用心得：**参考的网上的写法，以前我有几个项目就用的这种实现方式，实现代码详细，便于我们理解实现细节，并且个性化配置简单方便。另外代码比较老了算，但是仍然是一个学习的好资料。

**参考文章：**[http://blog.csdn.net/top_code/article/details/8990573](http://blog.csdn.net/top_code/article/details/8990573 "http://blog.csdn.net/top_code/article/details/8990573")

----------
## 使用github大神的PagerSlidingTabStrip实现TabLayout效果 ##
**先放效果：**
![这里写图片描述](http://img.blog.csdn.net/20150922144611327)
**使用心得：**github的开源库，配置简单方便，布局清晰，易于维护。

**开源库地址：**[https://github.com/astuetz/PagerSlidingTabStrip](https://github.com/astuetz/PagerSlidingTabStrip "https://github.com/astuetz/PagerSlidingTabStrip")

----------
## 自带design.widget.TabLayout实现TabLayout效果 ##
**先放效果：**
![这里写图片描述](http://img.blog.csdn.net/20150922144831012)
**使用心得：**android.support.design.widget.TabLayout 是谷歌自己推出的tablayout实现控件，更加美观易用，并且向下兼容，绝对是开发中最优的选择；

**需要的引用：**
    
	dependencies {
    	compile 'com.android.support:design:23.0.1'
	}
