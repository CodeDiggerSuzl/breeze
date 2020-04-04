

#  思路
1. 读取 xml 文件
2. 对指定的 package 进行扫描 找到标记@Component 的类,创建 BeanDefinition
    1. 把一个 package 下面的 class 变成 resource
    2. 使用**ASM**读取 Resource 中的注解
    3. 创建 BeanDefinition
3. 通过 BeanDefinition 创建 Bean 实例,根据注解进行注入

## ASM
一个可以操作和修改 Java 字节码的框架

可以出去/修改 class 中的字节码

工作模式: Visitor(design pattern)
- Class Reader
- Class Visitor (provided by the user of ASM)
- Class Writer (..)