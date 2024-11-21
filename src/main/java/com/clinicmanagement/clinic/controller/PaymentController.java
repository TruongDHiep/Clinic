package com.clinicmanagement.clinic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/payment")
public class PaymentController {

//    private final PaymentService paymentService;
//
//    @Value("${vnpay.returnUrl}")
//    private String returnUrl;
//
//    public PaymentController(PaymentService paymentService) {
//        this.paymentService = paymentService;
//    }
//
//    @PostMapping("/create")
//    public String createPayment(@ModelAttribute payment payment, Model model) {
//        payment createdPayment = paymentService.createPayment(payment);
//
//        // Redirect tới VNPay URL với thông tin thanh toán
////        String vnpayUrl = VNPayConfig.buildVNPayURL(createdPayment);
////        return "redirect:" + vnpayUrl;
//        return  null;
//    }
//
//    @GetMapping("/return")
//    public String handleVNPayReturn(
//            @RequestParam("vnp_Amount") String amount,
//            @RequestParam("vnp_BankCode") String bankCode,
//            @RequestParam("vnp_ResponseCode") String responseCode,
//            @RequestParam("vnp_TxnRef") String txnRef,
//            Model model) {
//
//        // Xử lý kết quả trả về từ VNPay
//        Optional<payment> paymentOptional = paymentService.getPaymentById(Integer.parseInt(txnRef));
//        if (paymentOptional.isPresent()) {
//            payment payment = paymentOptional.get();
//
//            if ("00".equals(responseCode)) {
//                paymentService.updatePaymentStatus(payment.getId(), "Success");
//                model.addAttribute("message", "Thanh toán thành công!");
//            } else {
//                paymentService.updatePaymentStatus(payment.getId(), "Failed");
//                model.addAttribute("message", "Thanh toán thất bại!");
//            }
//        }
//
//        return "payment-result";
//    }
}
