# Bean Factory (下) - 单一职责原则

目前的问题：`Bean factory` 有很多代码是用来处理 XML 相关的代码。
违反了面向对象的**单一职责原则**。

## 单一职责原则 SRP
>《敏捷软件开发-原则、模式、实践》- Robert Martin

职责：引起变化的原因
- 如果多余一个的动机去改变一个类，这个类就具有多于一个职责
- 多个职责耦合在一起，一个变化可能会削弱或者抑制这个类完成其他职责的能力

**SRP**：对于一个类而言，应该仅有一个引起它变化的的原因。
### 现有代码问题
目前 `DefaultBeanFactory` 有多余一个的职责：
1. 读取 `XMLBeanDefinitionReader` 文件，形成 `BeanDefinition` 的对象
2. 通过反射的方式去创建 Bean 的实例

### 解决方法：
1. 创建一个 `Reader` 类来读取 XML 文件，返回获取 `BeanDefinition` 对象
2. 在 `BeanFactory` 这个接口中提供一个方法：`RegisterBeanDefinition`，Reader 解析 XML 文件之后，可以掉用这个方法
3. 暗含的意思就是让 Reader 持有一个 `BeanFactory` 的实例，类图

但是这样的设计有问题的：`BeanFactory` 是让客户使用的，这样的话就提供 registerBeanDefinition 的方法，就可以修改内部实例的方法，这样不好。

BeanDefinition 是一个**内部**的概念，是把 XML 或者注解的元数据变成了一个 BeanDefinition。用 BeanDefinition 的目的是通过用反射来创建对象实例。

不希望让用户看到，`getBeanDefinition` 这样的方法也不应该放在 BeanFactory 上。

抽取新的接口：`BeanDefinitionRegistry` 专门用来处理 `Bean Definition`。


1. `XMLBeanDefinitionReader` 持有的不在是 `Factory` 而是 `BeanDefinitionRegister` 的实例。
2. 将 `getBeanDefinition` 和 `registerBeanDefinition` 从 `BeanFactory` 中删除掉(不希望客户调用这两个方法)。
3. `DefaultBeanFactory` 仍然需要实现 `BeanFactory` 接口，并且实现 `BeanDefinitionRegistry` 接口。

好处：
- 单一职责：`XMLBeanDefinitionReader` 只需要获取 `BeanDefinition` 和注册 `BeanDefinition`,不知道 `getBean` 方法，符合“接口最小化”原则。


### 编码

### 先修改测试用例
