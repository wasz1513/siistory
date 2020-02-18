<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

<link href="https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/darkly/bootstrap.min.css" rel="stylesheet" integrity="sha384-rCA2D+D9QXuP2TomtQwd+uP50EHjpafN+wruul0sXZzX/Da7Txn4tB9aLMZV4DZm" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.15/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.15/dist/summernote.min.js"></script>

<script>
	$(function() {
		
		// 에디터
		$('#summernote').summernote({
			height : 300,
			minHeight : null,
			maxHeight : null,
			focus : true,
			toolbar : []
		});
		
		// 이미지 업로드 관련
		var sel_files = {};
		$("#input_imgs").on("change", handleImgFileSelect);
		
		$(".upload").click(function(e){
			e.preventDefault();
			
			$.ajax({
				url:'write',
				type:'post',
				data : sel_files,
				enctype:"multipart/form-data",
		        contentType : false,
		        processData : false,
		        success:function(data){
		        	console.log("성공")
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
        filesArr.forEach(function(f) {
            if(!f.type.match("image.*")) {
                alert("확장자는 이미지 확장자만 가능합니다.");
                return;
            }

            sel_files.push(f);

            var reader = new FileReader();
            reader.onload = function(e) {
                var html = "<a href=\"javascript:void(0);\" onclick=\"deleteImageAction("+index+")\" id=\"img_id_"+index+"\"><img src=\"" + e.target.result + "\" data-file='"+f.name+"' class='selProductFile' title='Click to remove'></a>";
                $(".imgs_wrap").append(html);
                index++;

            }
            reader.readAsDataURL(f);
            
        });
    }

	function fileUploadAction() {
        $("#input_imgs").trigger('click');
    }
	
	function deleteImageAction(index) {
        sel_files.splice(index, 1);

        var img_id = "#img_id_"+index;
        $(img_id).remove(); 
    }
	
</script>

<form method="post" enctype="multipart/form-data">
	<textarea id="summernote" name="editordata"></textarea>
	<div>
        <h2><b>이미지 미리보기</b></h2>
        <div class="input_wrap">
            <a href="javascript:" onclick="fileUploadAction();" class="my_button">파일 업로드</a>
            <input type="file" id="input_imgs" multiple/>
        </div>
    </div>

    <div>
        <div class="imgs_wrap">
            <img id="img" />
        </div>
    </div>
</form>

        <button type="button" class="btn btn-primary upload">게시!!!!!!!!!!!!!!!!!</button>

