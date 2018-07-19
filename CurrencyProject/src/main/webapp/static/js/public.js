
//自定义日期format函数，例：new Date(Date.parse(data)).Format("yyyy-MM-dd hh:mm:ss");
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1,
        //月份
        "d+": this.getDate(),
        //日
        "h+": this.getHours(),
        //小时
        "m+": this.getMinutes(),
        //分
        "s+": this.getSeconds(),
        //秒
        "q+": Math.floor((this.getMonth() + 3) / 3),
        //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt. replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
};
var publicMethod = {
    alertSystemError : function (obj) {
        if (obj!=null&&obj.result!=undefined){
            if(obj.result=="timeout"){
                publicMethod.sessionTimeOut(obj.message);
            } else {
                publicMethod.alertError(obj.message);
            }
        }
    },
    alertError : function (errorMsg) {
        swal("温馨提示!", errorMsg, "error");
    },
    sessionTimeOut : function (msg){
        var seconds = 3;
        var CountDown = function () {
            setTimeout(function () {
                seconds--;
                $("#seconds").text(seconds);
                if(seconds==0){
                    window.location.href = '/system/login.html';
                    return;
                }
                CountDown();
            },1000);
        }

        swal({
            title: "Error!",
            text: msg +'<br/><span id="seconds" style="color:red">3</span>秒后跳转登录页面。',
            html: true,
            timer: 3000,
            showConfirmButton: false
        });
        CountDown();
    }
}

