
$(function () {
    // refresh()
    // this.router()
   // this.getNamespaces()


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
})

function router() {
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



function loadPage(pageId) {
    $("#body").load("pages/" + pageId + ".html", function (responseTxt, statusTxt, xhr) {
        // $.getScript("js/pages/" + pageId + ".js");
        this.scriptMap[pageId].refresh()
        replaceParamValNotRefresh('page', pageId)
    });
}
