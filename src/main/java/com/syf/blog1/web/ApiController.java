package com.syf.blog1.web;

import com.syf.blog1.service.BlogService;
import com.syf.blog1.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {
    private int likeCount = 0;

    @GetMapping("/likeCount")
    public int getLikeCount() {
        return likeCount;
    }

    @PostMapping("/like")
    public int increaseLikeCount() {
        likeCount++;
        return likeCount;
    }
}