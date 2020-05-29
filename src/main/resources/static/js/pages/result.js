console.log('result')

var results;

function getResults() {
    let service = getQueryVariable("server")
    if (!service) {
        service = ""
    }
    let url = host + "/result/list?service=" + service
    $.get(url, function (res) {
        //console.log(res)
        if (res.code !== 0) {
            layer.msg(url + ' 连接失败,msg:' + res.message)
        } else {
            results = res.data
        }
        refreshResults()
    })
}

$(function () {
    getResults()
})


function refreshResults() {
    layui.use('table', function () {
        var table = layui.table;

        let data = []
        for (let result of results) {
            data.push(
                {
                    server: result.service,
                    pod: result.container,
                    params: result.params,
                    result: result.result
                }
            )
        }


        //第一个实例
        table.render({
            elem: '#result'
            , height: 312
            //,url: '/demo/table/user/' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'server', title: '服务名', sort: true, fixed: 'left'}
                , {field: 'pod', title: 'pod'}
                , {field: 'params', title: '参数'}
                , {field: 'result', title: '结果'}
            ]],
            data: data
        });

    });
}
