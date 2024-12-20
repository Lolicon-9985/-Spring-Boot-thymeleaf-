package com.syf.blog1.web.admin;


import com.syf.blog1.po.Blog;
import com.syf.blog1.po.Type;
import com.syf.blog1.po.User;
import com.syf.blog1.service.BlogService;
import com.syf.blog1.service.TypeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.attribute.Attribute;
import java.util.Date;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size=3,sort={"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, Blog blog, Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));

        return "admin/blogs";

    }
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size=3,sort={"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, Blog blog, Model model) {
        model.addAttribute("page",blogService.listBlog(pageable,blog));

        return "admin/blogs :: blogList";

    }

    @GetMapping("/blogs/input")
    public String input(Model model) {
        setType(model);
        return "admin/blogs-input";
    }

    private void setType(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("blog",new Blog());

    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable long id, Model model) {
        setType(model);
        model.addAttribute("blog",blogService.getBlog(id));
        return "admin/blogs-input";
    }


    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));

        Blog b;

        // 检查是否为更新操作（有 ID 表示更新）
        if (blog.getId() != null) {
            // 获取现有博客记录，保留其 views 和 createTime
            Blog existingBlog = blogService.getBlog(blog.getId());
            if (existingBlog != null) {
                blog.setViews(existingBlog.getViews());
                blog.setCreateTime(existingBlog.getCreateTime());
                blog.setUpdateTime(new Date());
            }
            b = blogService.updateBlog(blog.getId(), blog); // 更新博客
        } else {
            // 如果没有 ID，表示新建博客
            b = blogService.saveBlog(blog);
        }

        // 检查操作结果并设置消息
        if (b == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }

        return "redirect:/admin/blogs";
    }
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id){
        blogService.deleteBlog(id);
        return "redirect:/admin/blogs";
    }



}
