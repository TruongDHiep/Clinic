package com.clinicmanagement.clinic.controller;

import com.clinicmanagement.clinic.config.VNPayService;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/vnpay")
public class VNPayController {
    @Autowired
    private VNPayService vnPayService;
    private static final Logger logger = LoggerFactory.getLogger(VNPayController.class);


    @PostMapping("/submitOrder")
    public String submidOrder(@RequestParam("amount") int orderTotal,
                              @RequestParam("orderInfo") String orderInfo,
                              HttpServletRequest request) {
        logger.info("Received amount: " + orderTotal);
        logger.info("Received orderInfo: " + orderInfo);

        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        logger.info("Base URL: " + baseUrl);

        String vnpayUrl = vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
        logger.info("Generated VNPay URL: " + vnpayUrl);

        if (vnpayUrl == null || vnpayUrl.isEmpty()) {
            return "redirect:/error?message=Invalid VNPay URL";
        }

        return String.format("redirect:%s",vnpayUrl);

    }

    @GetMapping("/vnpay-payment")
    public String GetMapping(HttpServletRequest request, Model model){
        int paymentStatus =vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);

        return paymentStatus == 1 ? "booking/ordersuccess" : "booking/orderfail";
    }
}
