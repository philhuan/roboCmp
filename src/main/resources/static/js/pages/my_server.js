console.log('my_server')
let services;






function getServers() {
    namespace_name = namespaces[namespace_id].metadata.name
    let url = host + "/services/list"
    $.get(url, function (res) {
        console.log(res)
        if (res.code !== 0) {
            layer.msg(url + ' 连接失败,msg:' + res.message)
        } else {
            services = res.data
            console.log(services)
        }
        renderService()
        loadNodes()
    })
}

function renderService() {
    layui.use('table', function () {

        console.log(services)

        let data = []
        let metadata;
        for (let i = 0; i < services.length; i++) {

            metadata = services[i]['metadata']
            console.log(metadata)
            if (metadata.name === 'kubernetes') {
                continue
            }
            let line = {
                'server': metadata.name,
                'creationTime': metadata.creationTimestamp,
                'ip': services[i].spec.clusterIP,
                'port': services[i]['spec']['ports'][0]['port']
            }
            data.push(line)
        }




        const table = layui.table;

        //第一个实例
        table.render({
            elem: '#my_server'
            , height: 312
            //,url: '/demo/table/user/' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'server', title: '服务名', sort: true, fixed: 'left'}
                , {field: 'creationTime', title: '创建时间',}
                , {field: 'ip', title: 'IP', sort: true}
                ,{field: 'port', title: 'port', sort: true}
                , {fixed: 'right', align: 'center', toolbar: '#barDemo'}
            ]],
            data: data
        });


        //监听工具条
        table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if (layEvent === 'detail') { //查看
                //do somehing
                // loadPage('server_detail')
                console.log(data.server)

                goto("server_detail.html?server="+data.server)
                console.log('detail')
            } else if (layEvent === 'del') { //删除
                layer.confirm('真的删除行么', function (index) {
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if (layEvent === 'edit') { //编辑
                console.log('edit')
                //do something

                //同步更新缓存对应的值
                obj.update({
                    username: '123'
                    , title: 'xxx'
                });
            } else if (layEvent === 'LAYTABLE_TIPS') {
                layer.alert('Hi，头部工具栏扩展的右侧图标。');
            }
        });

    });
}

