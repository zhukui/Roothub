/*var tab = "${tab}";
var url = "/?tab="+tab+"&"*/
$(function(){
  $("#shouye").addClass("active");
   /*  function session(){
      $.ajax({
        type:"get",
        url:"/session",
        async: false,
        cache: false,
        dataType:"json",
        success:function(data){
          console.log(JSON.stringify(data));
          if(data.success != null && data.success == true){
            $("#loginuser").show();
            $("#loginuser a").text(data.data.userName);
            $("#shezhili").show();
            $("#tuichuli").show();
            $("#session").append("<div class=\"panel-body\">\
              <div class=\"media\">\
                <div class=\"media-left\">\
                  <a href=\"/user/"+data.data.userName+"\">\
                    <img src=\"/resources/images/"+data.data.avatar+"\" title=\"\" class=\"avatar\">\
                  </a>\
                </div>\
                <div class=\"media-body\">\
                  <div class=\"media-heading\">\
                    <a href=\"/user/public\">"+data.data.userName+"</a>\
                    <div style=\"color: #7A7A7A; font-size: 12px; margin-top:5px;\">\
                      <i>"+data.data.signature+"</i>\
                    </div>\
                  </div>\
                </div>\
                <div style=\"margin-top: 15px;\">\
                  <a href=\"/topic/create\" style=\"text-decoration: underline\"><span class=\"glyphicon glyphicon-pencil\"></span><i>发布话题</i></a>\
                </div>\
              </div>\
            </div>\
            <div class=\"panel-footer\" style=\"background-color: white\">\
              <div class=\"row\">\
                <span class=\"col-md-6\"><a href=\"/notification/list\"><span id=\"n_count\">0</span> 条未读消息</a></span>\
                <span class=\"col-md-6 text-right\">声望：<a href=\"/top100\">0</a></span>\
              </div>\
            </div>");
          }
          if(data.success != null && data.success == false){
            $("#loginli").show();
            $("#zhuceli").show();
            $("#nologin").show();
          }
        },
        error:function(data){

        }
      });
    }; */
    
    function section(){
      if(tab == "good"){
        $("#section li:eq(1)").addClass("active");
      }else if(tab == "newest"){
        $("#section li:eq(2)").addClass("active");
      }else if(tab == "noReply"){
        $("#section li:eq(3)").addClass("active");
      }else{
        $("#section li:eq(0)").addClass("active");
      }
    };
    //session();
    section();
    /* $(".pagination2").pagination("${page.pageNumber}","${page.totalPage}",10);*/
    
    function changeSectionHref(){
    	$("#section li:eq(0) a").attr("href","/?tab=all&ptab="+ptab);
    	$("#section li:eq(1) a").attr("href","/?tab=good&ptab="+ptab);
    	$("#section li:eq(2) a").attr("href","/?tab=newest&ptab="+ptab);
    	$("#section li:eq(3) a").attr("href","/?tab=noReply&ptab="+ptab);
    }
    changeSectionHref();
    function changePtabClass(){
      if(ptab == "hot"){
       $("#ptab li:eq(1) a").addClass("tab_current");
     }else if(ptab == "pl"){
       $("#ptab li:eq(2) a").addClass("tab_current");
     }else if(ptab == "db"){
       $("#ptab li:eq(3) a").addClass("tab_current");
     }else if(ptab == "fe"){
       $("#ptab li:eq(4) a").addClass("tab_current");
     }else if(ptab == "play"){
       $("#ptab li:eq(5) a").addClass("tab_current");
     }else if(ptab == "creative"){
       $("#ptab li:eq(6) a").addClass("tab_current");
     }else if(ptab == "host"){
       $("#ptab li:eq(7) a").addClass("tab_current");
     }else if(ptab == "dn"){
       $("#ptab li:eq(8) a").addClass("tab_current");
     }else if(ptab == "blog"){
       $("#ptab li:eq(9) a").addClass("tab_current");
     }else if(ptab == "tea"){
       $("#ptab li:eq(10) a").addClass("tab_current");
     }else if(ptab == "news"){
       $("#ptab li:eq(11) a").addClass("tab_current");
     }else if(ptab == "qna"){
       $("#ptab li:eq(12) a").addClass("tab_current");
     }else{
       $("#ptab li:eq(0) a").addClass("tab_current");
     }
   };  
   changePtabClass();
   function showScroll(){
    $(window).scroll( function(){ 
     var scrollValue=$(window).scrollTop();
			//console.log(scrollValue);
			if (scrollValue > 200)
			{
				$('#back2Top').css("display","flex");
			} else {
				$('#back2Top').css("display","none");
			}
		});	
  }
  showScroll();
  $("#back2Top").click(function(){
		//缓慢效果回到顶部
		$('body,html').animate({scrollTop:0},500);
		return false;
		//直接回到顶部
		//window.scroll(0,0);
	});

  /*积分榜*/
  (function () {
    $.ajax({
     url:"/api/user/top100",
     type:"get",
     dataType:"json",
     data:{limit:10},
     success:function(data){
      if(data.success == true){
    	  $(".panel .top100").append('\
    			  <ol></ol>\
    	  ');
    	  for(var i = 0;i < data.data.length;i++){
    		  $(".panel .top100 ol").append('\
    				  <li>\
    				  <span class="top_score">'+data.data[i].score+'</span>\
    				  <span class="user_name"><a href="/user/'+data.data[i].userName+'">'+data.data[i].userName+'</a></span>\
    				  </li>\
    		  ');
    	  }
      }
     },
     error:function(data){
    	 
 	}
    });
  })();
});