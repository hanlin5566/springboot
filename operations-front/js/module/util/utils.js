define([],
        function() {

    var Export = {};
    Export.getUrlParam = function() {
        var query = window.location.search;
        var start = query.indexOf('?');
        if (start > -1) {
            query = query.substring(start + 1);
        }

        var paras = {};
        if (query) {
            var queryParams = query.split('&');
            for (var k in queryParams) {
                var param = queryParams[k].split('=');
                paras[param[0]] = decodeURIComponent(param[1]) || "";
            }
        }
        return paras;
    };

    Export.createUrlParam = function(para) {
        var uri = [];
        for (var k in para) {
            uri.push(k + "=" + encodeURIComponent(para[k]));
        }

        return "?" + uri.join("&");
    };

    Export.serializeForm = function(id) {
        var arr = $("#" + id).serializeArray();

        var ret = {};
        for (var k in arr) {
            if (ret[arr[k].name]) {
                ret[arr[k].name] = this.toArray(ret[arr[k].name]);
                ret[arr[k].name].push(arr[k].value);
            } else {
                ret[arr[k].name] = arr[k].value;
            }
        }

        return ret;
    };

    Export.toArray = function(obj) {
        return (!obj || $.isArray(obj)) ? obj : [obj];
    };

    Export.inArr = function(arr, val) {
        if (!arr) {
            return false;
        }

        for (var i in arr) {
            if (arr[i] == val) {
                return true;
            }
        }

        return false;
    };

    Export.startWith = function(str, find){
        var reg = new RegExp("^"+find);
        return reg.test(str);
    };

    Export.endWith = function(str, find){
        var reg = new RegExp(find+"$");
        return reg.test(str);
    };

    Export.concat = function(arr1, arr2) {
        if (!arr1 || !arr2) {
            return;
        }

        $(arr2).each(function() {
            arr1.push(this);
        });
    };

    //手机账号*格式化
    Export.cellPhoneFormat = function(phone){
	    var mphone =phone.substr(3,4);
            var lphone = phone.replace(mphone,"****");
            return lphone;
    };
    //银行账号*格式化
    Export.bankNoFormat = function(bankNo){
	    var mbankNo =bankNo.substr(5,8);
            var lbankNo = bankNo.replace(mbankNo,"********");
            return lbankNo;
    };
    
    Export.checkDirty = function(main, data) {
        var me = this;
        if ($.type(main) == "object") {
            if ($.type(data) != "object") {
                return true;
            }

            for (var k in main) {
                if (main[k] != null) {
                    if (me.checkDirty(main[k], data[k])) {
                        return true;
                    }
                }
            }
        } else if ($.type(main) == "array") {
            if ($.type(data) != "array") {
                return true;
            }

            for (var k in main) {
                if (main[k] != null) {
                    if (me.checkDirty(main[k], data[k])) {
                        return true;
                    }
                }
            }
        } else {
            return ($.trim(main) != $.trim(data));
        }

        return false;
    };

    return Export;
});