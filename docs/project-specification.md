## XMatch项目编码及管理规范

本项目要求遵守下述编码规范：

- [Java](http://www.hawstein.com/posts/google-java-style.html)
- [Python](http://zh-google-styleguide.readthedocs.io/en/latest/google-python-styleguide/contents/)
- [C++](http://zh-google-styleguide.readthedocs.io/en/latest/google-cpp-styleguide/contents/)

及以下额外要求：

- 缩进为4个空格；
- 在遇到复杂表达式时，用括号表明逻辑优先级；
- 注释要求体现：1.代码模块的作用；2.这样做的原因；3.特别需要注意的地方；
- 变量名遵守驼峰命名法，形如`xmatchCodeSpecification`；
- 代码逻辑分段，每一个代码段(函数方法、类等)都需要有注释；
- 对于左括号是否换行没有具体要求，但是同一文件内部的左括号需要保持统一；
- 本项目使用目录`tools/`下的脚本对文件代码规范进行自动化测试。

Github项目管理要求遵循：

- [github-example-repo](https://github.com/Wasdns/github-example-repo)

Editor: Wasdns.