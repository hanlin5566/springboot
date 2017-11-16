package com.hzcf.operation.controller;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.operation.base.entity.DerivedVariableExt;
import com.hzcf.operation.base.entity.PageEntity;
import com.hzcf.operation.base.entity.PageInfo;
import com.hzcf.operation.base.exception.CustomException;
import com.hzcf.operation.base.result.ResponseCode;
import com.hzcf.operation.base.result.Result;
import com.hzcf.operation.base.result.ResultPage;
import com.hzcf.operation.base.util.BeanUtils;
import com.hzcf.operation.base.util.CompileUtils;
import com.hzcf.operation.gen.entity.DerivedVariable;
import com.hzcf.operation.gen.entity.DerivedVariableExample;
import com.hzcf.operation.gen.mapper.DerivedVariableMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Create by hanlin on 2017年11月6日
 **/
@RestController
@RequestMapping(value="/derived")
@Api("衍生变量")
public class DerivedVariableController {
	@Autowired
	private DerivedVariableMapper derivedVariableMapper;
	
	
	@ApiOperation(value="获取衍生变量列表列表", notes="根据搜索条件查询衍生变量列表")
    @RequestMapping(value={""}, method=RequestMethod.GET)
    public ResultPage<DerivedVariable>getDerivedVariableList(DerivedVariable derivedVar,PageEntity page) {
		ResultPage<DerivedVariable> ret = new ResultPage<DerivedVariable>();
		DerivedVariableExample example = BeanUtils.example(derivedVar,DerivedVariableExample.class);
		PageInfo pageInfo = page.toPageInfo();
		List<DerivedVariable> result = derivedVariableMapper.selectByExampleWithRowbounds(example, pageInfo);
		ret.setPageInfo(pageInfo);
		ret.setData(result);
	    return ret;
    }
	
	@ApiOperation(value="获取衍生变量", notes="根据衍生变量ID获取衍生变量信息")
	@RequestMapping(value={"/{varId}"}, method=RequestMethod.GET)
	public Result<DerivedVariableExt>getDerivedVariable(@PathVariable Integer varId) throws Exception {
		Result<DerivedVariableExt> ret = new Result<DerivedVariableExt>();
		DerivedVariable result = derivedVariableMapper.selectByPrimaryKey(varId);
		//读取文件并将内容写入
		String path = result.getClazzPath();
		FileReader in = new FileReader(new File(path));
		String content = FileCopyUtils.copyToString(in);
		DerivedVariableExt copyResult = BeanUtils.copyProperties(result, DerivedVariableExt.class);
		copyResult.setContent(content);
		ret.setData(copyResult);
		return ret;
	}
	
	@ApiOperation(value="保存或新增衍生变量", notes="根据衍生变量ID，保存或新增衍生变量。获取request仅为了拿到存储文件地址")
    @RequestMapping(value={""}, method=RequestMethod.POST)
	public Result<Integer> saveOrUpdate(@RequestBody DerivedVariableExt derivedVar,HttpServletRequest request) throws Exception {
		if(request.getSession().getAttribute("sessionId") == null){
			throw new CustomException(ResponseCode.ERROR_PARAM, "sessionId为空");
		}
		if(derivedVar.getVarId()!= null){
			//更新时，path丢了，content没地方记录。加个字段？
			derivedVariableMapper.updateByPrimaryKey(derivedVar);
			
		}else{
			//新增状态文件保存至本地
			String fileName = UUID.randomUUID().toString().replaceAll("-", "")+".tmp";
			String filePath = request.getServletContext().getRealPath(fileName);
			FileCopyUtils.copy(derivedVar.getClazzPath().getBytes(), new File(filePath));
			derivedVar.setClazzPath(filePath);
			derivedVariableMapper.insert(derivedVar);
		}
		Result<Integer> ret = new Result<Integer>();
		ret.setData(derivedVar.getVarId());
		return ret;
	}
	
	@ApiOperation(value="编译传入的文件", notes="编译传入的文件")
    @RequestMapping(value={"/compile"}, method=RequestMethod.POST)
	public Result<String> compile(@RequestBody DerivedVariableExt derivedVar,HttpServletRequest request) throws Exception {
		String content = derivedVar.getContent();
		if(StringUtils.isEmpty(content)){
			throw new CustomException(ResponseCode.ERROR_PARAM, "传入文件内容不能为空");
		}
		Result<String> result = CompileUtils.javaCodeToObject(derivedVar.getClazzPath());
		return result;
	}
}
