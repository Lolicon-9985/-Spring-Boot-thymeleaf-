package com.syf.blog1.web;

import com.syf.blog1.po.Blog;
import com.syf.blog1.po.Type;
import com.syf.blog1.service.BlogService;
import com.syf.blog1.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size=3,sort={"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model){
        List<Type> types= typeService.listTypeTop(9999);
        if (id == -1){
            id = types.get(0).getId();
        }
        Blog blog = new Blog();
        Type type = new Type();
        type.setId(id); // 设置分类 ID
        blog.setType(type); // 将分类绑定到博客
        model.addAttribute("types", types);
//        ???
        model.addAttribute("page", blogService.listBlog(pageable,blog));
        model.addAttribute("activeTypeId", id);


        return "types";

    }
}
