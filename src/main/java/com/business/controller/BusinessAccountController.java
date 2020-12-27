package com.business.controller;

import cn.hutool.json.JSONUtil;
import com.business.entity.BusinessAccount;
import com.business.request.RequestBusinessAccount;
import com.business.result.Result;
import com.business.result.StatusCodeEnum;
import com.business.service.BusinessAccountService;
import com.business.utils.MapStringUtils;
import com.github.pagehelper.PageInfo;
import com.google.common.util.concurrent.RateLimiter;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.*;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 凤凰[小哥哥]
 * @Date: 2020/11/22 17:12
 * @Version: 1.0
 * @Email: 15290810931@163.com
 */
@Api(value = "BusinessAccountController")
@RestController
@RequestMapping("/businessAccount")
@CrossOrigin
@Slf4j
public class BusinessAccountController {

    /**
     * 定义每秒钟发放2个令牌
     */
    RateLimiter limiter = RateLimiter.create(2.0);
    int count = 1;
    int timeout  = 1;

    @Autowired
    private BusinessAccountService businessAccountService;


    /***
     * 新增BusinessAccount数据
     * @param data
     * @return
     */
    @ApiOperation(value = "BusinessAccount添加",notes = "添加BusinessAccount方法详情",tags = {"BusinessAccountController"})
    @PostMapping("/addRequestBody")
    @ResponseBody
    public Result addRequestBody(@RequestParam("data") String data) throws UnsupportedEncodingException {

        if(limiter.tryAcquire(count,timeout, TimeUnit.SECONDS)){
            String formData = URLDecoder.decode(data, "utf-8");
            BusinessAccount account = JSONUtil.toBean(JSONUtil.toJsonStr(MapStringUtils.json2map(formData)), BusinessAccount.class);
            businessAccountService.add(account);
            return new Result(true, StatusCodeEnum.OK.getCode(),"添加成功");
        }else {
            log.info("get fail,curr rate is:{}",limiter.getRate());
            return Result.error();
        }
    }


    /***
     * 新增BusinessAccount数据
     * @param businessAccount
     * @return
     */
    @ApiOperation(value = "BusinessAccount添加",notes = "添加BusinessAccount方法详情",tags = {"BusinessAccountController"})
    @PostMapping
    public Result add(@ModelAttribute RequestBusinessAccount businessAccount){
        //调用BusinessAccountService实现添加BusinessAccount
        BusinessAccount account = new BusinessAccount();
        BeanCopier beanCopier = BeanCopier.create(RequestBusinessAccount.class,BusinessAccount.class,false);
        beanCopier.copy(businessAccount,account,null);
        businessAccountService.add(account);
        return new Result(true, StatusCodeEnum.OK.getCode(),"添加成功");
    }

    /***
     * 根据ID删除品牌数据
     * @param id
     * @return
     */
    @ApiOperation(value = "BusinessAccount根据ID删除",notes = "根据ID删除BusinessAccount方法详情",tags = {"BusinessAccountController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @DeleteMapping(value = "/{id}" )
    public Result delete(@PathVariable Integer id){
        //调用BusinessAccountService实现根据主键删除
        businessAccountService.delete(id);
        return new Result(true,StatusCodeEnum.OK.getCode(),"删除成功");
    }

    /***
     * 修改BusinessAccount数据
     * @param businessAccount
     * @param id
     * @return
     */
    @ApiOperation(value = "BusinessAccount根据ID修改",notes = "根据ID修改BusinessAccount方法详情",tags = {"BusinessAccountController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @PutMapping(value="/{id}")
    public Result update(@RequestBody @ApiParam(name = "BusinessAccount对象",value = "传入JSON数据",required = false) BusinessAccount businessAccount,@PathVariable Integer id){
        //设置主键值
        businessAccount.setId(id);
        //调用BusinessAccountService实现修改BusinessAccount
        businessAccountService.update(businessAccount);
        return new Result(true,StatusCodeEnum.OK.getCode(),"修改成功");
    }

    /***
     * 根据ID查询BusinessAccount数据
     * @param id
     * @return
     */
    @ApiOperation(value = "BusinessAccount根据ID查询",notes = "根据ID查询BusinessAccount方法详情",tags = {"BusinessAccountController"})
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/{id}")
    public Result<BusinessAccount> findById(@PathVariable Integer id){
        //调用BusinessAccountService实现根据主键查询BusinessAccount
        BusinessAccount businessAccount = businessAccountService.findById(id);
        return new Result<BusinessAccount>(true,StatusCodeEnum.OK.getCode(),"查询成功",businessAccount);
    }

    /***
     * 查询BusinessAccount全部数据
     * @return
     */
    @ApiOperation(value = "查询所有BusinessAccount",notes = "查询所BusinessAccount有方法详情",tags = {"BusinessAccountController"})
    @GetMapping
    public Result<List<BusinessAccount>> findAll(){
        //调用BusinessAccountService实现查询所有BusinessAccount
        List<BusinessAccount> list = businessAccountService.findAll();
        return new Result<List<BusinessAccount>>(true, StatusCodeEnum.OK.getCode(),"查询成功",list) ;
    }

    /***
     * 多条件搜索品牌数据
     * @param businessAccount
     * @return
     */
    @ApiOperation(value = "BusinessAccount条件查询",notes = "条件查询BusinessAccount方法详情",tags = {"BusinessAccountController"})
    @PostMapping(value = "/search" )
    public Result<List<BusinessAccount>> findList(@RequestBody(required = false) @ApiParam(name = "BusinessAccount对象",value = "传入JSON数据",required = false) BusinessAccount businessAccount){
        //调用BusinessAccountService实现条件查询BusinessAccount
        List<BusinessAccount> list = businessAccountService.findList(businessAccount);
        return new Result<List<BusinessAccount>>(true,StatusCodeEnum.OK.getCode(),"查询成功",list);
    }

    /***
     * BusinessAccount分页条件搜索实现
     * @param businessAccount
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "BusinessAccount条件分页查询",notes = "分页条件查询BusinessAccount方法详情",tags = {"BusinessAccountController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @PostMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@RequestBody(required = false) @ApiParam(name = "BusinessAccount对象",value = "传入JSON数据",required = false) BusinessAccount businessAccount, @PathVariable  int page, @PathVariable  int size){
        //调用BusinessAccountService实现分页条件查询BusinessAccount
        PageInfo<BusinessAccount> pageInfo = businessAccountService.findPage(businessAccount, page, size);
        return new Result(true,StatusCodeEnum.OK.getCode(),"查询成功",pageInfo);
    }

    /***
     * BusinessAccount分页搜索实现
     * @param page:当前页
     * @param size:每页显示多少条
     * @return
     */
    @ApiOperation(value = "BusinessAccount分页查询",notes = "分页查询BusinessAccount方法详情",tags = {"BusinessAccountController"})
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "page", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "path", name = "size", value = "每页显示条数", required = true, dataType = "Integer")
    })
    @GetMapping(value = "/search/{page}/{size}" )
    public Result<PageInfo> findPage(@PathVariable  int page, @PathVariable  int size){
        //调用BusinessAccountService实现分页查询BusinessAccount
        PageInfo<BusinessAccount> pageInfo = businessAccountService.findPage(page, size);
        return new Result<PageInfo>(true,StatusCodeEnum.OK.getCode(),"查询成功",pageInfo);
    }

}
