# Winter Framework 
❄❄❄❄❄
A framework written in the style of Spring-Framework.

# 这里是dev分支，类似于是教程，开发日志
## 扫描资源 resource-resolve
只需要定义资源类以及相应的资源扫描类即可，可以扫描Class文件、Jar文件、普通文件
具体代码详见 `resource-resolve` 下的 `Resource` 和 `ResourceResolve`
测试代码都在 `test` 目录下
## 注入 property-resolve
主要作用是扫描properties和yaml文件中的key-value
## BeanDefinition