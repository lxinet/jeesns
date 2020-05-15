package cn.jeesns.web.front;

import cn.jeesns.model.common.Area;
import cn.jeesns.core.controller.BaseController;
import cn.jeesns.core.dto.Result;
import cn.jeesns.service.common.AreaService;
import org.springframework.stereotype.Controller;
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
    private AreaService areaService;


    @PostMapping("provinceList")
    @ResponseBody
    public Result provinceList(){
        List<Area> provinceList = areaService.provinceList();
        return new Result(provinceList);
    }


    @PostMapping("cityList/{provinceCode}")
    @ResponseBody
    public Result cityList(@PathVariable("provinceCode") String provinceCode){
        List<Area> cityList = areaService.cityList(provinceCode);
        return new Result(cityList);
    }


    @PostMapping("areaList/{cityCode}")
    @ResponseBody
    public Result areaList(@PathVariable("cityCode") String cityCode){
        List<Area> areaList = areaService.areaList(cityCode);
        return new Result(areaList);
    }


}
