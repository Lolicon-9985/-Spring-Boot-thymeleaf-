package com.syf.blog1.web.admin;


import com.syf.blog1.po.Type;
import com.syf.blog1.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 5 ,sort = "id",direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page",typeService.listType(pageable));

        return "admin/types";
    }
    @GetMapping("/types/input")
    public String input() {
        return "admin/types-input";
    }
//    改
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }
//    增
    @PostMapping("/types")
    public String post(Type type){
        Type t = typeService.saveType(type);
        if(t == null){

        }else{

        }
        return "redirect:/admin/types";

    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id){
        typeService.deleteType(id);
        return "redirect:/admin/types";
    }
}
