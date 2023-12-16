//工具方法
export default {
    //将form表单值转换成对象形式的 json 字符串
    getObjectJson: function (value) {
        let data = {};
        $.each(value, function (index, item) {
            data[item.name] = item.value;
        });
        return JSON.stringify(data);
    },

    //从session中获取类对象，根据 key
    getObjectBySession: function (key) {
        let obj = sessionStorage.getItem(key);
        return JSON.parse(obj);
    },

    //对时间进行格式化处理
    timeFormat: function (time) {
        const date = new Date(time);

        const year = date.getFullYear().toString();
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const day = date.getDate().toString().padStart(2, '0');
        const hours = date.getHours().toString().padStart(2, '0');
        const minutes = date.getMinutes().toString().padStart(2, '0');
        const seconds = date.getSeconds().toString().padStart(2, '0');

        return `${year}年${month}月${day}日 ${hours}时${minutes}分${seconds}秒`;
    },

    //获取传入的 url ? 后面的参数
    //name 是参数名
    getUrlParam: function (url, name) {
        //取得 url 地址后的所有参数
        let params = url.split("?")[1].split("&");

        let paramObject = {};
        //遍历
        params.forEach(item => {
            paramObject[item.split("=")[0]] = item.split("=")[1];
        });

        if (name) {
            return paramObject[name];
        } else {
            return paramObject;
        }
    },

    //获取地址最后一个 / 后面的值
    getUrlLastVal: function (href) {
        return href.substring(href.lastIndexOf("/") + 1);
    }
}