console.log('my_image')

var images;

$(function () {
    getImages()
})

function getImages() {
    let url = host + "/docker/images"
    $.get(url, function (res) {
        console.log(res)
        if (res.code !== 0) {
            layer.msg(url + ' 连接失败,msg:' + res.message)
        } else {
            images = res.data
            console.log(images)
        }
        renderImages()
    })
}

function renderImages() {



    layui.use('table', function(){

        if (images === null) {
            console.log("image load error")
        }
        data = []
        for (let i = 0; i < images.length ; i++) {
            let name = images[i].RepoTags

            if (!name  || name[0] === "<none>:<none>") {
                continue
            }
            images[i].Size/=1024*1024
            images[i].Size = images[i].Size.toFixed(2)
            data.push(images[i])
        }




        var table = layui.table;

        //第一个实例
        table.render({
            elem: '#my_image'
            ,height: 600
            //,url: '/demo/table/user/' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'RepoTags', title: '镜像',  sort: true, fixed: 'left'}
                ,{field: 'Id', title: 'id'}
                ,{field: 'Size', title: '大小(M)'}
                , {fixed: 'right', align: 'center', toolbar: '#bar_my_image'}
            ]],
            data:data
        });

        //监听工具条
        table.on('tool(bar_my_image)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
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
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                    console.log(data,index)
                });
            } else if (layEvent === 'use') { //使用
                image = data.RepoTags
                console.log('use',image)
                useImage(namespaces[namespace_id].metadata.name,getQueryVariable("server"),data.RepoTags)


                //do something

                //同步更新缓存对应的值
                // obj.update({
                //     username: '123'
                //     , title: 'xxx'
                // });
            } else if (layEvent === 'LAYTABLE_TIPS') {
                layer.alert('Hi，头部工具栏扩展的右侧图标。');
            }
        });

    });
}

function useImage(namespace, server, image) {

    let url = host + "/services/set_image?namespace="+namespace+"&service="+server+"&image="+encodeURI(image)
    $.get(url, function (res) {
        console.log(res)
        if (res.code !== 0) {
            layer.msg(url + ' 连接失败,msg:' + res.message)
        } else {
            alert("修改成功")
            refresh()
        }
        renderImages()
    })
}