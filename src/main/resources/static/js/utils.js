function getQueryVariable(variable) {
    const query = window.location.search.substring(1);
    console.log(query)
    const vars = query.split("&");
    for (let i = 0; i < vars.length; i++) {
        let pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return (false);
}

function replaceParamVal(paramName, replaceWith) {
    var oUrl = this.location.href.toString();
    var re = eval('/(' + paramName + '=)([^&]*)/gi');
    var nUrl = oUrl.replace(re, paramName + '=' + replaceWith);
    this.location = nUrl;
    window.location.href = nUrl
}

function replaceParamValNotRefresh(paramName, replaceWith) {
    var oUrl = this.location.href.toString();
    var re = eval('/(' + paramName + '=)([^&]*)/gi');
    var nUrl = oUrl.replace(re, paramName + '=' + replaceWith);
    window.history.pushState({}, 0, nUrl);
}

function setUrlNotRefresh(url) {
    window.history.pushState({}, 0, url);
}

function refresh() {
    window.location.href = this.location.href
}


function addParamVal(paramName, val) {
    var oUrl = this.location.href.toString();
    var nUrl = oUrl + "&"+paramName+"="+val
    setUrlNotRefresh(nUrl)
}

function replaceOrAddParamVal(paramName, replaceWith) {
    if (!getQueryVariable(paramName)) {
        addParamVal(paramName, replaceWith)
    }else {
        replaceParamValNotRefresh(paramName, replaceWith)
    }
}

function goto(url) {
    location.href=url
}
