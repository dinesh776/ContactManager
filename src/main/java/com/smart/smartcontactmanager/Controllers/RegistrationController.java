package com.smart.smartcontactmanager.Controllers;

import com.smart.smartcontactmanager.Message.Message;
import com.smart.smartcontactmanager.Entities.USER;
import com.smart.smartcontactmanager.Services.UserRepoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class RegistrationController {

    @Autowired
    private UserRepoService userRepoService;

    public BCryptPasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder(12);
    }


    @PostMapping("/do_register")
    public String registerUser(@Valid @ModelAttribute("user") USER user,BindingResult result1, @RequestParam(value = "agreement",defaultValue = "false") String agreement, Model model, RedirectAttributes redirectAttributes){
        if(result1.hasErrors()){
            System.out.println(result1.toString());
            return "Libraries/signup";
        }
        try{
            user.setRole("USER");
            user.setImageUrl("default.png");
            user.setActive(true);
            user.setPassword(this.getPasswordEncoder().encode(user.getPassword()));
            Message message=new Message();

            if(userRepoService.isUserExistsByUserName(user)){
                message.setContent("user already exists");
                message.setType("alert-danger");
                model.addAttribute("user",user);
            }
             else if(userRepoService.isUserExistsByEmail(user)){
                message.setContent("Email already registered with us");
                message.setType("alert-danger");
                model.addAttribute("user",user);
            }
            else{
                USER res=userRepoService.registerUser(user);
                model.addAttribute("user",new USER());
//                System.out.println(res);
                message.setContent("Successfully registered !!!");
                message.setType("alert-success");
            }
            redirectAttributes.addFlashAttribute("message",message);
        }
        catch (Exception e){
            e.printStackTrace();
            model.addAttribute("user",user);
            redirectAttributes.addFlashAttribute("message",new Message("Something went wrong !! try again later","alert-danger"));
        }
        return "redirect:/signup";
    }
}
