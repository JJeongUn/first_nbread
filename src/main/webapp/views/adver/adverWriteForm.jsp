<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/jquery-ui.min.css" />
    <link rel="stylesheet" href="/comi/resources/css/main.css" />
    <link rel="stylesheet" href="/comi/resources/css/review.css" />
    <link rel="stylesheet" href="/comi/resources/css/question.css" />
    <script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
    <script type="text/javascript" src="/comi/resources/js/common.js"></script>
    <script type="text/javascript" src="/comi/resources/js/question.js"></script>
    <style>
        /* 사용자 지정 CSS 스타일링 */
       
        .form-group {
            margin-bottom: 6px;
            margin-top: 6px;
        }
        label {
            font-weight: bold;
            margin: 5px;
        }
        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        input[type="file"] {
            margin-top: 5px;
        }
      
        }
        input[type="date"] {
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            
        }
  
   .btn {
    background-color: #FFCC76;
    color: /* #917443; */ #000000;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
   
   }
   .btn:hover {
            background-color: #fca005;
    color: #ffffff;
    }
   
   div.ad-container {
   display: flex;
   align-content: flex-start;
   justify-content: space-evenly;
   background-color: rgba(255, 237, 118, 0.4);
   
   border-radius: 10px;
   padding: 15px;
  
   
	height: 400px;
    
   }
 
	.ad-date {
	all: initial;
	display: flex;
    justify-content: space-evenly;
  	align-items: center;
  
   	}
	
	.qa-navbox {
		margin: 45px;
	 	height: 40px;
	
	}
   
    </style>
</head>

<body>
    <header id="header_view">
    	<%@ include file="../common/header.jsp" %> 
    </header>
    <main class="main_wrapper">
		<div class="container">
			
			<!--상단-->
			<div class="search-container">
				<div class="qa-title">
					광고 문의
				</div>
				
			</div>
			<!--상단end-->

			<!--게시판-->
           <div class="ad-container" style="width: 550px; border: 2px solid #ffed76; margin: 0 auto; text-align: center;">
				<form action="/comi/inadver" method="post"
					enctype="multipart/form-data">
					<input class="form-group" type="hidden" name="writer"
						value="<%=loginMember.getMeNum()%>">
					<table class="tg">
						<thead>
							<tr>
								<th class="tg-3xi5"><label for="title">제목</label> <input
									class="form-group" type="text" name="title" id="title" placeholder="제목을 입력하세요"></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="tg-0pky"><label for="content">광고 URL</label> <input
									class="form-group" type="text" name="url" id="url" placeholder="URL을 입력하세요"></td>
							</tr>
							<tr>
								<td class="tg-0pky"><label for="file1">첨부 파일 1</label> <input
									class="form-group" type="file" name="file1" id="file1" accept="image/*"
									onchange="previewImage(this, 'preview1')"> <img
									id="preview1" src="#" alt="미리보기 이미지"
									style="display: none; max-width: 100px;"></td>
							</tr>
							<tr>
								<td class="tg-0lax"><label for="file2">첨부 파일 2</label> <input
									class="form-group" type="file" name="file2" id="file2" accept="image/*"
									onchange="previewImage(this, 'preview2')"> <img
									id="preview2" src="#" alt="미리보기 이미지"
									style="display: none; max-width: 100px;"></td>
							</tr>
							<tr>
								<td class="tg-0lax"><label for="file3">첨부 파일 3</label> <input
									class="form-group" type="file" name="file3" id="file3" accept="image/*"
									onchange="previewImage(this, 'preview3')"> <img
									id="preview3" src="#" alt="미리보기 이미지"
									style="display: none; max-width: 100px;"></td>
							</tr>
							<tr>
								<td class="tg-0lax"><label for="file4">첨부 파일 4</label> <input
									class="form-group" type="file" name="file4" id="file4" accept="image/*"
									onchange="previewImage(this, 'preview4')"> <img
									id="preview4" src="#" alt="미리보기 이미지"
									style="display: none; max-width: 100px;"></td>
							</tr>
							<tr>
								<td class="tg-0lax"><label for="file5">첨부 파일 5</label> <input
									class="form-group" type="file" name="file5" id="file5" accept="image/*"
									onchange="previewImage(this, 'preview5')"> <img
									id="preview5" src="#" alt="미리보기 이미지"
									style="display: none; max-width: 100px;"></td>
							</tr>
							<tr>
								<td class="ad-date">광고 시작 날짜<input class="form-group" type="date"
									name="startDate" id="startDate" required></td>
							</tr>
							<tr>
								<td class="ad-date">광고 종료 날짜<input class="form-group" type="date"
									name="endDate" id="endDate" required></td>
							</tr>
						</tbody>
					</table>
					
					<input class="btn" type="submit" value="글쓰기">
				</form>
			</div>
           
           <!--게시판 E-->
            <script>
                // 파일 업로드 시 미리보기 이미지 업데이트 함수
                function previewImage(input, imgId) {
                    var preview = document.getElementById(imgId);
                    if (input.files && input.files[0]) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            preview.src = e.target.result;
                            preview.style.display = 'block';
                            // 파일 입력(input type="file") 숨기기
                            input.style.display = 'none';
                        };
                        reader.readAsDataURL(input.files[0]);
                    } else {
                        preview.src = "#";
                        preview.style.display = 'none';
                        // 파일 입력(input type="file") 다시 표시
                        input.style.display = 'block';
                    }
                }
            </script>
			<!--게시판end-->

			<!--버튼박스-->
			<div class="qa-container">

				<div class="qa-navbox">
					
					<button class="btn" id="qa_list" onclick="javascript:location.href='/comi/adsall?page=1';" style="cursor:pointer "> 목록
						
					</button>
					
				</div>
			</div>
			<!--버튼박스end-->
			
		</div>
	</main>
    <footer id="footer_view">
		<%@ include file="../common/footer.jsp" %> 
	</footer>
</body>

</html>