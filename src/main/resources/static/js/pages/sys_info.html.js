var sys_info;
var cpuInfo = []
var memInfo = []
var init = false

function loadSysInfo() {
    let url = host + "/sys/get"
    $.get(url, function (res) {
        console.log(res)
        if (res.code !== 0) {
            layer.msg(url + ' 连接失败,msg:' + res.message)
        } else {
            sys_info = res.data
            console.log(sys_info)
            if (!init) {
                img()
                init = true;
            }

            updateSysInfo()
        }

    })
}
function clear() {

    if (cpuInfo.length > 30) {
        let data;
        data = []
        for (let i = cpuInfo.length - 20; i < cpuInfo.length; i++) {
            data.push(cpuInfo[i])
        }

        cpuInfo = data

        data = []
        for (let i = memInfo.length - 20; i < memInfo.length; i++) {
            data.push(memInfo[i])
        }

        memInfo = data
    }
}

function updateSysInfo() {
    layui.use('element', function(){
        var element = layui.element;
        element.progress('cpu', sys_info.cpuLoad.toFixed(2)+"%");
        element.progress('mem', ((sys_info.usedPhysicalMemorySize/sys_info.physicalMemorySize)*100).toFixed(2)+"%");
            // 100 - (((sys_info.physicalMemorySize-sys_info.usedPhysicalMemorySize)*100).toFixed(2)));
        cpuInfo.push(sys_info.cpuLoad.toFixed(2))
        if (cpuInfo.length >20) {
            clear()
        }
        memInfo.push(((sys_info.usedPhysicalMemorySize/sys_info.physicalMemorySize)*100).toFixed(2))
        // if (memInfo.length >20) {
        //     memInfo.pop()
        // }
        $("#totalMem").text(sys_info.physicalMemorySize.toFixed(4)+"G")
        $("#processorCount").text(sys_info.processorCount)
        setTimeout(function () {
            loadSysInfo()


        },1000)
    });
}



$(function () {
    loadSysInfo()
})


function img() {
    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    function activeLastPointToolip(chart) {
        var points = chart.series[0].points;
        chart.tooltip.refresh(points[points.length -1]);
    }
    var chart = Highcharts.chart('cpu', {
        chart: {
            type: 'spline',
            marginRight: 10,
            events: {
                load: function () {
                    var series = this.series[0],
                        chart = this;
                    activeLastPointToolip(chart);
                    setInterval(function () {
                        var x = (new Date()).getTime(), // 当前时间
                            y = parseFloat(sys_info.cpuLoad.toFixed(2));          // 随机值
                        series.addPoint([x, y], true, true);
                        activeLastPointToolip(chart);
                    }, 1000);
                }
            }
        },
        title: {
            text: 'CPU'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: null
            }
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                    Highcharts.numberFormat(this.y, 2);
            }
        },
        legend: {
            enabled: false
        },
        series: [{
            name: 'CPU',
            data: (function () {
                // return cpuInfo
                // var series = this.series[0]
                // var x = (new Date()).getTime(), // 当前时间
                //     y = sys_info.cpuLoad.toFixed(2);          // 随机值
                // series.addPoint([x, y], true, true);
                // return series

                // 生成随机值
                var data = [],
                    time = (new Date()).getTime(),
                    i;
                //loadSysInfo()
                var x = (new Date()).getTime(), // 当前时间
                    y = sys_info.cpuLoad.toFixed(2);          // 随机值

                for (i = -19; i <= 0; i += 1) {

                    data.push({
                        x: time + i * 1000,
                        y: 0//get(i)
                    });
                }
                return data;
            }())
        }]
    });

    Highcharts.setOptions({
        global: {
            useUTC: false
        }
    });
    function activeLastPointToolip(chart) {
        var points = chart.series[0].points;
        chart.tooltip.refresh(points[points.length -1]);
    }
    var chart = Highcharts.chart('mem', {
        chart: {
            type: 'spline',
            marginRight: 10,
            events: {
                load: function () {
                    var series = this.series[0],
                        chart = this;
                    activeLastPointToolip(chart);
                    setInterval(function () {
                        var x = (new Date()).getTime(), // 当前时间
                            y = parseFloat(((sys_info.usedPhysicalMemorySize/sys_info.physicalMemorySize)*100).toFixed(2));          // 随机值
                        series.addPoint([x, y], true, true);
                        activeLastPointToolip(chart);
                    }, 1000);
                }
            }
        },
        title: {
            text: '内存'
        },
        xAxis: {
            type: 'datetime',
            tickPixelInterval: 150
        },
        yAxis: {
            title: {
                text: null
            }
        },
        tooltip: {
            formatter: function () {
                return '<b>' + this.series.name + '</b><br/>' +
                    Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                    Highcharts.numberFormat(this.y, 2);
            }
        },
        legend: {
            enabled: false
        },
        series: [{
            name: '内存',
            data: (function () {
                // return cpuInfo
                // var series = this.series[0]
                // var x = (new Date()).getTime(), // 当前时间
                //     y = sys_info.cpuLoad.toFixed(2);          // 随机值
                // series.addPoint([x, y], true, true);
                // return series

                // 生成随机值
                var data = [],
                    time = (new Date()).getTime(),
                    i;
                //loadSysInfo()
                var x = (new Date()).getTime(), // 当前时间
                    y = sys_info.cpuLoad.toFixed(2);          // 随机值

                for (i = -19; i <= 0; i += 1) {

                    data.push({
                        x: time + i * 1000,
                        y: 0//get(i)
                    });
                }
                return data;
            }())
        }]
    });
}

function get(i) {
    if (-i >= cpuInfo.length ) {
        return 0;
    }
    return parseFloat(cpuInfo[i+cpuInfo.length])
}