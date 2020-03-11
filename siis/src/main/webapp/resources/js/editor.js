$(function() {
	$(document).on("input", ".note-editable", function() {
		if($(".note-editable").text() != ''){
			$(".upload").prop("disabled", false)
		} else {
			$(".upload").prop("disabled", true)
		}
	});

	$("#input_imgs").hide();

	// 에디터
	$('#summernote').summernote({
		height : 100,
		minHeight : null,
		maxHeight : null,
		focus : true,
		toolbar : []
	});

	// 이미지 업로드 관련
	$("#input_imgs").on("change", handleImgFileSelect);

	// textarea 전송
	$(".upload").click(function(e) {
		var contentVo = {
			'board_content' : $("#summernote").val(),
			'board_pic_no' : $("#summernote").data("boardpicno"),
			'board_state' : $(".open").val()
		};

		$.ajax({
			url : 'post/addcontent',
			type : 'post',
			data : JSON.stringify(contentVo),
			contentType : "application/json",
			context : this,
			success : function(data) {
				$('#summernote').summernote('reset');
				sel_files = [];
				$(".imgs_wrap").empty();
				$(this).parents(".scroll-div").find(".editor").after(addcontent(data));
				$(".open option:eq(0)").prop("selected", true);
			}

		})
	});
});

function handleImgFileSelect(e) {

	// 이미지 정보들을 초기화
	sel_files = [];
	$(".imgs_wrap").empty();

	var files = e.target.files;
	var filesArr = Array.prototype.slice.call(files);

	var index = 0;
	filesArr
			.forEach(function(f) {
				if (!f.type.match("image.*")) {
					alert("확장자는 이미지 확장자만 가능합니다.");
					return;
				}

				var form = $(".imgsupload")[0];
				var data = new FormData(form);

				$
						.ajax({
							url : 'post/uploadimage',
							type : 'post',
							data : data,
							contentType : false,
							processData : false,
							success : function(data) {
								$("#summernote").data("boardpicno",
										data.board_pic_no);
								sel_files.push(f);

								var reader = new FileReader();
								reader.onload = function(e) {
									var html = "<a href=\"javascript:void(0);\" onclick=\"deleteImageAction("
											+ index
											+ ")\" id=\"img_id_"
											+ index
											+ "\"><img src=\""
											+ e.target.result
											+ "\" data-file='"
											+ f.name
											+ "' class='selProductFile' title='Click to remove' style='width: 200px'></a>";
									$(".imgs_wrap").append(html);
									index++;

								}
								reader.readAsDataURL(f);
							}

						});
			});
}

function fileUploadAction() {
	$("#input_imgs").trigger('click');
}

function deleteImageAction(index) {
	sel_files.splice(index, 1);

	var img_id = "#img_id_" + index;
	$(img_id).remove();
}

function addcontent(data) {
	var html = '';
	html += '<div class="card mb-3">';
	html += '<div class="card-body" data-seq="' + data.board_no + '">';
	html += '<div class="media p-3">';
	html += '<img src="util/download?member_no=' + data.member_no
			+ '" class="mr-3 mt-3 rounded-circle" style="width: 30px;">';
	html += '<div class="media-body">';
	html += '<h4>' + data.board_writer
			+ ' <small><i>Posted on February 19, 2016</i></small></h4>';
	if (data.photo == 1) {
		html += '<a href="post/' + data.board_no + '">';
		html += '<img src="post/image/' + data.board_no + '" width="100%">';
		html += '</a>';
	}
	html += '<p>' + data.board_content + '</p>';
	html += '</div>';
	html += '</div>';
	html += '</div>';
	html += '<ul class="list-group list-group-flush">';
	html += '<li class="list-group-item">';
	html += '<div class="input-group mb-3">';
	html += '<input type="text" class="form-control replycontent" placeholder="댓글 달기..">';
	html += '<div class="input-group-append">';
	html += '<button class="btn btn-primary submit" type="button">게시</button>';
	html += '</div>';
	html += '</div>';
	html += '</li>';
	html += '</ul>';
	html += '</div>';

	return $.parseHTML(html);
}

