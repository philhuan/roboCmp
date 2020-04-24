console.log('result')

var result = {
    refresh:function () {

        layui.use('table', function(){
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#result'
                ,height: 312
                //,url: '/demo/table/user/' //数据接口
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'server', title: '服务名',  sort: true, fixed: 'left'}
                    ,{field: 'pod', title: 'pod'}
                    ,{field: 'describe', title: '描述'}
                    ,{field: 'param', title: '参数'}
                    ,{field: 'result', title: '结果'}
                ]],
                data:[
                    {server:" GaitOptimization", pod : "GaitOptimization-2007220645-473vj",describe:"步态优化",param:"{1,2,3}",result:"12.2"},
                    {server:" BigFeet", pod : "BigFeet-2007220645-w9fkz",describe:"大脚",param:"{1,2,3}",result:"15.3"},
                ]
            });

        });
    }
}
