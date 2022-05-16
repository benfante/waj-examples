package com.benfante.waj.pagilashopmonolith.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Resource;

import com.benfante.waj.pagilashopmonolith.model.Film;
import com.benfante.waj.pagilashopmonolith.repository.FilmRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FilmController {
    @Resource
    private FilmRepository filmRepository;

    @GetMapping("/")
    public String getFilms(Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(40);
        Page<Film> films = filmRepository.findAll(PageRequest.of(currentPage - 1, pageSize, Direction.ASC, "title"));
        model.addAttribute("films", films);

        int totalPages = films.getTotalPages();
        if (totalPages > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "films";
    }

}