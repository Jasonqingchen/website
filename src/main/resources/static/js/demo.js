new Vue({
    el: '#app',
    data() {
        return {
            demodialog:false,
            activeName:'first',
            startTime: '',
            endTime: '',
            countnumber:0,
            form:{},
            orRuleForm: {
                orFormList: [
                    {
                        stiem: "",
                        etime: "",
                        indexid:''
                    },
                ],
            },

        }
    },

//初始化
    mounted: function () {

    },
    //方法事件
    methods: {
        demo(val){
            this.demodialog=true;

        },
        //标签触发事件
        handleClick(tab, event) {
            console.log(tab, event);
        },
        //操作动态增减
        addOrHanlder(index) {
            let obj = {
                stiem: "",
                etime: "",
                indexid: index+1
            };
            this.orRuleForm.orFormList.push(obj);
        },
        //表单删除行
        deleteOrHandler(every, index) {
            this.orRuleForm.orFormList.splice(index, 1);
        },
        //保存
        orSaveHandler(){
            var from = this.orRuleForm;
            var list = JSON.stringify(from.orFormList);
            var d = {
                'list': list,
            };
            var newthis = this;
            //请求后台
            var url = '/xx/xx';
            $.ajax({
                type: 'POST',
                url: url,
                data: d,
                dataType: 'json',
                success: function (result) {
                    //返回值 1 表成功 返回 2 表失败
                    if (result == 1) {
                        newthis.$message({
                            message: '设置成功',
                            type: 'success'
                        });
                    } else {
                        newthis.$message.error('设置失败');
                    }
                },
                error: function () {
                    console.log('error submit!!');
                    return false;
                }
            });
            console.log('submit!');
        },
        //取消
        fh () {

            this.demodialog = false;
        },
    }
})