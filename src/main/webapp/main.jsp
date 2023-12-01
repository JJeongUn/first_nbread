<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>comi</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/slick.min.css">
    <link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/slick-theme.min.css">
    <link rel="stylesheet" type="text/css" href="/comi/resources/css/lib/jquery-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/comi/resources/css/main.css"/>
    <script type="text/javascript" src="/comi/resources/js/lib/jquery.min.js"></script>
    <script type="text/javascript" src="/comi/resources/js/lib/lozad.min.js"></script>
    <script type="text/javascript" src="/comi/resources/js/lib/slick.min.js"></script>
    <script type="text/javascript" src="/comi/resources/js/util.js"></script>
    <script type="text/javascript" src="/comi/resources/js/main.js"></script>
</head>
<body>
    <!-- Header Section Begin -->
    <header id="header_view">
    	<%@ include file="./views/common/header.jsp" %>	
    </header>
    <!-- Header Section End -->

    <!-- main -->
    <main class="main_wrapper">
        <div class="container side_margin_zero">

            <!-- 슬라이더 -->
            <div class="main_slider">

                <div id="slider_main">
                    <div>
                        <div class="slide_box">
                            <div class="main_contentsbox">
                                <div class="main_textbox">
                                    <div class="text_large_bold slide_text_large">십시일반으로 <br>절약하기</div>
                                    <div class="text_medium_regular slide_text_small">모두가 함께 즐거운 시간을 보낼 수 있도록 정산부터 하고 만나요.<br>
                                        십시일반으로 먹고 마시고 즐겨요.</div>
                                </div>
                                <div class="main_imagebox">
                                    <img src="/comi/resources/images/mascot.png" class="main_image" >
                                </div>
                            </div>
                        </div>
                    </div>

                    <div>
                        <div class="slide_box">
                            <div class="main_contentsbox">
                                <div class="main_textbox">
                                    <div class="text_large_bold slide_text_large">돈도 아끼고 <br>시간도 아끼고</div>
                                    <div class="text_medium_regular slide_text_small">n명이서 나눠서 돈을 낼 수 있으니 <br>돈도 절약되고,
                                        빠른 모임 활성화를 통해 시간도 아껴요.</div>
                                </div>
                                <div class="main_imagebox">
                                    <img src="/comi/resources/images/empty.png" class="main_image" >
                                </div>
                            </div>
                        </div>
                    </div>

                    <div>
                        <div class="slide_box">
                            <div class="main_contentsbox">
                                <div class="main_textbox">
                                    <div class="text_large_bold slide_text_large">한계란 없다!</div>
                                    <div class="text_medium_regular slide_text_small">공유할 수 있는 모든 것을 공유하고,<br>
                                        모든 것을 함께 나눠요.</div>
                                </div>
                                <div class="main_imagebox">
                                    <img src="/comi/resources/images/exam_1.png" class="main_image" >
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    </div>
            </div>
        </div>

        <script type="text/javascript">new Util().slide($('#slider_main'));</script>

        <div class="container">
            
            <!-- 메인 2 -->
            <div class="main_contents">
                <div class="main_contentsbox">
                    <div class="main_textbox">
                        <div class="text_large_bold slide_text_large">새로운 세상과의 <br>새로운 소통</div>
                        <div class="text_medium_regular slide_text_small">모임, 공동구매, 렌탈, 게임, OTT까지<br>
                            즐길 수 있는 모든 것을 소통해요.</div>
                    </div>
                    <div class="main_imagebox">
                        <img src="/comi/resources/images/main_3.png" class="main_image" >
                    </div>
                </div>
            </div>

            <div class="main_line"></div>

            <div class="main_header">
                <div class="main_header_text text_large_bold">우리들 공유모임</div>
            </div>

            <div class="main_portfolio main_port_view" id="portf_box">
                
            </div>

            <div class="main_footer">
                <a href="/comi/partysall?type=findParty" class="main_footer_text">인기 공유 더보기</a>
            </div>
        </div>
    </main>
    

    <!-- Footer Section Begin -->
    <footer id="footer_view">
    	<%@ include file="./views/common/footer.jsp" %>	
    </footer>
    <!-- Footer Section End -->

</body>
</html>