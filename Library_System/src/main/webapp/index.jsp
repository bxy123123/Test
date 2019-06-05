<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书馆管理系统</title>
<link rel="stylesheet" href="static/css/bootstrap.min.css">
	<link rel="stylesheet" href="static/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
    <link rel="stylesheet" href="static/css/bootstrap-admin-theme.css">
    <script src="static/js/bootstrap.min.js"></script>
    <script src="static/jQuery/jquery-3.1.1.min.js"></script>
    <script src="static/js/bootstrap-dropdown.min.js"></script>
    <script src="static/js/reader.js"></script>
    <script src="static/js/readerUpdateInfo.js"></script>
    <script src="static/js/readerUpdatePwd.js"></script>
    <script src="static/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="my_js/base.js"></script>
	<script type="text/javascript" src="my_js/cookie_util.js"></script>
	<script type="text/javascript" src="my_js/index.js"></script>
	<script type="text/javascript" src="my_js/page.js"></script>
	<script type="text/javascript" src="my_js/regular_expression"></script>
	<script type="text/javascript">
		$(function(){
			//显示登录的用户
			ShowName();
			//修改密码
			$("#modifypwd_btn").click(modifypwd);
			
			
			$("#bookname").click(clean1);
			$("#booktype").click(clean2);
			$("#bookautho").click(clean3);
			$("#queryBookName").click(clean4);
			$("#BookType").click(clean5);
			$("#queryAutho").click(clean6);
			
			
			//查询图书
			$("#btn_query").click(getDataBookByMessage);
			//借阅图书
			$("#book_tr").on("click","#borrowing",BorrowBook);
			//管理员查询图书
			$("#managebook_btn").click(getDataFindBookByMessage);
			//弹出添加图书框
			$("#btn_add").click(ShowAdd);
			//管理员添加图书
			$("#addBook_btn").click(AddBook);
			//管理员修改图书
			$("#modifyBook_btn").click(UpdateBook);
		});
		
	</script>
</head>
<body class="bootstrap-admin-with-small-navbar">
<nav class="navbar navbar-inverse navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="collapse navbar-collapse main-navbar-collapse">
                    <a class="navbar-brand" href="index.html"><strong>欢迎使用图书馆管理系统</strong></a>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" role="button" class="dropdown-toggle" data-hover="dropdown"> <i class="glyphicon glyphicon-user"></i>     欢迎您，<b id="cookiename"></b></i></a>
                            <ul class="dropdown-menu">
                                <!-- <li><a href="#updateinfo" data-toggle="modal">个人资料</a></li> -->
                                <li role="presentation" class="divider"></li>
                                <li><a href="#updatepwd" data-toggle="modal">修改密码</a></li>
                                <li role="presentation" class="divider"></li>
                                <li><a href="logout.do" id="logout">退出</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>
