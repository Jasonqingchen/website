new Vue({
    el: '#app',
    data() {
        return {
            proname:'',
            activeIndex2:'',
            uurl:'',
            nname:'',
            details:'',
            prices:'',
            dialogVisible:false,
            dialogVisiblec:false,
            lis:[],
            form:{
                email:'',
                company:'',
                phone:'',
                counts:'',
                pname:'',
                youname:'',
                address:'',
                message:''
            }

        }
    },
/*
{detail:'Product description: Manufacturer Supplier China Cheap Multifunctional Lemon Juicer Extractor Machine',name:'TV',url:'http://www.clfgoodlife.com/Uploads/65489437c126e.jpg',price:'222$'},
{detail:'Product description: Manufacturer Supplier China Cheap Multifunctional Lemon Juicer Extractor Machine',name:'void1',url:'http://www.clfgoodlife.com/Uploads/654894592225e.jpg',price:'222$'},
{detail:'Product description: Manufacturer Supplier China Cheap Multifunctional Lemon Juicer Extractor Machine',name:'void2',url:'http://www.clfgoodlife.com/Uploads/654894818da4a.jpg',price:'222$'},
{detail:'Product description: Manufacturer Supplier China Cheap Multifunctional Lemon Juicer Extractor Machine',name:'void3',url:'http://www.clfgoodlife.com/Uploads/654894818da4a.jpg',price:'222$'},
*/

//初始化
    mounted: function () {
        var p =sessionStorage.getItem("p");
        var proname =sessionStorage.getItem("proname");
        var newthis = this;

        if(p!=null && p!=null){
            //点击标签弹出列表
            var url = '/search';
            $.ajax({
                type: 'POST',
                url: url,
                data:{ptype:p},
                dataType: 'json',
                success: function (result) {
                    document.getElementById("ts").innerText=" ";
                    if(result.length==0){
                        document.getElementById("ts").innerText="Sorry Don't Find Product";
                    }
                    newthis.lis=result;
                },
                error: function () {
                    console.log('error submit!!');
                    return false;
                }
            });
        }


        if(proname!=null && proname!=null){
            //主页全局搜索
            var url = '/zyqjsearch';
            $.ajax({
                type: 'POST',
                url: url,
                data:{pname:proname},
                dataType: 'json',
                success: function (result) {
                    document.getElementById("ts").innerText=" ";
                    if(result.length==0){
                        document.getElementById("ts").innerText="Sorry Don't Find Product";
                    }
                    newthis.lis=result;
                },
                error: function () {
                    console.log('error submit!!');
                    return false;
                }
            });
        }


    },
    //方法事件
    methods: {
        detail(val){
            this.uurl = val.purl;
            this.nname = val.pname;
            this.details = val.detail;
            this.prices = val.price;
            //打开详情页
            this.dialogVisible=true;

        },
        send(){
            this.form=[];
            this.form.pname = this.nname;
            //打开详情页
            this.dialogVisiblec=true;

        },
        //提交客户信息
        onSubmit(){
            var addForm = this.form;
            if (addForm.phone=="" || addForm.phone==null){
                this.$notify.error({
                    title: 'phone equal null',
                    offset: 300
                });
                return;
            }
            if (addForm.email=="" || addForm.email==null){
                this.$notify.error({
                    title: 'email equal null',
                    offset: 300
                });
                return;
            }
            var newthis = this;
            var d={
                'phone': addForm.phone,
                'company': addForm.company,
                'counts': addForm.counts,
                'pname': addForm.pname,
                'youname': addForm.youname,
                'address': addForm.address,
                'content': addForm.content,
                'message': addForm.message,
                'email':addForm.email
            }
            var url = '/add';
            $.ajax({
                type: 'POST',
                url: url,
                data: d,
                dataType: 'json',
                success: function (result) {
                    newthis.dialogVisiblec=false;
                    if (result == 1) {
                        newthis.addkun = false;
                        newthis.$notify({
                            title: 'success',
                            type: 'success',
                            offset: 300
                        });
                    } else {
                        newthis.$message.error('很遗憾，发送失败');
                    }
                },
                error: function () {
                    console.log('error submit!!');
                    return false;
                }
            });

        },
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(done) {
            this.$confirm('true close？')
                .then(_ => {
                    done();
                })
                .catch(_ => {});
        },
        handleCloses(done) {
            this.$confirm('true close？')
                .then(_ => {
                    done();
                })
                .catch(_ => {});
        },
        //'全局搜索
        search(){
            var newthis =this;
            var url = '/searchqj';
            $.ajax({
                type: 'POST',
                url: url,
                data: {"proname":this.proname },
                dataType: 'json',
                success: function (result) {
                    document.getElementById("ts").innerText=" ";
                    if(result.length==0){
                        document.getElementById("ts").innerText="Sorry Don't Find Product";
                    }
                    newthis.lis=result;

                },
                error: function () {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
    }
})