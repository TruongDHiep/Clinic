package com.clinicmanagement.clinic.controller.admin;

import com.clinicmanagement.clinic.Entities.Doctor;
import com.clinicmanagement.clinic.Entities.Specialization;
import com.clinicmanagement.clinic.dto.doctor.DoctorRequest;
import com.clinicmanagement.clinic.mapper.DoctorMapper;
import com.clinicmanagement.clinic.service.DoctorService;
import com.clinicmanagement.clinic.service.SpecializationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/doctors")
public class AdminDoctorController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private SpecializationService specializationService;
    @Autowired
    private DoctorMapper doctorMapper;

    @GetMapping
    public String getAllDoctors(Model model) {
        System.out.println("Get all doctors");
        List<Doctor> doctors = doctorService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return "admin/doctor/doctors";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("doctor", new DoctorRequest());
        model.addAttribute("specializations", specializationService.getAllSpecialization());
        return "admin/doctor/create"; // Trang form tạo bác sĩ
    }

    @PostMapping("/create")
    public String createDoctor(@Valid @ModelAttribute("doctor") DoctorRequest doctorRequest,
                               BindingResult result,
                               Model model) {
        List<Specialization> specializations = specializationService.getAllSpecialization();
        if (result.hasErrors()) {
            model.addAttribute("specializations", specializations);
            return "admin/doctor/create";
        }
        try {
            if(doctorService.findByEmail(doctorRequest.getEmail()) != null){
                model.addAttribute("errorMessage", "Email đã tồn tại");
                model.addAttribute("specializations", specializations);
                return "admin/doctor/create";
            }
            Doctor doctor = doctorMapper.toDoctor(doctorRequest);
            doctor.setStatus(true);
            doctorService.saveDoctor(doctor);
            return "redirect:/admin/doctors";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Không thể tạo bác sĩ");
            return "admin/doctor/create";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Integer id,Model model) {
        Optional<Doctor> doctor = doctorService.findById(id);
        System.out.println(doctor.get());
        model.addAttribute("doctor", doctorMapper.toDoctorRequest(doctor.get()));
        model.addAttribute("specializations", specializationService.getAllSpecialization());
        return "admin/doctor/update";
    }

    @PostMapping("/update")
    public String updateDoctor(@Valid @ModelAttribute("doctor") DoctorRequest doctorRequest,
                               BindingResult result,
                               Model model) {

        List<Specialization> specializations = specializationService.getAllSpecialization();

        if (result.hasErrors()) {
            model.addAttribute("specializations", specializations);
            return "admin/doctor/update";
        }

        if (doctorService.findByEmailAndNotId(doctorRequest.getEmail(), doctorRequest.getId()).isPresent()) {
            model.addAttribute("specializations", specializations);
            result.rejectValue("email", "error.doctor", "Email đã tồn tại");
            return "admin/doctor/update"; // Trả về trang form nếu có lỗi
        }
        try {
            doctorService.saveDoctor(doctorMapper.toDoctor(doctorRequest));
            return "redirect:/admin/doctors";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Không thể cập nhật bác sĩ");
            model.addAttribute("specializations", specializations);
            return "admin/doctor/update";
        }
    }
}