<div class="container">
    <!-- left, vertical navbar & content -->
    <div class="row">
        <!-- left, vertical navbar -->
        <div class="col-md-2 bootstrap-admin-col-left">
            <ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
            <!-- 用户 -->
            <shiro:hasRole name="user">
               <li class="user">
                   <a onclick="Show1()"><i class="glyphicon glyphicon-chevron-right"></i> 图书查询</a>
               </li>
            	<li class="user">
                 <a onclick="Show2()"><i class="glyphicon glyphicon-chevron-right"></i> 借阅信息</a>
                </li>
            </shiro:hasRole>
               <!-- 管理员功能  -->
               <shiro:hasRole name="admin">
             	<li class="admin">
                      <a onclick="Show3()"><i class="glyphicon glyphicon-chevron-right"></i> 图书管理</a>
                  </li>
               </shiro:hasRole>
            </ul>
        </div>

        <!-- 用户 -->
        <shiro:hasRole name="user">
        <div class="col-md-10 user" id="message_user">
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">图书查询</div>
                        </div>
                        <div class="bootstrap-admin-panel-content">
                            <ul>
                                <li>根据图书编号、图书名称查询图书信息</li>
                                <li>可查询图书的编号、名称、分类、作者、价格、在馆数量等</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">借阅信息</div>
                        </div>
                        <div class="bootstrap-admin-panel-content">
                            <ul>
                                <li>可查询除图书的基本信息、借阅日期、截止还书日期、超期天数等</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </shiro:hasRole>
        <!-- 管理员 -->
        <shiro:hasRole name="admin">
         <div class="col-md-10 admin" id="message_admin">
                <div class="row">
                    <div class="col-md-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <div class="text-muted bootstrap-admin-box-title">图书管理</div>
                            </div>
                            <div class="bootstrap-admin-panel-content">
                                <ul>
                                    <li>根据图书编号、图书名称查询图书基本信息</li>
                                    <li>添加、修改、删除图书</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
        </shiro:hasRole>
        
         <!-- 图书查询区 -->
        <div class="col-md-10" style="display:none" id="SelectBooks_div">
       <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default bootstrap-admin-no-table-panel">
                            <div class="panel-heading">
                                <div class="text-muted bootstrap-admin-box-title">查询</div>
                            </div>
                            <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                                <form class="form-horizontal" action="/books/selectServlet" method="post">
                                <input type="hidden" name="tip" value="2">
                                    <div class="col-lg-8 form-group">
                                        <label class="col-lg-4 control-label" for="query_bname">图书名称</label>
                                        <div class="col-lg-8">
                                            <input class="form-control" id="queryBookName" name="name" type="text" value="">
                                        <span id="queryBookName_span"></span>
                                        </div>
                                        
                                        <label class="col-lg-4 control-label" for="query_bname">图书类型</label>
                                       	<div class="col-sm-7">
												<select class="form-control opts" id="BookType" name="type">
                                           				 <option value="请选择">请选择</option>
                                           				 <option value="文学">文学</option>
                                           				 <option value="玄幻">玄幻</option>
                                           				 <option value="爱情">爱情</option>
                                      			  </select>
										<span id="queryBookType_span"></span>
										</div>
                                        
                                        <label class="col-lg-4 control-label" for="query_bname">图书作者</label>
                                        <div class="col-lg-8">
                                            <input class="form-control" id="queryAutho" name="name" type="text" value="">
                                        	<span id="queryAutho_spn"></span>
                                        </div>
                                    </div>
                                    
                                  
                                    <div class="col-lg-4 form-group">

                                        <button type="button" class="btn btn-primary" id="btn_query" >查询</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="row">
                    <div class="col-lg-12">
                        <table id="data_list" class="table table-hover table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>图书编号</th>
                                <th>图书类型</th>
                                <th>图书名称</th>
                                <th>作者</th>
                                 <th>出版社</th>
                                <th>总数量</th>
                                <th>操作</th>
                            </tr>
                            </thead>
							<tbody id="book_tr">
                      	  </tbody>
                        </table>
                    </div>
                    <!-- 分页 -->
                    <div id="page1" style="text-align: center;font-size: 17px">
                    	
                    </div>
			</div>
        </div>
        
            <!-- 借阅信息区 -->
        <div class="col-md-10" style="display:none" id="borrow_div">
           <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default bootstrap-admin-no-table-panel">
                            <div class="panel-heading">
                                <div class="text-muted bootstrap-admin-box-title">当前借阅信息</div>
                            </div>
                           
                        </div>
                    </div>
                </div>
    <div class="row">
                <div class="col-lg-12">
                  
                </div>
            </div>
                <div class="row">
                    <div class="col-lg-12">
                        <table id="data_list" class="table table-hover table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>图书编号</th>
	                            <th>图书名称</th>
	                            <th>借阅日期</th>
	                            <th>截止还书日期</th>
	                            <th>操作</th>
                            </tr>
                            </thead>
                              <tbody id="cz_tb">
	                         	                                  
                          	  </tbody>
                        </table>
                    </div>
                      <!--分页-->
       <div id="page2" style="text-align: center;font-size: 18px;">
      	</div>
         </div>
        </div>
        
          <!-- 图书管理区 -->
            <div class="col-md-10" style="display:none" id="managebooks_div">
              <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default bootstrap-admin-no-table-panel">
                            <div class="panel-heading">
                                <div class="text-muted bootstrap-admin-box-title">查询</div>
                            </div>
                            <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                                <form class="form-horizontal" action="/books/selectServlet" method="post">
                                <input type="hidden" name="tip" value="1">
                        			<div class="col-lg-7 form-group">
                                        <label class="col-lg-4 control-label" for="query_bname">图书名称</label>
                                        <div class="col-lg-8">
                                            <input class="form-control" id="bookname" name="name" type="text" value="">
                                        <span id="queryBookName_span"></span>
                                        </div>
                                        <label class="col-lg-4 control-label" for="query_bname">图书类型</label>
                                       	<div class="col-sm-7">
												<select class="form-control opts" id="booktype" name="type">
                                           				 <option value="请选择">请选择</option>
                                           				 <option value="文学">文学</option>
                                           				 <option value="玄幻">玄幻</option>
                                           				 <option value="爱情">爱情</option>
                                      			  </select>
										<span id="queryBookType_span"></span>
										</div>
                                        <label class="col-lg-4 control-label" for="query_bname">图书作者</label>
                                        <div class="col-lg-8">
                                            <input class="form-control" id="bookautho" name="name" type="text" value="">
                                        	<span id="queryAutho_spn"></span>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 form-group">
                                        <button type="button" class="btn btn-primary" id="managebook_btn">查询</button>
                                    </div>
                                    <div class="col-lg-3 form-group">
                                        <button type="button" class="btn btn-primary" id="btn_add" data-toggle="modal" data-target="#addModal">添加图书</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <table id="data_list" class="table table-hover table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>图书编号</th>
                                <th>图书类型</th>
                                <th>图书名称</th>
                                <th>作者名称</th>
                                 <th>出版社</th>
                                <th>总数量</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <!---在此插入信息-->
							<tbody id="managebook_tr">
                          	</tbody>
                        </table>
                    </div>
                </div>
                 <!-- 分页 -->
                    <div id="page3" style="text-align: center;font-size: 17px">
                    	
                    </div>
        </div>
        
         <!-- 读者管理区 -->
            <div class="col-md-10" style="display:none" id="managereader_div">
              <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default bootstrap-admin-no-table-panel">
                            <div class="panel-heading">
                                <div class="text-muted bootstrap-admin-box-title">读者管理</div>
                            </div>
                            <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                                <form class="form-horizontal" action="/books/selectServlet" method="post">
                        
                                    <div class="col-lg-3 form-group">

                                        <button type="button" class="btn btn-primary" id="btn_add" data-toggle="modal" data-target="#addModal">添加读者</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                <div class="row">
                    <div class="col-lg-12">
                        <table id="data_list" class="table table-hover table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>账号</th>
                                <th>邮箱</th>
                                <th>手机号</th>
                                <th>当前借阅数</th>
                                <th>历史借阅数</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <!---在此插入信息-->
								<tbody id="user_tb">
	                         	   	 
                          	  </tbody>
                        </table>
                    </div>
                </div>
                 <!--分页-->
       <div id="pages" style="text-align: center;font-size: 18px;"></div>
    </div>
    
      <!-- 图书借阅信息区 -->
            <div class="col-md-10" style="display:none" id="borrowbook_div">
              <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default bootstrap-admin-no-table-panel">
                            <div class="panel-heading">
                                <div class="text-muted bootstrap-admin-box-title">图书借阅信息</div>
                            </div>
                           
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-12">
                        <table id="data_list" class="table table-hover table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>图书号</th>
                                <th>图书名称</th>
                                <th>读者账号</th>
                                <th>借阅日期</th>
                                <th>截止还书日期</th>
                                <th>操作</th>
                                
                            </tr>
                            </thead>
                            <!---在此插入信息-->
                           	<tbody id="his_tb">
                                           
                        	</tbody>
                        </table>
                    </div>
                </div>
                <!--分页-->
       <div id="pages" style="text-align: center;font-size: 18px;">
      	</div>
        </div>
        
         <!-- 图书归还信息区 -->
            <div class="col-md-10" style="display:none" id="returnbook_div">
              <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default bootstrap-admin-no-table-panel">
                            <div class="panel-heading">
                                <div class="text-muted bootstrap-admin-box-title">图书归还信息</div>
                            </div>
                           
                        </div>
                    </div>
                </div>
                
                
                <div class="row">
                    <div class="col-lg-12">
                        <table id="data_list" class="table table-hover table-bordered" cellspacing="0" width="100%">
                            <thead>
                            <tr>
                                <th>图书号</th>
                                <th>图书名称</th>
                                <th>读者账号</th>
                                <th>借阅日期</th>
                                <th>还书日期</th>
                                
                            </tr>
                            </thead>
                            
                            
                            <!---在此插入信息-->
                            	<tbody id="his_tb">
                          	  </tbody>
                        </table>
                    </div>
                </div>
                <!--分页-->
       			<div id="pages" style="text-align: center;font-size: 18px;">
                </div>
        </div>
     </div>
