

function viewChat(data){
    //my-msg, continue-msg
    var el = `
    <div class="chating-body-msg my-msg">
        <div class="chating-body-msg-w">`

        if(data){
            el += `
            <div class="chating-body-msg-profile">
                <img class="chating-body-msg-profile-img" src="/comi/resources/images/profile.png">
                <div class="chating-body-msg-profile-name">`+data.name+`</div>
            </div>`
        }
            

        el += `
            
            <div class="chating-body-msg-box">
            <div class="chating-body-msg-box-read bubble-msg">
                `+data.message+`
            </div>
            <div class="chating-body-msg-box-date">
                `+data.date+`
            </div>
            <button class="chating-body-msg-box-pop">
                <img class="chating-body-msg-box-pop-img" src="/comi/resources/images/msg_pop_img.png">
            </button>
            </div>
        </div>
    </div>
    `
        $('.chating-body-box').append(el);
}
