package org.hiredgoons.gliese.controller;

import org.hiredgoons.common.dto.CatalogQuery;
import org.apache.commons.lang3.StringUtils;
import org.hiredgoons.gliese.entity.Gliese;
import org.hiredgoons.gliese.repository.GlieseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class GlieseQueryController {

    @Autowired
    GlieseRepository repository;

    @GetMapping(value="/")
    public @ResponseBody ResponseEntity<Page<Gliese>> query(GlieseCatalogQuery query) {

        Gliese example = new Gliese();
        if (StringUtils.isNotEmpty(query.getSpectralType())) {
            example.setSpectralType(query.getSpectralType());
        }

        if (StringUtils.isNotEmpty(query.getSpectralTypeSub())) {
            example.setSpectralTypeSub(query.getSpectralTypeSub());
        }

        if (StringUtils.isNotEmpty(query.getLuminosity())) {
            example.setLuminosityClass(query.getLuminosity());
        }

        Example<Gliese> e = Example.of(example);
        Pageable page = PageRequest.of(query.getPage(), query.getSize());
        Page<Gliese> result = repository.findAll(e, page);

        // TODO: If not found return 404 like a good little restful service
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Gliese> queryById(@PathVariable Long id) {

        Optional<Gliese> result = repository.findById(id);
        // the lambda for this is ugly
        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public static class GlieseCatalogQuery extends CatalogQuery<String> {

    }

    @GetMapping(value="/hc")
    public ResponseEntity<String> healthcheck() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
