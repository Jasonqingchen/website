new Vue({
    el: '#app',
    data() {
        return {
            urlss:'/container/shouye'
        }
    },

    //初始化
    mounted: function () {
    },
    //方法事件
    methods: {
        //退出
        out() {
            var url = '/login/out';
            $.ajax({
                type: 'POST',
                url: url,
                dataType: 'text',
                success: function (res) {
                    if (res=="success") {
                        window.location.href=urls+"/login";
                    }
                },
                error: function () {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        sel(key, keyPath) {
            if(key=="1") {
                this.urlss="/container/shouye";
            }
            if(key=="2") {
                this.urlss="/ec/ec";
            }
            if(key=="3") {
                this.urlss="/setting/setting";
            }
            if(key=="4") {
               // this.urlss="https://chat.openai.com/";
            }
        },

    }
})