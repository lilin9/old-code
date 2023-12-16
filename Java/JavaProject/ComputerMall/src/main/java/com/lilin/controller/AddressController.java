package com.lilin.controller;

import com.lilin.pojo.Address;
import com.lilin.service.AddressService;
import com.lilin.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by LiLin on 2022/04/12/12:02
 *
 * 用户收货地址的控制类
 */
@RestController
@RequestMapping("addresses")
public class AddressController extends BaseController {
    @Autowired
    private AddressService addressService;

    @PostMapping("add_new_address")
    public JsonResult<Void> addNewAddress(HttpSession session, Address address) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);

        addressService.addNewAddress(uid, username, address);
        return new JsonResult<>(OK);
    }

    @GetMapping({"", "/"})
    public JsonResult<List<Address>> getAddressByUid(HttpSession session) {
        return new JsonResult<>(OK, addressService.getAddressByUid(getUidFromSession(session)));
    }

    @GetMapping("set_default/{aid}")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        addressService.setDefaultAddress(getUidFromSession(session), aid, getUsernameFromSession(session), new Date());
        return new JsonResult<>(OK);
    }

    @PostMapping("delete_address/{aid}")
    public JsonResult<Void> deleteAddress(@PathVariable("aid") Integer aid, HttpSession session) {
        addressService.deleteAddress(getUidFromSession(session), aid, getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
}
