package com.syf.blog1.web;

import com.syf.blog1.NotFoundException;
import com.syf.blog1.po.Blog;
import com.syf.blog1.po.Comment;
import com.syf.blog1.service.BlogService;
import com.syf.blog1.service.CommentService;
import com.syf.blog1.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String index(@PageableDefault(size=4,sort={"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(5));
        model.addAttribute("blogRecommend",blogService.listRecommendBlogTop(5));

        return "index";
    }
    @GetMapping("/search")
    public String search(@PageableDefault(size=3,sort={"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String query,Model model) {
        model.addAttribute("page",blogService.listBlog("%"+query+"%",pageable));
        model.addAttribute("query",query);
        return "search";


    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model) {
        model.addAttribute("blog",blogService.getAndConvert(id));
        // 加载评论列表
        List<Comment> comments = commentService.listCommentByBlogId(id);
        model.addAttribute("comments",comments);
        return "blog";
    }

    @GetMapping("/footer/newblog")
    public String newblogs(Model model) {
        List<Blog> blogs = blogService.listRecommendBlogTop(3);
        model.addAttribute("newblogs", blogs);
        return "_fragments :: newblogList";
    }

}
