function ShowName(){
			var uname=getCookie("uname");
			$("#cookiename").html(uname);
		}
		//修改密码
		function modifypwd(){
			//1.清空提示信息
			$("#oldPwd_span").html("");
			$("#newPwd_span").html("");
			$("#new2Pwd_span").html("");
			//清空输入框中的数据
			$("#oldPwd").html("");
			$("#newPwd").html("");
			$("#new2Pwd").html("");
			//2.把提示框隐藏起来
			$("#oldPwd_span").hide();
			$("#newPwd_span").hide();
			$("#new2Pwd_span").hide();
			//获取当前用户账号
			var uname=getCookie("uname");
			//获取用户输入的密码
			var oldpwd = $("#oldPwd").val().trim();
			var newpwd = $("#newPwd").val().trim();
			var new2pwd = $("#new2Pwd").val().trim();
			var ok = true;
			if(oldpwd==""){
				ok=false;
				$("#oldPwd_span").html("* 原密码不能为空");
				$("#oldPwd_span").show();
			}
			if(newpwd==""){
				ok=false;
				$("#newPwd_span").html("* 新密码不能为空");
				$("#newPwd_span").show();
			}else{
				if(newpwd.length<6){
					ok=false;
					$("#newPwd_span").html("* 密码长度需大于6位");
					$("#newPwd_span").show();
				}
			}
			if(new2pwd==""){
				ok=false;
				$("#old2Pwd_span").html("* 确认密码不能为空");
				$("#old2Pwd_span").show();
			}
			
			if(newpwd!=new2pwd && newpwd!="" && new2pwd!=""){
				ok=false;
				$("#new2Pwd_span").html("* 两次密码不一致");
				$("#new2Pwd_span").show();
			}
			if(oldpwd==newpwd && oldpwd!="" && newpwd!="" ){
				ok=false;
				$("#newPwd_span").html("* 原密码不能和新密码相同");
				$("#newPwd_span").show();
			}
			if(ok){
				$.ajax({
					url:path+"/Updatepwd.do",
					type:"post",
					dataType:"json",
					data:{"uname":uname,"oldpwd":oldpwd,"newpwd":newpwd},
					success:function(d){
						if(d.status==0){
							$("#oldPwd_span").html("* 原密码错误");
							$("#oldPwd_span").show();
						}else if(d.status==1){
							alert("修改密码成功");
							location.href="logout.do";
						}
					},
					error:function(){
						alert("修改失败");
					}
				});
			}
		}
		
		//显示图书查询区
		function Show1(){
			$("#SelectBooks_div").css('display', 'block');
			$("#message_user").css('display', 'none');
			$("#borrow_div").css('display', 'none');
			//显示所有图书
			getDataAllBooks();
		}
		
		//显示借阅信息区
		function Show2(){
			$("#borrow_div").css('display', 'block');
			$("#message_user").css('display', 'none');
			$("#SelectBooks_div").css('display', 'none');
			//显示该用户的借阅信息
			getDataBorrow();
		}
		
		//显示图书管理区
		function Show3(){
			$("#managebooks_div").css('display', 'block');
			$("#message_admin").css('display', 'none');
			$("#borrowbook_div").css('display', 'none');
			$("#managereader_div").css('display', 'none');
			$("#returnbook_div").css('display', 'none');
			getDataManageAllBooks();
		}
		
		function clean1(){
			$("#booktype").val("请选择");
			$("#bookautho").val("");
		}
		function clean2(){
			$("#bookname").val("");
			$("#bookautho").val("");
		}
		function clean3(){
			$("#booktype").val("请选择");
			$("#bookname").val("");
		}
		function clean4(){
			$("#BookType").val("请选择");
			$("#queryAutho").val("");
		}
		function clean5(){
			$("#queryBookName").val("");
			$("#queryAutho").val("");
		}
		function clean6(){
			$("#BookType").val("请选择");
			$("#queryBookName").val("");
		}
		
		
		//显示所有图书
		function getDataAllBooks(curPage){
			$.ajax({
				url:path+"/ShowAllBooks.do",//请求路径
				type:"post",//请求方式
				dataType:"json",//数据格式
				data:{"curPage":curPage},
				success:function(d){
					if(d.status==0){
						var total = d.totalCount;//总条数
						var totalPage = d.totalPage;//总页数
						var curPage = d.currentPage;//当前页
						var books = d.data;
						//清空显示的数据
						$("#book_tr").empty();
						for(var i=0;i<books.length;i++){
							var trs = "";
								trs +="<tr>";
								trs +="	<td>"+books[i].book_id+"</td>";
								trs +="	<td>"+books[i].book_type+"</td>";
								trs +="	<td>"+books[i].book_name+"</td>";
								trs +="	<td>"+books[i].writer_name+"</td>";
								trs +="	<td>"+books[i].press+"</td>";
								trs +="	<td>"+books[i].counts+"</td>";
								trs +="	<td><a type='button' id='borrowing' class='btn btn-info btn-xs' data-toggle='modal'>借阅</a></td>";
								trs +="</tr>";
							var $tr = $(trs);
							$tr.data("bookId",books[i].book_id);
							$("#book_tr").append($tr);
						}
						var a="AllBooks";
						pages(a,curPage,totalPage,total,1);
					}
				},
				error:function(){
					alert("查询失败");
				}	
			});
		}
		
		
		//根据信息查询图书
		function getDataBookByMessage(curPage){
		   var name = $("#queryBookName").val().trim();
		   var booktype = $("#BookType").val();
		   var autho = $("#queryAutho").val().trim();
		   //如果所有都为空,那就不发送ajax查询
		   if(name=="" && booktype=="请选择" && autho==""){
			   return;
		   }else{
				$.ajax({
					url:path+"/SelectBookByMessage.do",//请求路径
					type:"post",//请求方式
					dataType:"json",//数据格式
					data:{"bookname":name,"booktype":booktype,"autho":autho,"curPage":curPage},
					success:function(d){
						if(d.status==1){
							alert(d.msg);
						}
						if(d.status==0){
							var total = d.totalCount;//总条数
							var totalPage = d.totalPage;//总页数
							var curPage = d.currentPage;//当前页
							var books = d.data;
							$("#book_tr").empty();
							for(var i=0;i<books.length;i++){
								var trs = "";
									trs +="<tr>";
									trs +="	<td>"+books[i].book_id+"</td>";
									trs +="	<td>"+books[i].book_type+"</td>";
									trs +="	<td>"+books[i].book_name+"</td>";
									trs +="	<td>"+books[i].writer_name+"</td>";
									trs +="	<td>"+books[i].press+"</td>";
									trs +="	<td>"+books[i].counts+"</td>";
									trs +="	<td><a type='button' id='borrowing' class='btn btn-info btn-xs' data-toggle='modal'>借阅</a></td>";
									trs +="</tr>";
								var $tr = $(trs);
								$tr.data("bookId",books[i].book_id);
								$("#book_tr").append($tr);
							}
							var a="BookByMessage";
							pages(a,curPage,totalPage,total,1);
						}
					},
					error:function(){
						alert("查询失败");
					}
				});
		   }
		}
		
		//借阅
		function BorrowBook(){
			var flag=confirm("确认要借阅吗？");
			if(flag){
			//获取当前用户账号
			var username = getCookie("uname");
			//获取图书id
			var bookId = $(this).parent().parent().data("bookId");
			$.ajax({
				url:path+"/BorrowBook.do",//请求路径
				type:"post",//请求方式
				dataType:"json",//数据格式
				data:{"bookId":bookId,"username":username},
				success:function(d){
					if(d.status==0){
						alert("借阅成功");
						getDataAllBooks();
					}
					if(d.status==1){
						alert("没有库存,无法借阅");
					}
				},
				error:function(){
					alert("借阅失败");
				}
			});
		}
		}
		//加载借阅记录
		function getDataBorrow(curPage){
			var username = getCookie("uname");
			$.ajax({
				url:path+"/ShowBorrowMessage.do",//请求路径
				type:"post",//请求方式
				dataType:"json",//数据格式
				data:{"username":username,"curPage":curPage},
				success:function(d){
					if(d.status==0){
						var totalCount = d.totalCount;//总条数
						var totalPage = d.totalPage;//总页数
						var currentPage = d.currentPage;//当前页
						$("#cz_tb").empty();
						var borrow=d.data;
						for(var i=0;i<borrow.length;i++){
							var tds = "";
							tds+="<tr>";
							tds+="<td>"+borrow[i].book_id+"</td>";
							tds+="<td>"+borrow[i].book_name+"</td>";
							tds+="<td>"+borrow[i].borrow_datetime+"</td>";
							tds+="<td>"+borrow[i].borrow_endtime+"</td>";
							tds+="<td>";
							if(borrow[i].borrow_status==0){
								tds+="<button type='button' class='btn btn-info btn-xs' data-toggle='modal' onclick='backbook("+borrow[i].book_id+")'>还书</button>";
							}
							if(borrow[i].borrow_status==1){
								tds+="<button type='button' class='btn btn-info btn-xs' data-toggle='modal'>已还</button>";
							}
							tds+="</td>";
							tds+="</tr>";
							var $td = $(tds);
							$("#cz_tb").append($td);
						}
						//调用分页条
						var a="Borrow";
						pages(a,currentPage,totalPage,totalCount,2);
					}
				},
				error:function(){
					alert("加载借阅记录失败");
				}
			});
		}
		
		/**
		 * 还书
		 * @param bookId
		 */
		function backbook(bookId){
			var flag=confirm("确认要还吗？");
			if(flag){
				$.ajax({
					url:path+"/BackBook.do",//请求路径
					type:"post",//请求方式
					dataType:"json",//数据格式
					data:{"bookId":bookId},
					success:function(d){
						if(d.status==0){
							alert("还书成功");
							getDataBorrow();
						}
					},
					error:function(){
						alert("还书失败");
					}
				});
			}
		}
		
		//图书管理(显示所有图书)
		function getDataManageAllBooks(curPage){
			$.ajax({
				url:path+"/ShowAllBooks.do",//请求路径
				type:"post",//请求方式
				dataType:"json",//数据格式
				data:{"curPage":curPage},
				success:function(d){
					if(d.status==0){
						var totalCount = d.totalCount;//总条数
						var totalPage = d.totalPage;//总页数
						var currentPage = d.currentPage;//当前页
						var books = d.data;
						//清空显示的数据
						$("#managebook_tr").empty();
						for(var i=0;i<books.length;i++){
							var trs = "";
								trs +="<tr>";
								trs +="	<td>"+books[i].book_id+"</td>";
								trs +="	<td>"+books[i].book_type+"</td>";
								trs +="	<td>"+books[i].book_name+"</td>";
								trs +="	<td>"+books[i].writer_name+"</td>";
								trs +="	<td>"+books[i].press+"</td>";
								trs +="	<td>"+books[i].counts+"</td>";
								trs +=" <td>";
								trs +=" <button type='button' class='btn btn-warning btn-xs' data-toggle='modal' data-target='#updateModal' id='btn_update' onclick='showBookById("+books[i].book_id+")'>修改</button>";
								trs +=" <button type='button' class='btn btn-danger btn-xs' onclick='deletebook("+books[i].book_id+")'>删除</button>";
								trs +=" </td>";
								trs +="</tr>";
							var $tr = $(trs);
							$tr.data("bookId",books[i].book_id);
							$("#managebook_tr").append($tr);
						}
						var a="ManageAllBooks";
						pages(a,currentPage,totalPage,totalCount,3);
					}
				},
				error:function(){
					alert("加载失败");
				}	
			});
		}
		
		//管理员根据信息查询图书
		function getDataFindBookByMessage(curPage){
		   var name = $("#bookname").val().trim();
		   var booktype = $("#booktype").val();
		   var autho = $("#bookautho").val().trim();
		   //如果所有都为空,那就不发送ajax查询
		   if(name=="" && booktype=="请选择" && autho==""){
			   return;
		   }else{
				$.ajax({
					url:path+"/SelectBookByMessage.do",//请求路径
					type:"post",//请求方式
					dataType:"json",//数据格式
					data:{"bookname":name,"booktype":booktype,"autho":autho,"curPage":curPage},
					success:function(d){
						if(d.status==1){
							alert(d.msg);
						}
						if(d.status==0){
							var total = d.totalCount;//总条数
							var totalPage = d.totalPage;//总页数
							var curPage = d.currentPage;//当前页
							var books = d.data;
							$("#managebook_tr").empty();
							for(var i=0;i<books.length;i++){
								var trs = "";
									trs +="<tr>";
									trs +="	<td>"+books[i].book_id+"</td>";
									trs +="	<td>"+books[i].book_type+"</td>";
									trs +="	<td>"+books[i].book_name+"</td>";
									trs +="	<td>"+books[i].writer_name+"</td>";
									trs +="	<td>"+books[i].press+"</td>";
									trs +="	<td>"+books[i].counts+"</td>";
									trs +=" <td>";
									trs +=" <button type='button' class='btn btn-warning btn-xs' data-toggle='modal' data-target='#updateModal' id='btn_update' onclick='showBookById("+books[i].book_id+")'>修改</button>";
									trs +=" <button type='button' class='btn btn-danger btn-xs' onclick='deletebook("+books[i].book_id+")'>删除</button>";
									trs +=" </td>";
									trs +="</tr>";
								var $tr = $(trs);
								$tr.data("bookId",books[i].book_id);
								$("#managebook_tr").append($tr);
							}
							var a="FindBookByMessage";
							pages(a,curPage,totalPage,total,3);
						}
					},
					error:function(){
						alert("查询失败");
					}
				});
		   }
		}
		//弹出添加图书框
		function ShowAdd(){
			$("#form_add").show();
			$(".modal-backdrop").show();
		}
		//关闭添加图书框
		function closeadd(){
			$("#form_add").hide();
			$(".modal-backdrop").hide();
		}
		
		//关闭修改图书框
		function closeupdate(){
			$("#form_update").hide();
			$(".modal-backdrop").hide();
		}
		
		
		//添加图书
		function AddBook(){
			//清空提示信息
			$("#addspn_1").html("");
			$("#addspn_2").html("");
			$("#addspn_3").html("");
			$("#addspn_4").html("");
			$("#addspn_5").html("");
			//获取数据
			var name = $("#addBookName").val().trim();
			var type = $("#addBookType").val().trim();
		    var autho = $("#addAutho").val().trim();
			var press = $("#addPress").val().trim();
			var counts = $("#addNum").val().trim();
			var ok = true;
			if(name==""){
				ok=false;
				$("#addspn_1").html("图书名不能为空");
			}
			if(type=="请选择"){
				ok=false;
				$("#addspn_2").html("请选着图书类型");
			}
			if(autho==""){
				ok=false;
				$("#addspn_3").html("作者不能为空");
			}
			if(press==""){
				ok=false;
				$("#addspn_4").html("出版社不能为空");
			}
			if(counts==""){
				ok=false;
				$("#addspn_5").html("库存不能为空");
			}else{
				var regnumber=/^\d+$/;
				if(!regnumber.test(counts)){
					var ok = false;
					$("#addspn_5").html("请输入有效数字");
				}
			}
			if(ok){
				$.ajax({
					url:path+"/AddBook.do",//请求路径
					type:"post",//请求方式
					dataType:"json",//数据格式
					data:{"name":name,"type":type,"autho":autho,"press":press,"counts":counts},
					success:function(d){
						if(d.status==0){
							alert("添加成功");
							//隐藏添加图书框
							$("#form_add").hide();
							$(".modal-backdrop").hide();
							getDataManageAllBooks();
						}
					},
					error:function(){
						alert("网络异常");
					}
				});
			}
		}
		
		
		//修改图书(数据的回显)
		function showBookById(bookId){
			//弹出修改榆树框
			$("#form_update").show();
			$(".modal-backdrop").show();
			//清空输入框内容
			$("#updateBookName").html("");
			$("#updateAutho").html("");
			$("#updatePress").html("");
			$("#updateNum").html("");
			$.ajax({
				url:path+"/showBookById.do",//请求路径
				type:"post",//请求方式
				dataType:"json",//数据格式
				data:{"bookId":bookId},
				success:function(d){
					if(d.status==0){
						var book = d.data;
						$("#updateBookName").val(book.book_name);
						$("#updateBookType").val(book.book_type);
						$("#updateAutho").val(book.writer_name);
						$("#updatePress").val(book.press);
						$("#updateNum").val(book.counts);
						$("#modifyBook_btn").data("bookid",book.book_id);
					}
				},
				error:function(){
					alert("网络异常");
				}
			});
		}
		
		//修改图书
		function UpdateBook(){
			//清空提示信息
			$("#modfspn_1").html("");
			$("#modfspn_2").html("");
			$("#modfspn_3").html("");
			$("#modfspn_4").html("");
			$("#modfspn_5").html("");
			//获取数据
			var name = $("#updateBookName").val().trim();
			var type = $("#updateBookType").val().trim();
		    var autho = $("#updateAutho").val().trim();
			var press = $("#updatePress").val().trim();
			var counts = $("#updateNum").val().trim();
			var bookId=$("#modifyBook_btn").data("bookid");
			console.log(bookId);
			var ok = true;
			if(name==""){
				ok=false;
				$("#modfspn_1").html("图书名不能为空");
			}
			if(type=="请选择"){
				ok=false;
				$("#modfspn_2").html("请选着图书类型");
			}
			if(autho==""){
				ok=false;
				$("#modfspn_3").html("作者不能为空");
			}
			if(press==""){
				ok=false;
				$("#modfspn_4").html("出版社不能为空");
			}
			if(counts==""){
				ok=false;
				$("#modfspn_5").html("库存不能为空");
			}else{
				var regnumber=/^\d+$/;
				if(!regnumber.test(counts)){
					var ok = false;
					$("#modfspn_5").html("请输入有效数字");
				}
			}
			if(ok){
				$.ajax({
					url:path+"/UpdateBook.do",//请求路径
					type:"post",//请求方式
					dataType:"json",//数据格式
					data:{"bookId":bookId,"name":name,"type":type,"autho":autho,"press":press,"counts":counts},
					success:function(d){
						if(d.status==0){
							alert("修改成功");
							//隐藏修改图书框
							$("#form_update").hide();
							$(".modal-backdrop").hide();
							getDataManageAllBooks();
						}
					},
					error:function(){
						alert("网络异常");
					}
				});
			}
		}
		
		//删除图书根据Id
		function deletebook(bookId){
			var flag=confirm("确认要删除吗？");
			if(flag){
				$.ajax({
					url:path+"/DeleteBook.do",//请求路径
					type:"post",//请求方式
					dataType:"json",//数据格式
					data:{"bookId":bookId},
					success:function(d){
						if(d.status==0){
							alert("删除成功");
							getDataManageAllBooks();
						}
					},
					error:function(){
						alert("网络异常");
					}
				});
			}
		}
		