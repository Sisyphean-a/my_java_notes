## Linux基础

### 目录结构

| 目录名 称 | 功能介绍                                                     |
| --------- | ------------------------------------------------------------ |
| /bin      | binarie:存放二进制可执行文件 (例如tomcat的bin目录)           |
| /sbin     | super binaries:存放二进制文件,只有root用户才能访问           |
| /boot     | 系统启动核心目录,存放系统启动程序文件，例:查看启动文件的大小 |
| /dev      | devices:用于存放硬件设备文件                                 |
| /etc      | etcetera:存放系统配置文件(字体文件等)                        |
| /home     | 家目录,每一个用户都有一个"家"，用户的起始目录,创建用户跟随创建相应的家目录 |
| /lib      | library:系统资源文件类库                                     |
| /proc     | 内存映射目录,可以查看系统相关硬件的信息                      |
| /var      | variable:可变的,变量. 部署项目用的                           |
| /tmp      | temporary:用来存放临时文件                                   |
| /root     | 超级用户root的家目录                                         |
| /usr      | unix shared resouce: 存放unix共享系统资源，类似于"C:/ Program Files",用于安装各种软件 |

### 常用命令

1. 查看目录下文件与目录

   ```sh
   ls		 # 列出目录所有文件
   ls -a 	 # 列出目录所有文件，包括隐藏文件
   ls -l 	 # 除了文件名，还列出文件的权限、所有者、文件大小等信息
   ```

2. 目录切换

   ```sh
   cd /	 # 进入主目录
   cd ~	 # 进入"home"目录
   cd .. 	 # 进入上一级工作路径
   ```

3. 查看完整路径

   ```sh
   pwd 	 # 查看当前路径
   pwd -P	 # 查看软链接的实际路径
   ```

4. 查看当前用户

   ```sh
   whoami   # 查看当前用户的名称
   ```

5. 用户切换

   ```sh
   su       # 普通用户切换到root用户
   su root  # 切换到root用户
   su 用户名 # 切换到普通用户
   exit     # 退回到原本用户
   ```

   > 使用su后应该使用exit，而不是继续使用su，避免造成用户的“叠加”

6. 管理用户

   ```sh
   useradd xxx # 创建用户
   passwd xxx  # 为指定的用户设置密码
   userdel xxx # 删除用户
   ```

7. 查看命令的所在目录

   ```sh
   which      # 查看指定命令所在的路径信息
   ```

8. 文件操作

   ```sh
   # touch创建文件
   touch t.conf			# 当前目录创建名为t.conf的文件
   
   # 回显内容
   echo xxx > fileName     # 把xxx覆写到文件中，若文件不存在则创建
   echo xxx >> fileName    # 把内容追加到文件中
   
   # 输出内容到终端
   cat fileName 			# 一次性全部输出
   more fileName			# 分屏查看文件各行内容，不支持回看
   less fileName			# 支持会看
   head -n fileName		# 输出前n行内容
   tail -n fileName		# 输出后n行内容
   wc fileName				# 查看文件的行数
   ```

9. 目录操作

   ```sh
   # mkdir创建文件夹
   mkdir t 				# 当前目录下创建名为t的文件夹
   mkdir -p /tmp/text/t1	# 在tmp目录下创建路径为text/t的目录，若不存在，则创建
   
   # 移动文件与重命名
   mv a.log a1.txt				# 重命名a.log
   mv -i a.txt a1.txt 			# 重命名文件，如果文件已存在，询问是否覆盖
   mv a1.txt a2.txt /test3 	# 将两个文件移动到test3目录中
   mv * ../					# 移动当前文件夹的所有文件到上一级目录
   
   # 拷贝目录或文件
   cp a.txt test 			# 复制a.txt到text目录下，如果原文件存在提示是否覆盖
   cp -r dir1 dir2     	# 使用递归拷贝目录，无视目录层次
   cp -s a.txt link_a.txt 	# 为a.txt创建一个链接（快捷方式） 
   
   # 删除目录
   rm -i *.log 	# 删除任何.log文件，删除前逐一询问确认
   rm -r dir 		# 递归删除目录，无视目录层次
   rm -rf text	 	# 递归强制删除text子目录的所有档案，不用一一确认
   rm -- -f* 		# 删除以-f开头的文件
   ```

10. 查找操作

    ```sh
    which	# 查看可执行文件的位置，显示的是PATH中的命令
    whereis	# 查看文件的位置，只能用于程序名的查找，仅搜索二进制，man，源代码文件
    locate	# 配合数据库查看文件位置
    find	# 实际搜寻硬盘查询文件名称
    grep xxx fileName # 从指定文件中查找指定的内容
    ```

11. 权限管理

    ```sh
    chmod 权限 文件/目录  # 管理指定文件的权限信息，r读:4 w写:2 x执行:1
    ```

12. 进程管理

    ```sh
    ps			  # 查看当前终端窗口里的进程
    ps -aux 	  # 查看系统中的所有进程
    ps -ef		  # 查看进程列表
    kill 进程号  	# 杀死指定的进程
    kill -9 进程号	# 强制杀死指定的进程
    ```

13. 其他命令

    ```sh
    # 查看地址
    ifconfig 	# 查看当前系统的IP地址等信息
    
    # 压缩与解压
    tar zcvf 压缩文件名.tar.gz dir1 dir2 ... 	# 实现一组文件或文件夹的打包
    tar zxvf 压缩文件名.tar.gz			# 实现解压缩解包
    ```

    













