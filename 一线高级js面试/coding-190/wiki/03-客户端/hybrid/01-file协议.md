# file 协议

> 我们最常用的肯定是 http 或者 https 协议，通过访问 server 端返回网页文件的形式来打开页面。

但你是否还记得你最开始学习 html 的时候，那会儿还不知道什么是 http 协议，因此你就写完一个 html 页面，直接双击，然后浏览器打开。这会儿看浏览器地址栏，是`file:///xxx/xxx/xxx/index.html`这么一种格式。这就是`file`协议。

其中的 js css img 文件都可以通过`file`协议来加载。。。。

计算机编程的协议有非常多，我们日常接触的有`http(s)` `ftp` `ws` `file` `ssh`等。`http(s)`是打开一个远程的页面，而`file`是打开一个本地的页面。那两者的区别是啥：

- `http(s)`协议需要通过网络请求，下载页面，然后打开
- `file`协议直接打开本地的，断网情况下也能打开，无需网络请求

从速度上来看，当然是`file`协议更快

一开始提到 hybrid 具有和 NA 一样的体验，其中一点就是和 NA 一样快。如何做到的？—— 就是用了`file`协议。

**hybrid 就是使用`file`协议访问本地的 html 页面**，因此它足够快。所谓“本地”是哪里？—— 每个 app 安装的时候都可以存储一些文件，这些 html 也就放在某一个文件目录下。就像你微信的聊天记录（文字、视频、图片等）都是会存储到系统的某一个地方，app 具有这种存储文件的能力。手机内存如果比较小，如 16g 的 iphone ，你还得定期清理这些垃圾文件。
