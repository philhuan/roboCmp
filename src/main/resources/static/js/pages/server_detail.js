

var server_detail={
    refresh:function () {
        layui.use('table', function(){
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#nodes'
                ,height: 312
                //,url: '/demo/table/user/' //数据接口
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'pod', title: 'pod',  sort: true, fixed: 'left'}
                    ,{field: 'ip', title: 'ip'}
                    ,{field: 'image_version', title: '镜像版本'}
                    ,{field: 'update_time', title: '更新时间'}
                    ,{field: 'host_ip', title: '宿主机ip'}
                    ,{field: 'status', title: '状态'}
                ]],
                data:[
                    {pod:" GaitOptimization-2007220645-473vj",ip:"172.17.0.5",image_version:"GaitOptimization:v0.0.1",update_time:"2020-04-12 15:24",host_ip:"192.168.61.142",status:"Running" },
                    {pod:" GaitOptimization-2007220645-w069t",ip:"172.17.0.6",image_version:"GaitOptimization:v0.0.1",update_time:"2020-04-12 15:25",host_ip:"192.168.61.141",status:"Running" },
                ]
            });

        });
    }
}

