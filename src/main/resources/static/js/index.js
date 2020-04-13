//一般直接写在一个js文件中
layui.use(['layer', 'form', 'element'], function() {
	var layer = layui.layer,
		form = layui.form;

	layer.msg('Hello World');
});

function loadPage(pageId) {
	$("#body").load("pages/" + pageId + ".html");
}

$(function() {
	loadPage('my_server')
})
