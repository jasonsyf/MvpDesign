# MvpDesign
这是一篇关于Mvp设计模式的demo
## 基本MVP模式
### model
 TestModel
### presenter
 BasePresenter

 TestPresenter
### view
BaseView

TestFragment

## RxMVP模式
### model
 TestModel
### presenter
 BaseRxPresenter

 TestRxPresenter
### view
BaseRxView

TestFragment


## 用到的第三方库
ButterKnife

SwipeRefreshLayout（官方的无上拉加载，准备自己封装一个）

Retrolambda （lambda风格的代码  极大减少缩进与回调代码量）

拟采用的网络请求 RxJava2.0+、Retrofit2.0+