package org.hiredgoons.yale.controller;

import org.hiredgoons.common.dto.CatalogQuery;
import org.apache.commons.lang3.StringUtils;
import org.hiredgoons.yale.entity.Yale;
import org.hiredgoons.yale.repository.YaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value="/")
public class YaleBrightStarController {

    @Autowired
    private YaleRepository repository;

    @GetMapping(value="/")
    public ResponseEntity<Page<Yale>> query(YaleCatalogQuery query) {

        Yale example = new Yale();
        if (StringUtils.isNotEmpty(query.getName())) {
            example.setName(query.getName());
        }

        if (StringUtils.isNotBlank(query.getSpectralType())) {
            example.setSpectralType(query.getSpectralType());
        }

        if (StringUtils.isNotBlank(query.getSpectralTypeSub())) {
            example.setSpectralTypeSub(query.getSpectralTypeSub());
        }

        Example<Yale> e = Example.of(example);
        Pageable page = PageRequest.of(query.getPage(), query.getSize());

        Page<Yale> result = repository.findAll(e, page);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<Yale> queryById(@PathVariable Integer id) {

        Optional<Yale> yale = repository.findById(id);
        if (yale.isPresent()) {
            ResponseEntity<Yale> result = new ResponseEntity<>(yale.get(), HttpStatus.OK);
            return result;
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value="/hc")
    public ResponseEntity<String> healthCheck() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    public static class YaleCatalogQuery extends CatalogQuery<String> {

    }
}
