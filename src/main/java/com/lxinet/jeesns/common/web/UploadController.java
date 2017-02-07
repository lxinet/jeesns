package com.lxinet.jeesns.common.web;

import com.lxinet.jeesns.core.dto.ResponseModel;
import com.lxinet.jeesns.core.utils.Const;
import com.lxinet.jeesns.core.utils.MemberUtil;
import com.lxinet.jeesns.core.web.BaseController;
import com.lxinet.jeesns.modules.mem.entity.Member;
import com.lxinet.jeesns.modules.mem.service.IMemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 文件上传
 * Created by zchuanzhao on 16/9/29.
 */
@Controller
@RequestMapping("/")
public class UploadController extends BaseController {
	@Resource
	private IMemberService memberService;

	@RequestMapping("${managePath}/uploadImage")
	@ResponseBody
	public Object uploadImage(@RequestParam(value = "file", required = false) MultipartFile file) {
		String fileName = file.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf("."),fileName.length());
		if(suffix == null || (!".png".equals(suffix) && !".jpg".equals(suffix) && !".gif".equals(suffix))) {
			return new ResponseModel(-1,"格式不支持");
		}
		Random ramdom = new Random();
		String newFileName = System.currentTimeMillis() + "" + ramdom.nextInt(6) + suffix;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(new Date());
		String path = Const.UPLOAD_PATH + "/images/"+ymd+"/";
		String savePath = request.getServletContext().getRealPath(path);

		File baseFile = new File(savePath);
		File targetFile = new File(baseFile, newFileName);

		if (!baseFile.exists()) {
			baseFile.mkdirs();
		}
		//保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseModel(0,"上传成功",path + newFileName);
	}

	/**
	 * 上传头像
	 * @param file
	 * @return
	 */
	@RequestMapping("member/uploadAvatar")
	@ResponseBody
	public Object uploadAvatar(@RequestParam(value = "__avatar1", required = false) MultipartFile file){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
		String fileName = System.currentTimeMillis()+".jpg";
		String ymd = simpleDateFormat.format(new Date());
		String filePath = Const.UPLOAD_PATH + "/avatar/" + ymd + "/";
		String savePath = request.getServletContext().getRealPath(filePath);
		File baseFile = new File(savePath);
		File targetFile = new File(savePath, fileName);

		if (!baseFile.exists()) {
			baseFile.mkdirs();
		}
		//保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Member loginMember = MemberUtil.getLoginMember(request);
		Member findMember = memberService.findById(loginMember.getId());
		Map result = new HashMap();
		if(findMember != null){
			String oldAvatar = findMember.getAvatar();
			findMember.setAvatar(filePath + fileName);
			ResponseModel responseModel = memberService.updateAvatar(findMember,oldAvatar,request);
			result.put("success",responseModel.getCode() == 0);
			result.put("msg",responseModel.getMessage());
		}else {
			result.put("success",true);
			result.put("msg","会员不存在!");
		}
		return result;
	}
}
