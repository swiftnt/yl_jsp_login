1.如何安装？</br>
使用的是javaBean，先将DAO.java中的package 改成自己的包名，编译后将编译生成的DAO.class放到自己项目的classes对应的包中，在需要登录注册的页面通过javaBean使用。</br>
2.如何使用？</br>
以我的举例，比如我的网站数据库名叫 yl</br>
数据库用户名叫 root</br>
数据库密码为 11111</br>
用户表名叫 _User </br>
用户名列名 username</br>
密码列名 password</br>
我放到了com.yl这个包中：</br>
<jsp:useBean id="dao" class="com.yl.DAO"></jsp:useBean></br>
<%</br>
//getClassForName(String mysqlOrSqlserver)</br>
//参数必须是"mysql" 或者 "sqlserver" 否则出错,我的是mysql</br>
String classForName = dao.getClassForName("mysql");</br>
</br>
//getUrl(String mysqlOrSqlserver, String db_name)</br>
//第一个参数和上一个一样， 第二个参数填网站的数据库名,我的是yl</br>
String url = dao.getUrl("mysql", "yl");</br>
</br>
</br>
//判断用户名是否存在</br>
//各个参数可以进java文件中看，还不会的可以问我</br>
boolean isExist = dao.isExist(classForName, url, "_User", "root", "11111", "username", "yuan");</br>
if (isExist==true){</br>
	//已存在</br>
}else{</br>
	//该用户不存在</br>
}</br>
</br>
//登录并判断是否登录成功</br>
boolean isLoginSuccessful = dao.isLoginSuccessful(classForName, url, "_User", "root", "11111", "username", "password", "yuanli", "123123");</br>
if (isLoginSuccessful==true) {</br>
	//登录成功</br>
	session.setAttribute("username", "yuanli");</br>
}else{</br>
	//登录失败</br>
	out.print("用户名或密码错误");</br>
}</br>
</br>
//注册并判断是否成功</br>
int i = dao.Register(classForName, url, "_User", "root", "11111", "username", "password", "username", "password");</br>
if (i > 0) {</br>
	//成功</br>
	out.print("注册成功");</br>
}else{</br>
	//失败</br>
	out.print("注册失败");</br>
}</br>
%></br>
