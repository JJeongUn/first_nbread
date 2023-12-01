var pageType = 'findParty';
var sort = 'current';
var classfy = -1;
var page = 1;
var keyword = '';
var limit = 15;
var adCount = page;

document.addEventListener('DOMContentLoaded', function(){
    
    var main = new Main();
    main.loadMoreData(); 

})
function Main(){
    this.util = new Util();
    this.isLoading = true;
}

Main.prototype = {
    loadMoreData : function(){
        _this = this;
        console.log('pageType : ' + pageType + '  sort : ' + sort + '  page : ' + page 
                    + '  classfy : ' + classfy + ' keyword : ' + keyword + ' limit : ' + limit);
        $.ajax({
            url : '/comi/partysall'
            ,type : 'get'
            ,dataType : 'json'
            ,data : {
                send : 'ajax'
                ,type : pageType
                ,sort : sort
                ,page : page
                ,classfy : classfy
                ,keyword : keyword
                ,limit : (limit == null || limit == undefined) ? 11 : limit
            }
            ,success : function(data){
                var jstr = JSON.stringify(data.list);
                var jdata = JSON.parse(jstr);

                var pstr = JSON.stringify(data.photo);
                var pdata = JSON.parse(pstr);
                
                var astr = JSON.stringify(data.advertise);
                var adata = JSON.parse(astr);
                var imgSrc = '';

                if(jdata.length > 0) {
                    for(var i=0; i<jdata.length; i++) {
                        //console.log('i : ' + jdata[i].paid);
                        for(var j=0; j<pdata.length; j++){
                            if(pdata[j].phnum == jdata[i].phNum) {
                                imgSrc = (pdata[j].photo_1 == null)
                                         ? '/comi/resources/images/empty.png'
                                         : '/comi/resources/partyfile/' + pdata[j].phnum + '/' + pdata[j].photo_1;
                                break;
                            }
                        }
                        
                        $('#portf_box').append(_this.setMainTag(jdata[i], imgSrc));
                    }

                    if(adata[adCount-1] == null || adata[adCount-1] == undefined) {
                        adCount = 1;
                    }

                    var photoArr = [];
                    for(var i=0; i<5; i++) {
                        var pho = adata[adCount-1]['photo_' + (i+1)];
                        if(pho != null && pho != undefined) {
                            photoArr.push('/comi/resources/adverfile/'+adata[adCount-1].photo+'/'+pho);
                        }
                    }
                    
                    $('#portf_box').append(_this.setAdvertiseTag(adata[adCount-1], photoArr));
                    _this.util.slide($('#sliderView_'+page));

                    var observer = lozad();
                    observer.observe();
        
                    page ++;
                }else{
                    $(window).off('scroll'); // 더 이상 데이터가 없으면 스크롤 이벤트 제거
                }
                
                isLoading = false;
            }
            ,error : function(jqXHR, textStatus, errorThrown){
                console.log('error : ' + jqXHR + ', '+ textStatus + ', ' + errorThrown);
            }
        })
    }
    ,
    setMainTag : function(data, src) {
        
        //var lazTag = '<img src="/comi/resources/images/empty.png" alt="" />';
        var endClass = (pageType == 'findParty') ? '' : 'endClass';
        var endTag = (pageType == 'findParty') ? '' : '<div class="party_closed_bottom">종료된 모임이에요.</div>';
        var el = `
        <a class="port_box `+endClass+` flexBox" href="/comi/partysel?panum=`+data.paid+`&act=`+data.act+`">
            <div class="image featured">
                `+this.lazyTag(src)+`
            </div>
            <div class="text_box">
                <div class="port_box_title">`+decode(data.title)+`</div>
                <div class="port_box_textbox">
                    <div class="port_box_price">가격 : <span class="price_all">`+data.price+`</span></div>
                    <div class="port_box_deposit port_box_text_right">
                        예치금 : <span class="price_deposit">`+data.deposit+`</span>
                    </div>
                </div>
                <div class="port_box_textbox">
                    <div class="port_box_address">`+decode(data.location)+`</div>
                    <div class="port_box_date port_box_text_right">`+data.enroll+`</div>
                </div>
                <div class="port_box_textbox">
                    <div class="port_box_text">인원 모집 `+data.totalNum+`명</div>
                    <div class="port_box_text port_box_text_right">인당 `+data.peoplePrice+`원</div>
                </div>
            </div>
            `+endTag+`
        </a>
        `

        return el;
    }
    ,
    setAdvertiseTag : function(data, photoArr) {
        var endClass = (pageType == 'findParty') ? '' : 'endClass';
        var el = `
        <div class="port_box flexBox advertise `+endClass+`">
            <div class="image featured">
                <!-- 슬라이더 -->
            
                <div class="slider_view" id="sliderView_`+page+`">`

                    for(var i=0; i<photoArr.length; i++){
                        //console.log('photoArr[i] : ' + photoArr[i]);
                        el += `    
                        <a href="`+data.url+`" target="_blank">
                            <div class="slide_box">
                                `+this.lazyTag((photoArr[i] == null || photoArr[i] == undefined) ? '/comi/resources/images/empty.png' : photoArr[i])+`
                            </div>
                        </a>`
                    }

                el += `
                </div>
                
            </div>
            <a href="`+data.url+`" target="_blank">
                <div class="text_box">
                ` + decode(data.title) + `
                </div>
            </a>
                
        </div>
        `
        return el;
    }
    ,
    lazyTag : function(src){
        var lazTag = '<img class="lozad" data-src="'+src+'" alt="" />';
        return lazTag;
    }
}

function decode (str) {
    var str2 = decodeURIComponent(str);
    return str2.replace(/\+/g, ' ');
}