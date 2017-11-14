package com.hzcf.operation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.operation.base.enums.DataStatus;
import com.hzcf.operation.gen.entity.SystemUser;
import com.hzcf.operation.gen.entity.SystemUserExample;
import com.hzcf.operation.gen.mapper.SystemUserMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Create by hanlin on 2017年11月7日
 **/
@RestController
@RequestMapping(value="/auth")
@Api("系统鉴权")
public class AuthController {
	@Autowired
	private SystemUserMapper systemUserMapper;
	
	
	@ApiOperation(value="登录", notes="根据用户名密码登录系统")
    @RequestMapping(value={""}, method=RequestMethod.POST)
    public List<SystemUser> login(@RequestBody SystemUser user) {
		SystemUserExample example = new SystemUserExample();
		example.createCriteria().andIsvalidEqualTo(DataStatus.NORMAL);
		List<SystemUser> result = systemUserMapper.selectByExample(example);
	    return result;
    }

}
