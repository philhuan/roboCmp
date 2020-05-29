let pods;

let podRefresh = 0

function loadNodes() {
    let namespace_name = namespaces[namespace_id].metadata.name
    let url = host + "/pods/list?service=" + getQueryVariable("server")
    $.get(url, function (res) {
        console.log(res)
        if (res.code !== 0) {
            layer.msg(url + ' 连接失败,msg:' + res.message)
        } else {
            pods = res.data
            console.log(pods)
        }
        renderPods()
    })
}

$(function () {
//注意：选项卡 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function () {
        var element = layui.element;

        //…
    });



    // refreshPods()
})

function scale(n) {
    let namespace_name = namespaces[namespace_id].metadata.name
    let url = host + "/services/scale?service=" + getQueryVariable("server")+"&namespace="+namespace_name+"&n="+n
    $.get(url, function (res) {
        console.log(res)
        if (res.code !== 0) {
            layer.msg(url + ' 连接失败,msg:' + res.message)
        } else {
            layer.msg("修改成功")
            refresh()
        }

    })
}

function renderPods() {


    layui.use('table', function () {
        console.log(pods)

        let data = []
        let metadata;
        let pod;
        for (let i = 0; i < pods.length; i++) {
            metadata = pods[i]['metadata']
            // console.log(metadata)
            pod = pods[i]
            let line = {
                'pod': metadata.name,
                'ip': pod.status.podIP,
                'image_version': pod.spec.containers[0].image,
                'port': pod['spec'].containers[0]['ports'][0]['containerPort'],
                'status': pod.status['phase']
            }
            if (pod.metadata.deletionTimestamp) {
                line.status = 'Terminating'
                podRefresh ++
                setTimeout(refreshPods, 1000);

            }else if (line.status === 'Pending') {
                podRefresh ++
                setTimeout(refreshPods, 1000);
            } else {

            }
            data.push(line)
        }


        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#nodes'
            , height: 700
            //,url: '/demo/table/user/' //数据接口
            , page: true //开启分页
            , cols: [[ //表头
                {field: 'pod', title: 'docker', sort: true, fixed: 'left'}
                , {field: 'ip', title: 'ip',sort: true}
                , {field: 'image_version', title: '镜像版本'}
                , {field: 'status', title: '状态'}
                //, {fixed: 'right', align: 'center', toolbar: '#bar_my_pod'}
            ]]
            , limit:20
            , data: data
        });
        //监听工具条
        table.on('tool(bar_my_pod)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

            if (layEvent === 'detail') { //查看
                //do somehing
                // loadPage('server_detail')
                console.log(data)

                console.log('detail')
            } else if (layEvent === 'del') { //删除
                layer.confirm('真的删除行么', function (index) {
                    // obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                    deletePod(data.pod)
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
        $('#server_name').text(getQueryVariable("server"))

    });
}

function refreshPods() {
    if (podRefresh === 1) {
        loadNodes()
    }

    // if (podRefresh) {
    //     renderPods()
    // }
    console.log("refresh")
    podRefresh --
}

function deletePod(podName) {
    if (!podName){
        return
    }
    let url = host + "/pods/delete?podName=" + podName
    $.get(url, function (res) {
        console.log(res)
        if (res.code !== 0 || res.data !== true) {
            layer.msg(url + ' 删除失败,msg:' + res.message)
            console.log(res.message)
        } else {
            //pods = res.data
            //console.log(pods)
        }
        setTimeout(loadNodes, 1000);
    }).fail(function (res) {
        layer.msg(url + ' 删除失败,msg:' + res.message)
        console.log(res.message)
    })

}