</div>


     <!--------------------------------------添加的模糊框------------------------>  
                                 <form class="form-horizontal" method="post" action="" id="form_add">   <!--保证样式水平不混乱-->   
                                        <!-- 模态框（Modal） -->
									<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close closeAddBook" aria-hidden="true">
														&times;
													</button>
													<h4 class="modal-title" id="myModalLabel">
														添加新图书
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------表单-------------------->
										
										
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">图书名称</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="addBookName" required="required" name="name"  placeholder="请输入图书名称">
													<label class="control-label" for="addBookName" style="display: none;"></label>	
													<span id="addspn_1" style="color:red;font-size:12px"></span>
												</div>
										</div>
											
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">图书类型</label>
											<div class="col-sm-7">
												 <select class="form-control opts" id="addBookType" name="type">
                                           				 <option value="请选择">请选择</option>
                                           				 <option value="文学">文学</option>
                                           				 <option value="玄幻">玄幻</option>
                                           				 <option value="爱情">爱情</option>
                                      			  </select>
												<span id="addspn_2" style="color:red;font-size:12px"></span>
											</div>
										</div>
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">作者名称</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="addAutho" required="required" name="autho"  placeholder="请输入作者名称">
												<label class="control-label" for="addAutho" style="display: none;"></label>	
												<span id="addspn_3" style="color:red;font-size:12px"></span>
												</div>
										</div>
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">出版社</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="addPress" required="required" name="press"  placeholder="请输入出版社">
												<label class="control-label" for="addPress" style="display: none;"></label>	
												<span id="addspn_4" style="color:red;font-size:12px"></span>
												</div>
										</div>
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">总数量</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="addNum" required="required" name="num" placeholder="请输入图书总数量">
												<label class="control-label" for="addNum" style="display: none;"></label>	
												<span id="addspn_5" style="color:red;font-size:12px"></span>
												</div>
										</div>
										<!---------------------表单-------------------->
									</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default closeAddBook" onclick="closeadd()">关闭
													</button>
													<button type="button" id="addBook_btn" class="btn btn-primary" >
														添加
													</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>

                                 </form>	
 								
 								     <!-- 修改模态框（Modal） -->
                               <form class="form-horizontal" method="post" action="" id="form_update">   <!--保证样式水平不混乱-->   
									<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close closeUpdateBook"  aria-hidden="true">
														&times;
													</button>
													<h4 class="modal-title" id="updateModalLabel">
														修改图书信息
													</h4>
												</div>
												<div class="modal-body">
												
										<!---------------------表单-------------------->
										 <div class="form-group">
											<label for="firstname" class="col-sm-3 control-label">图书名称</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateBookName" name="name"  placeholder="请输入图书名称">
												<span id="modfspn_1" style="color:red,font-size:12px"></span>
												</div>
										</div>
											
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">图书类型</label>
											<div class="col-sm-7">
												<select class="form-control opts" id="updateBookType" name="type">
                                           				 <option value="请选择">请选择</option>
                                           				 <option value="文学">文学</option>
                                           				 <option value="玄幻">玄幻</option>
                                           				 <option value="爱情">爱情</option>
                                           				 
                                      			  </select>
											<span id="modfspn_2" style="color:red,font-size:12px"></span>
											</div>
										</div>
											
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">作者名称</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateAutho" name="autho" placeholder="请输入作者名称">
												<span id="modfspn_3" style="color:red,font-size:12px"></span>
												</div>
										</div>
										
										
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">出版社</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updatePress" name="press"  placeholder="请输入出版社">
												<span id="modfspn_4" style="color:red,font-size:12px"></span>
												</div>
										</div>		
										<div class="form-group">	
											<label for="firstname" class="col-sm-3 control-label">总数量</label>
												<div class="col-sm-7">
													<input type="text" class="form-control" id="updateNum" name="num"  placeholder="请输入总数量">
												<span id="modfspn_5" style="color:red,font-size:12px"></span>
												</div>
										</div>	
										</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-default closeUpdateBook" onclick="closeupdate()">关闭
													</button>
													<button type="button" id="modifyBook_btn" class="btn btn-primary" >
														修改
													</button>
												</div>
											</div><!-- /.modal-content -->
										</div><!-- /.modal -->
									</div>
	
                                 </form>
                                   <!------------------------------修改图书框-------------------------------->
 
 								
								<!------------------------------修改密码框-------------------------------->  
                   <form class="form-horizontal" method="post" action="/books/AdminServlet">   <!--保证样式水平不混乱-->                  
                                     <!-- 模态框（Modal） -->
				<div class="modal fade" id="updatepwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
									&times;
								</button>
								<h4 class="modal-title" id="myModalLabel">
									修改密码
								</h4>
							</div>
							<div class="modal-body">
							 
								<!--正文-->
								<input type="hidden" name="tip" value="1">
								<input type="hidden" name="url" value="index">
							<div class="form-group">
								<label for="firstname" class="col-sm-3 control-label">原密码</label>
								<div class="col-sm-7">
									<input type="password" class="form-control" name="password" id="oldPwd"  placeholder="请输入原密码">
									<!-- <label class="control-label" for="oldPwd" style="display: none"></label>				 -->
									<span id="oldPwd_span" style="color:red;font-size:12px"></span>
								</div>
							</div>	
							
							<div class="form-group">
								<label for="firstname" class="col-sm-3 control-label">新密码</label>
								<div class="col-sm-7">
									<input type="password" class="form-control" name="password2" id="newPwd"  placeholder="请输入新密码,密码长度大于6">
									<!-- <label class="control-label" for="newPwd" style="display: none"></label>			 -->
									<span id="newPwd_span" style="color:red;font-size:12px"></span>
								</div>
							</div>	
							<div class="form-group">
								<label for="firstname" class="col-sm-3 control-label">确认新密码</label>
								<div class="col-sm-7">
									<input type="password" class="form-control" name="password3" id="new2Pwd"  placeholder="请确定新密码">
									<!-- <label class="control-label" for="newPwd" style="display: none"></label>			 -->
									<span id="new2Pwd_span" style="color:red;font-size:12px"></span>
								</div>
							</div>	
							
								<!--正文--> 
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default" data-dismiss="modal">关闭
								</button>
								<button type="button" id="modifypwd_btn" class="btn btn-primary" >
									修改
								</button>
							</div>
						</div><!-- /.modal-content -->
					</div><!-- /.modal -->
				</div>
				</form>	

    <div class="modal fade" id="modal_info" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="infoModalLabel">提示</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12" id="div_info"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btn_info_close" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>