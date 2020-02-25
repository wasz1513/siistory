$(function() {
	
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
			'board_pic_no' : $("#summernote").data("boardpicno")
		};

		$.ajax({
			url : 'post/addcontent',
			type : 'post',
			data : JSON.stringify(contentVo),
			contentType : "application/json",
			success : function(data) {
				$("#summernote").val("");
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
							+ "' class='selProductFile' title='Click to remove'></a>";
					$(".imgs_wrap").append(html);
					index++;

				}
				reader.readAsDataURL(f);

			});

	var form = $(".imgsupload")[0];
	var data = new FormData(form);

	$.ajax({
		url : 'post/uploadimage',
		type : 'post',
		data : data,
		contentType : false,
		processData : false,
		success : function(data) {
			$("#summernote").data("boardpicno", data.board_pic_no);
		}

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
