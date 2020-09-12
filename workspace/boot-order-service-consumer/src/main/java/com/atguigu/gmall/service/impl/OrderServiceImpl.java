package com.atguigu.gmall.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.service.OrderService;
import com.atguigu.gmall.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * 1、将服务提供者注册到注册中心（暴露服务）
 * 		1）、导入dubbo依赖（2.6.2）\操作zookeeper的客户端(curator)
 * 		2）、配置服务提供者
 * 
 * 2、让服务消费者去注册中心订阅服务提供者的服务地址
 * @author lfy
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	//@Reference(url = "127.0.0.1:20882")  zookeeper注册中心宕机后可以通过dubbo直连方式进行服务者与消费者之间的通信
	//@Reference(loadbalance = "random")//负载均衡机制
	@Reference//负载均衡机制
	private UserService userService;

	@Override
	public void initOrder(String userId) {
		System.out.println("用户id = " + userId);
		//1、查询用户的收货地址
		List<UserAddress> addressList = userService.getUserAddressList(userId);
		for (UserAddress userAddress : addressList) {
			System.out.println("userAddress.getUserAddress() = " + userAddress.getUserAddress());

		}
	}

	@HystrixCommand(fallbackMethod = "hello")//出错调用hello方法
	@Override
	public List<UserAddress> initOrderTwo(String userId) {
		System.out.println("用户id = " + userId);
		//1、查询用户的收货地址
		List<UserAddress> addressList = userService.getUserAddressList(userId);
		return addressList;
	}

	public List<UserAddress> hello(String userId) {

		return Arrays.asList(new UserAddress(10,"测试地址","1","测试","ceshi","Y"));
	}
}
