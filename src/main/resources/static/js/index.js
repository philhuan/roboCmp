var app = {
    host: "http://localhost:8080",
    namespaces: undefined,
    namespace_id: 0,
    scriptMap: {},
    refresh: function () {
        this.scriptMap = {

            "monitor": monitor,
            "my_server": my_server,
            "my_image": my_image,
            "flow": flow,
            "result": result,
            "server_detail": server_detail
        }

        this.router()
        this.getNamespaces()


        //一般直接写在一个js文件中
        layui.use([
            'layer',
            'form',
            'element',
        ], function () {
            var layer = layui.layer,
                form = layui.form;

            // layer.msg('Hello World');
        });
    },
    loadPage: function loadPage(pageId) {
        $("#body").load("pages/" + pageId + ".html", function (responseTxt, statusTxt, xhr) {
            // $.getScript("js/pages/" + pageId + ".js");
            this.scriptMap[pageId].refresh()
            replaceParamValNotRefresh('page', pageId)
        });
    },
    renderNamespace: function renderNamespace() {
        $("#now_namespace").text(namespaces[this.namespace_id].metadata.name)
        let s = ""
        for (let i = 0; i < this.namespaces.length; i++) {
            let name = this.namespaces[i].metadata.name
            s += '<dd><a namespace_id="' + i + '" href="javascript:switchNamespace(' + i + ')">' + name + '</a></dd>'
        }
        console.log(s)
        $('#namespaces').html(s)
    },
    switchNamespace:function switchNamespace(id) {
        this.namespace_id = id
        this.renderNamespace()
    },

    getNamespaces:function getNamespaces() {
    let url = this.host + "/namespaces/list"
    $.get(url, function (res) {
        //console.log(res)
        if (res.code !== 0) {
            layer.msg(url + ' 连接失败,msg:' + res.message)
        } else {
            this.namespaces = res.data
        }
        this.renderNamespace()
    })
},

    router:function router() {
    let page = getQueryVariable('page')
    if (!page) {
        page = 'my_server'
    }
    this.namespace_id = getQueryVariable('namespace_id')
    if (!this.namespace_id) {
        this.namespace_id = 0
    }
        this.loadPage(page)
}



}

$(function () {
    app.refresh()
})






