package com.tianmao.controller;

import com.tianmao.pojo.Address;
import com.tianmao.service.AddressService;
import com.tianmao.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by LiLin on 2022/05/19/11:23
 */
@RestController
@RequestMapping("address")
public class AddressController extends BaseController {
    @Autowired
    AddressService addressService;

    @GetMapping({"", "/"})
    public JsonResult<List<Address>> getAllAddress(HttpSession session) {
        return new JsonResult<>(OK, addressService.selectAllAddress(getUidFormSession(session)));
    }

    @PostMapping("add")
    public JsonResult<Void> addAddress(Address address, HttpSession session) {
        addressService.insert(getUidFormSession(session), getUsernameFromSession(session), address);
        return new JsonResult<>(OK);
    }

    @GetMapping("/setDefault/{aid}")
    public JsonResult<Void> setDefault(@PathVariable("aid") Integer aid, HttpSession session) {
        addressService.updateDefaultAddress(getUidFormSession(session), aid, getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @GetMapping("delete/{aid}")
    public JsonResult<Void> deleteAddress(@PathVariable("aid") Integer aid, HttpSession session) {
        addressService.deleteAddress(getUidFormSession(session), aid, getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }

    @PostMapping("update/{aid}")
    public JsonResult<Void> updateAddress(@PathVariable("aid") Integer aid, Address address, HttpSession session) {
        addressService.updateAddress(aid, getUsernameFromSession(session), address);
        return new JsonResult<>(OK);
    }

    @PostMapping("get/{aid}")
    public JsonResult<Address> getAddress(@PathVariable("aid") Integer aid, HttpSession session) {
        return new JsonResult<>(OK, addressService.selectAddressByAid(aid,
                getUidFormSession(session)));
    }
}
