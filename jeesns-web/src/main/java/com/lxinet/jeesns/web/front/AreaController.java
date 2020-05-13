package com.lxinet.jeesns.web.front;

import com.lxinet.jeesns.core.controller.BaseController;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.lxinet.jeesns.core.utils.JeesnsConfig;
import com.lxinet.jeesns.model.common.Area;
import com.lxinet.jeesns.model.system.ActionLog;
import com.lxinet.jeesns.service.common.IAreaService;
import com.lxinet.jeesns.service.system.IActionLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 省市区接口
 * Created by zchuanzhao on 2019/5/16.
 */
@Controller("frontAreaController")
@RequestMapping("/area/")
public class AreaController extends BaseController {
    @Resource
    private IAreaService areaService;


    @PostMapping("provinceList")
    @ResponseBody
    public ResultModel provinceList(){
        List<Area> provinceList = areaService.provinceList();
        return new ResultModel(provinceList);
    }


    @PostMapping("cityList/{provinceCode}")
    @ResponseBody
    public ResultModel cityList(@PathVariable("provinceCode") String provinceCode){
        List<Area> cityList = areaService.cityList(provinceCode);
        return new ResultModel(cityList);
    }


    @PostMapping("areaList/{cityCode}")
    @ResponseBody
    public ResultModel areaList(@PathVariable("cityCode") String cityCode){
        List<Area> areaList = areaService.areaList(cityCode);
        return new ResultModel(areaList);
    }


}
