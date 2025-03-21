package com.smart.smartcontactmanager.Controllers;

import com.smart.smartcontactmanager.Entities.ContactCard;
import com.smart.smartcontactmanager.Entities.NAME;
import com.smart.smartcontactmanager.Entities.USER;
import com.smart.smartcontactmanager.EntityWrappers.AddContactPage;
import com.smart.smartcontactmanager.EntityWrappers.UserNameWrapper;
import com.smart.smartcontactmanager.HelperMethods.DashboardHelper;
import com.smart.smartcontactmanager.Services.ContactCardService;
import com.smart.smartcontactmanager.Services.UserRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Controller
@RequestMapping("/user")
public class DashboardController {


    @Autowired
    private UserRepoService userRepoService;

    @Autowired
    private ContactCardService contactCardService;

    @GetMapping("/authenticate_user")
    public void Auth(){
        System.out.println("authentication.......");
    }


    @ModelAttribute
    private void addCommonAttributes(Model model, Principal principal) throws IOException {

//        date formater
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH);
        String formattedDate = date.format(formatter);

//        get user
        String username = principal.getName();
        USER user = userRepoService.getUser(username);

//        it's for profile.html
        UserNameWrapper userNameWrapper=new UserNameWrapper();
        NAME name=user.getName();

        if(name==null){
            name=new NAME();
            user.setName(name);
        }

        userNameWrapper.setUser(user);
        userNameWrapper.setName(name);


//        System.out.println(image);
//        getContactCards
        List<ContactCard> contactCards=contactCardService.getAllContactCards(user.getU_id());

