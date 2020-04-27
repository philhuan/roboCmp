console.log('namespace')
var namespaces = undefined
var namespace_id = 0

function getNamespaces() {
    let url = host + "/namespaces/list"
    $.get(url, function (res) {
        //console.log(res)
        if (res.code !== 0) {
            layer.msg(url + ' 连接失败,msg:' + res.message)
        } else {
            namespaces = res.data
        }
        renderNamespace()
        getServers()

    })
}

$(function () {
    getNamespaces()
})

function switchNamespace(id) {
    namespace_id = id
    getNamespaces()
}

function renderNamespace() {
    $("#now_namespace").text(namespaces[namespace_id].metadata.name)
    let s = ""
    for (let i = 0; i < namespaces.length; i++) {
        let name = namespaces[i].metadata.name
        s += '<dd><a namespace_id="' + i + '" href="javascript:switchNamespace(' + i + ')">' + name + '</a></dd>'
    }
    console.log(s)
    $('#namespaces').html(s)

}