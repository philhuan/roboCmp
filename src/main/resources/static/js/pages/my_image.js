console.log('my_image')

var my_image ={
    refresh:function () {

        layui.use('table', function(){
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#my_image'
                ,height: 312
                //,url: '/demo/table/user/' //数据接口
                ,page: true //开启分页
                ,cols: [[ //表头
                    {field: 'id', title: '镜像id',  sort: true, fixed: 'left'}
                    ,{field: 'image_version', title: '镜像版本'}
                    ,{field: 'update_time', title: '更新时间'}
                    ,{field: 'describe', title: '描述'}
                ]],
                data:[
                    {id:12 ,image_version:"GaitOptimization:v0.0.1",update_time:"2020-04-10 15:15",describe:"步态优化第一版" },
                    {id:13 ,image_version:"GaitOptimization:v0.0.2",update_time:"2020-04-12 10:50",describe:"步态优化第二版，修改了评价函数"},
                ]
            });

        });
    }
}
