const fs = require('fs');
const pathLib = require('path');

Date.prototype.format = function (format) {
    //eg:format="yyyy-MM-dd hh:mm:ss";

    if (!format) {
        format = "yyyy-MM-dd hh:mm:ss";
    }

    var o = {
        "M+": this.getMonth() + 1,  // month
        "d+": this.getDate(),       // day
        "H+": this.getHours(),      // hour
        "h+": this.getHours(),      // hour
        "m+": this.getMinutes(),    // minute
        "s+": this.getSeconds(),    // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
        "S": this.getMilliseconds()
    };

    if (/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    }

    for (var k in o) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1
                ? o[k]
                : ("00" + o[k]).substr(("" + o[k]).length));
        }
    }

    return format;
};

var myDate = new Date();
myDate.getYear();        //获取当前年份(2位)
myDate.getFullYear();    //获取完整的年份(4位,1970-????)
myDate.getMonth();       //获取当前月份(0-11,0代表1月)
myDate.getDate();        //获取当前日(1-31)
myDate.getDay();         //获取当前星期X(0-6,0代表星期天)
myDate.getTime();        //获取当前时间(从1970.1.1开始的毫秒数)
myDate.getHours();       //获取当前小时数(0-23)
myDate.getMinutes();     //获取当前分钟数(0-59)
myDate.getSeconds();     //获取当前秒数(0-59)
myDate.getMilliseconds();    //获取当前毫秒数(0-999)
myDate.toLocaleDateString();     //获取当前日期

var preDay = myDate.getDay();
if(preDay === 0){
    preDay = 7
}
//上个周日的零点
var preSunday =  new Date(myDate.getTime() - preDay*24*60*60*1000 -
    (myDate.getHours()*60*60*1000 + myDate.getMinutes()*60*1000 + myDate.getSeconds()*1000 +  myDate.getMilliseconds()))

//当前24小时分钟 映射为 3小时内秒
var now = myDate.getHours() * 60 + myDate.getMinutes()
var cloneNow = (now / (24*60))* 3*60*60;

var cloneHour = myDate.getDay();
if(cloneHour === 0){
    cloneHour = 7
}
cloneHour = cloneHour * 3;

var cloneTime = (cloneHour * 60 * 60 + cloneNow)*1000
preSunday = new Date(preSunday.getTime() + cloneTime)
//2017-11-10T22:30:12+08:00
var haha = preSunday.format("yyyy-MM-ddTHH:mm:ss+08:00")

var a = fs.readFileSync(pathLib.join(__dirname ,'commit.sh')).toString()
var b = a.replace(/GIT_AUTHOR_DATE=".*?"/g,'GIT_AUTHOR_DATE="'+ haha +'"')
b = b.replace(/GIT_COMMITTER_DATE=".*?"/g,'GIT_COMMITTER_DATE="'+ haha +'"')
fs.writeFileSync(pathLib.join(__dirname ,'commit.sh'), b, 'utf8');

var exec = require('child_process').exec;
var cmdStr = 'chmod 777 '+pathLib.join(__dirname, './commit.sh');
exec(cmdStr, function(err,stdout,stderr){
    if(err) {
        console.log('error:'+stderr);
    } else {
        console.log(stdout);
    }
});

cmdStr = 'sh '+pathLib.join(__dirname, './commit.sh');
exec(cmdStr, function(err,stdout,stderr){
    if(err) {
        console.log('error:'+stderr);
    } else {
        console.log(stdout);
    }
});
