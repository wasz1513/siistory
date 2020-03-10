$(function(){
		var origin = location.origin;
		var context = "siistory";
	
	//신고 버튼 클릭시 새창 이벤트(warning 페이지 )
	$(document).on("click",".warningadd", function(event){
		
		var popupX=(window.screen.width)/2-(200/2);
		var popupY=(window.screen.height) /2-(300/2);  
 		
		var target_no = $(this).data(target_no).target_no;
		var pusher_no =$(this).data(pusher_no).pusher_no;
		var board_no = $(this).data(board_no).board_no;
		var board_writer=$(this).data(board_writer).board_writer;
		
		console.log = (target_no);
		console.log = (pusher_no);
		console.log = (board_no);
		console.log = (board_writer);
	
		window.open(origin+"/"+context+"/member/warning?target_no="+target_no+"&pusher_no="+pusher_no+"&board_no="+board_no+"&board_writer="+board_writer+"",
 				 	"신고 페이지",
 				 	"width=300, height=450, toolbar=no, menubar=no,scrollbars=yes, resizable=no, location=no, left="+popupX+",top="+popupY
		); 
		
	});
	
	
	
	
	$(".replycontent").on("input", function() {
		if($(this).val() != ''){
			console.log($(this).val().length)
			$(this).parents(".mb-3").find(".submit").prop("disabled", false)
		} else {
			$(this).parents(".mb-3").find(".submit").prop("disabled", true)
		}
	});
	
	// 댓글 쓰기 + 답글 쓰기 누르면 submit 전 정보 세팅 이벤트
	$(document).on("click", ".replyadd", function(){
		var writer = "@"+$(this).parents(".r").data("writer")+" ";
		$(this).parents(".mb-3").find(".replycontent").val(writer).focus();
		var replyseq = $(this).parents(".r").data("seq");
		$(this).parents(".mb-3").find(".replycontent").data("replyseq", replyseq);
		
		console.log(location.host)
	});
	
	// 댓글 입력 전송 이벤트
	$(".submit").click(function(e){
		e.preventDefault();
		var content = $(this).parent().prev(".replycontent").val();
		var boardseq = $(this).parents(".mb-3").find(".card-body").data("seq");
		var replyseq = $(this).parent().prev(".replycontent").data("replyseq");
		
		$.ajax({
			url:"post/replyinsert",
			type:"post",
			data:{'board_no':boardseq, 'super_no':replyseq, 'reply_content':content},
			dataType:"JSON",
			context:this,
			success:function(data){
				if(data.depth == 0){
					$(this).parents(".mb-3").find(".card-body").after(replyadd(data));
				} else {
					$(this).parents(".mb-3").find("#"+data.super_no).append(commentadd(data));
					$(this).parents(".mb-3").find(".replycontent").data("replyseq", 0);
				}
				$(this).parent().prev(".replycontent").val("");
			}
		});
		
	});
	
	// 댓글 더보기 버튼 (10개씩 불러옴)
	$(".replymore").click(function(){
		
		var board_no = $(this).parents(".mb-3").find(".card-body").data("seq");
		var count = $(this).data("click");

		$.ajax({
			url:"post/morereply",
			type:"get",
			data:{'board_no':board_no, 'start':(count*10)+1, 'end':(count*10)+10},
			context:this,
			success:function(data){
				for(var i in data){
					var a = data[i];
					$(this).parents(".mb-3").find(".card-body").after(replyadd(a));
					$(this).data("click", count+1);
				}
			}
		});
	});

	// 답글(comment) 더보기 버튼 이벤트
	$(document).on("click", ".commentview", function(){
		var super_no = $(this).parent(".media-body").attr("id");
		var board_no = $(this).parents(".mb-3").find(".card-body").data("seq");
		
		$.ajax({
			url:"post/commentview?super_no="+super_no+"&board_no="+board_no,
			type:"get",
			context:this,
			success:function(data){
				for(var i in data){
					var a = data[i];
					$(this).parents(".mb-3").find("#"+a.super_no).append(commentadd(a));
				}
			}
		});
	});
	
	$(".following").click(function(){
		var url = "util/follow";
		var method = "post";
		
		var member_no = $(this).prev(".warningadd").data("pusher_no");
		var friend_no = $(this).prev(".warningadd").data("target_no");
		
		$.ajax({
			url : url,
			type : method,
			data : {'member_no':member_no, 'friend_no':friend_no, 'following':0},
			context: this,
			success:function(resp){
				console.log("성공");
				$("."+friend_no).hide();
			}
		});			
	});
});
	
	
// 댓글 추가하면 jsp에 댓글 생성해주는 태그 함수
function replyadd(data){
	var html = '';
	html += '<ul class="list-group list-group-flush r" data-seq="'+data.reply_no+'" data-writer="'+data.reply_writer+'">';
	html += '<li class="list-group-item rr">';
	html += '<div class="media p-3">';
	html += '<img src="util/download?member_no='+data.writer_no+'" class="mr-3 mt-3 rounded-circle" style="width: 30px;">';
	html += '<div class="media-body" id="'+data.reply_no+'">';
	html += '<h4>'+data.reply_writer+' <small><i>Posted on ?? days ago</i></small></h4>';
	html += '<p>'+data.reply_content+'</p>';
	html += '<button class="btn">좋아요 ??개</button>';
	html += '<button class="btn replyadd">답글달기</button>';
	html += '<button class="btn commentview">답글 보기(??개)</button>';
	html += '</div>';
	html += '</div>';
	html += '</li>';
	html += '</ul>';
	
	return $.parseHTML(html);
};

// 답글 추가하면 jsp에 답글 생성해주는 태그 함수
function commentadd(data){
	var html = "";
	html += '<div class="media p-3">';
	html += '<img src="util/download?member_no='+data.writer_no+'" class="mr-3 mt-3 rounded-circle" style="width: 30px;">';
	html += '<div class="media-body" id="'+data.reply_no+'">';
	html += '<h4>'+data.reply_writer+' <small><i>Posted on ?? days ago</i></small></h4>';
	html += '<p>'+data.reply_content+'</p>';
	html += '<button class="btn">좋아요 ??개</button>';
	html += '<button class="btn replyadd">답글달기</button>';
	html += '</div>';
	html += '</div>';
	
	return $.parseHTML(html);
};

