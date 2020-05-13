var init = true;
var provinceChange = true;
var defaultProvince = "";
var defaultCity = "";
var defaultArea = "";
$(document).ready(function () {
    defaultProvince = $("#js-province").attr("js-default");
    defaultCity = $("#js-city").attr("js-default");
    defaultArea = $("#js-area").attr("js-default");
    console.log(defaultProvince)
    console.log(defaultCity)
    console.log(defaultArea)
    area.provinceList();
    $("#js-province").change(function () {
        provinceChange = true;
        var code = $(this).children('option:selected').attr("js-code");
        area.cityList(code);
        $("#js-area").html("");
    });
    $("#js-city").change(function () {
        var code = $(this).children('option:selected').attr("js-code");
        area.areaList(code);
    });

})

var area = {
    loadingIndex :0,
    provinceList : function () {
        init = false;
        area.loadingIndex = jeesnsDialog.loading();
        jeesns.jeesnsAjax(basePath + "/area/provinceList","post","","area.provinceRender");
    },
    cityList : function (fcode) {
        area.loadingIndex = jeesnsDialog.loading();
        jeesns.jeesnsAjax(basePath + "/area/cityList/"+fcode,"post","","area.cityRender");
    },
    areaList : function (fcode) {
        area.loadingIndex = jeesnsDialog.loading();
        jeesns.jeesnsAjax(basePath + "/area/areaList/"+fcode,"post","","area.areaRender");
    },
    provinceRender : function (res) {
        var html = area.getHtml("province",res);
        $("#js-province").html(html);
        if (provinceChange){
            var provinceCode = $("#js-province").children('option:selected').attr("js-code");
            area.cityList(provinceCode);
        }
        jeesnsDialog.close(area.loadingIndex);
    },
    cityRender : function (res) {
        var html = area.getHtml("city",res);
        $("#js-city").html(html);
        if (provinceChange){
            var cityCode = $("#js-city").children('option:selected').attr("js-code");
            area.areaList(cityCode);
            provinceChange = false;
        }
        jeesnsDialog.close(area.loadingIndex);
    },
    areaRender : function (res) {
        var html = area.getHtml("area",res);
        $("#js-area").html(html);
        jeesnsDialog.close(area.loadingIndex);
    },
    getHtml : function (type,res) {
        var data = JSON.parse(res).data;
        var html = "";
        var isSelected = false;
        for (var i in data){
            var area = data[i];
            if (type == "province"){
                if (defaultProvince != "" && defaultProvince == area.name){
                    isSelected = true;
                    defaultProvince = "";
                }
            } else if (type == "city"){
                if (defaultCity != "" && defaultCity == area.name){
                    isSelected = true;
                    defaultCity = "";
                }
            } else {
                if (defaultArea != "" && defaultArea == area.name){
                    isSelected = true;
                    defaultArea = "";
                }
            }
            if (isSelected){
                html += "<option value='"+area.name+"' js-code='"+area.code+"' selected>"+area.name+"</option>";
            } else {
                html += "<option value='"+area.name+"' js-code='"+area.code+"'>"+area.name+"</option>";
            }
            isSelected = false;
        }
        return html;
    }
}