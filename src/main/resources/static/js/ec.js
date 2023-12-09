new Vue({
    el: '#app',
    data() {
        return {
            da:[]
        }
    },

    //初始化
    mounted: function () {
        this.yue();
        this.dd();
    },
    //方法事件
    methods: {
        //折线图
        yue() {
                //查询数据
                var url = '/ec/selectHgAndOrder';
                $.ajax({
                    type: 'POST',
                    url: url,
                    dataType: 'json',
                    success: function (res) {
                        var chartDom = document.getElementById('yue');
                        var myChart = echarts.init(chartDom);
                        var option;

                        option = {
                            title: {
                                text: 'Changes in orders and containers within one year'
                            },
                            tooltip: {
                                trigger: 'axis'
                            },
                            legend: {},
                            toolbox: {
                                show: true,
                                feature: {
                                    dataZoom: {
                                        yAxisIndex: 'none'
                                    },
                                    dataView: { readOnly: false },
                                    magicType: { type: ['line', 'bar'] },
                                    restore: {},
                                    saveAsImage: {}
                                }
                            },
                            xAxis: {
                                type: 'category',
                                boundaryGap: false,
                                data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月']
                            },
                            yAxis: {
                                type: 'value',
                                axisLabel: {
                                    formatter: '{value}'
                                }
                            },
                            series: [
                                {
                                    name: '货柜数量',
                                    type: 'line',
                                    data: res[0],
                                    markPoint: {
                                        data: [
                                            { type: 'max', name: 'Max' },
                                            { type: 'min', name: 'Min' }
                                        ]
                                    },
                                    markLine: {
                                        data: [{ type: 'average', name: 'Avg' }]
                                    }
                                },
                                {
                                    name: '订单数量',
                                    type: 'line',
                                    data: res[1],
                                    markPoint: {
                                        data: [
                                            { type: 'max', name: 'Max' },
                                            { type: 'min', name: 'Min' }
                                        ]
                                    },
                                    markLine: {
                                        data: [
                                            { type: 'average', name: 'Avg' },
                                            [
                                                {
                                                    symbol: 'none',
                                                    x: '90%',
                                                    yAxis: 'max'
                                                },
                                                {
                                                    symbol: 'circle',
                                                    label: {
                                                        position: 'start',
                                                        formatter: 'Max'
                                                    },
                                                    type: 'max',
                                                    name: '最高点'
                                                }
                                            ]
                                        ]
                                    }
                                }
                            ]
                        };
                        myChart.setOption(option);

                    },
                    error: function () {
                        console.log('error submit!!');
                        return false;
                    }
                });
        },
        //饼状图
        dd() {
            var chartDom = document.getElementById('dd');
            var myChart = echarts.init(chartDom);
            var option;
            //调用数据并渲染饼状图
            var url = '/ec/selectByjxc';
            $.ajax({
                type: 'POST',
                url: url,
                dataType: 'json',
                success: function (res) {
                    /*for (var i=0;i<res.length;i++){
                        newthis.da.add(res[i]);
                    }*/
                    option = {
                        title: {
                            text: 'order count',
                            subtext: 'data detail',
                            left: 'center'
                        },
                        tooltip: {
                            trigger: 'item'
                        },
                        legend: {
                            orient: 'vertical',
                            left: 'left'
                        },
                        series: [
                            {
                                name: 'Access From',
                                type: 'pie',
                                radius: '50%',
                                data: res,
                                emphasis: {
                                    itemStyle: {
                                        shadowBlur: 10,
                                        shadowOffsetX: 0,
                                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                                    }
                                }
                            }
                        ]
                    };
                    myChart.setOption(option);
                    },
        error: function () {
            console.log('error submit!!');
            return false;
        }
    });

        },
    }
})