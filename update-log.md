            /**
             * 3.5 : 12348 可与电脑端视频（固定视频房间）
             * 3.6 ：连接 12348 硬件视频
             * 3.7 : 修复bug（暂停 MediaPlayer 时报错导致静态变量被清空）
             * 自动分流 12348 视频咨询事件。
             * 2018年12月26日09点19分 3.8：我的消息已读消息列表标题变灰。
             * 修复 留言咨询和意见投诉的显示不全bug(LayoutManager 禁止了滑动)
             * 2019年1月4日17点53分 3.9：1、控制用户权限（大众留言投诉只有自己提交的列表，工作人员有自己提交的和带处理的）。
             * 			2、工作人员投诉待处理列表新增分配处理功能。
             * 2019年1月9日14点48分 4.0：1、人民调解新增代理人录入。
             */
3.5 : 12348 可与电脑端视频（固定视频房间）
3.6 ：连接 12348 硬件视频
3.7 : 修复bug（暂停 MediaPlayer 时报错导致静态变量被清空）
        自动分流 12348 视频咨询事件。

release-3.8
*我的消息已读消息列表标题变灰。
*修复 留言咨询和意见投诉的显示不全bug(LayoutManager 禁止了滑动)

release-3.9
*1、控制用户权限（大众留言投诉只有自己提交的列表，工作人员有自己提交的和带处理的）。
*2、工作人员投诉待处理列表新增分配处理功能。

release-4.0
*1、人民调解新增代理人录入。

2019年1月12日17点07分
release-4.1
*1、新版工作人员首页。
*2、修复一些bug,优化用户体验。

release-4.2
*1、修复 bug，优化用户体验。

release-4.3
*1、新增社区矫正远程探视申请表。

release-4.4
*1、12348 视频咨询增加判断人员在线状态。

release-4.5
*1、首页添加蒙语显示

release-4.6
1、标题栏添加蒙语显示。
2、性能优化。

release-4.7
1、底部栏蒙语调整显示。
2、修复bug。

release-4.8
1、重大bug，更新时闪退。
2、修改了蒙语图片。

release-4.9
1、放大蒙语图片。

release—5.0
1、首页 12348 视频点击出来的入口更多了。
2、法律援助的承办机构审核节点
  快速申请的指定承办人节点
  人民调解的司法所所长指定人民调解员节点
   三个节点添加督办。

debug-5.1
1、永远在路上...

config.gradle 里面修改版本号
update-log.md 里面增加修改日志
NetConstant 检查是否是正式环境 ip port
后台上传和修改版本管理信息
测试更新下载