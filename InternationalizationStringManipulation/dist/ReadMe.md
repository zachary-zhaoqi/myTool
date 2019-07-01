# 简略使用说明


window.intlFormat("jlt.**模块名**.label.**英文字符串**", "**中文字符串**")


启动时输入"模块名"

复制如下格式的字符串到剪贴板

> - "预报警编码"——带双引号
> - '预报警编码'——带单引号  
> - 预报警编码——不带引号

双击capslk键 + (格式字符)

- m => window.intlFormat("jlt.模块名.**menu**.英文字符串", "中文字符串") 
- b => window.intlFormat("jlt.模块名.**button**.英文字符串", "中文字符串") 
- l => window.intlFormat("jlt.模块名.**label**.英文字符串", "中文字符串") 
- g => window.intlFormat("jlt.模块名.**grid**.英文字符串", "中文字符串") 
- d => window.intlFormat("jlt.模块名.**dictionary**.英文字符串", "中文字符串") 
- i => window.intlFormat("jlt.模块名.**impexp**.英文字符串", "中文字符串")
- p => window.intlFormat("jlt.模块名.**prompt**.英文字符串", "中文字符串")

处理后自动复制到剪贴板，粘贴出来即可。

ps：出错了就重启吧，鲁棒性没做那么严谨。![avatar](https://s9.rr.itc.cn/r/wapChange/20172_15_15/a69gf0948242652613.jpg)