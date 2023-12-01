
var ZOOM_RATE = 1;
var globalClick = {
    x: 0,
    y: 0
};

document.addEventListener("DOMContentLoaded", function () {
    
    var partyMake = new Party_make();
    partyMake.init();

    
});

function Party_make(){
    this.depositRnum = document.getElementById('setting_deposit');
}

Party_make.prototype = {
    init : function(){
        let settingDeposit = document.getElementById('setting_deposit');
        this.updateSlider(Number(settingDeposit.value));
        this.dragEvent();
    }
    ,
    dragEvent : function(){
        _this = this;
        //드래그
        $('#view-price-cursor').draggable({
            containment: 'parent',
            
            drag: function (e, ui) {

                let position = ui.position.left / ($(this).parent().width() - $(this).width());
                // 드래그된 위치에 따라 원하는 작업 수행
                console.log("드래그 위치:", position);
                let settingPrice = document.getElementById('setting_price');
                let deposit = position*settingPrice.value;
                _this.updateSlider(deposit);
            },
            stop: function (e, ui) {
                let position = ui.position.left / ($(this).parent().width() - $(this).width());
                let settingPrice = document.getElementById('setting_price');
                let deposit = Number(position*settingPrice.value);
                let depositR = Math.round(deposit / 100) * 100;
                _this.depositRnum = depositR;
                _this.updateSlider(depositR);
            }
        })

        $('#setting_people').on('change', function(){
            _this.updateSlider(_this.depositRnum);
        })

        $('#setting_price').on('change', function(){
            _this.updateSlider(_this.depositRnum);
        })

        $('#setting_deposit').on('change', function(){
            _this.updateSlider(_this.depositRnum);
        })
    }
    ,updateSlider :function(deposit) {
        
        
        let settingDeposit = document.getElementById('setting_deposit');
        let depositBar = document.getElementById('view-price-bar-deposit');
        let settingPeople = document.getElementById('setting_people');
        let settingPrice = document.getElementById('setting_price');
        let allPrice = settingPrice.value;
        let pricePercentage = (deposit / allPrice) * 100;
        let depositNum = deposit.toFixed();
        settingDeposit.value = depositNum;
        console.log('settingPrice.value : ' + settingPrice.value);
        console.log('settingDeposit.value : ' + settingDeposit.value);
        //let remain = 

        $('#view-price-cursor').css({
            'left' : 'calc('+pricePercentage + '% - ' +($('#view-price-cursor').width()/2) + 'px)'
        })
        let cursorleft = Number($('#view-price-cursor').css('left').split('px')[0]);
        console.log('cursorleft : ' + cursorleft);
        let barLeft = $('#view-price-bar-price').width() - $('#view-price-cursor').width();
        if(cursorleft >= barLeft) {
            console.log('barLeft : ' + barLeft);
            $('#view-price-cursor').css('left', barLeft);
            $('#view-price-bar-deposit').css({
                'width' : 'calc('+100 + '% )'
            })
        }else{
            $('#view-price-bar-deposit').css({
                'width' : cursorleft
            })
        }

        let perPeople = Number((allPrice - depositNum)/settingPeople.value);
        let monyPeople = perPeople/Number($('#setting_people').val());
        console.log('perPeople : ' + perPeople);
        $('#people_money').text(monyPeople.toFixed());
    }
    
}


function handleImageUpload(obj, previewId) {
    var file = obj.files[0];
    var file_name = file.name;
    var file_type = file_name.substring(file_name.lastIndexOf('.') + 1).toLowerCase();

    var check_file_type = ['jpg', 'gif', 'png', 'jpeg', 'bmp'];

    if (check_file_type.indexOf(file_type) === -1) {
        alert('이미지 파일만 선택할 수 있습니다.');
        obj.value = ''; // 파일 선택값 초기화
        return;
    }

    var reader = new FileReader();
    reader.onload = function () {
        var img = document.getElementById(previewId);
        img.src = reader.result;

        // 이미지 미리보기가 업로드될 때 라벨을 숨깁니다.
        var uploadLabel = document.getElementById('upload-label' + previewId.charAt(previewId.length - 1));
        uploadLabel.style.display = 'none';
    };
    reader.readAsDataURL(file);
}