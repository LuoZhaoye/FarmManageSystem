package com.it.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.it.entity.Actual;
import com.it.entity.FAdmin;
import com.it.service.ActualService;
import com.it.service.FAdminService;
import com.it.util.ItdragonUtils;
import com.it.util.Result;
import com.it.util.ResultResponse;
import com.it.util.TableResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈实列接口〉<br>
 *
 * @author
 */
@Controller
@RequestMapping("/admin")
public class FAdminController {
    @Autowired
    private FAdminService fAdminService;


    // 新增
    @ResponseBody
    @PostMapping("/addUser.do")
    public ResultResponse addUser(FAdmin fAdmin) {
        boolean result = fAdminService.addUser(fAdmin);
        if (!result) {
            return Result.resuleError("新增失败");
        }
        return Result.resuleSuccess();
    }

    // 修改


    // 删除



//    /**
//     * 删除数据接口
//     *
//     * @param ids
//     * @return
//     */
//    @ResponseBody
//    @DeleteMapping("/actual.do")
//    public ResultResponse delete(String ids) {
//        boolean result = actualService.deleteById(ids);
//        if (!result) {
//            return Result.resuleError("删除失败");
//        }
//        return Result.resuleSuccess();
//    }


}