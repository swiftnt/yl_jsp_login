1.如何安装？
使用的是javaBean，先将DAO.java中的package 改成自己的包名，编译后将编译生成的DAO.class放到自己项目的classes对应的包中，在需要登录注册的页面通过javaBean使用。
2.如何使用？
以我的举例，比如我的网站数据库名叫 yl
数据库用户名叫 root
数据库密码为 11111
用户表名叫 _User 
用户名列名 username
密码列名 password
我放到了com.yl这个包中：
<jsp:useBean id="dao" class="com.yl.DAO"></jsp:useBean>
<%
//getClassForName(String mysqlOrSqlserver)
//参数必须是"mysql" 或者 "sqlserver" 否则出错,我的是mysql
String classForName = dao.getClassForName("mysql");

//getUrl(String mysqlOrSqlserver, String db_name)
//第一个参数和上一个一样， 第二个参数填网站的数据库名,我的是yl
String url = dao.getUrl("mysql", "yl");


//判断用户名是否存在
//各个参数可以进java文件中看，还不会的可以问我
boolean isExist = dao.isExist(classForName, url, "_User", "root", "11111", "username", "yuan");
if (isExist==true){
	//已存在
}else{
	//该用户不存在
}

//登录并判断是否登录成功
boolean isLoginSuccessful = dao.isLoginSuccessful(classForName, url, "_User", "root", "11111", "username", "password", "yuanli", "123123");
if (isLoginSuccessful==true) {
	//登录成功
	session.setAttribute("username", "yuanli");
}else{
	//登录失败
	out.print("用户名或密码错误");
}

//注册并判断是否成功
int i = dao.Register(classForName, url, "_User", "root", "11111", "username", "password", "username", "password");
if (i > 0) {
	//成功
	out.print("注册成功");
}else{
	//失败
	out.print("注册失败");
}
%>
