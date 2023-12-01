<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<% request.setAttribute("type", "make" ); %>
		<!DOCTYPE html>
		<html lang="ko">

		<head>
			<meta charset="UTF-8">
			<title>party_make</title>

			<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
			<link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/jquery-ui.min.css" />
			<link rel="stylesheet" type="text/css" href="/comi/resources/css/main.css" />
			<link rel="stylesheet" type="text/css" href="/comi/resources/css/view_reply.css" />
			<link rel="stylesheet" type="text/css" href="/comi/resources/css/party_make.css" />
			<script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
			<script type="text/javascript" src="/comi/resources/js/lib/jquery-ui-zoom.js"></script>
			<script type="text/javascript" src="/comi/resources/js/lib/jquery.ui.touch-punch.min.js"></script>
			<script type="text/javascript" src="/comi/resources/js/util.js"></script>
			<script type="text/javascript" src="/comi/resources/js/party_make.js"></script>
			<script type="text/javascript"
				src="//dapi.kakao.com/v2/maps/sdk.js?appkey=fe01c342eff0d762beaefc4763bf3f91&libraries=services"></script>
			<!--
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=발급받은 APP KEY를 사용하세요&libraries=services"></script>
	-->
		</head>

		<body>
			<!-- Header Section Begin -->
			<header id="header_view">
				<%@ include file="../common/header.jsp" %>
			</header>
			<!-- Header Section End -->

			<!-- main -->
			<main class="main_wrapper">
				<div class="container make-view">

					<form action="/comi/partyi" method="post" enctype="multipart/form-data">
						<%if(loginMember != null){%>
							<input type="hidden" name="usernum" value="<%= loginMember.getMeNum()%>">
						<%}%>
						<!-- 카테고리 설정 -->
						<section class="view-section view-category">
							<div class="title-text view-category-title">카테고리를 설정해주세요.</div>
							<div class="view-category-content">
								<label class="view-category-box">
									<input type="radio" name="category_check" value="모임"><span>모임</span>
								</label>
								<label class="view-category-box">
									<input type="radio" name="category_check" value="공구"><span>공구</span>
								</label>
								<label class="view-category-box">
									<input type="radio" name="category_check" value="렌탈"><span>렌탈</span>
								</label>
								<label class="view-category-box">
									<input type="radio" name="category_check" value="게임"><span>게임</span>
								</label>
								<label class="view-category-box">
									<input type="radio" name="category_check" value="OTT"><span>OTT</span>
								</label>
								<label class="view-category-box">
									<input type="radio" name="category_check" value="기타"><span>기타 </span>
								</label>
							</div>
						</section>
						<!-- 카테고리 설정 end-->

						<!-- 가격 및 디파짓 및 인원 설정 -->
						<section class="view-section view-price">
							<div class="title-text view-category-title">가격및 거치금을 설정해주세요.</div>
							<div class="view-price-content">
								<div class="view-price-barbox">
									<div class="view-price-bar">
										<div id="view-price-bar-price"></div>
										<div id="view-price-bar-deposit"></div>
									</div>
									<div id="view-price-cursor" class="view-price-cursor" draggable="true"></div>
								</div>
								<fieldset class="view-price-field">
									<div class="view-price-field-div">
										가격 &nbsp;<input type="number" id="setting_price" min="10000" max="10000000"
											value="50000" name="pa_total_amount">
									</div>
									<div class="view-price-field-div">
										거치금 &nbsp;<input type="number" id="setting_deposit" min="10000" max="10000000"
											value="10000" name="pa_deposit">
									</div>
									<div class="view-price-field-div">
										모집인원 &nbsp;<input type="number" id="setting_people" min="1" max="10" step="1"
											value="1" name="pa_total_num">
									</div>
								</fieldset>
							</div>
							<div id="view-price-peopleprice" class="view-price-peopleprice">1인당 금액 : <span id="people_money">5000</span>원</div>
						</section>

						<script>
							
						</script>

						<!-- 게시글 설정 -->
						<section class="view-section view-notice">
							<div class="view-notice-title title-text">공유 모임글을 작성해주세요.</div>
							<div class="view-notice-area">

								<div class="view-notice-box">
									<div class="view-notice-box-title">공유 날짜</div>
									<div class="review-write-area-inbox">
										<input type="date" title="공유 날짜" value="2023-09-01" class="review-input-date"
											name="pa_time_1" id="notice_write_date_1">
										<input type="time" title="공유 날짜" class="review-input-date" name="pa_time_2"
											id="notice_write_date_2">
									</div>
								</div>

								<div class="view-notice-box">
									<div class="view-notice-box-title">제목</div>
									<div class="review-write-area-inbox">
										<input type="text" title="제목" class="review-write-textarea input"
											name="pa_title" id="notice_write_input">

									</div>
								</div>

								<div class="view-notice-box">
									<div class="view-notice-box-title">자세한 설명</div>
									<div class="review-write-area-inbox">
										<textarea title="자세한 설명" class="review-write-textarea" name="pa_con"
											id="notice_write_textarea" rows="3" cols="30"></textarea>

									</div>
								</div>

								<div class="view-notice-box">
									<div class="view-notice-box-title under-line">사진</div>
									<div class="view-notice-picture-box">

										<div class="view-notice-picture-left">
											<div class="view-notice-picture-explain">
												<div class="view-notice-picture-textbig">이미지 추가등록</div>
												<div class="view-notice-picture-textsmall">
													이미지는 최대 5장까지 등록가능합니다.<br> 이미지 권장 사이즈는 700 * 500입니다.
												</div>
											</div>
										</div>

										<div class="view-notice-picture-area">
											<label class="view-notice-picture">
												<img id="preview1" alt="" style="max-width: 130px; max-height: 130px;"
													aria-disabled="true">
												<input type="file" name="pa_img1" style="display: none;"
													accept='image/jpeg,image/gif,image/png'
													onchange='handleImageUpload(this, "preview1")'>
												<samp id="upload-label1">이미지<br>업로드</samp>
											</label>
											<label class="view-notice-picture">
												<img id="preview2" alt="" style="max-width: 130px; max-height: 130px;"
													aria-disabled="true">
												<input type="file" name="pa_img2" style="display: none;"
													accept='image/jpeg,image/gif,image/png'
													onchange='handleImageUpload(this, "preview2")'>
												<samp id="upload-label2">이미지<br>업로드</samp>
											</label>
											<label class="view-notice-picture">
												<img id="preview3" alt="" style="max-width: 130px; max-height: 130px;"
													aria-disabled="true">
												<input type="file" name="pa_img3" style="display: none;"
													accept='image/jpeg,image/gif,image/png'
													onchange='handleImageUpload(this, "preview3")'>
												<samp id="upload-label3">이미지<br>업로드</samp>
											</label>
											<label class="view-notice-picture">
												<img id="preview4" alt="" style="max-width: 130px; max-height: 130px;"
													aria-disabled="true">
												<input type="file" name="pa_img4" style="display: none;"
													accept='image/jpeg,image/gif,image/png'
													onchange='handleImageUpload(this, "preview4")'>
												<samp id="upload-label4">이미지<br>업로드</samp>
											</label>
											<label class="view-notice-picture">
												<img id="preview5" alt="" style="max-width: 130px; max-height: 130px;"
													aria-disabled="true">
												<input type="file" name="pa_img5" style="display: none;"
													accept='image/jpeg,image/gif,image/png'
													onchange='handleImageUpload(this, "preview5")'>
												<samp id="upload-label5">이미지<br>업로드</samp>
											</label>
										</div>


									</div>
								</div>

								<div class="view-notice-box">
								</div>



								<div class="view-notice-box-title">공유 희망 장소</div>
								<div class="view-notice-location">
									<div class="view-notice-location-title">
										<div class="notice-text-bold">파티원들과 만나서 물건을 전달하거나 모임을 가질
											장소를 선택해 주세요.</div>
										<div class="notice-text-normal">누구나 찾기 쉬운 공공장소가 좋아요.</div>
									</div>
									<div class="view-notice-location-box">
										<div class="view-notice-location-search location-box-style">
											<div class="view-notice-location-searchtext">
												<input type="hidden" id="address" name="address"
													class="postcodify_address" size="100">
												<input type="text" value="서울역" id="keyword" size="100">
											</div>

											<input type="button" value="검색하기" onclick="searchPlaces(); return false;">
										</div>
										<div class="map_wrap">
											<div id="map" style="width:100%;height:100%;"></div>
											<div id="menu_wrap" class="bg_white" style="display: none;">
												<div class="option">
													<!-- <div>
														<form onsubmit="searchPlaces(); return false;">
														</form>
													</div> -->
												</div>
												<hr>
												<ul id="placesList" style="display: none;"></ul>
												<div id="pagination" style="display: none;"></div>
											</div>
										</div>
									</div>
								</div>
								<script>
									var address_name = [];
									// 마커를 담을 배열입니다
									var markers = [];
									address = "";
									var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
										mapOption = {
											center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
											level: 3 // 지도의 확대 레벨
										};

									// 지도를 생성합니다    
									var map = new kakao.maps.Map(mapContainer, mapOption);

									// 장소 검색 객체를 생성합니다
									var ps = new kakao.maps.services.Places();

									// 검색 결과 목록이나 마커를 클릭했을 때 장소명을 표출할 인포윈도우를 생성합니다
									var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });

									// 키워드로 장소를 검색합니다
									searchPlaces();

									// 키워드 검색을 요청하는 함수입니다
									function searchPlaces() {

										var keyword = document.getElementById('keyword').value;

										if (!keyword.replace(/^\s+|\s+$/g, '')) {
											alert('키워드를 입력해주세요!');
											return false;
										}

										// 장소검색 객체를 통해 키워드로 장소검색을 요청합니다
										ps.keywordSearch(keyword, placesSearchCB);


									}

									// 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
									function placesSearchCB(data, status, pagination) {
										if (status === kakao.maps.services.Status.OK) {

											// 정상적으로 검색이 완료됐으면
											// 검색 목록과 마커를 표출합니다
											displayPlaces(data);

											// 페이지 번호를 표출합니다
											displayPagination(pagination);

										} else if (status === kakao.maps.services.Status.ZERO_RESULT) {

											alert('검색 결과가 존재하지 않습니다.');
											return;

										} else if (status === kakao.maps.services.Status.ERROR) {

											alert('검색 결과 중 오류가 발생했습니다.');
											return;

										}
									}

									// 검색 결과 목록과 마커를 표출하는 함수입니다
									function displayPlaces(places) {

										var listEl = document.getElementById('placesList'),
											menuEl = document.getElementById('menu_wrap'),
											fragment = document.createDocumentFragment(),
											bounds = new kakao.maps.LatLngBounds(),
											listStr = '';

										// 검색 결과 목록에 추가된 항목들을 제거합니다
										removeAllChildNods(listEl);

										// 지도에 표시되고 있는 마커를 제거합니다
										removeMarker();

										for (var i = 0; i < places.length; i++) {

											// 마커를 생성하고 지도에 표시합니다
											var placePosition = new kakao.maps.LatLng(places[i].y, places[i].x),
												marker = addMarker(placePosition, i),
												itemEl = getListItem(i, places[i]); // 검색 결과 항목 Element를 생성합니다
											//console.log("index : "+ (i+1) +"\n주소 :"+places[i].road_address_name);
											address_name[i] = places[i].road_address_name;
											//console.log("address_name : " + address_name[i]);
											// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
											// LatLngBounds 객체에 좌표를 추가합니다
											bounds.extend(placePosition);

											// 마커와 검색결과 항목에 mouseover 했을때
											// 해당 장소에 인포윈도우에 장소명을 표시합니다
											// mouseout 했을 때는 인포윈도우를 닫습니다
											(function (marker, title, marker_address_name) {
												kakao.maps.event.addListener(marker, 'mouseover', function () {
													displayInfowindow(marker, title);
												});
												kakao.maps.event.addListener(marker, 'click', function () {
													// 마커 위에 인포윈도우를 표시합니다
													console.log(marker_address_name);
													$('#address').val(marker_address_name);
													$('#keyword').val(marker_address_name);
												});

												kakao.maps.event.addListener(marker, 'mouseout', function () {
													infowindow.close();
												});

												itemEl.onmouseover = function () {
													displayInfowindow(marker, title);
												};

												itemEl.onmouseout = function () {
													infowindow.close();
												};
											})(marker, places[i].place_name, places[i].road_address_name);

											fragment.appendChild(itemEl);
										}

										// 검색결과 항목들을 검색결과 목록 Element에 추가합니다
										listEl.appendChild(fragment);
										menuEl.scrollTop = 0;

										// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
										map.setBounds(bounds);

									}

									// 검색결과 항목을 Element로 반환하는 함수입니다
									function getListItem(index, places) {

										var el = document.createElement('li'),
											itemStr = '<span class="markerbg marker_' + (index + 1) + '"></span>' +
												'<div class="info">' +
												'   <h5>' + places.place_name + '</h5>';

										if (places.road_address_name) {
											itemStr += '    <span>' + places.road_address_name + ' *주소*' + '</span>' +
												'   <span class="jibun gray">' + places.address_name + '</span>';
										} else {
											itemStr += '    <span>' + places.address_name + '</span>';
										}

										itemStr += '  <span class="tel">' + places.phone + '</span>' +
											'</div>';

										el.innerHTML = itemStr;
										el.className = 'item';

										return el;
									}

									// 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
									function addMarker(position, idx, title) {
										var imageSrc = 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_number_blue.png', // 마커 이미지 url, 스프라이트 이미지를 씁니다
											imageSize = new kakao.maps.Size(36, 37),  // 마커 이미지의 크기
											imgOptions = {
												spriteSize: new kakao.maps.Size(36, 691), // 스프라이트 이미지의 크기
												spriteOrigin: new kakao.maps.Point(0, (idx * 46) + 10), // 스프라이트 이미지 중 사용할 영역의 좌상단 좌표
												offset: new kakao.maps.Point(13, 37) // 마커 좌표에 일치시킬 이미지 내에서의 좌표
											},
											markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize, imgOptions),
											marker = new kakao.maps.Marker({
												position: position, // 마커의 위치
												image: markerImage
											});

										marker.setMap(map); // 지도 위에 마커를 표출합니다
										markers.push(marker);  // 배열에 생성된 마커를 추가합니다

										return marker;
									}

									// 지도 위에 표시되고 있는 마커를 모두 제거합니다
									function removeMarker() {
										for (var i = 0; i < markers.length; i++) {
											markers[i].setMap(null);
										}
										markers = [];
									}

									// 검색결과 목록 하단에 페이지번호를 표시는 함수입니다
									function displayPagination(pagination) {
										var paginationEl = document.getElementById('pagination'),
											fragment = document.createDocumentFragment(),
											i;

										// 기존에 추가된 페이지번호를 삭제합니다
										while (paginationEl.hasChildNodes()) {
											paginationEl.removeChild(paginationEl.lastChild);
										}

										for (i = 1; i <= pagination.last; i++) {
											var el = document.createElement('a');
											el.href = "#";
											el.innerHTML = i;

											if (i === pagination.current) {
												el.className = 'on';
											} else {
												el.onclick = (function (i) {
													return function () {
														pagination.gotoPage(i);
													}
												})(i);
											}

											fragment.appendChild(el);
										}
										paginationEl.appendChild(fragment);
									}

									// 검색결과 목록 또는 마커를 클릭했을 때 호출되는 함수입니다
									// 인포윈도우에 장소명을 표시합니다
									function displayInfowindow(marker, title) {
										var content = '<div style="padding:5px;z-index:1;">' + title + '</div>';

										infowindow.setContent(content);
										infowindow.open(map, marker);
									}

									// 검색결과 목록의 자식 Element를 제거하는 함수입니다
									function removeAllChildNods(el) {
										while (el.hasChildNodes()) {
											el.removeChild(el.lastChild);
										}
									}

								</script>



								<div class="view-notice-box">
									<% if(loginMember != null){%>
										<input type="submit" value="작성완료" class="view-notice-btn">
									<%}else{%>
										<input type="button" value="로그인" class="view-notice-btn" onclick="location.href='/comi/views/member/member_login.html'">
									<%}%>
								</div>
							</div>
						</section>
					</form>

					<!-- 결제 설정 -->
					<!--
			<section class="view-section view-location">
				<button class="view-payment-btn">
					<img class="view-payment-btn-img" src="/comi/resources/images/payment_btn.png">
				</button>
			</section>
			-->
					<!-- 결제 설정 end-->

				</div>
			</main>
			<!-- main end-->

			<!-- Footer Section Begin -->
			<footer id="footer_view">
				<%@ include file="../common/footer.jsp" %>
			</footer>
			<!-- Footer Section End -->
		</body>

		</html>