
function ClickView() {
}

ClickView.prototype = {
    init : function(party, member) {
        if(!common.chkJsonNull(party) || !common.chkJsonNull(member)) return;
        var imgUrl = party.phNum;
        console.log('member : ' + member);
        var nickname = common.decode(member.nickname);
        var region = common.decode(member.address);
        var location = common.decode(party.location);
        var beleiveNum = member.like;
        var belImgUrl = "/comi/resources/images/launcher.png";

        var title = common.decode(party.title);
        var category = party.category;
        var befordate = 1;
        var price = party.price;
        var deposit = party.deposit;
        var barPercent = (deposit == 0) ? 0 : deposit/price * 100;
        var people = party.totalNum;
        var people_price = (price - deposit)/people;
        var detail = common.decode(party.contents);

        var callNum = party.likes;
        var chatNum = party.count;
        var selecNum = party.views;

        //슬라이드
        this.setSlide();
        
        //프로필
        $("#article-profile-image-box img").attr("src", imgUrl);
        $("#article-nickname").text(nickname);
        $("#article-region-name").text(region);
        
        //신뢰도
        $("#bar-color-id").css({"width" : beleiveNum+"%"})
        $("#temperature-face-id").attr("src", belImgUrl);
        $("#text-color-id").text(beleiveNum+"%");

        //게시글 타이틀
        $("#article-title").text(title);
        $("#article-category").text(this.changeCategory(category));
        $("#article-befordate").text(befordate + "일 전");
        if(location) $("#article-location").text('장소 : ' + location);

        //총금액
        $("#article-price").text("가격 : " + common.comma(String(price)) + "원");

        //거치금
        $("#article-deposit").text("예치금 : " + common.comma(String(deposit)) + "원");

        //막대 크기
        $("#article-deposit-bar").css({"width": barPercent + "%"});

        //인원
        $("#article-people").text("모집인원 " + people + "명");
        $("#article-people-price").text("인당 " + common.comma(String(people_price)) + "원");

        //게시글 내용
        $("#article-detail p").html(detail);

        //하단 
        $("#article-counts").text("찜 " + callNum + " ∙ 채팅 " + chatNum + " ∙ 조회 " + selecNum);

    }
    ,
    changeCategory : function(cate){
        console.log('cate : ' + cate);
        var cateStr = '';
        if(cate !== undefined && cate !== null) {
            switch (Number(cate)) {
                case 1:
                    cateStr = '모임';
                    break;
                case 2:
                    cateStr = '';
                    break;
                case 3:
                    cateStr = '';
                    break;
                case 4:
                    cateStr = '';
                    break;
                default:
                    cateStr = '';
                    break;
            }

        }
        return cateStr;
    }
    ,
    setSlide : function(data){
        if(!common.chkJsonNull(data)) return;

        for(var i in data) {
            var imgUrl = '/comi/resources/images/pic01.jpg';
            var tag = `
            <div>
                <div class="slide_box">
                    <img class="picture_box" src="` + imgUrl + `" />
                </div>
            </div>
            `;

            $('#slider_main').append(tag);
        }
        
    },
    buttonEvent : function(){
        
        //프로필
        $("#article-profile-image").on("click", function(){
            
        })
    }
}