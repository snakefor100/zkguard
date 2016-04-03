function initLeftPanel() {
    var setting = {
        data: {
            simpleData: {
                enable: true,
            }
        },
        callback: {
            onClick: zTreeOnClick
        }
    };
    //初始化左侧菜单
    $.fn.zTree.init($("#monitorTree"), setting, monitorMenu);
    $.fn.zTree.init($("#sysSetting"), setting, sysSettingMenu);
}

function zTreeOnClick(event, treeId, treeNode, clickFlag) {
    if (treeNode.click != false) {
        open(treeNode.name, treeNode.actionUrl);
    }
}

// 开启一个新的tab页面
function open(text, url) {
    if ($("#mainTabs").tabs('exists', text)) {
        $('#mainTabs').tabs('select', text);
    } else {
        var content = '<div style="width:100%;height:100%;overflow:hidden;">'
            + '<iframe src="'
            + url
            + '" scrolling="auto" style="width:100%;height:100%;border:0;" ></iframe></div>';

        $('#mainTabs').tabs('add', {
            title: text,
            content: content,
            closable: true
        });
    }
}