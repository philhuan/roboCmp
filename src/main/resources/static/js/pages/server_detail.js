var pods;



function loadNodes() {
    let namespace_name = namespaces[namespace_id].metadata.name
    let url = host + "/pods/list?service="+getQueryVariable("server")
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

function renderPods() {

    layui.use('table', function(){
        console.log(pods)

        let data = []
        let metadata;
        let pod;
        for (let i = 0; i < pods.length; i++) {
            metadata = pods[i]['metadata']
            console.log(metadata)
            pod = pods[i]
            let line = {
                'pod': metadata.name,
                'ip': pod.status.podIP,
                'image_version': pod.spec.containers[0].image,
                'port': pod['spec'].containers[0]['ports'][0]['containerPort'],
                'status':JSON.stringify(pod.status.containerStatuses[0].state)
            }
            data.push(line)
        }



        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#nodes'
             ,height: 600
            //,url: '/demo/table/user/' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'pod', title: 'pod',  sort: true, fixed: 'left'}
                ,{field: 'ip', title: 'ip'}
                ,{field: 'image_version', title: '镜像版本'}
                ,{field: 'status', title: '状态'}
            ]],
            data:data
        });
        $('#server_name').text(getQueryVariable("server"))

    });
}
