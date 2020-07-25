package me.eltacshikhsaidov.dailytech.controllers;

import me.eltacshikhsaidov.dailytech.entities.Blog;
import me.eltacshikhsaidov.dailytech.services.BlogService;
import me.eltacshikhsaidov.dailytech.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class BlogController {

    private BlogService blogService;

    private UserService userService;

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/addBlog")
    public String blogForm(String email , Model model , HttpSession httpSession) {

        httpSession.setAttribute("email", email);
        model.addAttribute("blog", new Blog());

        return "views/blogForm";
    }

    @PostMapping("/addBlog")
    public String addBlog(@Valid Blog blog, BindingResult bindingResult, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "views/blogForm";
        }

        String email = (String) httpSession.getAttribute("email");
        blogService.addBlog(blog, userService.findOne(email));

        return "redirect:/profile";
    }

}
