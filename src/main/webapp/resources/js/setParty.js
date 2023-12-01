/**
 * click페이지 하단 파티 생성
 */
function SetParty(){
    this.count = 0;
}

SetParty.prototype = {
    
    init : function(partyData, box){
        //하단 리스트 붙이기
        $.ajax({
            url : '/comi/partylist?type=findParty&start=1&end=6&panum=' + partyData.paid
            ,type : 'get'
            ,dataType : 'json'
            ,success : function(data){
                
                var str = JSON.stringify(data);
                var json = JSON.parse(str);
                var value = '';
                console.log('json : ' + json.list);

                //하단 리스트 불러오기
                var makeParty = new MakeParty();

                for(var i in json.list) {
                    var obj = json.list[i];
                    box.append(makeParty.getTag(obj));
                }
            }
            ,error : function(jqXHR, textStatus, errorThrown){
                console.log('error : ' + jqXHR + ', '+ textStatus + ', ' + errorThrown);
            }
        })
    }
}


