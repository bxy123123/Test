//分页条
	function pages(a,curPage,totalPage,total,type){
		if(curPage > totalPage){
			curPage = totalPage;
		}
		var pageStr ="";
		//如果不是第一页
		if(curPage>1){
			pageStr += "<span><a href='javascript:getData"+a+"(1)' rel='1'>首页</a></span><span><a href='javascript:getData"+a+"("+(curPage - 1)+")' rel='"+ (curPage - 1) + "'>上一页</a></span>";
		}
		//有多少页显示多少页
		for (var i = 1; i<=totalPage; i++) {
			//当前页加粗 
			if(curPage==i){
				pageStr += "<span><a href='javascript:getData"+a+"("+i+")' checked><b>["+i+"]</b></a></span>";
			}else{
				pageStr += "<span><a href='javascript:getData"+a+"("+i+")' checked>["+i+"]</a></span>";
			}
		}
		if(curPage != totalPage){
			pageStr +="<span><a href='javascript:getData"+a+"("+((curPage) + 1)+")' rel='"
			+ (parseInt(curPage) + 1)
			+ "'>下一页</a></span><span><a href='javascript:getData"+a+"("+totalPage+")' rel='"
			+ totalPage + "'>尾页</a></span>";
		}
		pageStr += "<p></p><span>共" + total + "条</span><span>" + curPage + "/"
		+ totalPage + "</span>";
		if(type==1){
			$("#page1").html(pageStr);
		}
		if(type==2){
			$("#page2").html(pageStr);
		}
		if(type==3){
			$("#page3").html(pageStr);
		}
	}