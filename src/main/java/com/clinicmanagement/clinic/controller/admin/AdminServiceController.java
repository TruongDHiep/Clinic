package com.clinicmanagement.clinic.controller.admin;

import com.clinicmanagement.clinic.Entities.Services;
import com.clinicmanagement.clinic.dto.ServiceRequest;
import com.clinicmanagement.clinic.mapper.ServiceMapper;
import com.clinicmanagement.clinic.service.ServicesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/services")
public class AdminServiceController {

    @Autowired
    private ServicesService servicesService;

    @Autowired
    private ServiceMapper serviceMapper;

    @GetMapping
    public String showAllServices(Model model) {
        List<Services> services = servicesService.getAllServices();
        model.addAttribute("services", services);
        return "admin/service/services";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("service", new ServiceRequest());
        return "admin/service/create";
    }

    @PostMapping("/create")
    public String createService(@Valid @ModelAttribute("service") ServiceRequest serviceRequest,
                                       BindingResult result,
                                       Model model) {
        if (result.hasErrors()) {
            return "admin/service/create";
        }
        try {
            if(servicesService.findByServiceName(serviceRequest.getServiceName()) != null){
                result.rejectValue("serviceName", "error.service", "Dịch vụ đã tồn tại");
                return "admin/service/create";
            }
            Services service = serviceMapper.toServices(serviceRequest);
            service.setStatus(true);
            servicesService.saveService(service);
            return "redirect:/admin/services";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Không thể tạo dịch vụ");
            return "admin/service/create";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
        Optional<Services> service = servicesService.findById(id);
        model.addAttribute("service", serviceMapper.toServiceRequest(service.get()));
        return "admin/service/update";
    }

    @PostMapping("/update")
    public String updateSpecialization(@Valid @ModelAttribute("service") ServiceRequest serviceRequest,
                                       BindingResult result,
                                       Model model) {

        if (result.hasErrors()) {
            return "admin/service/update";
        }

        if (servicesService.findByServiceNameAndNotId(serviceRequest.getServiceName(),serviceRequest.getId()).isPresent()) {
            result.rejectValue("serviceName", "error.service", "Dịch vụ đã tồn tại");
            return "admin/service/update"; // Trả về trang form nếu có lỗi
        }
        try {
            servicesService.saveService(serviceMapper.toServices(serviceRequest));
            return "redirect:/admin/services";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Dịch vụ cập nhật chuyên khoa");
            return "admin/service/update";
        }
    }

    @GetMapping("/disable/{id}")
    public String disablePatient(@PathVariable Integer id) {
        Optional<Services> service = servicesService.findById(id);
        service.get().setStatus(false);
        servicesService.saveService(service.get());
        return "redirect:/admin/services";
    }

    @GetMapping("/enable/{id}")
    public String enablePatient(@PathVariable Integer id) {
        Optional<Services> service = servicesService.findById(id);
        service.get().setStatus(true);
        servicesService.saveService(service.get());
        return "redirect:/admin/services";
    }
}
