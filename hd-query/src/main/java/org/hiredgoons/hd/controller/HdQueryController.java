package org.hiredgoons.hd.controller;

import org.hiredgoons.common.dto.CatalogQuery;
import org.apache.commons.lang3.StringUtils;
import org.hiredgoons.hd.entity.Hd;
import org.hiredgoons.hd.repository.HdRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class HdQueryController {

    private static final Logger log = LoggerFactory.getLogger(HdQueryController.class);
    @Autowired
    private HdRespository repository;

    @GetMapping(value="/")
    public @ResponseBody ResponseEntity<Page<Hd>> query(HdCatalogQuery query) {

        log.debug("Entered query()");

        Hd example = new Hd();
        if (query.getName() != null) {
            example.setHdId(query.getName());
        }

        if (StringUtils.isNotBlank(query.getSpectralType())) {
            example.setSpectralType(query.getSpectralType());
        }

        if (StringUtils.isNotBlank(query.getSpectralTypeSub())) {
            example.setSpectralTypeSub(query.getSpectralTypeSub());
        }

        Example<Hd> e= Example.of(example);
        Pageable page = PageRequest.of(query.getPage(), query.getSize());

        Page<Hd> result = repository.findAll(e, page);

        // TODO: If the query didn't return anything return 404 like a good little restful service
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @GetMapping(value="/{id}")
    public @ResponseBody ResponseEntity<Hd> queryById(@PathVariable Integer id) {

        log.debug("Entered queryById(), id: {}", id);

        Optional<Hd> result = repository.findById(id);
        // the lambda for this is ugly
        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            System.out.println("result wasn't present");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/hc")
    public ResponseEntity<String> hc() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    public static class HdCatalogQuery extends CatalogQuery<Integer> {

    }
}