        model.addAttribute("contactCards",contactCards);
        model.addAttribute("userWrapper",userNameWrapper);
        model.addAttribute("formattedDate", formattedDate);

   }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/dashboard")
    public String userDashboard() {
        return "Dashboard/User/home";
    }

    @GetMapping("/add-contact")
    public String addContact(Model model) {
        model.addAttribute("contact",new AddContactPage());
        return "Dashboard/User/addContact";
    }

    @GetMapping("/profile")
    public String profile(){
        return "Dashboard/User/profile";
    }

    @GetMapping("/contacts")
    public String contacts(){
        return "Dashboard/User/contacts";
    }

    @GetMapping("/fav")
    public String fav(){
        return "Dashboard/User/favourites";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/dashboard")
    public String adminDashboard(){
        return "Dashboard/Admin/adminDashboard";
    }

    @PostMapping(value = "/process-contact",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String addContact(@ModelAttribute AddContactPage addContactPage, RedirectAttributes attributes,Model model){
        try {
            DashboardHelper dashboardHelper=new DashboardHelper();
            String imageUrl="default.png";
            if(!addContactPage.getContactCard().getImageFile().getOriginalFilename().equals("")){
                imageUrl=dashboardHelper.processImageFile(addContactPage.getContactCard().getImageFile());
            }
            addContactPage.getContactCard().setImageUrl(imageUrl);
            addContactPage.setContactName();
            UserNameWrapper userNameWrapper= (UserNameWrapper) model.getAttribute("userWrapper");
            addContactPage.getContactCard().setUser(userNameWrapper.getUser());
            ContactCard contactCard=contactCardService.saveContact(addContactPage.getContactCard());
//            System.out.println(contactCard);
            attributes.addFlashAttribute("successMessage","Successfully added...");
            return "redirect:/user/add-contact";
        }catch (Exception e){
            e.printStackTrace();
        }
        attributes.addFlashAttribute("errorMessage", "Something went wrong try again ...");
        return "redirect:/user/add-contact";
    }


    @PostMapping(path = "/update-user", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String updateUser(@ModelAttribute UserNameWrapper userNameWrapper,RedirectAttributes attributes){
        try {
            USER existingUser=userRepoService.getUser(userNameWrapper.getUser().getUserName());

            MultipartFile imageFile=userNameWrapper.getUser().getImageFile();
            if(!imageFile.getOriginalFilename().equals("")){
                DashboardHelper dashboardHelper=new DashboardHelper();
                String imageUrl=dashboardHelper.processImageFile(imageFile);
                new DashboardHelper().deleteImage(existingUser.getImageUrl());
                existingUser.setImageUrl(imageUrl);
            }


//            userNameWrapper.getUser().setImageUrl(imageUrl);


            if(existingUser.getName()==null){
                existingUser.setName(userNameWrapper.getName());
            }
            else{
                existingUser.getName().setFirst_name(userNameWrapper.getName().getFirst_name());
                existingUser.getName().setMiddle_name(userNameWrapper.getName().getMiddle_name());
                existingUser.getName().setLast_name(userNameWrapper.getName().getLast_name());
            }
            existingUser.setDob(userNameWrapper.getUser().getDob());
            existingUser.setPersonalContact(userNameWrapper.getUser().getPersonalContact());
            existingUser.setAbout(userNameWrapper.getUser().getAbout());


            USER user=userRepoService.saveUser(existingUser);
            attributes.addFlashAttribute("successMessage","successfully updated ...");

        } catch (Exception e) {
            e.printStackTrace();
            attributes.addFlashAttribute("errorMessage","something Went Wrong ....");
        }
        return "redirect:/user/profile";
    }


    @GetMapping("/contacts/{contact_id}")
    public String viewContact(@PathVariable("contact_id") int contact_id,Model model,Principal principal,RedirectAttributes attributes){
        ContactCard contactCard=contactCardService.getContactCard(contact_id);
        USER CurrentUser=userRepoService.getUser(principal.getName());
        if(CurrentUser.getU_id()==contactCard.getUser().getU_id()){
            model.addAttribute("contactCard",contactCard);
            return "Dashboard/User/viewContact";
        }
        attributes.addFlashAttribute("errorMessage","you dont have permission to see other's contact's");
        return "redirect:/user/contacts";
    }

    @PostMapping("/contacts/update-contact/{contact_id}")
    public String updateContact(@PathVariable("contact_id") int contact_id,@ModelAttribute ContactCard contactCard,RedirectAttributes attributes) throws IOException {
//        System.out.println(contactCard);
        ContactCard existingContactCard=contactCardService.getContactCard(contact_id);
//        System.out.println(existingContactCard);
        if(existingContactCard!=null){
            if(!contactCard.getImageFile().getOriginalFilename().equals("")){
                DashboardHelper dashboardHelper=new DashboardHelper();
                String image=dashboardHelper.processImageFile(contactCard.getImageFile());
                dashboardHelper.deleteImage(existingContactCard.getImageUrl());
                existingContactCard.setImageUrl(image);
            }


            existingContactCard.getName().setFirst_name(contactCard.getName().getFirst_name());
            existingContactCard.getName().setMiddle_name(contactCard.getName().getMiddle_name());
            existingContactCard.getName().setLast_name(contactCard.getName().getLast_name());

            existingContactCard.setNickName(contactCard.getNickName());
            existingContactCard.setDob(contactCard.getDob());
            existingContactCard.setContact_number(contactCard.getContact_number());
            existingContactCard.setEmail(contactCard.getEmail());
            existingContactCard.setDesignation(contactCard.getDesignation());
            existingContactCard.setWorkExp(contactCard.getWorkExp());
            existingContactCard.setAbout(contactCard.getAbout());

            try {
                contactCardService.updateContact(existingContactCard);
                attributes.addFlashAttribute("successMessage","Successfully updated ...");
            }catch (Exception e){
                attributes.addFlashAttribute("errorMessage","Some thing problem with database ....");
            }

        }
        else{
            attributes.addFlashAttribute("errorMessage","contact Not Found ....");
        }
        return "redirect:/user/contacts/"+contact_id;
    }


    @PostMapping("/fav/{contact_id}/{page}")
    public String fav(@PathVariable("contact_id") int contact_id,@PathVariable("page") int page){
        ContactCard existingcontactCard=contactCardService.getContactCard(contact_id);
        existingcontactCard.setFav(!existingcontactCard.isFav());
        contactCardService.updateContact(existingcontactCard);
        if(page==1){
            return "redirect:/user/contacts";
        }
        return "redirect:/user/fav";
    }


    @PostMapping("/contacts/delete/{contact_id}")
    public String deleteContact(@PathVariable("contact_id") int contact_id,Principal principal,RedirectAttributes attributes){
        String userName=principal.getName();
        USER presentUser=userRepoService.getUser(userName);
        ContactCard existingContact=contactCardService.getContactCard(contact_id);
        if(presentUser.getU_id()==existingContact.getUser().getU_id()){
            try{
                new DashboardHelper().deleteImage(existingContact.getImageUrl());
                contactCardService.deleteContact(existingContact);
                attributes.addFlashAttribute("successMessage","Contact Deleted successfully...");
            } catch (Exception e) {
                attributes.addFlashAttribute("errorMessage","Some thing wrong with database");
            }
            return "redirect:/user/contacts";
        }
        attributes.addFlashAttribute("errorMessage","you dont have permission");
        return "redirect:/user/contacts";
    }
}
