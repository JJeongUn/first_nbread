
function Util() {
}

Util.prototype = {
    comma : function (num) {
        var str = String(num);
        var str2 = str.split('.').shift();
        var str3 = str2.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');


        console.log('str3 : ' + str3);
        return (str3 == undefined || str3 == null || str3 == 'undefined') ? '0' : str3;
    },
    chkArrayNull : function (arr) {
        if(arr !== null && arr !== undefined && arr && arr.length > 0) {
            return true;
        }
        return false;
    },
    chkJsonNull : function (json) {
        if(json !== null && json !== undefined && json && Object.keys(json).length > 0) {
            return true;
        }
        return false;
    },
    digit : function(c){
        console.log('----c.toString().length : ' + c.toString().length)
        if(c.toString().length == 1) {
            c = '0' + c;
        }

        return c;
    },
    decode : function(str) {
        var str2 = decodeURIComponent(str);
        return str2.replace(/\+/g, ' ');
    },
    slide : function(obj) {
        obj.slick({
            slide: 'div',        //슬라이드 되어야 할 태그 ex) div, li 
            infinite : true,     //무한 반복 옵션 
            autoplay : false,
            slidesToScroll : 1,        //스크롤 한번에 움직일 컨텐츠 개수
            speed : 600,     // 다음 버튼 누르고 다음 화면 뜨는데까지 걸리는 시간(ms)
            arrows : true,         // 옆으로 이동하는 화살표 표시 여부
            dots : true,         // 스크롤바 아래 점으로 페이지네이션 여부	
            pauseOnHover: true, // 마우스오버시 멈춤여부(생략가능)
            pauseOnDotsHover: true, // 동그라미번호버튼에 호버시 멈춤여부(생략가능)
            pauseOnFocus: false,  // 동그라미번호버튼 클릭시 자동실행 멈춤여부
            prevArrow : "<button type='button' class='slick-prev'>Previous</button>",        // 이전 화살표 모양 설정
            nextArrow : "<button type='button' class='slick-next'>Next</button>",        // 다음 화살표 모양 설정
            dotsClass : "slick-dots",     //아래 나오는 페이지네이션(점) css class 지정
            responsive: [ // 반응형 웹 구현 옵션
                {  
                    breakpoint: 960, //화면 사이즈 960px
                    settings: {
                        //위에 옵션이 디폴트 , 여기에 추가하면 그걸로 변경
                        slidesToShow:1 
                    } 
                },
                { 
                    breakpoint: 768, //화면 사이즈 768px
                    settings: {    
                        //위에 옵션이 디폴트 , 여기에 추가하면 그걸로 변경
                        slidesToShow:1 
                    } 
                }
            ]
        });
        
    }
}




